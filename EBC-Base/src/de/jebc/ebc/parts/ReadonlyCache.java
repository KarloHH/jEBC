package de.jebc.ebc.parts;

import java.util.HashMap;
import java.util.Map;

import de.jebc.ebc.InChannel;
import de.jebc.ebc.InPin;
import de.jebc.ebc.OutChannel;
import de.jebc.ebc.impl.OutChannelImpl;

public class ReadonlyCache<TKey, TVal> implements Cache<TKey, TVal> {

    private Map<TKey, TVal> cache = new HashMap<TKey, TVal>();
    private InChannel<TKey, TVal> getPin = new InChannel<TKey, TVal>() {

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
    private OutChannel<TKey, TVal> requestPin = new OutChannelImpl<TKey, TVal>();

    @Override
    public InChannel<TKey, TVal> get() {
        return getPin;
    }

    @Override
    public OutChannel<TKey, TVal> request() {
        return requestPin;
    }

}
