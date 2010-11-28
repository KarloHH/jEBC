package de.jebc.ebc.addressbook.gui.detail;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class DetailPanel extends JPanel {
    private JTextField txtGivenName;
    private JTextField txtLastName;
    private JTextField txtZipCode;
    private JTextField txtCity;
    private JTextField txtStreet;
    private JTextField txtCountry;
    private JTextField txtCategory;

    /**
     * Create the panel.
     */
    public DetailPanel() {
        setLayout(new MigLayout("", "[][][grow][][grow]", "[][][][][][][]"));
        
        JLabel lblGivenName = new JLabel("Given Name");
        add(lblGivenName, "cell 1 1,alignx trailing");
        
        txtGivenName = new JTextField();
        add(txtGivenName, "flowx,cell 2 1,growx");
        txtGivenName.setColumns(10);
        
        JLabel lblLastName = new JLabel("Last Name");
        add(lblLastName, "cell 3 1");
        
        txtLastName = new JTextField();
        add(txtLastName, "cell 4 1,growx");
        txtLastName.setColumns(10);
        
        JLabel lblZipCode = new JLabel("Zip code");
        add(lblZipCode, "cell 1 3,alignx trailing");
        
        txtZipCode = new JTextField();
        add(txtZipCode, "cell 2 3");
        txtZipCode.setColumns(6);
        
        JLabel lblCity = new JLabel("City");
        add(lblCity, "cell 3 3,alignx trailing");
        
        txtCity = new JTextField();
        add(txtCity, "cell 4 3,growx");
        txtCity.setColumns(10);
        
        JLabel lblStreet = new JLabel("Street");
        add(lblStreet, "cell 1 4,alignx trailing");
        
        txtStreet = new JTextField();
        add(txtStreet, "cell 2 4 3 1,growx");
        txtStreet.setColumns(10);
        
        JLabel lblCountry = new JLabel("Country");
        add(lblCountry, "cell 1 5,alignx trailing");
        
        txtCountry = new JTextField();
        add(txtCountry, "cell 2 5 3 1,growx");
        txtCountry.setColumns(10);
        
        JLabel lblCategory = new JLabel("Category");
        add(lblCategory, "cell 1 6,alignx trailing");
        
        txtCategory = new JTextField();
        add(txtCategory, "cell 2 6,growx");
        txtCategory.setColumns(10);

    }

}
