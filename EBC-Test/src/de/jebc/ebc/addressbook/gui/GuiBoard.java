package de.jebc.ebc.addressbook.gui;

import javax.swing.tree.TreeModel;

import de.jebc.ebc.InPin;
import de.jebc.ebc.OutPin;
import de.jebc.ebc.addressbook.domain.addressdetails.Address;
import de.jebc.ebc.addressbook.domain.baseadresses.BaseAddressData;
import de.jebc.ebc.addressbook.gui.detail.DetailsController;
import de.jebc.ebc.addressbook.gui.main.MainController;
import de.jebc.ebc.addressbook.gui.tree.AddressTreePanel;
import de.jebc.ebc.addressbook.gui.tree.AuswahlSplitter;
import de.jebc.ebc.impl.Board;
import de.jebc.ebc.impl.TriggerBroadcast;

public class GuiBoard extends Board {

    private final DetailsController details;
    private final AddressTreePanel tree;
    private final TriggerBroadcast broadcast = new TriggerBroadcast();
    private final AuswahlSplitter splitter = new AuswahlSplitter();

    public GuiBoard(DetailsController details, MainController main,
            AddressTreePanel tree) {
        this.details = details;
        this.tree = tree;
        // wire
        connect(main.Delete(), tree.DeleteCurrent());
        connect(broadcast.Out(), main.Saved());
        connect(details.Changed(), main.Changed());
        connect(main.New(), details.New());
        connect(main.Save(), details.Save());
        connect(broadcast.Out(), tree.Saved());
        connect(details.Changed(), tree.Changed());
        connect(broadcast.Out(), details.Saved());
        connect(tree.Select(), splitter.In());
        connect(splitter.Selected(), main.AddressSelected());
    }

    public InPin<TreeModel> DisplayTree() {
        return tree.DisplayTree();
    }

    public OutPin<BaseAddressData> Select() {
        return splitter.Select();
    }

    public OutPin<BaseAddressData> Delete() {
        return tree.Delete();
    }

    public OutPin<Address> Save() {
        return details.Update();
    }

    public OutPin<Address> SaveNew() {
        return details.SaveNew();
    }

    public InPin<Address> Display() {
        return details.Display();
    }

    public InPin<Void> Saved() {
        return broadcast.In();
    }
}
