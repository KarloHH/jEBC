package de.jebc.ebc.parts;

import de.jebc.ebc.QueryInPin;
import de.jebc.ebc.QueryOutPin;


public interface Cache<TKey, TVal> {

    QueryInPin<TKey, TVal> get();
    
    QueryOutPin<TKey, TVal> request();
}
