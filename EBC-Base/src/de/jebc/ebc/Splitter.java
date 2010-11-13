package de.jebc.ebc;

public interface Splitter<TInput, TOutput1, TOutput2> {

    public InPin<TInput> in();

    public OutPin<TOutput1> out1();

    public OutPin<TOutput2> out2();

}
