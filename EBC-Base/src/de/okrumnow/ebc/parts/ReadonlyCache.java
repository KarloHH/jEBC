package de.okrumnow.ebc.parts;

import java.util.HashMap;
import java.util.Map;

import de.okrumnow.ebc.InPin;
import de.okrumnow.ebc.ServiceInPin;
import de.okrumnow.ebc.ServiceOutPin;
import de.okrumnow.ebc.impl.ServiceOutPinImpl;

public class ReadonlyCache<TKey, TVal> implements Cache<TKey, TVal> {

    private Map<TKey, TVal> cache = new HashMap<TKey, TVal>();
    private ServiceInPin<TKey, TVal> getPin = new ServiceInPin<TKey, TVal>() {

        @Override
        public void receive(final TKey message, final InPin<TVal> response) {
            if (cache.containsKey(message)) {
                response.receive(cache.get(message)); 
            } else {
                requestPin.transmit(message, new InPin<TVal>() {

                    @Override
                    public void receive(TVal result) {
                        cache.put(message, result);
                        response.receive(result);
                    }
                });
            }
        }
    };
    private ServiceOutPin<TKey, TVal> requestPin = new ServiceOutPinImpl<TKey, TVal>();

    @Override
    public ServiceInPin<TKey, TVal> Get() {
        return getPin;
    }

    @Override
    public ServiceOutPin<TKey, TVal> Request() {
        return requestPin;
    }

}
