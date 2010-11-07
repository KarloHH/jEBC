package de.okrumnow.ebc.impl;

import java.util.ArrayList;
import java.util.List;

import de.okrumnow.ebc.InPin;
import de.okrumnow.ebc.ServiceInPin;
import de.okrumnow.ebc.ServiceOutPin;

public class ServiceOutPinImpl<T1, T2> implements ServiceOutPin<T1, T2> {

    List<ServiceInPin<T1, T2>> pins = new ArrayList<ServiceInPin<T1,T2>>();
    
    @Override
    public void connect(ServiceInPin<T1, T2> inPin) {
        if (!pins.contains(inPin))
            pins.add(inPin);
    }

    @Override
    public void transmit(T1 message, InPin<T2> response) {
        for (ServiceInPin<T1, T2> pin : pins) {
            pin.receive(message, response);
        }
    }

}
