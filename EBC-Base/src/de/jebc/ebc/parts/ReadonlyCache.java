package de.jebc.ebc.parts;

import java.util.HashMap;
import java.util.Map;

import de.jebc.ebc.QueryInPin;
import de.jebc.ebc.InPin;
import de.jebc.ebc.QueryOutPin;
import de.jebc.ebc.impl.QueryPinImpl;

public class ReadonlyCache<TKey, TVal> implements Cache<TKey, TVal> {

    private Map<TKey, TVal> cache = new HashMap<TKey, TVal>();
    private QueryInPin<TKey, TVal> getPin = new QueryInPin<TKey, TVal>() {

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
    private QueryOutPin<TKey, TVal> requestPin = new QueryPinImpl<TKey, TVal>();

    @Override
    public QueryInPin<TKey, TVal> get() {
        return getPin;
    }

    @Override
    public QueryOutPin<TKey, TVal> request() {
        return requestPin;
    }

}
