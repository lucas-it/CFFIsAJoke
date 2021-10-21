package ch.lucas.bot.cff.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Message {
    private final Logger LOGGER = LoggerFactory.getLogger(Message.class);
    private final Date dateOfReport;
    private final int nbrOfTravels;
    private final int nbrOfDelayedTravels;
    private final int nbrOfDeletedTravels;
    private final long cumulatedDelay;
    private final long averageDelayPerTrain;
    private final double percentageOfDelayedTravels;
    private final double percentageOfDeletedTravels;
    private final DecimalFormat decimalFormatter = new DecimalFormat("#,###");

    /**
     * Create a new message with information about disruptions.
     * @param dateOfReport The date of the report.
     * @param nbrOfTravels The number of travels.
     * @param nbrOfDelayedTravels The number of delayed travels.
     * @param averageDelayPerTrain The average delay per train in seconds.
     * @param cumulatedDelay The total delayed time in seconds.
     */
    public Message(Date dateOfReport, int nbrOfTravels, int nbrOfDelayedTravels, int nbrOfDeletedTravels, long averageDelayPerTrain, long cumulatedDelay) {
        this.dateOfReport = dateOfReport;
        this.cumulatedDelay = cumulatedDelay;
        this.nbrOfTravels = nbrOfTravels;
        this.nbrOfDelayedTravels = nbrOfDelayedTravels;
        this.nbrOfDeletedTravels = nbrOfDeletedTravels;
        this.averageDelayPerTrain = averageDelayPerTrain;
        this.percentageOfDelayedTravels = (double) Math.round(((double) nbrOfDelayedTravels / nbrOfTravels) * 10000) / 100;
        this.percentageOfDeletedTravels = (double) Math.round(((double) nbrOfDeletedTravels / nbrOfTravels) * 10000) / 100;
    }

    /**
     * Get the date of the report.
     * @return the date of report
     */
    public Date getDateOfReport() {
        return dateOfReport;
    }

    /**
     * Get the cumulated delay.
     * @return the cumulated delay
     */
    public long getCumulatedDelay() {
        return cumulatedDelay;
    }

    /**
     * Get the number of travels.
     * @return the number of travels
     */
    public int getNbrOfTravels() {
        return nbrOfTravels;
    }

    /**
     * Get the number of delayed travels.
     * @return the number of delayed travels
     */
    public int getNbrOfDelayedTravels() {
        return nbrOfDelayedTravels;
    }

    /**
     * Get the number of deleted travels.
     * @return the number of deleted travels.
     */
    public int getNbrOfDeletedTravels() {
        return nbrOfDeletedTravels;
    }

    /**
     * Get the pourcentage of delay.
     * @return the pourcentage of delay
     */
    public double getPercentageOfDelayedTravels() {
        return percentageOfDelayedTravels;
    }

    /**
     * Get the pourcentage of deleted travels.
     * @return the pourcentage of deleted travels.
     */
    public double getPercentageOfDeletedTravels() {
        return percentageOfDeletedTravels;
    }

    /**
     * Get the average delay per train
     * @return the average delay per train in seconds.
     */
    public long getAverageDelayPerTrain() {
        return averageDelayPerTrain;
    }

    /**
     * Get the message ready to be tweeted.
     * @return the formatted message
     */
    public String getFormattedMessage(Locale locale) {
        LOGGER.info("getFormattedMessage - Format message in {}", locale);

        String result = "";
        String averageDelayPerTrainFormatted = TimeFormatter.convertSecondsToTime(getAverageDelayPerTrain(), locale);
        String cumulatedDelayFormatted = TimeFormatter.convertSecondsToTime(getCumulatedDelay(), locale);
        DateFormat dateFormatter;

        if(locale == Locale.FRENCH) {
            dateFormatter = new SimpleDateFormat("EEEE d MMMM yyyy", locale);
            result = "Retards #CFF #SBB #FFS du " + dateFormatter.format(getDateOfReport()) + "\n\n";
            result += "\uD83D\uDE86 Nombre de voyages: " + decimalFormatter.format(getNbrOfTravels()) + "\n";
            result += "⏰ Trains en retard: " + decimalFormatter.format(getNbrOfDelayedTravels()) + " (" + getPercentageOfDelayedTravels() + " %)" + "\n";
            result += "\uD83D\uDDD1️ Trains supprimés: " + decimalFormatter.format(getNbrOfDeletedTravels()) + " (" + getPercentageOfDeletedTravels() + " %)" + "\n";
            result += "\uD83D\uDE89 Retard moyen par train: " + averageDelayPerTrainFormatted + "\n";
            result += "⏱ Retard cumulé: " + cumulatedDelayFormatted + "\n";
        } else if(locale == Locale.GERMAN) {
            dateFormatter = new SimpleDateFormat("EEEE d'.' MMMM yyyy", locale);
            result = "Verzögerungen #CFF #SBB #FFS ab " + dateFormatter.format(getDateOfReport()) + "\n\n";
            result += "\uD83D\uDE86 Anzahl der Fahrten: " + decimalFormatter.format(getNbrOfTravels()) + "\n";
            result += "⏰ Verspätete Züge: " + decimalFormatter.format(getNbrOfDelayedTravels()) + " (" + getPercentageOfDelayedTravels() + " %)" + "\n";
            result += "\uD83D\uDDD1️ Gelöschte Züge: " + decimalFormatter.format(getNbrOfDeletedTravels()) + " (" + getPercentageOfDeletedTravels() + " %)" + "\n";
            result += "\uD83D\uDE89 Durchschnittliche Verspätung pro Zug: " + averageDelayPerTrainFormatted + "\n";
            result += "⏱ Kumulative Verzögerung: " + cumulatedDelayFormatted + "\n";
        }

        return result;
    }
}
