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
     * @param seconds1 the seconds to convert
     * @return the formatted time
     */
    public static String convertSecondsToTime(long seconds1) {
        long months = 0;
        long weeks = 0;
        long days = 0;
        long hours = 0;
        long minutes = 0;
        long seconds = 0;
        long seconds2 = seconds1;

        while(seconds2 != 0) {
            seconds++;

            if(seconds == 60) {
                minutes++;
                seconds = 0;
            }
            if(minutes == 60) {
                hours++;
                minutes = 0;
            }
            if(hours == 24) {
                days++;
                hours = 0;
            }
            if(days == 7) {
                weeks++;
                days = 0;
            }
            if(weeks == 4) {
                months++;
                weeks = 0;
            }

            seconds2--;
        }

        StringBuilder result = new StringBuilder();
        boolean setMins = minutes != 0;

        if(months != 0) {
            if(weeks != 0 || days != 0 || hours != 0) {
                result.append(months + " mois, ");
            } else {
                result.append(months + " mois");
            }
            setMins = false;
        }
        if(weeks != 0) {
            if(days != 0 || hours != 0) {
                result.append(weeks + (weeks == 1 ? " semaine, " : " semaines, "));
            } else {
                result.append(weeks + (weeks == 1 ? " semaine" : " semaines"));
            }
            setMins = false;
        }
        if(days != 0) {
            if(hours != 0 || setMins) {
                result.append(days + (days == 1 ? " jour, " : " jours, "));
            } else {
                result.append(days + (days == 1 ? " jour" : " jours"));
            }
        }
        if(hours != 0) {
            if(setMins) {
                result.append(hours + (hours == 1 ? " heure, " : " heures, "));
            } else {
                result.append(hours + (hours == 1 ? " heure" : " heures"));
            }
        }
        if(setMins) {
            result.append(minutes + (minutes == 1 ? " minute" : " minutes"));
        }

        return result.toString();
    }
}
