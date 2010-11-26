package de.jebc.ebc.addressbook.domain.deleteaddress;

import de.jebc.ebc.addressbook.data.Query;
import de.jebc.ebc.addressbook.domain.baseadresses.BaseAddressData;
import de.jebc.ebc.impl.ProcessImpl;

public class GenerateDeleteCommand extends ProcessImpl<BaseAddressData, Query> {

    @Override
    protected void process(BaseAddressData message) {
        final String command = "DELETE FROM Adressen WHERE ID = "
                + message.getId();
        Query result = new Query(command);
        Result().send(result);
    }

}
