package de.jebc.ebc.impl;

import de.jebc.ebc.InTrigger;
import de.jebc.ebc.OutTrigger;

public class TriggerBroadcast {
    private InTrigger inPin = new InTrigger() {

        @Override
        public void receive() {
            outPin.send();
        }
    };
    private OutTrigger outPin = new BroadcastOutTrigger();

    public InTrigger In() {
        return inPin;
    }

    public OutTrigger Out() {
        return outPin;
    }

}
