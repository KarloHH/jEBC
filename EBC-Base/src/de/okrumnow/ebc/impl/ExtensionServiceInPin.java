package de.okrumnow.ebc.impl;

import de.okrumnow.ebc.InPin;
import de.okrumnow.ebc.ServiceInPin;

public class ExtensionServiceInPin<T1, T2> implements ServiceInPin<T1, T2> {

    private final ServiceInPin<T1, T2> pin;
    public ExtensionServiceInPin(ServiceInPin<T1, T2> pin) {
        this.pin = pin;
    }
    @Override
    public void receive(T1 message, InPin<T2> response) {
        pin.receive(message, response);
    }

}
