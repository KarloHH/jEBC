package de.jebc.ebc.impl;

import de.jebc.ebc.InPin;
import de.jebc.ebc.OutPin;
import de.jebc.ebc.QueryOutPin;
import de.jebc.ebc.QueryInPin;

public abstract class Board {

    protected <T> T with(T what) {
        return what;
    }

    protected <T1, T2> void connect(QueryOutPin<T1, T2> out, QueryInPin<T1, T2> in) {
        out.connect(in);
    }

    protected <T1, T2> QueryInPin<T1, T2> join(InPin<T1> in, OutPin<T2> out) {
        return new QueryComposer<T1, T2>(in, out);
    }

    protected <T1, T2> QueryOutPin<T1, T2> extend(QueryOutPin<T1, T2> out) {
        return new QueryPinExtension<T1, T2>(out);
    }
    
    protected <T1, T2> QueryInPin<T1, T2> extend(QueryInPin<T1, T2> inChannel) {
        return new ServicePinExtension<T1, T2>(inChannel);
    }

    protected <T> InPin<T> extend(InPin<T> in2) {
        return new InPinExtension<T>(in2);
    }

    protected <T> OutPin<T> extend(OutPin<T> out2) {
        return new OutPinExtension<T>(out2);
    }

    protected <T> void connect(OutPin<T> outPin, InPin<T> inPin) {
        outPin.connect(inPin);
    }

    protected <T1, T2> void monitor(QueryOutPin<T1, T2> output, QueryInPin<T1, T2> input, QueryMonitor<T1, T2> monitor) {
        connect(output, monitor.in());
        connect(monitor.out(), input);
    }
    
}