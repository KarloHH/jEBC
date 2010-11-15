package de.jebc.ebc;

public interface InPin<T> {
    void receive(T message);
}
