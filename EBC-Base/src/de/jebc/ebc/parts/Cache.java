package de.jebc.ebc.parts;

import de.jebc.ebc.ServicePin;
import de.jebc.ebc.QueryPin;


public interface Cache<TKey, TVal> {

    ServicePin<TKey, TVal> get();
    
    QueryPin<TKey, TVal> request();
}
