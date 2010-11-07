package de.okrumnow.ebc.impl;

import java.util.ArrayList;
import java.util.List;

import de.okrumnow.ebc.InPin;
import de.okrumnow.ebc.InChannel;
import de.okrumnow.ebc.OutChannel;

public class OutChannelImpl<T1, T2> implements OutChannel<T1, T2> {

    List<InChannel<T1, T2>> pins = new ArrayList<InChannel<T1,T2>>();
    
    @Override
    public void connect(InChannel<T1, T2> inPin) {
        if (!pins.contains(inPin))
            pins.add(inPin);
    }

    @Override
    public void transmit(T1 message, InPin<T2> response) {
        for (InChannel<T1, T2> pin : pins) {
            pin.receive(message, response);
        }
    }

}
