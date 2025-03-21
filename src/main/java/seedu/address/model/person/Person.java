package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final Email email;

    // Data fields
    private final TelegramHandle telegramHandle;
    private final Set<ModTutGroup> modTutGroups = new HashSet<>();


    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Email email, TelegramHandle telegramHandle, Set<ModTutGroup> modTutGroups) {
        requireAllNonNull(name, email, telegramHandle, modTutGroups);
        this.name = name;
        this.email = email;
        this.telegramHandle = telegramHandle;
        this.modTutGroups.addAll(modTutGroups);
    }

    public Name getName() {
        return name;
    }

    public Email getEmail() {
        return email;
    }

    public TelegramHandle getTelegramHandle() {
        return telegramHandle;
    }

    /**
     * Returns an immutable mod - tutorial group set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<ModTutGroup> getModTutGroups() {
        return Collections.unmodifiableSet(modTutGroups);
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getName().equals(getName());
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return name.equals(otherPerson.name)
                && telegramHandle.equals(otherPerson.telegramHandle)
                && email.equals(otherPerson.email)
                && modTutGroups.equals(otherPerson.modTutGroups);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, email, telegramHandle, modTutGroups);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("email", email)
                .add("telegramHandle", telegramHandle)
                .add("modTutGroups", modTutGroups)
                .toString();
    }

}
