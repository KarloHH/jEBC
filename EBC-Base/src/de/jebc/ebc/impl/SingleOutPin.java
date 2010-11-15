package de.jebc.ebc.impl;

import de.jebc.ebc.InPin;
import de.jebc.ebc.OutPin;

public class SingleOutPin<T> implements OutPin<T> {

    private InPin<T> inPin;

    @Override
    public void connect(InPin<T> pin) {
        this.inPin = pin;
    }

    @Override
    public void send(T message) {
        inPin.receive(message);
    }

}
