package ch.lucas.bot.cff.utils;

/**
 * Utility class for the time.
 *
 * @author Lucas-it@github
 */
public class TimeFormatter {
    private TimeFormatter() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Convert seconds to a formatted time.
     * 60 seconds become '1 minute'
     * 1200 seconds become '20 minutes'
     * 7260 secondes become '2 heures, 1 minute'
     * @param seconds the seconds to convert
     * @return the formatted time
     */
    public static String convertSecondsToTime(long seconds) {
        long months = seconds / (30 * 24 * 3600);

        seconds = seconds % (30 * 24 * 3600);
        long days = seconds / (24 * 3600);

        seconds = seconds % (24 * 3600);
        long hours = seconds / 3600;

        seconds %= 3600;
        long minutes = seconds / 60 ;

        // Format result
        StringBuilder result = new StringBuilder();
        boolean setMins = minutes != 0;

        if(months != 0) {
            result.append(months + " mois");
            result.append(days != 0 || hours != 0 ? ", " : "");
            setMins = false;
        }
        if(days != 0) {
            result.append(days + (days == 1 ? " jour" : " jours"));
            result.append(hours != 0 || setMins ? ", " : "");
        }
        if(hours != 0) {
            result.append(hours + (hours == 1 ? " heure" : " heures"));
            result.append(setMins ? ", " : "");
        }
        if(setMins) {
            result.append(minutes + (minutes == 1 ? " minute" : " minutes"));
        }

        return result.toString();
    }
}
