package de.jebc.ebc.addressbook.gui.tree;

import javax.swing.JPanel;
import javax.swing.JTree;
import java.awt.BorderLayout;
import javax.swing.event.TreeSelectionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;

@SuppressWarnings("serial")
public class AdressTreePanel extends JPanel {

    /**
     * Create the panel.
     */
    public AdressTreePanel() {
        setLayout(new BorderLayout(0, 0));
        
        JTree tree = new JTree();
        tree.addTreeSelectionListener(new TreeSelectionListener() {
            public void valueChanged(TreeSelectionEvent arg0) {
                MutableTreeNode node = (MutableTreeNode) arg0.getNewLeadSelectionPath().getLastPathComponent();
            }
        });
        add(tree);

    }

}
