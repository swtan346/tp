package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.person.AdmissionDate;
import seedu.address.model.person.Dob;
import seedu.address.model.person.Ic;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Ward;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;
import seedu.address.model.person.Remark;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_DOB = "01/01/1990";
    public static final String DEFAULT_IC = "S1234567A";
    public static final String DEFAULT_ADMISSION_DATE = "01/01/2020";
    public static final String DEFAULT_WARD = "A1";
    public static final String DEFAULT_REMARK = "";

    private Name name;
    private Set<Tag> tags;
    private Dob dob;
    private Ic ic;
    private AdmissionDate admissionDate;
    private Ward ward;
    private Remark remark;
    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        tags = new HashSet<>();
        dob = new Dob(DEFAULT_DOB);
        ic = new Ic(DEFAULT_IC);
        admissionDate = new AdmissionDate(DEFAULT_ADMISSION_DATE);
        ward = new Ward(DEFAULT_WARD);
        remark = new Remark(DEFAULT_REMARK);
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        tags = new HashSet<>(personToCopy.getTags());
        dob = personToCopy.getDob();
        ic = personToCopy.getIc();
        admissionDate = personToCopy.getAdmissionDate();
        ward = personToCopy.getWard();
        remark = personToCopy.getRemark();
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Dob} of the {@code Person} that we are building.
     */
    public PersonBuilder withDob(String dob) {
        this.dob = new Dob(dob);
        return this;
    }

    /**
     * Sets the {@code Ic} of the {@code Person} that we are building.
     */
    public PersonBuilder withIc(String ic) {
        this.ic = new Ic(ic);
        return this;
    }

    /**
     * Sets the {@code AdmissionDate} of the {@code Person} that we are building.
     */
    public PersonBuilder withAdmissionDate(String admissionDate) {
        this.admissionDate = new AdmissionDate(admissionDate);
        return this;
    }

    /**
     * Sets the {@code Ward} of the {@code Person} that we are building.
     */
    public PersonBuilder withWard(String ward) {
        this.ward = new Ward(ward);
        return this;
    }

    /**
     * Sets the {@code Remark} of the {@code Person} that we are building.
     **/
    public PersonBuilder withRemark(String remark) {
        this.remark = new Remark(remark);
        return this;
    }

    public Person build() {
        return new Person(name, tags, dob, ic, admissionDate, ward, remark);
    }

}
