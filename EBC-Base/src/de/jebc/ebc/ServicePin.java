package de.jebc.ebc;


public interface ServicePin<T1, T2> {

    void receive(T1 message, InPin<T2> response);
}
