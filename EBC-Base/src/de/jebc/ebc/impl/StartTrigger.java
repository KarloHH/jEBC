package de.jebc.ebc.impl;

import de.jebc.ebc.OutTrigger;

public class StartTrigger<T> extends ProcessImpl<T, T> {

    private OutTrigger triggerPin = new BroadcastOutTrigger();

    @Override
    protected void process(T message) {
        Trigger().send();
        Result().send(message);
    }
    
    public OutTrigger Trigger() {
        return triggerPin;
    }
}
