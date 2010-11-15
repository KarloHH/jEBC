package de.jebc.ebc;


public interface QueryPin<T1, T2> {

    void connect(ServicePin<T1, T2> inPin);
    
    void send(T1 message, InPin<T2> response);
}
