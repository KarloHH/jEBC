package de.jebc.ebc.impl;

import java.util.ArrayList;
import java.util.List;

import de.jebc.ebc.InPin;
import de.jebc.ebc.OutPin;

public class BroadcastOutPin<T> implements OutPin<T> {

    private List<InPin<T>> pins = new ArrayList<InPin<T>>();

    @Override
    public void connect(InPin<T> pin) {
        if (!pins.contains(pin)) {
            pins.add(pin);
        }
    }

    @Override
    public void send(T message) {
        for (InPin<T> pin : pins) {
            pin.receive(message);
        }
    }

}
