package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_WARD;

import java.util.List;
import java.util.stream.Stream;

import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.ListKeywordsPredicate;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class ListCommandParser implements Parser<ListCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ListCommand
     * and returns a ListCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ListCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        // If there are no arguments, return a ListCommand object with no predicate
        if (trimmedArgs.isEmpty()) {
            return new ListCommand(); // return a ListCommand object with no predicate
        }

        // If there are arguments, parse the arguments and return a ListCommand object with the parsed predicate
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TAG, PREFIX_WARD);
        if (!argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
        }
        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_WARD);

        List<String> tagList = List.of();
        if (arePrefixesPresent(argMultimap, PREFIX_TAG)) {
            tagList = ParserUtil.parseTagsKeywords(argMultimap.getAllValues(PREFIX_TAG));
            assert !tagList.isEmpty();
        }

        String ward = "";
        if (arePrefixesPresent(argMultimap, PREFIX_WARD)) {
            ward = ParserUtil.parseWard(argMultimap.getValue(PREFIX_WARD).orElse(null)).toString();
            assert !ward.isEmpty();
        }

        if (ward.isEmpty() && tagList.isEmpty()) { // If there are no valid arguments, throw an exception
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
        }

        assert !tagList.isEmpty() || !ward.isEmpty();

        return new ListCommand(new ListKeywordsPredicate(tagList, ward));
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
