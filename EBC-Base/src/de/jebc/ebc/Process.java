package de.jebc.ebc;

public interface Process<TInput, TOutput> {

    public abstract OutPin<TOutput> Result();

    public abstract InPin<TInput> Start();

}