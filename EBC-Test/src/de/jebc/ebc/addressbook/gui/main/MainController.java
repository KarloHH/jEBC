package de.jebc.ebc.addressbook.gui.main;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import de.jebc.ebc.InTrigger;
import de.jebc.ebc.OutTrigger;
import de.jebc.ebc.impl.BroadcastOutTrigger;

@SuppressWarnings("serial")
public class MainController {
    
    private class SaveAction extends AbstractAction {

        private final MainController mainController;

        public SaveAction(MainController mainController) {
            this.mainController = mainController;
        }

        @Override
        public void actionPerformed(ActionEvent arg0) {
            mainController.Save().send();
        }
        
    }
    
    private class ExitAction extends AbstractAction {

        private final MainController mainController;

        public ExitAction(MainController mainController) {
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
            this.mainController = mainController;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            mainController.New().send();
        }
        
    }
    
    private class DeleteAction extends AbstractAction {

        private final MainController mainController;

        public DeleteAction(MainController mainController) {
            this.mainController = mainController;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            mainController.Delete().send();
        }
        
    }
    
    private OutTrigger savePin = new BroadcastOutTrigger();
    private OutTrigger newPin = new BroadcastOutTrigger();
    private OutTrigger deletePin = new BroadcastOutTrigger();
    private InTrigger changedPin = new InTrigger() {
        
        @Override
        public void receive() {
            save.setEnabled(true);
        }
    };
    private InTrigger savedPin = new InTrigger() {
        
        @Override
        public void receive() {
            save.setEnabled(false);
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

    public OutTrigger Save() {
        return savePin;
    }
    
    public OutTrigger New() {
        return newPin;
    }
    
    public OutTrigger Delete() {
        return deletePin;
    }

    public InTrigger Changed() {
        return changedPin;
    }
    
    public InTrigger Saved() {
        return savedPin;
    }
}
