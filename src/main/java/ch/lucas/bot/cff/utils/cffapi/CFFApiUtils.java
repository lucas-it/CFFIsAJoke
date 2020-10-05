package ch.lucas.bot.cff.utils.cffapi;

import ch.lucas.bot.cff.utils.Message;
import ch.lucas.bot.cff.utils.RowLate;
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

/**
 * This class provide utility methods to get information from SBB api.
 *
 * @author Lucas-it@github
 */
public class CFFApiUtils {
    private final Logger LOGGER = LoggerFactory.getLogger(CFFApiUtils.class);
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

    /**
     * Get the information about disruptions from the SBB api about the precedent day.
     * @return a Message object
     * @throws IOException where was an error when parsing the API response JSON
     */
    public Message getInformationsFromAPI() throws IOException {
        // Obtaining travels that are late
        LOGGER.info("getInformationsFromAPI - Initialize connection to SBB API");
        URL url = new URL("https://data.sbb.ch/api/records/1.0/search/?dataset=ist-daten-sbb&q=&rows=10000&sort=-linien_id&facet=betreiber_id&facet=produkt_id&facet=linien_id&facet=linien_text&facet=verkehrsmittel_text&facet=faellt_aus_tf&facet=bpuic&facet=ankunftszeit&facet=an_prognose&facet=an_prognose_status&facet=ab_prognose_status&facet=ankunftsverspatung&facet=abfahrtsverspatung&refine.produkt_id=Zug&refine.ankunftsverspatung=true");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        // Parse JSON
        LOGGER.info("getInformationsFromAPI - Read API response");
        BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
        LOGGER.info("getInformationsFromAPI - Parse API response to JSON");
        JsonArray recordsLates = JsonParser.parseReader(br).getAsJsonObject().getAsJsonArray("records");

        // Defin number of lates
        long cumulativeLate = 0;
        int numberOfLates = 0;
        int lastLineId = 0;

        RowLate lastLostRow = null;
        LinkedList<RowLate> lastRows = new LinkedList<>();

        LOGGER.info("getInformationsFromAPI - Process late trains data");
        for(JsonElement jsonElement : recordsLates) {
            try {
                String recordId = jsonElement.getAsJsonObject().get("recordid").getAsString();
                JsonObject fields = jsonElement.getAsJsonObject().get("fields").getAsJsonObject();
                int lineId = fields.get("linien_id").getAsInt();
                String stopName = fields.get("haltestellen_name").getAsString();
                Date arrivedProgrammedDate = simpleDateFormat.parse(fields.get("ankunftszeit").getAsString());
                Date arrivedDate = simpleDateFormat.parse(fields.get("an_prognose").getAsString());

                if(lastLostRow != null && !lastRows.contains(lastLostRow)) {
                    lastRows.add(lastLostRow);
                }

                if(lastRows.isEmpty()) {
                    lastRows.add(new RowLate(recordId, stopName, lineId, arrivedDate, arrivedProgrammedDate));
                    lastLineId = lineId;
                } else if(lastLineId == lineId) {
                    lastRows.add(new RowLate(recordId, stopName, lineId, arrivedDate, arrivedProgrammedDate));
                } else {
                    lastLostRow = new RowLate(recordId, stopName, lineId, arrivedDate, arrivedProgrammedDate);
                }

                if(lastLineId != lineId) {
                    Date lateArrivedProgrammedDate = simpleDateFormat.parse("2000-01-01T00:00:00");

                    for(RowLate rowLate : lastRows) {
                        if(rowLate.getArrivedProgrammedDate().getTime() > lateArrivedProgrammedDate.getTime()) {
                            lateArrivedProgrammedDate = rowLate.getArrivedProgrammedDate();
                        }
                    }

                    lastRows.clear();

                    cumulativeLate += lastLostRow.getArrivedDate().getTime() - lastLostRow.getArrivedProgrammedDate().getTime();
                    numberOfLates++;
                }

                lastLineId = lineId;
            } catch(ParseException e) {
                LOGGER.error(e.getMessage(), e);
            }

        }

        simpleDateFormat = new SimpleDateFormat("EEEE d MMMM yyyy");
        // Get total travels
        LOGGER.info("getInformationsFromAPI - Get total number of travels");
        int totalTravels = getTotalTravels();
        // Calcul pourcentage of delayed trains
        LOGGER.info("getInformationsFromAPI - Calcul pourcentage of late trains");
        double latePourcent = ((double) numberOfLates / totalTravels) * 100;
        // Create a new message
        LOGGER.info("getInformationsFromAPI - Create a new message with all informations");
        return new Message(simpleDateFormat.format(System.currentTimeMillis() - 86400000), totalTravels, numberOfLates, (double) Math.round(latePourcent * 100) / 100, TimeFormatter.convertSecondsToTime(cumulativeLate / 1000));
    }

    /**
     * Get total travels of the precedent day.
     * Every time a train leave a departure station and arrived at the terminus it's one travel.
     * @return the number of travel
     */
    private int getTotalTravels() {
        try {
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
        } catch(IOException e) {
            LOGGER.error(e.getMessage(), e);
        }

        return 1;
    }
}
