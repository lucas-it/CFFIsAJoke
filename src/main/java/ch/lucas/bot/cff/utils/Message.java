package ch.lucas.bot.cff.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DecimalFormat;

public class Message {
    private final Logger LOGGER = LoggerFactory.getLogger(Message.class);
    private final String dateOfReport;
    private final String cumulatedDelay;
    private final int nbrOfTravels;
    private final int nbrOfDelayedTravels;
    private final double percentageOfDelay;
    private final DecimalFormat decimalFormatter = new DecimalFormat("#,###");

    /**
     * Create a new message with information about disruptions.
     * @param dateOfReport The date of the informations. For exemple: delays of 1st october 2020
     * @param nbrOfTravels The number of travels.
     * @param nbrOfDelayedTravels The number of delayed travels.
     * @param percentageOfDelay The pourcentage of delayed travels.
     * @param cumulatedDelay The total delayed time. For exemple: 2 hours, 1 minute.
     */
    public Message(String dateOfReport, int nbrOfTravels, int nbrOfDelayedTravels, double percentageOfDelay, String cumulatedDelay) {
        this.dateOfReport = dateOfReport;
        this.cumulatedDelay = cumulatedDelay;
        this.nbrOfTravels = nbrOfTravels;
        this.nbrOfDelayedTravels = nbrOfDelayedTravels;
        this.percentageOfDelay = percentageOfDelay;
    }

    /**
     * Get the date of the report.
     * @return the date of report
     */
    public String getDateOfReport() {
        return dateOfReport;
    }

    /**
     * Get the cumulated delay.
     * @return the cumulated delay
     */
    public String getCumulatedDelay() {
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
     * Get the pourcentage of delay.
     * @return the pourcentage of delay
     */
    public double getPercentageOfDelay() {
        return percentageOfDelay;
    }

    /**
     * Get the message ready to be tweeted.
     * @return the formatted message
     */
    public String getFormattedMessage() {
        LOGGER.info("Format message");
        String  result = "Retards #cff du " + getDateOfReport() + "\n\n";
                result += "\uD83D\uDE86 Nombre de voyages: " + decimalFormatter.format(getNbrOfTravels()) + "\n";
                result += "⏰ Trains en retard: " + decimalFormatter.format(getNbrOfDelayedTravels()) + " (" + (double) Math.round(getPercentageOfDelay() * 100) / 100 + " %)" + "\n";
                result += "⏱ Retard cumulé: " + getCumulatedDelay() + "\n";

        return result;
    }
}
