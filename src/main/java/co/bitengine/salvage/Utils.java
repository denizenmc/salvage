package co.bitengine.salvage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Utils {
    public static String getDateTime() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

    public static double getRandomValue(double min, double max) {
        if (min == max) return min;
        double x = (Math.random() * ((max - min) + 1)) + min;
        return Math.round(x * 100.0) / 100.0;
    }
}
