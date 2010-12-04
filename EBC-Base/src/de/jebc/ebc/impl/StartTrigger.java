package de.jebc.ebc.impl;

import de.jebc.ebc.OutPin;

public class StartTrigger<T> extends ProcessImpl<T, T> {

    private OutPin<Void> triggerPin = new BroadcastOutPin<Void>();

    @Override
    protected void process(T message) {
        Trigger().send(null);
        Result().send(message);
    }
    
    public OutPin<Void> Trigger() {
        return triggerPin;
    }
}
