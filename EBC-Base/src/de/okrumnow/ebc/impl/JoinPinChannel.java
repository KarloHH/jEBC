package de.okrumnow.ebc.impl;

import de.okrumnow.ebc.InPin;
import de.okrumnow.ebc.OutPin;
import de.okrumnow.ebc.InChannel;

public class JoinPinChannel<T1, T2> implements InChannel<T1, T2> {

    private InPin<T1> inPin;
    private InPin<T2> response;

    public JoinPinChannel(InPin<T1> in, OutPin<T2> out) {
        inPin = in;
        out.connect(new InPin<T2>() {
            
            @Override
            public void receive(T2 message) {
                response.receive(message);
            }
        });
    }

    @Override
    public void receive(T1 message, InPin<T2> response) {
        this.response = response;
        inPin.receive(message);
    }

}
