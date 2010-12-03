package de.jebc.ebc.addressbook.gui.detail;

import de.jebc.ebc.OutPin;
import de.jebc.ebc.addressbook.domain.addressdetails.Address;

public interface IDetailsView {

    public abstract void setAddress(Address address);

    public abstract Address getAddress();

    public abstract OutPin<Boolean> Changed();

}