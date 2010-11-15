package de.jebc.ebc;

public interface OutPin<T> {
    void connect(InPin<T> pin);

    void send(T message);
}
