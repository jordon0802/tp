package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Comparator;
import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniquePersonList persons;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        persons = new UniquePersonList();
    }

    public AddressBook() {
    }

    /**
     * Creates an AddressBook using the Persons in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setPersons(List<Person> persons) {
        this.persons.setPersons(persons);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());
    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in ConnectS.
     */
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return persons.contains(person);
    }

    /**
     * Returns true if there's a person with the same identity as {@code editedPerson}, excluding {@code personToEdit}.
     */
    public boolean hasEditedPerson(Person personToEdit, Person editedPerson) {
        requireAllNonNull(personToEdit, editedPerson);
        return persons.containsExcluding(editedPerson, personToEdit);
    }

    /**
     * Adds a person to ConnectS.
     * The person must not already exist in ConnectS.
     */
    public void addPerson(Person p) {
        persons.add(p);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the ConnectS.
     * The person identity of {@code editedPerson} must not be the same as another existing person in ConnectS.
     */
    public void setPerson(Person target, Person editedPerson) {
        requireNonNull(editedPerson);

        persons.setPerson(target, editedPerson);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in ConnectS.
     */
    public void removePerson(Person key) {
        persons.remove(key);
    }

    /**
     * Sorts the persons in the address book using the given {@code comparator}.
     */
    public void sort(Comparator<Person> comparator) {
        requireNonNull(comparator);
        persons.sort(comparator);
    }

    /**
     * Replaces the person {@code target} in the list with {@code pinnedPerson},
     * and pins the person to the top of the list.
     * {@code target} and {@code pinnedPerson} must be the same person with different pin status.
     */
    public void pin(Person target, Person pinnedPerson) {
        requireAllNonNull(target, pinnedPerson);
        persons.pin(target, pinnedPerson);
    }

    /**
     * Replaces the {@code target} in the list with {@code unpinnedPerson}, and unpins the person.
     * The person is then put at the bottom of the list.
     * {@code target} and {@code unpinnedPerson} must be the same person with different pin status.
     */
    public void unpin(Person target, Person unpinnedPerson) {
        requireAllNonNull(target, unpinnedPerson);
        persons.unpin(target, unpinnedPerson);
    }

    /// / util methods

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("persons", persons)
                .toString();
    }

    @Override
    public ObservableList<Person> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddressBook)) {
            return false;
        }

        AddressBook otherAddressBook = (AddressBook) other;
        return persons.equals(otherAddressBook.persons);
    }

    @Override
    public int hashCode() {
        return persons.hashCode();
    }
}
