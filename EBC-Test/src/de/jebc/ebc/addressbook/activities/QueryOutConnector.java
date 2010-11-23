package de.jebc.ebc.addressbook.activities;

import de.jebc.ebc.InPin;
import de.jebc.ebc.QueryInPin;
import de.jebc.ebc.impl.QueryPinImpl;

public class QueryOutConnector<T1, T2> extends QueryPinImpl<T1, T2> {

    private QueryInPin<T1, T2> inPin = new QueryInPin<T1, T2>() {

        @Override
        public void receive(T1 message, InPin<T2> response) {
            send(message, response);
        }
    };

    public QueryInPin<T1, T2> in() {
        return inPin;
    }
}
