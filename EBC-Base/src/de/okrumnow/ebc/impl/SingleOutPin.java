package de.okrumnow.ebc.impl;

import de.okrumnow.ebc.InPin;
import de.okrumnow.ebc.OutPin;

public class SingleOutPin<T> implements OutPin<T> {

    private InPin<T> inPin;

    @Override
    public void connect(InPin<T> pin) {
        this.inPin = pin;
    }

    @Override
    public void transmit(T message) {
        inPin.receive(message);
    }

}
