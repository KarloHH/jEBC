package de.jebc.ebc.addressbook.domain.addressdetails;

import de.jebc.ebc.addressbook.data.Query;
import de.jebc.ebc.addressbook.domain.baseadresses.BaseAddressData;
import de.jebc.ebc.impl.ProcessImpl;

public class GenerateQueryForAddress extends ProcessImpl<BaseAddressData, Query> {

    protected void process(BaseAddressData message) {
        Query queryCommand = new Query("SELECT * FROM Adressen WHERE ID = "
                + message.getId());
        Result().send(queryCommand);
    }
}
