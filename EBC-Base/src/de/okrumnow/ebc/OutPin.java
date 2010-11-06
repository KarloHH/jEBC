package de.okrumnow.ebc;

public interface OutPin<T> {
    void connect(InPin<T> pin);

    void transmit(T message);
}
