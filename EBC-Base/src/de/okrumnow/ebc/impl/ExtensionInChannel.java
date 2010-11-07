package de.okrumnow.ebc.impl;

import de.okrumnow.ebc.InPin;
import de.okrumnow.ebc.InChannel;

public class ExtensionInChannel<T1, T2> implements InChannel<T1, T2> {

    private final InChannel<T1, T2> pin;
    public ExtensionInChannel(InChannel<T1, T2> pin) {
        this.pin = pin;
    }
    @Override
    public void receive(T1 message, InPin<T2> response) {
        pin.receive(message, response);
    }

}
