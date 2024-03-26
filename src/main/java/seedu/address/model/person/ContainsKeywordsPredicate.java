package seedu.address.model.person;

import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class ContainsKeywordsPredicate implements Predicate<Person> {
    private final String nameKeyword;
    private final String icKeyword;

    /**
     * Initializes find keywords.
     *
     * @param nameKeyword keyword of name field
     * @param icKeyword keyword for ic field
     */
    public ContainsKeywordsPredicate(String nameKeyword, String icKeyword) {
        this.nameKeyword = nameKeyword;
        this.icKeyword = icKeyword;
    }

    @Override
    public boolean test(Person person) {
        return StringUtil.containsWordIgnoreCase(person.getName().fullName, nameKeyword)
                && StringUtil.containsWordIgnoreCase(person.getIc().value, icKeyword);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ContainsKeywordsPredicate)) {
            return false;
        }

        ContainsKeywordsPredicate otherContainsKeywordsPredicate = (ContainsKeywordsPredicate) other;
        return nameKeyword.equals(otherContainsKeywordsPredicate.nameKeyword);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", nameKeyword).toString();
    }
}
