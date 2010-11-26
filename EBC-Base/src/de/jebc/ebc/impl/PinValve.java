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
    private InPin<Object> openPin = new InPin<Object>() {
        
        @Override
        public void receive(Object message) {
            valveOpen = true;
            if (data != null)
                send();
        }
    };
    private InPin<Object> resetPin = new InPin<Object>() {

        @Override
        public void receive(Object message) {
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
    
    public InPin<Object> Open() {
        return openPin;
    }
    
    public InPin<Object> Reset() {
        return resetPin;
    }

    public OutPin<T> Out() {
        return outPin;
    }

}
