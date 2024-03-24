package seedu.address.logic.parser;

import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.ListKeywordsPredicate;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static seedu.address.logic.parser.CliSyntax.*;

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
        if (trimmedArgs.isEmpty()) {
            return new ListCommand();   // return a ListCommand object with no predicate
        }

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TAG, PREFIX_WARD);

        List<String> tagList = ParserUtil
                .parseTagsKeywords(argMultimap.getAllValues(PREFIX_TAG));
        String ward = argMultimap.getValue(PREFIX_WARD).orElse("");

        return new ListCommand(new ListKeywordsPredicate(tagList, ward));
    }
}
