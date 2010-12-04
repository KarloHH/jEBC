package de.jebc.ebc.addressbook.gui.main;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import de.jebc.ebc.InPin;
import de.jebc.ebc.OutPin;
import de.jebc.ebc.impl.BroadcastOutPin;

@SuppressWarnings("serial")
public class MainController {

    private class SaveAction extends AbstractAction {

        private final MainController mainController;

        public SaveAction(MainController mainController) {
            super("Save");
            this.mainController = mainController;
        }

        @Override
        public void actionPerformed(ActionEvent arg0) {
            mainController.Save().send(null);
        }

    }

    private class ExitAction extends AbstractAction {

        private final MainController mainController;

        public ExitAction(MainController mainController) {
            super("Exit");
            this.mainController = mainController;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            mainController.exit();
        }

    }

    private class NewAction extends AbstractAction {

        private final MainController mainController;

        public NewAction(MainController mainController) {
            super("New");
            this.mainController = mainController;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            mainController.New().send(null);
        }

    }

    private class DeleteAction extends AbstractAction {

        private final MainController mainController;

        public DeleteAction(MainController mainController) {
            super("Delete");
            this.mainController = mainController;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            mainController.Delete().send(null);
        }

    }

    private OutPin<Void> savePin = new BroadcastOutPin<Void>();
    private OutPin<Void> newPin = new BroadcastOutPin<Void>();
    private OutPin<Void> deletePin = new BroadcastOutPin<Void>();
    private InPin<Boolean> changedPin = new InPin<Boolean>() {

        @Override
        public void receive(Boolean message) {
            save.setEnabled(message);
        }
    };
    private InPin<Void> savedPin = new InPin<Void>() {

        @Override
        public void receive(Void v) {
            save.setEnabled(false);
        }
    };
    private InPin<Boolean> addressSelectedPin = new InPin<Boolean>() {

        @Override
        public void receive(Boolean message) {
            delete.setEnabled(message);
        }
    };

    private DeleteAction delete;
    private ExitAction exit;
    private NewAction neww;
    private SaveAction save;

    public MainController(IMainWindow view) {
        delete = new DeleteAction(this);
        view.setDeleteAction(delete);
        exit = new ExitAction(this);
        view.setExitAction(exit);
        neww = new NewAction(this);
        view.setNewAction(neww);
        save = new SaveAction(this);
        view.setSaveAction(save);
        //
        save.setEnabled(false);
        delete.setEnabled(false);
    }

    public void exit() {

    }

    public OutPin<Void> Save() {
        return savePin;
    }

    public OutPin<Void> New() {
        return newPin;
    }

    public OutPin<Void> Delete() {
        return deletePin;
    }

    public InPin<Boolean> Changed() {
        return changedPin;
    }

    public InPin<Void> Saved() {
        return savedPin;
    }

    public InPin<Boolean> AddressSelected() {
        return addressSelectedPin;
    }
}
