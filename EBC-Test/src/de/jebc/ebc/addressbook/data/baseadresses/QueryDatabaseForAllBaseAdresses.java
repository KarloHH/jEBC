package de.jebc.ebc.addressbook.data.baseadresses;

import de.jebc.ebc.InPin;
import de.jebc.ebc.OutPin;
import de.jebc.ebc.QueryOutPin;
import de.jebc.ebc.addressbook.data.Resultset;
import de.jebc.ebc.impl.QueryPinImpl;
import de.jebc.ebc.impl.SingleOutPin;

public class QueryDatabaseForAllBaseAdresses {
    
    private InPin<Object> trigger = new InPin<Object>() {

        @Override
        public void receive(Object message) {
            execute();
        }
    };
    private QueryOutPin<String, Resultset> outQuery = new QueryPinImpl<String, Resultset>();
    private OutPin<Resultset> result = new SingleOutPin<Resultset>();

    public InPin<Object> start() {
        return trigger;
    }
    
    public QueryOutPin<String, Resultset> queryDatabase() {
        return outQuery;
    }
    
    public OutPin<Resultset> result() {
        return result;
    }

    protected void execute() {
        String queryCommand = "SELECT Category, Name, GivenName FROM Adresses";
        queryDatabase().send(queryCommand, new InPin<Resultset>() {

            @Override
            public void receive(Resultset message) {
                result().send(message);
            }
        });
    }
}
