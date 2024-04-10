package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.AdmissionDate;
import seedu.address.model.person.Dob;
import seedu.address.model.person.Ic;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Remark;
import seedu.address.model.person.Ward;
import seedu.address.model.tag.Tag;


/**
 * Jackson-friendly version of {@link Person}.
 */
class JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String name;
    private final List<JsonAdaptedTag> tags = new ArrayList<>();
    private final String dob;
    private final String ic;
    private final String admissionDate;
    private final String ward;
    private final String remark;
    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("name") String name,
            @JsonProperty("tags") List<JsonAdaptedTag> tags, @JsonProperty("dob") String dob,
            @JsonProperty("ic") String ic, @JsonProperty("admissionDate") String admissionDate,
            @JsonProperty("ward") String ward, @JsonProperty("remark") String remark) {
        this.name = name;
        if (tags != null) {
            this.tags.addAll(tags);
        }
        this.dob = dob;
        this.ic = ic;
        this.admissionDate = admissionDate;
        this.ward = ward;
        this.remark = remark;
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedPerson(Person source) {
        name = source.getName().toString();
        tags.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
        dob = source.getDob().toString();
        ic = source.getIc().toString();
        admissionDate = source.getAdmissionDate().toString();
        ward = source.getWard().toString();
        remark = source.getRemark().toString();
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Person toModelType() throws IllegalValueException {
        final List<Tag> personTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tags) {
            personTags.add(tag.toModelType());
        }

        final Name modelName = validateAndConvertName();
        final Set<Tag> modelTags = new HashSet<>(personTags);
        final Dob modelDob = validateAndConvertDob();
        final Ic modelIc = validateAndConvertIc();
        final AdmissionDate modelAdmissionDate = validateAndConvertAdmissionDate();
        final Ward modelWard = validateAndConvertWard();
        final Remark modelRemark = validateAndConvertRemark();

        isDobBeforeAdmissionDate(modelDob, modelAdmissionDate);

        return new Person(modelName, modelTags, modelDob, modelIc,
                modelAdmissionDate, modelWard, modelRemark);
    }

    /**
     * Validates and converts the name of the person.
     *
     * @return Name object.
     * @throws IllegalValueException if the name is invalid.
     */
    private Name validateAndConvertName() throws IllegalValueException {
        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(name);
    }

    /**
     * Validates and converts the date of birth of the person.
     *
     * @return Dob object.
     * @throws IllegalValueException if the date of birth is invalid.
     */
    private Dob validateAndConvertDob() throws IllegalValueException {
        if (dob == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Dob.class.getSimpleName()));
        }
        if (!Dob.isValidDate(dob)) {
            throw new IllegalValueException(Dob.MESSAGE_CONSTRAINTS_FORMAT);
        }
        if (!Dob.isValidDob(dob)) {
            throw new IllegalValueException(Dob.MESSAGE_CONSTRAINTS_OCCURRENCE);
        }
        return new Dob(dob);
    }

    /**
     * Validates and converts the IC of the person.
     *
     * @return Ic object.
     * @throws IllegalValueException if the IC is invalid.
     */
    private Ic validateAndConvertIc() throws IllegalValueException {
        if (ic == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Ic.class.getSimpleName()));
        }
        if (!Ic.isValidIc(ic)) {
            throw new IllegalValueException(Ic.MESSAGE_CONSTRAINTS);
        }
        return new Ic(ic);
    }

    /**
     * Validates and converts the admission date of the person.
     *
     * @return AdmissionDate object.
     * @throws IllegalValueException if the admission date is invalid.
     */
    private AdmissionDate validateAndConvertAdmissionDate() throws IllegalValueException {
        if (admissionDate == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    AdmissionDate.class.getSimpleName()));
        }
        if (!AdmissionDate.isValidDate(admissionDate)) {
            throw new IllegalValueException(AdmissionDate.MESSAGE_CONSTRAINTS_FORMAT);
        }
        if (!AdmissionDate.isValidAdmissionDate(admissionDate)) {
            throw new IllegalValueException(AdmissionDate.MESSAGE_CONSTRAINTS_OCCURRENCE);
        }
        return new AdmissionDate(admissionDate);
    }

    /**
     * Validates and converts the ward of the person.
     *
     * @return Ward object.
     * @throws IllegalValueException if the ward is invalid.
     */
    private Ward validateAndConvertWard() throws IllegalValueException {
        if (ward == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Ward.class.getSimpleName()));
        }
        if (!Ward.isValidWard(ward)) {
            throw new IllegalValueException(Ward.MESSAGE_CONSTRAINTS);
        }
        return new Ward(ward);
    }

    /**
     * Validates and converts the remark of the person.
     *
     * @return Remark object.
     * @throws IllegalValueException if the remark is invalid.
     */
    private Remark validateAndConvertRemark() throws IllegalValueException {
        if (remark == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Remark.class.getSimpleName()));
        }
        return new Remark(remark);
    }

    private void isDobBeforeAdmissionDate(Dob dob, AdmissionDate admissionDate) throws IllegalValueException {
        if (dob.date.isAfter(admissionDate.date)) {
            throw new IllegalValueException(Dob.MESSAGE_CONSTRAINTS_OCCURRENCE);
        }
    }
}
