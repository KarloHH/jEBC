package de.jebc.ebc.impl;

import de.jebc.ebc.OutPin;

public class StopTrigger<T> extends ProcessImpl<T, T> {

    private OutPin<Object> triggerPin = new BroadcastOutPin<Object>();

    @Override
    protected void process(T message) {
        Result().send(message);
        Trigger().send(null);
    }
    
    public OutPin<Object> Trigger() {
        return triggerPin;
    }
}
