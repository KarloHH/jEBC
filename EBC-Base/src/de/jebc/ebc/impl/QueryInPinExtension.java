package de.jebc.ebc.impl;

import de.jebc.ebc.QueryInPin;
import de.jebc.ebc.InPin;

public class QueryInPinExtension<T1, T2> implements QueryInPin<T1, T2> {

    private final QueryInPin<T1, T2> pin;
    public QueryInPinExtension(QueryInPin<T1, T2> pin) {
        this.pin = pin;
    }
    @Override
    public void receive(T1 message, InPin<T2> response) {
        pin.receive(message, response);
    }

}
