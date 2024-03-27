package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.AdmissionDate;
import seedu.address.model.person.Dob;
import seedu.address.model.person.Ic;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Ward;
import seedu.address.model.tag.Tag;
import seedu.address.model.person.Remark;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[]{
            new Person(new Name("Alex Yeoh"),
                        getTagSet("Diabetes"),
                        new Dob("01/01/1990"),
                        new Ic("S1234567A"),
                        new AdmissionDate("01/01/2022"),
                        new Ward("Ward A"),
                        new Remark("")),
            new Person(new Name("Bernice Yu"),
                        getTagSet("FallRisk", "Diabetes"),
                        new Dob("01/01/1990"),
                        new Ic("S1234567A"),
                        new AdmissionDate("02/01/2022"),
                        new Ward("Ward B"),
                        new Remark("")),
            new Person(new Name("Charlotte Oliveiro"),
                        getTagSet("FallRisk"),
                        new Dob("01/01/1990"),
                        new Ic("S1234567A"),
                        new AdmissionDate("03/01/2022"),
                        new Ward("Ward C"),
                        new Remark("")),
            new Person(new Name("David Li"),
                        getTagSet("Dementia"),
                        new Dob("01/01/1990"),
                        new Ic("S1234567A"),
                        new AdmissionDate("04/01/2022"),
                        new Ward("Ward D"),
                        new Remark("")),
            new Person(new Name("Irfan Ibrahim"),
                        getTagSet("NPO"),
                        new Dob("01/01/1990"),
                        new Ic("S1234567A"),
                        new AdmissionDate("05/01/2022"),
                        new Ward("Ward E"),
                        new Remark("")),
            new Person(new Name("Roy Balakrishnan"),
                        getTagSet("Dementia"),
                        new Dob("01/01/1990"),
                        new Ic("S1234567A"),
                        new AdmissionDate("14/03/2024"),
                        new Ward("Ward F"),
                        new Remark(""))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
