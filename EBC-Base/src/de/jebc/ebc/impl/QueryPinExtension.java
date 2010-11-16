package de.jebc.ebc.impl;

import de.jebc.ebc.QueryOutPin;
import de.jebc.ebc.QueryInPin;
import de.jebc.ebc.InPin;

public class QueryPinExtension<T1, T2> implements QueryOutPin<T1, T2> {

    private final QueryOutPin<T1, T2> pin;
    public QueryPinExtension(QueryOutPin<T1, T2> pin) {
        this.pin = pin;
    }
    @Override
    public void connect(QueryInPin<T1, T2> responsePin) {
        pin.connect(responsePin);
    }
    @Override
    public void send(T1 message, InPin<T2> response) {
        pin.send(message, response);
    }

}
