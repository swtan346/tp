package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import seedu.address.commons.util.DateUtil;

/**
 * Represents a patient's date of birth in the address book.
 */
public class Dob extends DateUtil {
    public static final String DATE_TYPE = "Date of birth";
    public static final String MESSAGE_CONSTRAINTS_FORMAT =
            String.format(DateUtil.MESSAGE_CONSTRAINTS_FORMAT, DATE_TYPE);
    public static final String MESSAGE_CONSTRAINTS_FUTURE_OCCURRENCE =
            String.format(DateUtil.MESSAGE_CONSTRAINTS_FUTURE_OCCURRENCE, DATE_TYPE);

    public static final String MESSAGE_DOB_AFTER_ADMISSION =
            "Date of birth should not be later than date of admission.";

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
        checkArgument(!isFutureDate(value), MESSAGE_CONSTRAINTS_FUTURE_OCCURRENCE);
        this.dob = LocalDate.parse(value, formatter);
        this.value = value;
    }

    /**
     * Checks if the date of birth is after the admission date
     */
    public boolean isAfterAdmissionDate(AdmissionDate admissionDate) {
        return dob.isAfter(admissionDate.getDate());
    }

    /**
     * Returns the date of birth
     */
    public LocalDate getDate() {
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
