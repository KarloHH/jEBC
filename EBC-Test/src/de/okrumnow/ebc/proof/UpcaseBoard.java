package de.okrumnow.ebc.proof;

import de.okrumnow.ebc.ServiceInPin;
import de.okrumnow.ebc.impl.ExtensionServiceInPin;
import de.okrumnow.ebc.parts.Cache;
import de.okrumnow.ebc.parts.ReadonlyCache;

public class UpcaseBoard {

    private ServiceInPin<String, String> request;

    public ServiceInPin<String, String> Request() {
        return request;
    }

    public UpcaseBoard() {
        // creating the parts
        UpcaseFilter upcase = new UpcaseFilterImpl();
        Cache<String, String> cache = new ReadonlyCache<String, String>();

        // extend the open pins to the outside
        request = new ExtensionServiceInPin<String, String>(cache.Get());

        // plumbing the echo board
        cache.Request().connect(new JoinServicePin<String, String>(upcase.Request(), upcase.Response()));
    }
}