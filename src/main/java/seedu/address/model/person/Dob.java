package seedu.address.model.person;

import seedu.address.commons.util.DateUtil;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a patient's date of birth in the address book.
 */
public class Dob extends DateUtil {
    public static final String MESSAGE_CONSTRAINTS_FORMAT =
            "Dates of birth takes in a date of format dd/MM/yyyy";

    public static final String MESSAGE_CONSTRAINTS_OCCURRENCE_LATER_THAN_CURRENT_DATE =
            "Date of birth should not be later than current date";

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private final LocalDate dob;
    private final String value;

    /**
     * Constructs a {@code Dob}.
     *
     * @param value A valid date of birth.
     */
    public Dob(String value) {
        requireNonNull(value);
        checkArgument(isValidDate(value), MESSAGE_CONSTRAINTS_FORMAT);
        checkArgument(!isFutureDate(value), MESSAGE_CONSTRAINTS_OCCURRENCE_LATER_THAN_CURRENT_DATE);
        this.dob = LocalDate.parse(value, formatter);
        this.value = value;
    }

    /**
     * Returns the date of birth
     */
    public LocalDate getDob() {
        return dob;
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
        return dob.equals(otherDob.dob);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
