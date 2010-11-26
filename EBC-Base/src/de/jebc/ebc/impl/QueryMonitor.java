package de.jebc.ebc.impl;

import de.jebc.ebc.InPin;
import de.jebc.ebc.QueryOutPin;
import de.jebc.ebc.QueryInPin;

public abstract class QueryMonitor<T1, T2> {

    private QueryOutPin<T1, T2> monitorOut = new QueryPinImpl<T1, T2>();
    private QueryInPin<T1, T2> monitorIn = new QueryInPin<T1, T2>() {

        @Override
        public void receive(T1 message, final InPin<T2> originalResponse) {
            inspectRequest(message);
            monitorOut.send(message, new InPin<T2>() {

                @Override
                public void receive(T2 message) {
                    inspectResponse(message);
                    originalResponse.receive(message);
                }
            });
        }
    };

    public QueryOutPin<T1, T2> out() {
        return monitorOut;
    }

    public QueryInPin<T1, T2> in() {
        return monitorIn;
    }

    protected abstract void inspectRequest(T1 message);

    protected abstract void inspectResponse(T2 message);

}
