package de.jebc.ebc;

public interface PinMonitor<T> {

    public abstract InPin<T> MonitorIn();

    public abstract OutPin<T> MonitorOut();

}
