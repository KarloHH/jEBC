package de.okrumnow.ebc.parts;

import de.okrumnow.ebc.InPin;
import de.okrumnow.ebc.OutPin;

public interface Cache<TKey, TVal> {
    InPin<TKey> GetValue();

    OutPin<TKey> RequestValue();

    InPin<TVal> ReceiveValue();

    OutPin<TVal> ReturnValue();
}
