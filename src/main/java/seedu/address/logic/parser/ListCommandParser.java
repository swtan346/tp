package seedu.address.logic.parser;

import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Ward;
import seedu.address.model.tag.Tag;
import seedu.address.model.tag.TagContainsKeywordsPredicate;

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
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TAG, PREFIX_WARD);

        List<String> tagList = ParserUtil.parseTagsKeywords(argMultimap.getAllValues(PREFIX_TAG));
        // Ward ward = ParserUtil.parseWard(argMultimap.getValue(PREFIX_WARD).orElse(null));

        if (tagList.isEmpty()) {
            return new ListCommand();   // return a ListCommand object with no predicate
        } else {
            return new ListCommand(new TagContainsKeywordsPredicate(tagList));
        }
    }

}
