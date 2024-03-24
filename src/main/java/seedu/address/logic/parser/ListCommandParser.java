package seedu.address.logic.parser;

import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.TagContainsKeywordsPredicate;

import java.util.Arrays;

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

        String[] tagKeywords = trimmedArgs.split("\\s+");

        return new ListCommand(new TagContainsKeywordsPredicate(Arrays.asList(tagKeywords)));
    }

}
