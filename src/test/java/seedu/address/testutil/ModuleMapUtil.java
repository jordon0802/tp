package seedu.address.testutil;

import static java.util.Objects.requireNonNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.person.ModTutGroup;
import seedu.address.model.person.Person;

/**
 * Utility class for the module map found in ModTutGroup
 */
public class ModuleMapUtil {
    private static final Map<String, Map<String, Integer>> moduleMap = new HashMap<>();

    private ModuleMapUtil() {
    }

    public static Map<String, Map<String, Integer>> setModuleMap(List<Person> personList) {
        requireNonNull(personList);
        moduleMap.clear();
        for (Person person : personList) {
            for (ModTutGroup modTutGroup : person.getModTutGroups()) {
                String modTutGroupString = modTutGroup.toString();
                String moduleString = modTutGroupString.split("-")[0];
                String tutorialString = modTutGroupString.split("-")[1];

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
        }
        return moduleMap;
    }

    public static void deleteModule(String moduleName) {
        moduleMap.remove(moduleName);
    }

    /**
     * Deletes {@code tutorial} from the {@code moduleMap}.
     *
     * @param modTutGroup name of the tutorial to be removed
     */
    public static void deleteTutorial(String modTutGroup) {
        requireNonNull(modTutGroup);
        assert StringUtil.isUpperCase(modTutGroup) : "Module - Tutorial Group should be in uppercase";

        String moduleString = modTutGroup.split("-")[0];
        String tutorialString = modTutGroup.split("-")[1];

        if (moduleMap.containsKey(moduleString)) {
            moduleMap.get(moduleString).remove(tutorialString);
        }
    }

    /**
     * Decrements the count or removes the {@code tutorial} from the {@code moduleMap}. If the {@code tutorial} removed
     * is the last {@code tutorial} in the {@code module}, the {@code module} will be removed from the {@code moduleMap}
     * as well.
     *
     * @param modTutGroup name of the tutorial to be decremented/removed
     */
    public static void decreaseTutorialCount(String modTutGroup) {
        requireNonNull(modTutGroup);
        assert StringUtil.isUpperCase(modTutGroup) : "Module - Tutorial Group should be in uppercase";

        String moduleString = modTutGroup.split("-")[0];
        String tutorialString = modTutGroup.split("-")[1];

        if (moduleMap.containsKey(moduleString)) {
            Map<String, Integer> tutorialMap = moduleMap.get(moduleString);
            if (moduleMap.get(moduleString).containsKey(tutorialString)) {
                int count = moduleMap.get(moduleString).get(tutorialString);
                if (count <= 1) {
                    tutorialMap.remove(tutorialString);
                    if (tutorialMap.isEmpty()) {
                        moduleMap.remove(moduleString);
                    }
                } else {
                    tutorialMap.put(tutorialString, count - 1);
                }
            }
        }
    }

    public static Map<String, Map<String, Integer>> getModuleMap() {
        return moduleMap;
    }
}
