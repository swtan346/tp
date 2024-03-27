package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class ListKeywordsPredicate implements Predicate<Person> {
    private final List<String> tagKeywords;
    private final String wardKeyword;

    /**
     * Constructs a ListKeywordsPredicate object.
     * @param tagKeywords List of tag keywords to filter the list of persons.
     * @param wardKeyword Ward keyword to filter the list of persons.
     */
    public ListKeywordsPredicate(List<String> tagKeywords, String wardKeyword) {
        this.tagKeywords = tagKeywords;
        this.wardKeyword = wardKeyword;
    }

    @Override
    public boolean test(Person person) {
        // If wardKeyword is not empty, check if the person's ward matches the wardKeyword
        if (!wardKeyword.isEmpty() && !tagKeywords.isEmpty()) {
            return tagKeywords.stream()
                    .allMatch(keyword -> person.getTags().stream()
                            .anyMatch(tag ->
                                    StringUtil.containsWordIgnoreCase(tag.tagName, keyword))
                            && StringUtil.containsWordIgnoreCase(person.getWard().toString(), wardKeyword));

        } else if (wardKeyword.isEmpty()) {
            return tagKeywords.stream()
                    .allMatch(keyword -> person.getTags().stream()
                            .anyMatch(tag -> StringUtil.containsWordIgnoreCase(tag.tagName, keyword)));

        } else {
            return StringUtil.containsWordIgnoreCase(person.getWard().toString(), wardKeyword);
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ListKeywordsPredicate)) {
            return false;
        }

        ListKeywordsPredicate otherListKeywordsPredicate = (ListKeywordsPredicate) other;
        return tagKeywords.equals(otherListKeywordsPredicate.tagKeywords)
                && wardKeyword.equals(otherListKeywordsPredicate.wardKeyword);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("tag", tagKeywords)
                .add("ward", wardKeyword)
                .toString();
    }

    /**
     * Returns a string representation of the keywords.
     * @return String representation of the keywords.
     */
    public String getKeywordsString() {
        String tags = "\ntags: " + tagKeywords.toString()
                .replace("[", "")
                .replace("]", "");
        String ward = "\nward: " + wardKeyword;
        return tags + ward;
    }
}
