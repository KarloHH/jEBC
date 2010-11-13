package de.jebc.ebc;

public interface PinFilter<T> {

    public abstract InPin<T> in();

    public abstract OutPin<T> out();

}
