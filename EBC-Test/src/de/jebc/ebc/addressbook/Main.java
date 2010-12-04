package de.jebc.ebc.addressbook;

import java.awt.EventQueue;

import de.jebc.ebc.addressbook.activities.DeleteCurrentAddress;
import de.jebc.ebc.addressbook.activities.DisplayAddressDetails;
import de.jebc.ebc.addressbook.activities.DisplayTreeOfAllAdresses;
import de.jebc.ebc.addressbook.activities.SaveAddressData;
import de.jebc.ebc.addressbook.activities.SaveNewAddressData;
import de.jebc.ebc.addressbook.data.ConnectionFactory;
import de.jebc.ebc.addressbook.data.jdbc.ExecuteDatasource;
import de.jebc.ebc.addressbook.data.jdbc.JdbcConnectionFactory;
import de.jebc.ebc.addressbook.data.jdbc.JdbcExecuteDatasourceQuery;
import de.jebc.ebc.addressbook.gui.GuiBoard;
import de.jebc.ebc.addressbook.gui.detail.DetailPanel;
import de.jebc.ebc.addressbook.gui.detail.DetailsController;
import de.jebc.ebc.addressbook.gui.main.MainController;
import de.jebc.ebc.addressbook.gui.main.MainWindow;
import de.jebc.ebc.addressbook.gui.tree.AddressTreePanel;
import de.jebc.ebc.impl.Board;

public class Main extends Board {

    private GuiBoard gui;
    private DisplayTreeOfAllAdresses displayTree;
    private DeleteCurrentAddress deleteCurrent;
    private SaveAddressData save;
    private SaveNewAddressData saveNew;
    private DisplayAddressDetails displayDetails;

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
        displayTree.Start().receive();
    }

    private void configure(MainWindow frame, String[] args) throws Exception {
        createComponents(frame, args);
        // wire
        connect(deleteCurrent.Result(), displayTree.Start());
        connect(gui.Delete(), deleteCurrent.Start());
        connect(displayTree.Tree(), gui.DisplayTree());
        connect(gui.Save(), save.Start());
        connect(save.Result(), gui.Saved());
        connect(gui.SaveNew(), saveNew.Start());
        connect(saveNew.Result(), gui.Display());
        connect(saveNew.Completed(), displayTree.Start());
        connect(displayDetails.Result(), gui.Display());
        connect(gui.Select(), displayDetails.start());
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
