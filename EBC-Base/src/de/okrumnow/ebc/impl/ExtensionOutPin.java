package de.okrumnow.ebc.impl;

import de.okrumnow.ebc.InPin;
import de.okrumnow.ebc.OutPin;

public class ExtensionOutPin<T> implements OutPin<T> {

    private InPin<T> inPin;

    public ExtensionOutPin(OutPin<T> pin) {
        pin.connect(new InPin<T>() {

            @Override
            public void receive(T message) {
                transmit(message);
            }
        });
    }

    @Override
    public void connect(InPin<T> pin) {
        inPin = pin;
    }

    @Override
    public void transmit(T message) {
        inPin.receive(message);
    }

}
