package de.jebc.ebc.addressbook.gui.detail;

import java.awt.event.KeyAdapter;
import java.util.Observable;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;

import de.jebc.ebc.addressbook.domain.AddressCategory;
import de.jebc.ebc.addressbook.domain.addressdetails.Address;

@SuppressWarnings("serial")
public class DetailPanel extends JPanel implements IDetailsView {
    private JTextField txtGivenName;
    private JTextField txtLastName;
    private JTextField txtZipCode;
    private JTextField txtCity;
    private JTextField txtStreet;
    private JTextField txtCountry;
    private JTextField txtCategory;
    private int id;
    private Address original;
    private Observable changeObservable = new Observable();
    private Boolean changed = null;

    private KeyAdapter keyTyped = new KeyAdapter() {

        public void keyTyped(java.awt.event.KeyEvent e) {
            checkChanged();
        };
    };

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
        txtGivenName.addKeyListener(keyTyped);

        JLabel lblLastName = new JLabel("Last Name");
        add(lblLastName, "cell 3 1");

        txtLastName = new JTextField();
        add(txtLastName, "cell 4 1,growx");
        txtLastName.setColumns(10);
        txtLastName.addKeyListener(keyTyped);

        JLabel lblZipCode = new JLabel("Zip code");
        add(lblZipCode, "cell 1 3,alignx trailing");

        txtZipCode = new JTextField();
        add(txtZipCode, "cell 2 3");
        txtZipCode.setColumns(6);
        txtZipCode.addKeyListener(keyTyped);

        JLabel lblCity = new JLabel("City");
        add(lblCity, "cell 3 3,alignx trailing");

        txtCity = new JTextField();
        add(txtCity, "cell 4 3,growx");
        txtCity.setColumns(10);
        txtCity.addKeyListener(keyTyped);

        JLabel lblStreet = new JLabel("Street");
        add(lblStreet, "cell 1 4,alignx trailing");

        txtStreet = new JTextField();
        add(txtStreet, "cell 2 4 3 1,growx");
        txtStreet.setColumns(10);
        txtStreet.addKeyListener(keyTyped);

        JLabel lblCountry = new JLabel("Country");
        add(lblCountry, "cell 1 5,alignx trailing");

        txtCountry = new JTextField();
        add(txtCountry, "cell 2 5 3 1,growx");
        txtCountry.setColumns(10);
        txtCountry.addKeyListener(keyTyped);

        JLabel lblCategory = new JLabel("Category");
        add(lblCategory, "cell 1 6,alignx trailing");

        txtCategory = new JTextField();
        add(txtCategory, "cell 2 6,growx");
        txtCategory.setColumns(10);
        txtCategory.addKeyListener(keyTyped);

    }

    protected void checkChanged() {
        setChanged(original == getAddress());
    }

    private void setChanged(boolean newValue) {
        if (newValue != changed) {
            changed = newValue;
            changeObservable.notifyObservers(changed);
        }
    }

    /* (non-Javadoc)
     * @see de.jebc.ebc.addressbook.gui.detail.IDetailsView#setAddress(de.jebc.ebc.addressbook.domain.addressdetails.Address)
     */
    @Override
    public void setAddress(Address address) {
        original = address;
        txtCategory.setText(address.getCategory().getName());
        txtCity.setText(address.getCity());
        txtCountry.setText(address.getCountry());
        txtGivenName.setText(address.getGivenName());
        txtLastName.setText(address.getName());
        txtStreet.setText(address.getStreet());
        txtZipCode.setText(address.getZipCode());
        id = address.getId();
        setChanged(false);
    }

    /* (non-Javadoc)
     * @see de.jebc.ebc.addressbook.gui.detail.IDetailsView#getAddress()
     */
    @Override
    public Address getAddress() {
        Address result = new Address(id, new AddressCategory(
                txtCategory.getText()), txtLastName.getText(),
                txtGivenName.getText(), txtZipCode.getText(),
                txtCity.getText(), txtStreet.getText(), txtCountry.getText());
        return result;
    }

    /* (non-Javadoc)
     * @see de.jebc.ebc.addressbook.gui.detail.IDetailsView#changed()
     */
    @Override
    public Observable changed() {
        return changeObservable;
    }
}
