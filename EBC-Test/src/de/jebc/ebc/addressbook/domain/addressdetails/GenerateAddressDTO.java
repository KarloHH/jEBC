package de.jebc.ebc.addressbook.domain.addressdetails;

import de.jebc.ebc.addressbook.data.DataException;
import de.jebc.ebc.addressbook.data.Resultset;
import de.jebc.ebc.addressbook.domain.AddressCategory;
import de.jebc.ebc.impl.ProcessImpl;

public class GenerateAddressDTO extends ProcessImpl<Resultset, Address> {

    @Override
    protected void process(Resultset rs) {
        try {
            rs.next();
            Address dto = new Address(rs.getInt("ID"), new AddressCategory(
                    rs.getString("Category")), rs.getString("Name"),
                    rs.getString("GivenName"), rs.getString("ZipCode"),
                    rs.getString("City"), rs.getString("Street"),
                    rs.getString("Country"));
            Result().send(dto);
        } catch (DataException e) {
            // TODO handle exception
            e.printStackTrace();
        }
    }
}
