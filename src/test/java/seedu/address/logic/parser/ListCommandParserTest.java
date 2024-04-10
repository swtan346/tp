package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_DIABETES;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FALL_RISK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_DIABETES;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FALL_RISK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_WARD_BOB;
import static seedu.address.logic.commands.CommandTestUtil.WARD_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.WARD_DESC_BOB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_WARD;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.ListCommand;
import seedu.address.model.person.ListKeywordsPredicate;

public class ListCommandParserTest {

    private ListCommandParser parser = new ListCommandParser();

    @Test
    public void parse_invalidArgs_failure() {
        // ineligible parameters after list command
        assertParseFailure(parser, "BOO",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsListCommand() {
        // no leading and trailing whitespaces
        ListCommand expectedListCommand =
                new ListCommand(
                        new ListKeywordsPredicate(Arrays.asList(VALID_TAG_FALL_RISK, VALID_TAG_DIABETES),
                                VALID_WARD_BOB));
        assertParseSuccess(parser, TAG_DESC_DIABETES
                + TAG_DESC_FALL_RISK + WARD_DESC_BOB, expectedListCommand);

        // multiple fields repeated for ward
        assertParseFailure(parser, WARD_DESC_AMY + WARD_DESC_BOB,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_WARD));
    }

}
