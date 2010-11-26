package de.jebc.ebc.addressbook.domain.saveaddress;

import de.jebc.ebc.addressbook.data.Query;
import de.jebc.ebc.addressbook.domain.addressdetails.Address;
import de.jebc.ebc.impl.ProcessImpl;

public class GenerateUpdateQuery extends ProcessImpl<Address, Query> {

    @Override
    protected void process(Address address) {
        String command = String.format("UPDATE Adressen SET Category = '%2$s', Name = '%3$s', GivenName = '%4$s'," +
        		"ZipCode = '%5$s', City = '%6$s', Street = '%7$s', Country = '%8$s' " +
        		"WHERE ID = %1$d", 
                address.getId(), address.getCategory().getName(),
                address.getName(), address.getGivenName(), address.getZipCode(),
                address.getCity(), address.getStreet(), address.getCountry());
        Query result = new Query(command);
        Result().send(result);
    }

}
