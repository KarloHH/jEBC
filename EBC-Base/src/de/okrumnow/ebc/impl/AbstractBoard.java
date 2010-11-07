package de.okrumnow.ebc.impl;

import de.okrumnow.ebc.InChannel;
import de.okrumnow.ebc.InPin;
import de.okrumnow.ebc.OutChannel;
import de.okrumnow.ebc.OutPin;

public abstract class AbstractBoard {

    public AbstractBoard() {
        super();
    }

    protected <T> T with(T what) {
        return what;
    }

    protected <T1, T2> void connect(OutChannel<T1, T2> out, InChannel<T1, T2> in) {
        out.connect(in);
    }

    protected <T1, T2> InChannel<T1, T2> join(InPin<T1> in, OutPin<T2> out) {
        return new JoinPinChannel<T1, T2>(in, out);
    }

    protected <T1, T2> InChannel<T1, T2> extend(InChannel<T1, T2> inChannel) {
        return new ExtensionInChannel<T1, T2>(inChannel);
    }

}