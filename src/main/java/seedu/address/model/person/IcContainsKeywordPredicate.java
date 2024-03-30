package seedu.address.model.person;

import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Person}'s {@code Ic} matches any of the keywords given.
 */
public class IcContainsKeywordPredicate implements Predicate<Person> {
    private final String keyword;

    /**
     * Instantiates the keyword.
     *
     * @param keyword to be found
     */
    public IcContainsKeywordPredicate(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean test(Person person) {
        return StringUtil.containsWordIgnoreCase(person.getIc().value, keyword);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof IcContainsKeywordPredicate)) {
            return false;
        }

        IcContainsKeywordPredicate otherIcContainKeywordsPredicate = (IcContainsKeywordPredicate) other;
        return keyword.equals(otherIcContainKeywordsPredicate.keyword);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("ic keyword", keyword).toString();
    }
}
