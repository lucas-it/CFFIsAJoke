package ch.lucas.bot.cff.utils.cffapi;

import ch.lucas.bot.cff.utils.Message;
import ch.lucas.bot.cff.utils.TimeFormatter;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * This class provide utility methods to get information from SBB api.
 *
 * @author Lucas-it@github
 */
public class CFFApiUtils {
    private final Logger LOGGER = LoggerFactory.getLogger(CFFApiUtils.class);
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    private DisruptionStats disruptionStats;
    private int deletedTravels;
    private int totalTravels;

    /**
     * Get the information about disruptions from the SBB api about the precedent day.
     * @return a Message object
     * @throws IOException error while connecting to the SBB API
     */
    public Message getInformationFromAPI() throws IOException, InterruptedException {
        Thread t1 = new Thread(() -> {
            try {
                LOGGER.info("getInformationFromAPI - Get disruption statistics");
                disruptionStats = getDisruptionStats();
            } catch(IOException e) {
                LOGGER.error(e.getMessage(), e);
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                LOGGER.info("getInformationAPI - Get total number of deleted travels");
                deletedTravels = getDeletedTravels();
            } catch(IOException e) {
                LOGGER.error(e.getMessage(), e);
            }
        });

        Thread t3 = new Thread(() -> {
            try {
                LOGGER.info("getInformationFromAPI - Get total number of travels");
                totalTravels = getTotalTravels();
            } catch(IOException e) {
                e.printStackTrace();
            }
        });

        // Start threads
        t1.start();
        t2.start();
        t3.start();

        // Waiting for the end of each thread
        t1.join();
        t2.join();
        t3.join();

        // Date formatting
        simpleDateFormat = new SimpleDateFormat("EEEE d MMMM yyyy");
        // Calcul pourcentage of delayed travels
        LOGGER.info("getInformationFromAPI - Calcul pourcentage of late travels");
        double latePourcent = ((double) disruptionStats.getNumberOfDelayedTravels() / totalTravels) * 100;
        // Calcul pourcentage of deleted travels
        LOGGER.info("getInformationFromAPI - Calcul pourcentage of deleted travels");
        double deletedPourcent = ((double) deletedTravels / totalTravels) * 100;
        // Create a new message
        LOGGER.info("getInformationFromAPI - Create a new message with all information");
        return new Message(simpleDateFormat.format(System.currentTimeMillis() - 86400000), totalTravels, disruptionStats.getNumberOfDelayedTravels(), deletedTravels, (double) Math.round(latePourcent * 100) / 100, (double) Math.round(deletedPourcent * 100) / 100, TimeFormatter.convertSecondsToTime(disruptionStats.getCumulativeLate() / 1000));
    }

    /**
     * Get statistics about disruption. The number of delayed travels and the cumulated delay.
     * @return DisruptionStats
     * @throws IOException error while connecting to the SBB API
     */
    private DisruptionStats getDisruptionStats() throws IOException {
        // Obtaining travels that are late
        LOGGER.info("getDisruptionStats - Initialize connection to SBB API");
        URL url = new URL("https://data.sbb.ch/api/records/1.0/search/?dataset=ist-daten-sbb&q=&rows=10000&sort=-linien_id&facet=betreiber_id&facet=produkt_id&facet=linien_id&facet=linien_text&facet=verkehrsmittel_text&facet=faellt_aus_tf&facet=bpuic&facet=ankunftszeit&facet=an_prognose&facet=an_prognose_status&facet=ab_prognose_status&facet=ankunftsverspatung&facet=abfahrtsverspatung&refine.produkt_id=Zug&refine.ankunftsverspatung=true");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        // Parse JSON
        LOGGER.info("getDisruptionStats - Read API response");
        BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
        LOGGER.info("getDisruptionStats - Parse API response to JSON");
        JsonArray recordsLates = JsonParser.parseReader(br).getAsJsonObject().getAsJsonArray("records");

        long cumulativeLate = 0;
        int numberOfDelayedTravels = 0;
        List<TrainLate> delayedTrains = new ArrayList<>();

        LOGGER.info("getDisruptionStats - Process late travels data");
        for(JsonElement jsonElement : recordsLates) {
            try {
                String recordId = jsonElement.getAsJsonObject().get("recordid").getAsString();
                JsonObject fields = jsonElement.getAsJsonObject().get("fields").getAsJsonObject();
                int lineId = fields.get("linien_id").getAsInt();
                String stopName = fields.get("haltestellen_name").getAsString();
                Date arrivedProgrammedDate = simpleDateFormat.parse(fields.get("ankunftszeit").getAsString());
                Date arrivedDate = null;
                arrivedDate = simpleDateFormat.parse(fields.get("an_prognose").getAsString());

                delayedTrains.add(new TrainLate(recordId, stopName, lineId, arrivedDate, arrivedProgrammedDate));
            } catch(ParseException e) {
                LOGGER.error(e.getMessage(), e);
            }
        }

        /* Group every trains stop by lineId (int) */
        Map<Integer, List<TrainLate>> delayedTrainsPerLineId = delayedTrains.stream().collect(Collectors.groupingBy(TrainLate::getLineId));

        /* Calcul the cumulated delay */
        for(Map.Entry<Integer, List<TrainLate>> entry : delayedTrainsPerLineId.entrySet()) {
            delayedTrains = entry.getValue();

            /* Get the late when the train reach the terminus -> take the highest date */
            TrainLate delayedTrain = Collections.max(delayedTrains, Comparator.comparing(TrainLate::getArrivedProgrammedDate));

            cumulativeLate += delayedTrain.getArrivedDate().getTime() - delayedTrain.getArrivedProgrammedDate().getTime();
        }

        numberOfDelayedTravels = delayedTrainsPerLineId.size();

        return new DisruptionStats(numberOfDelayedTravels, cumulativeLate);
    }

    /**
     * Get the number of deleted travels.
     * @return number of deleted travels
     * @throws IOException error while connecting to the SBB API
     */
    private int getDeletedTravels() throws IOException {
        // Obtaining travels that are late
        LOGGER.info("getDisruptionStats - Initialize connection to SBB API");
        URL url = new URL("https://data.sbb.ch/api/records/1.0/search/?dataset=ist-daten-sbb&q=&rows=10000&sort=-linien_id&facet=betreiber_id&facet=linien_id&facet=faellt_aus_tf&facet=ankunftszeit&facet=an_prognose&facet=ankunftsverspatung&facet=abfahrtsverspatung&refine.faellt_aus_tf=true");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        // Parse JSON
        LOGGER.info("getDeletedTravels - Read API response");
        BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
        LOGGER.info("getDeletedTravels - Parse API response to JSON");
        JsonArray recordsLates = JsonParser.parseReader(br).getAsJsonObject().getAsJsonArray("records");

        // Defin number of lates
        int nbrOfDeletedTravels = 0;
        int lastLineId = 0;

        LOGGER.info("getDeletedTravels - Process deleted travels data");
        for(JsonElement jsonElement : recordsLates) {
            JsonObject fields = jsonElement.getAsJsonObject().get("fields").getAsJsonObject();
            int lineId = fields.get("linien_id").getAsInt();

            if(lastLineId != lineId) {
                nbrOfDeletedTravels++;
            }

            lastLineId = lineId;
        }

        return nbrOfDeletedTravels;
    }

    /**
     * Get total travels of the precedent day.
     * Every time a train leave a departure station and arrived at the terminus it's one travel.
     * @return the number of travel
     * @throws IOException error while connecting to the SBB API
     */
    private int getTotalTravels() throws IOException {
        // Get all travels from API : around 65000 entries
        LOGGER.info("getTotalTravels - Initialize connection to SBB API");
        URL allDataJSON = new URL("https://data.sbb.ch/explore/dataset/ist-daten-sbb/download/?format=json&timezone=Europe/Berlin&lang=fr&sort=-linien_id");
        URLConnection allDataJSONConn = allDataJSON.openConnection();

        // Parse JSON
        LOGGER.info("getTotalTravels - Read API response");
        BufferedReader allDataJSONReader = new BufferedReader(new InputStreamReader(allDataJSONConn.getInputStream()));
        LOGGER.info("getTotalTravels - Parse API response to JSON");
        JsonReader jsonReader = new JsonReader(allDataJSONReader);
        JsonArray records = JsonParser.parseReader(jsonReader).getAsJsonArray();

        List<Integer> linesId = new ArrayList<>();

        LOGGER.info("getTotalTravels - Process data");
        for(JsonElement jsonElement : records) {
            int lineId = jsonElement.getAsJsonObject().get("fields").getAsJsonObject().get("linien_id").getAsInt();

            if(!linesId.contains(lineId)) linesId.add(lineId);
        }

        int nbrOfTravels = linesId.size();

        LOGGER.info("getTotalTravels - Return {} travels", nbrOfTravels);
        return nbrOfTravels;
    }
}
