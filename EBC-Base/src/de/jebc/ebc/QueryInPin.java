package de.jebc.ebc;


public interface QueryInPin<T1, T2> {

    void receive(T1 message, InPin<T2> response);
}
