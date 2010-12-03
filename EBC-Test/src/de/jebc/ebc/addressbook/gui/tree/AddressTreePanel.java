package de.jebc.ebc.addressbook.gui.tree;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTree;

import de.jebc.ebc.InPin;
import de.jebc.ebc.InTrigger;
import de.jebc.ebc.OutPin;
import de.jebc.ebc.addressbook.domain.baseadresses.BaseAddressData;
import de.jebc.ebc.impl.SingleOutPin;
import javax.swing.event.TreeSelectionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeModel;

@SuppressWarnings("serial")
public class AddressTreePanel extends JPanel {

    protected BaseAddressData selectedAddress;
    private InTrigger deleteCurrentPin = new InTrigger() {
        
        @Override
        public void receive() {
            if (selectedAddress != null) {
                Delete().send(selectedAddress);
            }
        }
    };
    private OutPin<BaseAddressData> selectPin = new SingleOutPin<BaseAddressData>();
    private OutPin<BaseAddressData> deletePin = new SingleOutPin<BaseAddressData>();
    private InTrigger savedPin = new InTrigger() {
        
        @Override
        public void receive() {
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
            tree.setModel(message);
            tree.clearSelection();
        }
    };


    private JTree tree;    /**
     * Create the panel.
     */
    public AddressTreePanel() {
        setLayout(new BorderLayout(0, 0));
        
        tree = new JTree();
        tree.addTreeSelectionListener(new TreeSelectionListener() {
            public void valueChanged(TreeSelectionEvent event) {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) event.getPath().getLastPathComponent();
                Object userObject = node.getUserObject();
                if (userObject instanceof BaseAddressData) {
                    selectedAddress = (BaseAddressData) userObject;
                    Select().send(selectedAddress);
                }
            }
        });
        add(tree);

    }
    
    protected void setChanged(boolean b) {
        // TODO ???
    }

    public InTrigger DeleteCurrent() {
        return deleteCurrentPin;
    }
    
    public InTrigger Saved() {
        return savedPin;
    }
    
    public InPin<Boolean> Changed() {
        return changedPin;
    }
    
    public OutPin<BaseAddressData> Select() {
        return selectPin;
    }
    
    public OutPin<BaseAddressData> Delete() {
        return deletePin;
    }
    
    public InPin<TreeModel> DisplayTree() {
        return displayTreePin;
    }

}
