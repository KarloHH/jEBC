package de.jebc.ebc.addressbook;

import de.jebc.ebc.addressbook.data.Query;
import de.jebc.ebc.addressbook.domain.addressdetails.Address;
import de.jebc.ebc.impl.ProcessImpl;

public class GenerateInsertCommand extends ProcessImpl<Address, Query> {

    @Override
    protected void process(Address message) {
        String command = String
                .format("INSERT INTO Adressen (Category, Name, GivenName, ZipCode, City, Street, Country) "
                        + "VALUES ('%1$s', '%2$s', '%3$s', '%4$s', '%5$s', '%6$s', '%7$s')",
                        message.getCategory().getName(), message.getName(),
                        message.getGivenName(), message.getZipCode(),
                        message.getCity(), message.getStreet(),
                        message.getCountry());
        Query result = new Query(command);
        Result().send(result);
    }

}
