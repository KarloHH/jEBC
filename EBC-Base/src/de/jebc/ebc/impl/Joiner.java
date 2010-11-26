package de.jebc.ebc.impl;

import de.jebc.ebc.InPin;
import de.jebc.ebc.OutPin;

public abstract class Joiner<TInput1, TInput2, TOutput> {

    private OutPin<TOutput> outPin = new SingleOutPin<TOutput>();
    private InPin<TInput2> inPin2 = new InPin<TInput2>() {

        @Override
        public void receive(TInput2 message) {
            data2 = message;
            if (data1 != null)
                send();
        }
    };
    private InPin<TInput1> inPin1 = new InPin<TInput1>() {

        @Override
        public void receive(TInput1 message) {
            data1 = message;
            if (data2 != null)
                send();
        }
    };
    private InPin<Object> resetPin = new InPin<Object>() {
        
        @Override
        public void receive(Object message) {
            data1 = null;
            data2 = null;
        }
    };

    private TInput1 data1 = null;
    private TInput2 data2 = null;

    private void send() {
        Out().send(join(data1, data2));
    }

    protected abstract TOutput join(TInput1 in1, TInput2 in2);

    public InPin<TInput1> In1() {
        return inPin1;
    }

    public InPin<TInput2> In2() {
        return inPin2;
    }

    public OutPin<TOutput> Out() {
        return outPin;
    }
    
    public InPin<Object> Reset() {
        return resetPin;
    }
}
