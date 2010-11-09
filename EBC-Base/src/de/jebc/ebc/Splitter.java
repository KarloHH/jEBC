package de.jebc.ebc;

public interface Splitter<TInput, TOutput1, TOutput2> {

    public InPin<TInput> In();

    public OutPin<TOutput1> Out1();

    public OutPin<TOutput2> Out2();

}
