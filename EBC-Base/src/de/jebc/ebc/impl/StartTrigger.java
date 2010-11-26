package de.jebc.ebc.impl;

import de.jebc.ebc.OutPin;

public class StartTrigger<T> extends ProcessImpl<T, T> {

    private OutPin<Object> triggerPin = new BroadcastOutPin<Object>();

    @Override
    protected void process(T message) {
        Trigger().send(null);
        Result().send(message);
    }
    
    public OutPin<Object> Trigger() {
        return triggerPin;
    }
}
