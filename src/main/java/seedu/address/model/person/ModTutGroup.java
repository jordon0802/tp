package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.HashMap;
import java.util.Map;


/**
 * Represents a Person's module - tutorial group in ConnectS.
 * Guarantees: immutable; is valid as declared in {@link #isValidModTutGroup(String)} (String)}
 */
public class ModTutGroup {

    public static final String MESSAGE_CONSTRAINTS = "Module - Tutorial Group should only contain "
            + "alphanumeric characters with a dash in between";
    private static final String VALIDATION_REGEX = "^[A-Za-z0-9]+-[A-Za-z0-9]+$";

    private static final Map<String, Map<String, Integer>> moduleMap = new HashMap<>();

    public final String value;

    private final Module module;
    private final Tutorial tutorialGroup;

    /**
     * Constructs a module - tutorial group.
     *
     * @param modTutGroup valid module - tutorial group.
     */
    public ModTutGroup(String modTutGroup) {
        requireNonNull(modTutGroup);
        checkArgument(isValidModTutGroup(modTutGroup), MESSAGE_CONSTRAINTS);
        value = modTutGroup;

        String moduleString = modTutGroup.split("-")[0];
        String tutorialString = modTutGroup.split("-")[1];

        module = new Module(moduleString);
        tutorialGroup = new Tutorial(tutorialString);

        Map<String, Integer> tutorialMap;
        if (!moduleMap.containsKey(moduleString)) {
            tutorialMap = new HashMap<>();
            tutorialMap.put(tutorialString, 1);
        } else {
            tutorialMap = moduleMap.get(moduleString);
            if (moduleMap.get(moduleString).containsKey(tutorialString)) {
                int count = moduleMap.get(moduleString).get(tutorialString);
                tutorialMap.put(tutorialString, count + 1);
            } else {
                tutorialMap.put(tutorialString, 1);
            }
        }
        moduleMap.put(moduleString, tutorialMap);
    }

    public static Map<String, Map<String, Integer>> getModuleMap() {
        return moduleMap;
    }

    public Module getModule() {
        return module;
    }

    public Tutorial getTutorial() {
        return tutorialGroup;
    }

    /**
     * Returns if a given string is a valid module - tutorial group.
     */
    public static boolean isValidModTutGroup(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ModTutGroup)) {
            return false;
        }

        ModTutGroup otherModTutGroup = (ModTutGroup) other;
        return this.module.equals(otherModTutGroup.getModule())
                && this.tutorialGroup.equals(otherModTutGroup.getTutorial());
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
