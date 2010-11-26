package de.jebc.ebc.impl;

import de.jebc.ebc.InPin;
import de.jebc.ebc.InTrigger;
import de.jebc.ebc.OutPin;
import de.jebc.ebc.OutTrigger;
import de.jebc.ebc.QueryInPin;
import de.jebc.ebc.QueryOutPin;

public abstract class Board {

    protected <T> T with(T what) {
        return what;
    }

    protected <T1, T2> void connect(QueryOutPin<T1, T2> out,
            QueryInPin<T1, T2> in) {
        out.connect(in);
    }

    protected <T1, T2> QueryInPin<T1, T2> join(InPin<T1> in, OutPin<T2> out) {
        return new QueryComposer<T1, T2>(in, out);
    }

    protected <T> void connect(OutPin<T> outPin, InPin<T> inPin) {
        outPin.connect(inPin);
    }
    
    protected void connect(OutTrigger out, InTrigger in) {
        out.connect(in);
    }

    protected <T1, T2> void monitor(QueryOutPin<T1, T2> output,
            QueryInPin<T1, T2> input, QueryMonitor<T1, T2> monitor) {
        connect(output, monitor.in());
        connect(monitor.out(), input);
    }

    protected <T> void monitor(OutPin<T> output, InPin<T> input,
            Monitor<T> monitor) {
        connect(output, monitor.in());
        connect(monitor.out(), input);
    }

    protected <T1, T2> void filter(OutPin<T1> output, InPin<T2> input,
            Filter<T1, T2> filter) {
        connect(output, filter.in());
        connect(filter.out(), input);
    }
}