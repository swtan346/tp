package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a patient's admission date in the address book.
 */
public class AdmissionDate {
    public static final String MESSAGE_CONSTRAINTS_FORMAT =
            "Admission dates take in date of format dd/MM/yyyy.";
    public static final String MESSAGE_CONSTRAINTS_OCCURRENCE =
            "Admission date should not be earlier than date of birth or later than current date";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public final LocalDate date;
    public final String value;

    /**
     * Constructs a {@code AdmissionDate}.
     *
     * @param value A valid admission date.
     */
    public AdmissionDate(String value) {
        requireNonNull(value);
        checkArgument(isValidDate(value), MESSAGE_CONSTRAINTS_FORMAT);
        checkArgument(isValidAdmissionDate(value), MESSAGE_CONSTRAINTS_OCCURRENCE);
        this.date = LocalDate.parse(value, formatter);
        this.value = value;
    }

    /**
     * Returns true if a given string is a valid date.
     *
     * @param admissionDate The admission date to be checked.
     */
    public static boolean isValidDate(String admissionDate) {
        try {
            LocalDate date = LocalDate.parse(admissionDate, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * Returns true if a given string is a valid admission date.
     *
     * @param admissionDate The admission date to be checked.
     */
    public static boolean isValidAdmissionDate(String admissionDate) {
        if (isValidDate(admissionDate)) {
            LocalDate date = LocalDate.parse(admissionDate, formatter);
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

        if (!(other instanceof AdmissionDate)) {
            return false;
        }

        AdmissionDate otherAdmissionDate = (AdmissionDate) other;
        return date.equals(otherAdmissionDate.date);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
