package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a patient's date of birth in the address book.
 */
public class Dob {
    public static final String MESSAGE_CONSTRAINTS_FORMAT =
            "Dates of birth takes in a date of format dd/MM/yyyy";

    public static final String MESSAGE_CONSTRAINTS_OCCURRENCE_LATER_THAN_CURRENT_DATE =
            "Date of birth should not be later than current date";

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public final LocalDate date;
    public final String value;

    /**
     * Constructs a {@code Dob}.
     *
     * @param value A valid date of birth.
     */
    public Dob(String value) {
        requireNonNull(value);
        checkArgument(isValidDate(value), MESSAGE_CONSTRAINTS_FORMAT);
        checkArgument(isValidDob(value), MESSAGE_CONSTRAINTS_OCCURRENCE_LATER_THAN_CURRENT_DATE);
        this.date = LocalDate.parse(value, formatter);
        this.value = value;
    }

    /**
     * Returns true if a given string is a valid date.
     *
     * @param dob The date of birth to be checked.
     */
    public static boolean isValidDate(String dob) {
        try {
            LocalDate date = LocalDate.parse(dob, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * Returns true if a given string is a valid date of birth.
     *
     * @param dob The date of birth to be checked.
     */
    public static boolean isValidDob(String dob) {
        if (isValidDate(dob)) {
            LocalDate date = LocalDate.parse(dob, formatter);
            return !date.isAfter(LocalDate.now());
        }
        return false;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Dob)) {
            return false;
        }

        Dob otherDob = (Dob) other;
        return date.equals(otherDob.date);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
