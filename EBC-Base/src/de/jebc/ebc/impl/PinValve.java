package de.jebc.ebc.impl;

import de.jebc.ebc.InPin;
import de.jebc.ebc.InTrigger;
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
    private InTrigger openPin = new InTrigger() {
        
        @Override
        public void receive() {
            valveOpen = true;
            if (data != null)
                send();
        }
    };
    private InTrigger resetPin = new InTrigger() {

        @Override
        public void receive() {
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
    
    public InTrigger Open() {
        return openPin;
    }
    
    public InTrigger Reset() {
        return resetPin;
    }

    public OutPin<T> Out() {
        return outPin;
    }

}
