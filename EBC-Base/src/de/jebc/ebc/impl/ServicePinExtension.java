package de.jebc.ebc.impl;

import de.jebc.ebc.ServicePin;
import de.jebc.ebc.InPin;

public class ServicePinExtension<T1, T2> implements ServicePin<T1, T2> {

    private final ServicePin<T1, T2> pin;
    public ServicePinExtension(ServicePin<T1, T2> pin) {
        this.pin = pin;
    }
    @Override
    public void receive(T1 message, InPin<T2> response) {
        pin.receive(message, response);
    }

}
