package de.okrumnow.ebc.parts;

import de.okrumnow.ebc.InChannel;
import de.okrumnow.ebc.OutChannel;


public interface Cache<TKey, TVal> {

    InChannel<TKey, TVal> Get();
    
    OutChannel<TKey, TVal> Request();
}
