package de.jebc.ebc.impl;

import de.jebc.ebc.InPin;
import de.jebc.ebc.OutPin;

public class ExtensionOutPin<T> implements OutPin<T> {

    private OutPin<T> outPin;

    public ExtensionOutPin(OutPin<T> pin) {
        outPin=pin;
    }

    @Override
    public void connect(InPin<T> pin) {
        outPin.connect(pin);
    }

    @Override
    public void transmit(T message) {
        outPin.transmit(message);
    }

}
