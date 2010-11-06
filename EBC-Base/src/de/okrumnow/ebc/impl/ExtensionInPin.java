package de.okrumnow.ebc.impl;

import de.okrumnow.ebc.InPin;

public class ExtensionInPin<T> implements InPin<T> {

    private final InPin<T> pin;

    public ExtensionInPin(InPin<T> pin) {
        this.pin = pin;
    }
    
    @Override
    public void receive(T message) {
        pin.receive(message);
    }

}
