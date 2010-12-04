package de.jebc.ebc.impl;

import de.jebc.ebc.InPin;
import de.jebc.ebc.OutPin;

public class PinValve<T> {

    private boolean valveOpen;
    private T data = null;

    private InPin<T> inPin = new InPin<T>() {

        @Override
        public void receive(T message) {
            data = message;
            if (valveOpen)
                send();
        }
    };
    private OutPin<T> outPin = new BroadcastOutPin<T>();
    private InPin<Void> openPin = new InPin<Void>() {
        
        @Override
        public void receive(Void v) {
            valveOpen = true;
            if (data != null)
                send();
        }
    };
    private InPin<Void> resetPin = new InPin<Void>() {

        @Override
        public void receive(Void v) {
            data = null;
            valveOpen = false;
        }
    };

    protected void send() {
        outPin.send(data);
    }

    public InPin<T> In() {
        return inPin;
    }
    
    public InPin<Void> Open() {
        return openPin;
    }
    
    public InPin<Void> Reset() {
        return resetPin;
    }

    public OutPin<T> Out() {
        return outPin;
    }

}
