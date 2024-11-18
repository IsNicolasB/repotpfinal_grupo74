package ar.edu.unju.escmi.tpfinal.utils;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
public class FechaUtil {
    public static LocalDate convertirStringLocalDate(String fechaStr) throws IllegalArgumentException {
        // Define el formato de fecha
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try {
            // Parsea la fecha con el formato indicado
            return LocalDate.parse(fechaStr, formatter);
        } catch (DateTimeParseException e) {
            // Lanza una excepción si el formato no es correcto
            throw new IllegalArgumentException("La fecha ingresada no tiene el formato correcto: dd/MM/yyyy", e);
        }
    }
}