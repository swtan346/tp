package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Format full help instructions for every command for display.
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions within application.\n"
            + "Example: " + COMMAND_WORD;

    public static final String SHOWING_HELP_MESSAGE = "Here are the list of available commands:\n\n"
            + "Add: add n\\NAME ic\\IC_NUMBER dob\\DATE_OF_BIRTH ad\\ADMISSION_DATE w\\WARD [t\\TAG]...\n"
            + "   Example: add n\\John Doe ic\\T1234567P dob\\01/01/2000 "
            + "ad\\25/03/2024 w\\A1 t\\Diabetes t\\FallRisk\n\n"
            + "Clear: clear\n"
            + "   Clears all entries from the address book.\n\n"
            + "Delete: delete INDEX\n"
            + "   Example: delete 3\n\n"
            + "Edit: edit INDEX [n\\NAME] [ic\\IC_NUMBER] [dob\\DATE_OF_BIRTH] "
            + "[ad\\ADMISSION_DATE] [w\\WARD] [t\\TAG]...\n"
            + "   Example: edit 1 ic\\T0123456P t\\\n\n"
            + "Find: find KEYWORD [MORE_KEYWORDS]\n"
            + "   Example: find John\n\n"
            + "List: list\n"
            + "   Lists all patients.\n\n"
            + "Exit: exit\n"
            + "   Exits the application.\n\n"
            + "For more detailed information on each command, please refer to the User Guide.";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(SHOWING_HELP_MESSAGE, false, false);
    }
}
