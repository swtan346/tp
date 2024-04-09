package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADMISSION_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DOB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_IC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_WARD;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Adds a person to the address book.
 */
public class AddCommand extends Command {
    public static final String COMMAND_WORD = "add";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a person to the address book. \n"
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_IC + "IC_NUMBER "
            + PREFIX_DOB + "DATE_OF_BIRTH "
            + PREFIX_ADMISSION_DATE + "ADMISSION_DATE "
            + PREFIX_WARD + "WARD_NUMBER "
            + "[" + PREFIX_REMARK + "REMARK] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_IC + "S0123456A "
            + PREFIX_DOB + "01/01/2000 "
            + PREFIX_ADMISSION_DATE + "15/03/2024 "
            + PREFIX_WARD + "3 "
            + PREFIX_REMARK + "Prevent bed sores. "
            + PREFIX_TAG + "FallRisk";
    public static final String MESSAGE_SUCCESS = "New patient added: %1$s";

    public static final String MESSAGE_DUPLICATE_PERSON = "A patient with this IC already exists in the address book";

    public static final String MESSAGE_DOB_AFTER_ADMISSION =
            "Date of birth should not be later than date of admission.";

    private final Person toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public AddCommand(Person person) {
        requireNonNull(person);
        toAdd = person;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasPerson(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        if (toAdd.getDob().getDob().isAfter(toAdd.getAdmissionDate().date)) {
            throw new CommandException(MESSAGE_DOB_AFTER_ADMISSION);
        }

        model.addPerson(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(toAdd)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddCommand)) {
            return false;
        }

        AddCommand otherAddCommand = (AddCommand) other;
        return toAdd.equals(otherAddCommand.toAdd);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toAdd", toAdd)
                .toString();
    }
}
