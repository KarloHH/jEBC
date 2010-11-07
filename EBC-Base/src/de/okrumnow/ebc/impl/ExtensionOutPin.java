package de.okrumnow.ebc.impl;

import de.okrumnow.ebc.InPin;
import de.okrumnow.ebc.OutPin;

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
