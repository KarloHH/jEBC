package de.jebc.ebc.addressbook;

import java.awt.EventQueue;
import java.util.Arrays;
import java.util.Enumeration;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.jebc.ebc.addressbook.activities.DeleteCurrentAddress;
import de.jebc.ebc.addressbook.activities.DisplayAddressDetails;
import de.jebc.ebc.addressbook.activities.DisplayTreeOfAllAdresses;
import de.jebc.ebc.addressbook.activities.SaveAddressData;
import de.jebc.ebc.addressbook.activities.SaveNewAddressData;
import de.jebc.ebc.addressbook.data.ConnectionFactory;
import de.jebc.ebc.addressbook.data.jdbc.ExecuteDatasource;
import de.jebc.ebc.addressbook.data.jdbc.JdbcConnectionFactory;
import de.jebc.ebc.addressbook.data.jdbc.JdbcExecuteDatasourceQuery;
import de.jebc.ebc.addressbook.domain.addressdetails.Address;
import de.jebc.ebc.addressbook.domain.baseadresses.BaseAddressData;
import de.jebc.ebc.addressbook.gui.GuiBoard;
import de.jebc.ebc.addressbook.gui.detail.DetailPanel;
import de.jebc.ebc.addressbook.gui.detail.DetailsController;
import de.jebc.ebc.addressbook.gui.main.MainController;
import de.jebc.ebc.addressbook.gui.main.MainWindow;
import de.jebc.ebc.addressbook.gui.tree.AddressTreePanel;
import de.jebc.ebc.impl.Board;
import de.jebc.ebc.impl.Monitor;
import de.jebc.logging.pin.LogBoardPin;
import de.jebc.logging.pin.LogDebugPin;
import de.jebc.logging.pin.LogInfoPin;

public class Main extends Board {

    private Logger log = LoggerFactory.getLogger(Main.class);

    private GuiBoard gui;
    private DisplayTreeOfAllAdresses displayTree;
    private DeleteCurrentAddress deleteCurrent;
    private SaveAddressData save;
    private SaveNewAddressData saveNew;
    private DisplayAddressDetails displayDetails;

    private Monitor<BaseAddressData> logDeleteAddress;

    private Monitor<TreeModel> logDisplayTree;

    private Monitor<Address> logSaveAddress;

    private Monitor<BaseAddressData> logSelected;

    /**
     * @param args
     */
    public static void main(final String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MainWindow frame = new MainWindow();
                    Main main = new Main();
                    main.configure(frame, args);
                    frame.setVisible(true);
                    main.run();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    protected void run() {
        log.info("Start Program");
        displayTree.Start().receive();
    }

    private void configure(MainWindow frame, String[] args) throws Exception {
        createComponents(frame, args);
        createLoggers();
        // wire
        connect(deleteCurrent.Result(), displayTree.Start());
        monitor(gui.Delete(), deleteCurrent.Start(), logDeleteAddress);
        monitor(displayTree.Tree(), gui.DisplayTree(), logDisplayTree);
        monitor(gui.Save(), save.Start(), logSaveAddress);
        connect(save.Result(), gui.Saved());
        monitor(gui.SaveNew(), saveNew.Start(), logSaveAddress);
        connect(saveNew.Result(), gui.Display());
        connect(saveNew.Completed(), displayTree.Start());
        connect(displayDetails.Result(), gui.Display());
        monitor(gui.Select(), displayDetails.start(), logSelected);
    }

    @SuppressWarnings("unchecked")
    private void createLoggers() {
        logDeleteAddress = new LogInfoPin<BaseAddressData>(log) {

            @Override
            protected String getLogMessage(BaseAddressData message) {
                return "Delete address " + message;
            }
        };

        logDisplayTree = new LogBoardPin<TreeModel>(Arrays.asList(
                new LogInfoPin<TreeModel>(log) {

                    @Override
                    protected String getLogMessage(TreeModel message) {
                        return "Display the tree";
                    }
                }, new LogDebugPin<TreeModel>(log) {

                    final String newLine = System.getProperty("line.separator");

                    @Override
                    protected String getLogMessage(TreeModel message) {
                        StringBuilder sb = new StringBuilder();
                        DefaultMutableTreeNode node = (DefaultMutableTreeNode) message
                                .getRoot();
                        logNode("", node, sb);
                        sb.append("--------------------------------");
                        return sb.toString();
                    }

                    private void logNode(String prefix,
                            DefaultMutableTreeNode node, StringBuilder sb) {
                        sb.append(prefix)
                                .append(node.getUserObject().toString())
                                .append(newLine);
                        Enumeration<DefaultMutableTreeNode> children = node
                                .children();
                        while (children.hasMoreElements())
                            logNode("- " + prefix, children.nextElement(), sb);
                    }
                }));

        logSaveAddress = new LogInfoPin<Address>(log) {

            @Override
            protected String getLogMessage(Address message) {
                return "Saving address id #" + message.getId();
            }
        };

        logSelected = new LogInfoPin<BaseAddressData>(log) {

            @Override
            protected String getLogMessage(BaseAddressData message) {
                return "Display details for " + message.getName();
            }
        };
    }

    protected void createComponents(MainWindow frame, String[] args)
            throws Exception {
        JdbcConnectionFactory connection = new JdbcConnectionFactory(
                ConfigureConnection.Configure(args));
        gui = createGuiBoard(frame);
        displayTree = new DisplayTreeOfAllAdresses(getDatasource(connection));
        deleteCurrent = new DeleteCurrentAddress(getDatasource(connection));
        save = new SaveAddressData(getDatasource(connection));
        saveNew = new SaveNewAddressData(getDatasource(connection));
        displayDetails = new DisplayAddressDetails(getDatasource(connection));
    }

    protected GuiBoard createGuiBoard(MainWindow frame) {
        MainController main = new MainController(frame);
        DetailPanel detailPanel = new DetailPanel();
        DetailsController details = new DetailsController(detailPanel);
        AddressTreePanel tree = new AddressTreePanel();
        frame.setSplitter(tree, detailPanel);
        GuiBoard gui = new GuiBoard(details, main, tree);
        return gui;
    }

    protected ExecuteDatasource getDatasource(ConnectionFactory connection) {
        return new JdbcExecuteDatasourceQuery(connection);
    }

}
