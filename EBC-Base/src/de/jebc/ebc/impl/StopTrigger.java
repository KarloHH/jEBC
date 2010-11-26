package de.jebc.ebc.impl;

import de.jebc.ebc.OutTrigger;

public class StopTrigger<T> extends ProcessImpl<T, T> {

    private OutTrigger triggerPin = new BroadcastOutTrigger();

    @Override
    protected void process(T message) {
        Result().send(message);
        Trigger().send();
    }
    
    public OutTrigger Trigger() {
        return triggerPin;
    }
}
