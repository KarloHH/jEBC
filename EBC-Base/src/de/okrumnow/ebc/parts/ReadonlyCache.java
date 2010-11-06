package de.okrumnow.ebc.parts;

import java.util.HashMap;
import java.util.Map;

import de.okrumnow.ebc.InPin;
import de.okrumnow.ebc.OutPin;
import de.okrumnow.ebc.impl.SingleOutPin;

public class ReadonlyCache<TKey, TVal> implements Cache<TKey, TVal> {

    private Map<TKey, TVal> cache = new HashMap<TKey, TVal>();
    private TKey lastKey;
    private OutPin<TKey> requestPin = new SingleOutPin<TKey>();
    private OutPin<TVal> deliverPin = new SingleOutPin<TVal>();
    private InPin<TKey> getValuePin = new InPin<TKey>() {


        @Override
        public void receive(TKey message) {
            if (cache.containsKey(message)) {
                ReturnValue().transmit(cache.get(message));
            } else {
                lastKey = message;
                RequestValue().transmit(message);
            }
        }
    };
    private InPin<TVal> receivePin = new InPin<TVal>() {

        @Override
        public void receive(TVal message) {
            cache.put(lastKey, message);
            ReturnValue().transmit(message);
        }
    };

    @Override
    public InPin<TKey> GetValue() {
        return getValuePin;
    }

    @Override
    public OutPin<TKey> RequestValue() {
        return requestPin;
    }

    @Override
    public InPin<TVal> ReceiveValue() {
        return receivePin;
    }

    @Override
    public OutPin<TVal> ReturnValue() {
        return deliverPin;
    }

}
