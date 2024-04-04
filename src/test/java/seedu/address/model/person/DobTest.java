package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class DobTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Dob(null));
    }

    @Test
    public void constructor_invalidDob_throwsIllegalArgumentException() {
        String invalidDob = "";
        assertThrows(IllegalArgumentException.class, () -> new Dob(invalidDob));
    }

    @Test
    public void isValidDob() {
        // null dob
        assertThrows(NullPointerException.class, () -> Dob.isValidDate(null));

        // blank dob
        assertFalse(Dob.isValidDate("")); // empty string
        assertFalse(Dob.isValidDate(" ")); // spaces only

        // missing parts
        assertFalse(Dob.isValidDate("17/03/")); // missing year
        assertFalse(Dob.isValidDate("/03/2024")); // missing day
        assertFalse(Dob.isValidDate("17/2024")); // missing month
        assertFalse(Dob.isValidDate("17/03")); // missing year
        assertFalse(Dob.isValidDate("03/2024")); // missing day
        assertFalse(Dob.isValidDate("17/2024")); // missing month

        // future date
        LocalDate today = LocalDate.now();
        LocalDate tomorrow = today.plusDays(1);
        // Define date format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        // Format date
        String todayFormatted = today.format(formatter);
        String tomorrowFormatted = tomorrow.format(formatter);

        // valid Dob date
        assertTrue(Dob.isValidDate("17/03/2024"));
        assertTrue(Dob.isValidDate(todayFormatted));

        // valid Dob
        assertFalse(Dob.isValidDob(tomorrowFormatted));
    }

    @Test
    public void equals() {
        Dob dob = new Dob("17/03/2024");

        // same values -> returns true
        assertTrue(dob.equals(new Dob("17/03/2024")));

        // same object -> returns true
        assertTrue(dob.equals(dob));

        // null -> returns false
        assertFalse(dob.equals(null));

        // different types -> returns false
        assertFalse(dob.equals(17));

        // different values -> returns false
        assertFalse(dob.equals(new Dob("16/03/2024")));
    }
}
