package de.jebc.ebc.addressbook.domain.addressdetails;

import de.jebc.ebc.InPin;
import de.jebc.ebc.OutPin;
import de.jebc.ebc.addressbook.data.Query;
import de.jebc.ebc.addressbook.domain.baseadresses.BaseAddressData;
import de.jebc.ebc.impl.SingleOutPin;

public class GenerateQueryForAddress {

    private InPin<BaseAddressData> trigger = new InPin<BaseAddressData>() {

        @Override
        public void receive(BaseAddressData message) {
            Query queryCommand = new Query("SELECT * FROM Adressen WHERE ID = "
                    + message.getId());
            accessDatasource().send(queryCommand);
        }
    };

    private OutPin<Query> outQuery = new SingleOutPin<Query>();

    public InPin<BaseAddressData> start() {
        return trigger;
    }

    public OutPin<Query> accessDatasource() {
        return outQuery;
    }
}
