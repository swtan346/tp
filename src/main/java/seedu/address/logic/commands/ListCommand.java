package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.tag.TagContainsKeywordsPredicate;

/**
 * Lists all persons in the address book to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists relevant contacts from the address book. "
            + "Parameters: "
            + "[" + PREFIX_TAG + "WARD]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_TAG + "Fall-risk ";

    private final TagContainsKeywordsPredicate predicate;

    public static final String MESSAGE_SUCCESS_LIST = "Listed all persons";
    public static final String MESSAGE_SUCCESS_LIST_TAG = "Listed all persons with tag: %1$s";

    public ListCommand(TagContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    public ListCommand() {
        this.predicate = null;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        if (predicate != null) {
            model.updateFilteredPersonList(predicate);
            return new CommandResult(String.format(MESSAGE_SUCCESS_LIST_TAG, predicate.getKeywordsString()));
        } else {
            model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
            return new CommandResult(MESSAGE_SUCCESS_LIST);
        }
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicate == null ? "null" : predicate)
                .toString();
    }
}
