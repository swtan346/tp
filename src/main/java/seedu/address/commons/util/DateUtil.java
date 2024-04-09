package seedu.address.commons.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateUtil {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    /**
     * Returns true if a given string is a valid date.
     *
     * @param value The date to be checked.
     */
    public static boolean isValidDate(String value) {
        try {
            LocalDate date = LocalDate.parse(value, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
    /**
     * Returns true if a given string is a future date.
     *
     * @param value The date to be checked.
     */
    public static boolean isFutureDate(String value) {
        if (isValidDate(value)) {
            LocalDate date = LocalDate.parse(value, formatter);
            return date.isAfter(LocalDate.now());
        }
        return false;
    }
}
