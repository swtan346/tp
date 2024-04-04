package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.IC_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_IC_AMY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DOB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_IC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindCommand;
import seedu.address.model.person.IcContainsKeywordPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidArg_throwsParseException() {
        // no prefix
        assertParseFailure(parser, " bob", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));

        // wrong prefix
        assertParseFailure(parser, " " + PREFIX_DOB + "02/02/2000", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindCommand.MESSAGE_USAGE));

        // multiple ic
        assertParseFailure(parser, " " + PREFIX_IC + "a1233556a b2222222b",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));

        // both name and ic
        assertParseFailure(parser, " " + PREFIX_NAME + "Alice Bob " + PREFIX_IC + "a1233556a",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        assertParseFailure(parser, " " + PREFIX_IC + "a1233556a " + PREFIX_NAME + "Alice Bob",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validNameArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")));
        assertParseSuccess(parser, " " + PREFIX_NAME + "Alice Bob", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " " + PREFIX_NAME + " \n Alice \n \t Bob  \t", expectedFindCommand);
    }

    @Test
    public void parse_nameArgsWithoutKeyword_returnsFindCommand() {
        // no keyword
        FindCommand expectedFindCommand =
                new FindCommand(new NameContainsKeywordsPredicate(Collections.emptyList()));
        assertParseSuccess(parser, " " + PREFIX_NAME , expectedFindCommand);

        // multiple whitespaces
        assertParseSuccess(parser, " " + PREFIX_NAME + "   ", expectedFindCommand);
    }

    @Test
    public void parse_validIcArg_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new IcContainsKeywordPredicate(VALID_IC_AMY));
        assertParseSuccess(parser, IC_DESC_AMY, expectedFindCommand);
    }

    @Test
    public void parse_icArgWithoutKeyword_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand = new FindCommand(new IcContainsKeywordPredicate(""));
        assertParseSuccess(parser, " " + PREFIX_IC, expectedFindCommand);

        // multiple whitespaces
        assertParseSuccess(parser, " " + PREFIX_IC + "   ", expectedFindCommand);
    }
}
