package de.jebc.ebc.parts;

import de.jebc.ebc.InChannel;
import de.jebc.ebc.OutChannel;


public interface Cache<TKey, TVal> {

    InChannel<TKey, TVal> get();
    
    OutChannel<TKey, TVal> request();
}
