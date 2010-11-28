package de.jebc.ebc.addressbook.gui.main;

import javax.swing.Action;

public interface IMainWindow {

    public abstract void setDeleteAction(Action delete);

    public abstract void setNewAction(Action neww);

    public abstract void setExitAction(Action exit);

    public abstract void setSaveAction(Action save);

}