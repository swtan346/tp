package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.HelpCommand.SHOWING_HELP_MESSAGE;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.ui.HelpWindow;

public class HelpCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();
    private HelpWindow helpWindow = new HelpWindow();

    @Test
    public void execute_help_success() {
        HelpCommand helpCommand = new HelpCommand(helpWindow);
        CommandResult expectedCommandResult = new CommandResult(SHOWING_HELP_MESSAGE, true, false);
        assertCommandSuccess(helpCommand, model, expectedCommandResult, expectedModel);
        assertTrue(helpWindow.isShowing());
    }
}
