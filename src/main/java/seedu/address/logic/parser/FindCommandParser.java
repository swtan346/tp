package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_IC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.ContainsKeywordsPredicate;
import seedu.address.model.person.IcContainKeywordPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_IC);

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_IC);

        String keyword = null;

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            keyword = argMultimap.getValue(PREFIX_NAME).get();
            return new FindCommand(new NameContainsKeywordsPredicate(keyword));
        } else if (argMultimap.getValue(PREFIX_IC).isPresent()) {
            keyword = argMultimap.getValue(PREFIX_IC).get();
            return new FindCommand(new IcContainKeywordPredicate(keyword));
        } else {
            assert false : "no keyword";
        }
        return
    }

}
