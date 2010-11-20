package de.jebc.ebc.addressbook.domain.baseadresses;

import de.jebc.ebc.InPin;
import de.jebc.ebc.OutPin;
import de.jebc.ebc.addressbook.data.Query;
import de.jebc.ebc.addressbook.data.Resultset;
import de.jebc.ebc.impl.SingleOutPin;

public class GenerateQueryForAllBaseAdresses {
    
    private InPin<Object> trigger = new InPin<Object>() {

        @Override
        public void receive(Object message) {
            execute();
        }
    };
    private OutPin<Query> outQuery = new SingleOutPin<Query>();
    private OutPin<Resultset> result = new SingleOutPin<Resultset>();

    public InPin<Object> start() {
        return trigger;
    }
    
    public OutPin<Query> accessDatasource() {
        return outQuery;
    }
    
    public OutPin<Resultset> result() {
        return result;
    }

    protected void execute() {
        Query queryCommand = new Query("Adressen", new String[]{"ID", "Category", "Name", "GivenName"});
        accessDatasource().send(queryCommand);
    }
}
