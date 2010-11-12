package de.jebc.ebc;

public interface ChannelMonitor<T1, T2> {

    public abstract InChannel<T1, T2> MonitorIn();

    public abstract OutChannel<T1, T2> MonitorOut();

}
