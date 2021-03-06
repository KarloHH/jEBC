package de.jebc.ebc.addressbook.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;

import de.jebc.ebc.addressbook.domain.baseadresses.BaseAddressData;
import de.jebc.ebc.impl.ProcessImpl;

public class ConvertAddressesIntoTreeStructure extends
        ProcessImpl<List<BaseAddressData>, TreeModel> {

    @Override
    protected void process(List<BaseAddressData> message) {
        TreeModel tree = convertListToTree(message);
        Result().send(tree);
    }

    private TreeModel convertListToTree(List<BaseAddressData> message) {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode(
                "All Addresses");
        DefaultTreeModel tree = new DefaultTreeModel(root);
        Map<AddressCategory, DefaultMutableTreeNode> categoryNodes = new HashMap<AddressCategory, DefaultMutableTreeNode>();
        //
        for (BaseAddressData addressData : message) {
            AddressCategory category = addressData.getCategory();
            if (!categoryNodes.containsKey(category)) {
                DefaultMutableTreeNode node = new DefaultMutableTreeNode(
                        category);
                tree.insertNodeInto(node, root, tree.getChildCount(root));
                categoryNodes.put(category, node);
            }
            DefaultMutableTreeNode node = new DefaultMutableTreeNode(
                    addressData);
            DefaultMutableTreeNode parent = categoryNodes.get(category);
            tree.insertNodeInto(node, parent, tree.getChildCount(parent));
        }
        //
        return tree;
    }

}
