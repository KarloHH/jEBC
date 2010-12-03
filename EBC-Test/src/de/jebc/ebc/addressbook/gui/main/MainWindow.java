package de.jebc.ebc.addressbook.gui.main;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class MainWindow extends JFrame implements IMainWindow {

    private JPanel contentPane;
    private JMenuItem mnuDelete;
    private JMenuItem mnuExit;
    private JMenuItem mnuSave;
    private JMenuItem mnuNewAddress;
    private JSplitPane splitPane;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MainWindow frame = new MainWindow();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public MainWindow() {
        setTitle("Address Book");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu mnuFile = new JMenu("File");
        menuBar.add(mnuFile);

        mnuNewAddress = new JMenuItem("New address...");
        mnuFile.add(mnuNewAddress);

        mnuSave = new JMenuItem("Save");
        mnuFile.add(mnuSave);

        mnuExit = new JMenuItem("Exit");
        mnuFile.add(mnuExit);

        JMenu mnuEdit = new JMenu("Edit");
        menuBar.add(mnuEdit);

        mnuDelete = new JMenuItem("Delete");
        mnuEdit.add(mnuDelete);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        splitPane = new JSplitPane();
        contentPane.add(splitPane, BorderLayout.CENTER);
    }
    
    public void setSplitter(Component left, Component right) {
        splitPane.setLeftComponent(left);
        splitPane.setRightComponent(right);
    }

    /* (non-Javadoc)
     * @see de.jebc.ebc.addressbook.gui.main.IMainWindow#setDeleteAction(javax.swing.Action)
     */
    @Override
    public void setDeleteAction(Action delete) {
        mnuDelete.setAction(delete);
    }

    /* (non-Javadoc)
     * @see de.jebc.ebc.addressbook.gui.main.IMainWindow#setNewAction(javax.swing.Action)
     */
    @Override
    public void setNewAction(Action neww) {
        mnuNewAddress.setAction(neww);
    }

    /* (non-Javadoc)
     * @see de.jebc.ebc.addressbook.gui.main.IMainWindow#setExitAction(javax.swing.Action)
     */
    @Override
    public void setExitAction(Action exit) {
        mnuExit.setAction(exit);
    }

    /* (non-Javadoc)
     * @see de.jebc.ebc.addressbook.gui.main.IMainWindow#setSaveAction(javax.swing.Action)
     */
    @Override
    public void setSaveAction(Action save) {
        mnuSave.setAction(save);
    }

}
