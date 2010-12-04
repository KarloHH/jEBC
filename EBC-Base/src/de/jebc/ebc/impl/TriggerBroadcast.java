package de.jebc.ebc.impl;

import de.jebc.ebc.InPin;
import de.jebc.ebc.OutPin;

public class TriggerBroadcast {
    private InPin<Void> inPin = new InPin<Void>() {

        @Override
        public void receive(Void v) {
            outPin.send(v);
        }
    };
    private OutPin<Void> outPin = new BroadcastOutPin<Void>();

    public InPin<Void> In() {
        return inPin;
    }

    public OutPin<Void> Out() {
        return outPin;
    }

}
