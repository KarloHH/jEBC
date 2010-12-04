package de.jebc.ebc.impl;

import de.jebc.ebc.OutPin;

public class StopTrigger<T> extends ProcessImpl<T, T> {

    private OutPin<Void> triggerPin = new BroadcastOutPin<Void>();

    @Override
    protected void process(T message) {
        Result().send(message);
        Trigger().send(null);
    }
    
    public OutPin<Void> Trigger() {
        return triggerPin;
    }
}
