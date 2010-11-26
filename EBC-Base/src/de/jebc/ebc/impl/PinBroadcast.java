package de.jebc.ebc.impl;

import de.jebc.ebc.InPin;
import de.jebc.ebc.OutPin;

public class PinBroadcast<T> {

    private InPin<T> inPin = new InPin<T>() {

        @Override
        public void receive(T message) {
            outPin.send(message);
        }
    };
    private OutPin<T> outPin = new BroadcastOutPin<T>();

    public InPin<T> In() {
        return inPin;
    }

    public OutPin<T> Out() {
        return outPin;
    }

}
