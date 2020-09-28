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

    public Message(String dateOfReport, int nbrOfTravels, int nbrOfDelayedTravels, double percentageOfDelay, String cumulatedDelay) {
        this.dateOfReport = dateOfReport;
        this.cumulatedDelay = cumulatedDelay;
        this.nbrOfTravels = nbrOfTravels;
        this.nbrOfDelayedTravels = nbrOfDelayedTravels;
        this.percentageOfDelay = percentageOfDelay;
    }

    public String getDateOfReport() {
        return dateOfReport;
    }

    public String getCumulatedDelay() {
        return cumulatedDelay;
    }

    public int getNbrOfTravels() {
        return nbrOfTravels;
    }

    public int getNbrOfDelayedTravels() {
        return nbrOfDelayedTravels;
    }

    public double getPercentageOfDelay() {
        return percentageOfDelay;
    }

    public String getFormattedMessage() {
        LOGGER.info("Format message");
        String  result = "Retards #cff du " + getDateOfReport() + "\n\n";
                result += "\uD83D\uDE86 Nombre de voyages: " + decimalFormatter.format(getNbrOfTravels()) + "\n";
                result += "⏰ Trains en retard: " + decimalFormatter.format(getNbrOfDelayedTravels()) + " (" + (double) Math.round(getPercentageOfDelay() * 100) / 100 + " %)" + "\n";
                result += "⏱ Retard cumulé: " + getCumulatedDelay() + "\n";

        return result;
    }
}
