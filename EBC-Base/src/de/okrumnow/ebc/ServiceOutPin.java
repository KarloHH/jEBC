package de.okrumnow.ebc;


public interface ServiceOutPin<T1, T2> {

    void connect(ServiceInPin<T1, T2> inPin);
    
    void transmit(T1 message, InPin<T2> response);
}
