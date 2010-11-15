package de.jebc.ebc.impl;

import de.jebc.ebc.InPin;
import de.jebc.ebc.OutPin;

public abstract class Splitter<TInput, TOutput1, TOutput2> {

    private InPin<TInput> inPin = new InPin<TInput>() {

        @Override
        public void receive(TInput message) {
            out1().send(getMessage1(message));
            out2().send(getMessage2(message));
        }
    };
    private OutPin<TOutput1> out1 = new SingleOutPin<TOutput1>();
    private OutPin<TOutput2> out2 = new SingleOutPin<TOutput2>();

    protected abstract TOutput1 getMessage1(TInput message);

    protected abstract TOutput2 getMessage2(TInput message);   

    public InPin<TInput> in() {
        return inPin;
    }

    public OutPin<TOutput1> out1() {
        return out1;
    }

    public OutPin<TOutput2> out2() {
        return out2;
    }

}
