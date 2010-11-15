package de.jebc.ebc.parts;

import java.util.HashMap;
import java.util.Map;

import de.jebc.ebc.ServicePin;
import de.jebc.ebc.InPin;
import de.jebc.ebc.QueryPin;
import de.jebc.ebc.impl.QueryPinImpl;

public class ReadonlyCache<TKey, TVal> implements Cache<TKey, TVal> {

    private Map<TKey, TVal> cache = new HashMap<TKey, TVal>();
    private ServicePin<TKey, TVal> getPin = new ServicePin<TKey, TVal>() {

        @Override
        public void receive(final TKey message, final InPin<TVal> response) {
            if (cache.containsKey(message)) {
                response.receive(cache.get(message)); 
            } else {
                requestPin.send(message, new InPin<TVal>() {

                    @Override
                    public void receive(TVal result) {
                        cache.put(message, result);
                        response.receive(result);
                    }
                });
            }
        }
    };
    private QueryPin<TKey, TVal> requestPin = new QueryPinImpl<TKey, TVal>();

    @Override
    public ServicePin<TKey, TVal> get() {
        return getPin;
    }

    @Override
    public QueryPin<TKey, TVal> request() {
        return requestPin;
    }

}
