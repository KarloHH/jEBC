package de.jebc.ebc;

public interface ChannelFilter<T1, T2> {

    public abstract InChannel<T1, T2> in();

    public abstract OutChannel<T1, T2> out();

}
