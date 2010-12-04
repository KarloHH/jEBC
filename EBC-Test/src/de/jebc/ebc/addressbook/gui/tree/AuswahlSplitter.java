package de.jebc.ebc.addressbook.gui.tree;

import javax.swing.tree.DefaultMutableTreeNode;

import de.jebc.ebc.InPin;
import de.jebc.ebc.OutPin;
import de.jebc.ebc.addressbook.domain.baseadresses.BaseAddressData;
import de.jebc.ebc.impl.SingleOutPin;

public class AuswahlSplitter {

    private OutPin<BaseAddressData> selectPin = new SingleOutPin<BaseAddressData>();
    private OutPin<Boolean> selectedPin = new SingleOutPin<Boolean>();

    private InPin<DefaultMutableTreeNode> inPin = new InPin<DefaultMutableTreeNode>() {

        @Override
        public void receive(DefaultMutableTreeNode node) {
            Object userObject = node.getUserObject();
            if (userObject instanceof BaseAddressData) {
                BaseAddressData selectedAddress = (BaseAddressData) userObject;
                Select().send(selectedAddress);
                Selected().send(true);
            } else {
                Selected().send(false);
            }
        }
    };

    public OutPin<BaseAddressData> Select() {
        return selectPin;
    }

    public OutPin<Boolean> Selected() {
        return selectedPin;
    }

    public InPin<DefaultMutableTreeNode> In() {
        return inPin;
    }
}
