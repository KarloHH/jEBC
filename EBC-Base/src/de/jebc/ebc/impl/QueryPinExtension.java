package de.jebc.ebc.impl;

import de.jebc.ebc.QueryPin;
import de.jebc.ebc.ServicePin;
import de.jebc.ebc.InPin;

public class QueryPinExtension<T1, T2> implements QueryPin<T1, T2> {

    private final QueryPin<T1, T2> pin;
    public QueryPinExtension(QueryPin<T1, T2> pin) {
        this.pin = pin;
    }
    @Override
    public void connect(ServicePin<T1, T2> responsePin) {
        pin.connect(responsePin);
    }
    @Override
    public void send(T1 message, InPin<T2> response) {
        pin.send(message, response);
    }

}
