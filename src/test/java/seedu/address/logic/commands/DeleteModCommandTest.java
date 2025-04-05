package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MOD_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MOD_BOB;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.getSortedTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.ModTutGroup;
import seedu.address.model.person.Module;
import seedu.address.testutil.ModuleMapUtil;


public class DeleteModCommandTest {
    private Model model = new ModelManager(getSortedTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validMod_success() {
        Module moduleToDelete = ALICE.getModTutGroups().iterator().next().getModule();
        DeleteModCommand deleteModCommand = new DeleteModCommand(moduleToDelete.toString());

        ModTutGroup.setModuleMap(model.getFilteredPersonList());

        ModuleMapUtil.setModuleMap(model.getFilteredPersonList());
        ModuleMapUtil.deleteModule(moduleToDelete.toString());

        try {
            deleteModCommand.execute(model);
        } catch (CommandException e) {
            fail(e.toString());
        }

        assertEquals(ModuleMapUtil.getModuleMap(), ModTutGroup.getModuleMap());
    }

    @Test
    public void equals() {
        DeleteModCommand deleteModFirstCommand = new DeleteModCommand(VALID_MOD_AMY);
        DeleteModCommand deleteModSecondCommand = new DeleteModCommand(VALID_MOD_BOB);
        //same object -> returns true
        assertTrue(deleteModFirstCommand.equals(deleteModFirstCommand));

        //same values -> returns true
        DeleteModCommand deleteModFirstCommandCopy = new DeleteModCommand(VALID_MOD_AMY);
        assertTrue(deleteModFirstCommand.equals(deleteModFirstCommandCopy));


        //different types -> returns false
        assertFalse(deleteModFirstCommand.equals(1));

        //null -> returns false
        assertFalse(deleteModFirstCommand.equals(null));

        //different person -> returns false
        assertFalse(deleteModFirstCommand.equals(deleteModSecondCommand));
    }
}
