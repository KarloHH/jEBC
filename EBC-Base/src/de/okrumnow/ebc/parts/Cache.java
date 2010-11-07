package de.okrumnow.ebc.parts;

import de.okrumnow.ebc.ServiceInPin;
import de.okrumnow.ebc.ServiceOutPin;


public interface Cache<TKey, TVal> {

    ServiceInPin<TKey, TVal> Get();
    
    ServiceOutPin<TKey, TVal> Request();
}
