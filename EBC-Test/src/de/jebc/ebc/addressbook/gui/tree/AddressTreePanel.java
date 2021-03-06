package de.jebc.ebc.addressbook.gui.tree;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTree;

import de.jebc.ebc.InPin;
import de.jebc.ebc.OutPin;
import de.jebc.ebc.addressbook.domain.baseadresses.BaseAddressData;
import de.jebc.ebc.impl.SingleOutPin;
import javax.swing.event.TreeSelectionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeModel;

@SuppressWarnings("serial")
public class AddressTreePanel extends JPanel {

    private InPin<Void> deleteCurrentPin = new InPin<Void>() {

        @Override
        public void receive(Void v) {
            if (selectedNode != null) {
                Object userObject = selectedNode.getUserObject();
                if (userObject instanceof BaseAddressData) {
                    BaseAddressData selectedAddress = (BaseAddressData) userObject;
                    Delete().send(selectedAddress);
                }
            }
        }
    };
    private OutPin<DefaultMutableTreeNode> selectPin = new SingleOutPin<DefaultMutableTreeNode>();
    private OutPin<BaseAddressData> deletePin = new SingleOutPin<BaseAddressData>();
    private InPin<Void> savedPin = new InPin<Void>() {

        @Override
        public void receive(Void v) {
            setChanged(false);
        }
    };
    private InPin<Boolean> changedPin = new InPin<Boolean>() {

        @Override
        public void receive(Boolean message) {
            setChanged(message);
        }
    };
    private InPin<TreeModel> displayTreePin = new InPin<TreeModel>() {

        @Override
        public void receive(TreeModel message) {
            suppressSelection = true;
            tree.clearSelection();
            tree.setModel(message);
            suppressSelection = false;
        }
    };

    private JTree tree;
    private boolean suppressSelection = false;
    protected DefaultMutableTreeNode selectedNode;

    /**
     * Create the panel.
     */
    public AddressTreePanel() {
        setLayout(new BorderLayout(0, 0));

        tree = new JTree();
        tree.addTreeSelectionListener(new TreeSelectionListener() {
            public void valueChanged(TreeSelectionEvent event) {
                if (!suppressSelection) {
                    selectedNode = (DefaultMutableTreeNode) event.getPath()
                            .getLastPathComponent();
                    Select().send(selectedNode);
                }
            }
        });
        add(tree);

    }

    protected void setChanged(boolean b) {
        // TODO ???
    }

    public InPin<Void> DeleteCurrent() {
        return deleteCurrentPin;
    }

    public InPin<Void> Saved() {
        return savedPin;
    }

    public InPin<Boolean> Changed() {
        return changedPin;
    }

    public OutPin<DefaultMutableTreeNode> Select() {
        return selectPin;
    }

    public OutPin<BaseAddressData> Delete() {
        return deletePin;
    }

    public InPin<TreeModel> DisplayTree() {
        return displayTreePin;
    }

}
