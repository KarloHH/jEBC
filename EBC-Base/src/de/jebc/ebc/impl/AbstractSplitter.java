package de.jebc.ebc.impl;

import de.jebc.ebc.InPin;
import de.jebc.ebc.OutPin;
import de.jebc.ebc.Splitter;

public abstract class AbstractSplitter<TInput, TOutput1, TOutput2> implements
        Splitter<TInput, TOutput1, TOutput2> {

    private InPin<TInput> inPin = new InPin<TInput>() {

        @Override
        public void receive(TInput message) {
            Out1().transmit(getPart1(message));
            Out2().transmit(getPart2(message));
        }
    };
    private OutPin<TOutput1> out1 = new SingleOutPin<TOutput1>();
    private OutPin<TOutput2> out2 = new SingleOutPin<TOutput2>();

    protected abstract TOutput1 getPart1(TInput message);

    protected abstract TOutput2 getPart2(TInput message);   

    @Override
    public InPin<TInput> In() {
        return inPin;
    }

    @Override
    public OutPin<TOutput1> Out1() {
        return out1;
    }

    @Override
    public OutPin<TOutput2> Out2() {
        return out2;
    }

}
