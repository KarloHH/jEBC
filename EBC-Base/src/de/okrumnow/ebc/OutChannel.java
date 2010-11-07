package de.okrumnow.ebc;


public interface OutChannel<T1, T2> {

    void connect(InChannel<T1, T2> inPin);
    
    void transmit(T1 message, InPin<T2> response);
}
