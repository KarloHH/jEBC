package de.jebc.ebc.addressbook.domain.baseadresses;

import de.jebc.ebc.InPin;
import de.jebc.ebc.OutPin;
import de.jebc.ebc.addressbook.data.Query;
import de.jebc.ebc.impl.SingleOutPin;

public class GenerateQueryForAllBaseAdresses {

    private InPin<Object> trigger = new InPin<Object>() {

        @Override
        public void receive(Object message) {
            Query queryCommand = new Query("Adressen", new String[] { "ID",
                    "Category", "Name", "GivenName" });
            accessDatasource().send(queryCommand);
        }
    };
    private OutPin<Query> outQuery = new SingleOutPin<Query>();

    public InPin<Object> start() {
        return trigger;
    }

    public OutPin<Query> accessDatasource() {
        return outQuery;
    }
}
