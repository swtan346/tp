package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_IC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_IC_BOB;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class IcContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        IcContainsKeywordPredicate firstPredicate = new IcContainsKeywordPredicate(VALID_IC_AMY);
        IcContainsKeywordPredicate secondPredicate = new IcContainsKeywordPredicate(VALID_IC_BOB);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        IcContainsKeywordPredicate firstPredicateCopy = new IcContainsKeywordPredicate(VALID_IC_AMY);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_icContainsKeywords_returnsTrue() {
        // One keyword
        IcContainsKeywordPredicate predicate = new IcContainsKeywordPredicate(VALID_IC_AMY);
        assertTrue(predicate.test(new PersonBuilder().withIc(VALID_IC_AMY).build()));

        // Mixed-case keyword
        predicate = new IcContainsKeywordPredicate("A1234567a");
        assertTrue(predicate.test(new PersonBuilder().withIc("A1234567A").build()));
    }

    @Test
    public void test_icDoesNotContainKeyword_returnsFalse() {
        // Zero keywords
        IcContainsKeywordPredicate predicate = new IcContainsKeywordPredicate("");
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").build()));

        // Non-matching keyword
        predicate = new IcContainsKeywordPredicate(VALID_IC_AMY);
        assertFalse(predicate.test(new PersonBuilder().withName(VALID_IC_BOB).build()));
    }

    @Test
    public void toStringMethod() {
        IcContainsKeywordPredicate predicate = new IcContainsKeywordPredicate(VALID_IC_AMY);

        String expected = IcContainsKeywordPredicate.class.getCanonicalName() + "{ic keyword=" + VALID_IC_AMY + "}";
        assertEquals(expected, predicate.toString());
    }
}
