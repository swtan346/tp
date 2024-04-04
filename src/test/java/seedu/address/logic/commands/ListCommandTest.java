package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_DIABETES;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FALL_RISK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_WARD_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_WARD_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.ListKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        assertCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listFiltered_showsFiltered() {
        String expectedMessage =
                String.format(ListCommand.MESSAGE_SUCCESS_LIST, "\nTags: Diabetes, FallRisk\nWard: B1");
        ListKeywordsPredicate predicate =
                new ListKeywordsPredicate(Arrays.asList(VALID_TAG_DIABETES, VALID_TAG_FALL_RISK),
                        VALID_WARD_BOB);
        ListCommand command = new ListCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        ListKeywordsPredicate firstPredicate =
                new ListKeywordsPredicate(Arrays.asList(VALID_TAG_DIABETES, VALID_TAG_FALL_RISK),
                        VALID_WARD_BOB);
        ListKeywordsPredicate secondPredicate =
                new ListKeywordsPredicate(Arrays.asList(VALID_TAG_DIABETES, VALID_TAG_FALL_RISK),
                        VALID_WARD_AMY);

        ListCommand listFirstCommand = new ListCommand(firstPredicate);
        ListCommand listSecondCommand = new ListCommand(secondPredicate);

        // same object -> returns true
        assertTrue(listFirstCommand.equals(listFirstCommand));

        // same values -> returns true
        ListCommand listFirstCommandCopy = new ListCommand(firstPredicate);
        assertTrue(listFirstCommand.equals(listFirstCommandCopy));

        // different types -> returns false
        assertFalse(listFirstCommand.equals(1));

        // null -> returns false
        assertFalse(listFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(listFirstCommand.equals(listSecondCommand));
    }

    @Test
    public void toStringMethod() {
        ListKeywordsPredicate predicate =
                new ListKeywordsPredicate(Arrays.asList(VALID_TAG_DIABETES, VALID_TAG_FALL_RISK), VALID_WARD_BOB);
        ListCommand listCommand = new ListCommand(predicate);
        String expected = ListCommand.class.getCanonicalName() + "{predicate=" + predicate + "}";
        assertEquals(expected, listCommand.toString());
    }
}
