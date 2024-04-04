package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;
public class RemarkTest {
    private static final String VALID_REMARK_STRING = "Valid Remark";
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Remark(null));
    }

    @Test
    public void equals() {
        Remark remark = new Remark(VALID_REMARK_STRING);

        // same values -> returns true
        assertTrue(remark.equals(new Remark(VALID_REMARK_STRING)));

        // same object -> returns true
        assertTrue(remark.equals(remark));

        // null -> returns false
        assertFalse(remark.equals(null));

        // different types -> returns false
        assertFalse(remark.equals(5.0f));

        // different values -> returns false
        assertFalse(remark.equals(new Remark("Other Valid Remark")));
    }
    @Test
    public void toStringMethod() {
        Remark remark = new Remark(VALID_REMARK_STRING);
        assertTrue(remark.toString().equals(VALID_REMARK_STRING));
    }
}
