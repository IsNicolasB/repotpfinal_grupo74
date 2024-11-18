package ar.edu.unju.escmi.tpfinal.utils;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class TimeUtil {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

    public static LocalTime parseTime(String timeText) {
        try {
            return LocalTime.parse(timeText, FORMATTER);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Formato de hora inválido: " + timeText);
        }
    }
}