package de.jebc.ebc.addressbook;

import java.awt.EventQueue;

import de.jebc.ebc.addressbook.activities.DeleteCurrentAddress;
import de.jebc.ebc.addressbook.activities.DisplayAddressDetails;
import de.jebc.ebc.addressbook.activities.DisplayTreeOfAllAdresses;
import de.jebc.ebc.addressbook.activities.SaveAddressData;
import de.jebc.ebc.addressbook.activities.SaveNewAddressData;
import de.jebc.ebc.addressbook.data.jdbc.JdbcExecuteDatasourceQuery;
import de.jebc.ebc.addressbook.gui.GuiBoard;
import de.jebc.ebc.addressbook.gui.detail.DetailPanel;
import de.jebc.ebc.addressbook.gui.detail.DetailsController;
import de.jebc.ebc.addressbook.gui.main.MainController;
import de.jebc.ebc.addressbook.gui.main.MainWindow;
import de.jebc.ebc.addressbook.gui.tree.AddressTreePanel;
import de.jebc.ebc.impl.Board;

public class Main extends Board {

    private static GuiBoard gui;
    private static DisplayTreeOfAllAdresses displayTree;
    private static DeleteCurrentAddress deleteCurrent;
    private static SaveAddressData save;
    private static SaveNewAddressData saveNew;
    private static DisplayAddressDetails displayDetails;
    private static ConfigureConnection connection;

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
        displayTree.start().receive();
    }

    private void configure(MainWindow frame, String[] args) throws Exception {
        MainController main = new MainController(frame);
        DetailPanel detailPanel = new DetailPanel();
        DetailsController details = new DetailsController(detailPanel);
        AddressTreePanel tree = new AddressTreePanel();
        frame.setSplitter(tree, detailPanel);
        gui = new GuiBoard(details, main, tree);
        displayTree = new DisplayTreeOfAllAdresses(new JdbcExecuteDatasourceQuery());
        deleteCurrent = new DeleteCurrentAddress(new JdbcExecuteDatasourceQuery());
        save = new SaveAddressData(new JdbcExecuteDatasourceQuery());
        saveNew = new SaveNewAddressData(new JdbcExecuteDatasourceQuery());
        displayDetails = new DisplayAddressDetails(new JdbcExecuteDatasourceQuery());
        connection = new ConfigureConnection(args);
        // wire
        connect(deleteCurrent.connection(),connection.ProvideConnection());
        connect(save.Connection(),connection.ProvideConnection());
        connect(saveNew.Connection(),connection.ProvideConnection());
        connect(displayDetails.Connection(),connection.ProvideConnection());
        connect(displayTree.connection(), connection.ProvideConnection());
        connect(deleteCurrent.tree(), gui.DisplayTree());
        connect(gui.Delete(), deleteCurrent.in());
        connect(displayTree.tree(), gui.DisplayTree());
        connect(gui.Save(), save.Start());
        connect(save.Result(), gui.Saved());
        connect(gui.SaveNew(), saveNew.Start());
        connect(saveNew.Result(), gui.Display());
        connect(saveNew.Completed(), displayTree.start());
        connect(displayDetails.result(), gui.Display());
        connect(gui.Select(), displayDetails.start());
    }

}
