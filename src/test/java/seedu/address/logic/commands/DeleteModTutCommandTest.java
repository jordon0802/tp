package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODTUT_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODTUT_BOB;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.getSortedTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.ModTutGroup;
import seedu.address.testutil.ModuleMapUtil;


public class DeleteModTutCommandTest {
    private Model model = new ModelManager(getSortedTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validModTut_success() {
        ModTutGroup modTutGroupToDelete = ALICE.getModTutGroups().iterator().next();
        DeleteModTutCommand deleteModTutCommand = new DeleteModTutCommand(modTutGroupToDelete);

        ModTutGroup.setModuleMap(model.getFilteredPersonList());

        ModuleMapUtil.setModuleMap(model.getFilteredPersonList());
        ModuleMapUtil.deleteTutorial(modTutGroupToDelete.toString());

        try {
            deleteModTutCommand.execute(model);
        } catch (CommandException e) {
            fail(e.toString());
        }

        assertEquals(ModuleMapUtil.getModuleMap(), ModTutGroup.getModuleMap());
    }

    @Test
    public void equals() {
        DeleteModTutCommand deleteModTutFirstCommand = new DeleteModTutCommand(new ModTutGroup(VALID_MODTUT_AMY));
        DeleteModTutCommand deleteModTutSecondCommand = new DeleteModTutCommand(new ModTutGroup(VALID_MODTUT_BOB));
        //same object -> returns true
        assertTrue(deleteModTutFirstCommand.equals(deleteModTutFirstCommand));

        //same values -> returns true
        DeleteModTutCommand deleteModTutFirstCommandCopy = new DeleteModTutCommand(new ModTutGroup(VALID_MODTUT_AMY));
        assertTrue(deleteModTutFirstCommand.equals(deleteModTutFirstCommandCopy));


        //different types -> returns false
        assertFalse(deleteModTutFirstCommand.equals(1));

        //null -> returns false
        assertFalse(deleteModTutFirstCommand.equals(null));

        //different person -> returns false
        assertFalse(deleteModTutFirstCommand.equals(deleteModTutSecondCommand));
    }
}
