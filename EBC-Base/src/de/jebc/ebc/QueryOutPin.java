package de.jebc.ebc;

public interface QueryOutPin<T1, T2> {

    void connect(QueryInPin<T1, T2> inPin);

    void send(T1 message, InPin<T2> response);
}
