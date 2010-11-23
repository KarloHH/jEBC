package de.jebc.ebc.impl;

import de.jebc.ebc.InPin;
import de.jebc.ebc.OutPin;
import de.jebc.ebc.Process;

public abstract class ProcessImpl<TInput, TOutput> implements
        Process<TInput, TOutput> {

    InPin<TInput> startPin = new InPin<TInput>() {

        @Override
        public void receive(final TInput query) {
            process(query);
        }

    };
    private OutPin<TOutput> resultPin = new SingleOutPin<TOutput>();

    protected abstract void process(final TInput query);

    public ProcessImpl() {
        super();
    }

    @Override
    public OutPin<TOutput> Result() {
        return resultPin;
    }

    @Override
    public InPin<TInput> Start() {
        return startPin;
    }

}