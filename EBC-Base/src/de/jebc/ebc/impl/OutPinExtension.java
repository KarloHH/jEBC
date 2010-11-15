package de.jebc.ebc.impl;

import de.jebc.ebc.InPin;
import de.jebc.ebc.OutPin;

public class OutPinExtension<T> implements OutPin<T> {

    private OutPin<T> outPin;

    public OutPinExtension(OutPin<T> pin) {
        outPin=pin;
    }

    @Override
    public void connect(InPin<T> pin) {
        outPin.connect(pin);
    }

    @Override
    public void send(T message) {
        outPin.send(message);
    }

}
