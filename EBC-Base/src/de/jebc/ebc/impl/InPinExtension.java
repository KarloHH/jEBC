package de.jebc.ebc.impl;

import de.jebc.ebc.InPin;

public class InPinExtension<T> implements InPin<T> {

    private final InPin<T> pin;

    public InPinExtension(InPin<T> pin) {
        this.pin = pin;
    }
    
    @Override
    public void receive(T message) {
        pin.receive(message);
    }

}
