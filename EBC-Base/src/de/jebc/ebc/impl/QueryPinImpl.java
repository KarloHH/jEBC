package de.jebc.ebc.impl;

import de.jebc.ebc.QueryOutPin;
import de.jebc.ebc.InPin;
import de.jebc.ebc.QueryInPin;

public class QueryPinImpl<T1, T2> implements QueryOutPin<T1, T2> {

    private QueryInPin<T1, T2> pin;

    @Override
    public void connect(QueryInPin<T1, T2> inPin) {
        pin = inPin;
    }

    @Override
    public void send(T1 message, InPin<T2> response) {
        pin.receive(message, response);
    }

}
