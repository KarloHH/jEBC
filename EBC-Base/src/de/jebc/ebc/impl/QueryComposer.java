package de.jebc.ebc.impl;

import de.jebc.ebc.QueryInPin;
import de.jebc.ebc.InPin;
import de.jebc.ebc.OutPin;

public class QueryComposer<T1, T2> implements QueryInPin<T1, T2> {

    private InPin<T1> inPin;
    private InPin<T2> response;

    public QueryComposer(InPin<T1> in, OutPin<T2> out) {
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
