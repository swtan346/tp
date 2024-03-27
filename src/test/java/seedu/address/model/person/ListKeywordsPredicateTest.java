package seedu.address.model.person;

import org.junit.jupiter.api.Test;
import seedu.address.testutil.PersonBuilder;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.logic.commands.CommandTestUtil.*;

public class ListKeywordsPredicateTest {

    @Test
    public void equals() {
        ListKeywordsPredicate firstPredicate =
                new ListKeywordsPredicate(Arrays.asList(VALID_TAG_DIABETES, VALID_TAG_FALL_RISK), VALID_WARD_BOB);
        ListKeywordsPredicate secondPredicate =
                new ListKeywordsPredicate(Arrays.asList(VALID_TAG_DIABETES, VALID_TAG_FALL_RISK), VALID_WARD_AMY);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        ListKeywordsPredicate firstPredicateCopy =
                new ListKeywordsPredicate(Arrays.asList(VALID_TAG_DIABETES, VALID_TAG_FALL_RISK), VALID_WARD_BOB);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_tagContainsKeywords_returnsTrue() {
        // One keyword for tag
        ListKeywordsPredicate predicate =
                new ListKeywordsPredicate(Arrays.asList(VALID_TAG_DIABETES), "");
        assertTrue(predicate.test(new PersonBuilder()
                .withTags("Diabetes")
                .build()));

        // One keyword for ward
        predicate = new ListKeywordsPredicate(Collections.emptyList(), VALID_WARD_BOB);
        assertTrue(predicate.test(new PersonBuilder().withWard(VALID_WARD_BOB).build()));

        // Multiple keywords (tag)
        predicate = new ListKeywordsPredicate(Arrays.asList("Diabetes", "FalLRisk"), VALID_WARD_BOB);
        assertTrue(predicate.test(new PersonBuilder()
                .withTags("Diabetes", "FallRisk")
                .withWard(VALID_WARD_BOB)
                .build()));

        // Multiple keywords (tag and ward)
        predicate = new ListKeywordsPredicate(Arrays.asList(VALID_TAG_DIABETES), VALID_WARD_BOB);
        assertTrue(predicate.test(new PersonBuilder()
                .withTags("Diabetes")
                .withWard(VALID_WARD_BOB)
                .build()));

        // Only one matching keyword
        predicate = new ListKeywordsPredicate(Arrays.asList(VALID_TAG_DIABETES, "LowSodium"), VALID_WARD_BOB);
        assertFalse(predicate.test(new PersonBuilder()
                .withTags("Diabetes")
                .withTags("FallRisk")
                .withWard(VALID_WARD_BOB)
                .build()));

        // Mixed-case keyword
        predicate = new ListKeywordsPredicate(Arrays.asList("diABeteS", "falLRisk"), VALID_WARD_BOB);
        assertTrue(predicate.test(new PersonBuilder()
                .withTags("Diabetes", "FallRisk")
                .withWard(VALID_WARD_BOB)
                .build()));
    }

    @Test
    public void test_doesNotContainKeywords_returnsFalse() {
        // Non-matching keyword for tag
        ListKeywordsPredicate predicate =
                new ListKeywordsPredicate(Arrays.asList(VALID_TAG_DIABETES), VALID_WARD_BOB);
        assertFalse(predicate.test(new PersonBuilder().withTags("LowSodium").build()));

        // Non-matching keyword for ward
        assertFalse(predicate.test(new PersonBuilder().withWard("5").build()));
    }

    @Test
    public void toStringMethod() {
        List<String> keywords = List.of("keyword1", "keyword2");
        ListKeywordsPredicate predicate = new ListKeywordsPredicate(keywords, VALID_WARD_BOB);

        String expected = ListKeywordsPredicate.class.getCanonicalName()
                + "{tag=" + keywords + ", ward=" + VALID_WARD_BOB + "}";
        assertEquals(expected, predicate.toString());
    }
}
