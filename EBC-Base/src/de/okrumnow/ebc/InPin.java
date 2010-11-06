package de.okrumnow.ebc;

public interface InPin<T> {
    void receive(T message);
}
