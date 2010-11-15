package de.jebc.ebc.impl;

import de.jebc.ebc.QueryPin;
import de.jebc.ebc.InPin;
import de.jebc.ebc.ServicePin;

public class QueryPinImpl<T1, T2> implements QueryPin<T1, T2> {

    private ServicePin<T1, T2> pin;

    @Override
    public void connect(ServicePin<T1, T2> inPin) {
        pin = inPin;
    }

    @Override
    public void send(T1 message, InPin<T2> response) {
        pin.receive(message, response);
    }

}
