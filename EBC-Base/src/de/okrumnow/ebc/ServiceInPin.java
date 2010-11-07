package de.okrumnow.ebc;


public interface ServiceInPin<T1, T2> {

    void receive(T1 message, InPin<T2> response);
}
