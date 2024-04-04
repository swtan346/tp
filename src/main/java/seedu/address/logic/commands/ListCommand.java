package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_WARD;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.Model;
import seedu.address.model.person.ListKeywordsPredicate;

/**
 * Lists all persons in the address book to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists relevant contacts from the address book. "
            + "Parameters: "
            + "[" + PREFIX_TAG + "TAG]..."
            + "[" + PREFIX_WARD + "WARD]\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_TAG + "FallRisk "
            + PREFIX_WARD + "3 ";

    public static final String MESSAGE_SUCCESS = "Listed all persons";
    public static final String MESSAGE_SUCCESS_LIST = "Listed all persons with: %1$s";
    private final ListKeywordsPredicate predicate;

    public ListCommand(ListKeywordsPredicate predicate) {
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
            return new CommandResult(String.format(MESSAGE_SUCCESS_LIST, predicate.getKeywordsString()));
        } else {
            model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
            return new CommandResult(MESSAGE_SUCCESS);
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ListCommand)) {
            return false;
        }

        ListCommand otherListCommand = (ListCommand) other;
        return predicate.equals(otherListCommand.predicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicate == null ? "null" : predicate)
                .toString();
    }
}
