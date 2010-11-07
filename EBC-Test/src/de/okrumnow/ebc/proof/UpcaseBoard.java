package de.okrumnow.ebc.proof;

import de.okrumnow.ebc.InChannel;
import de.okrumnow.ebc.impl.ExtensionInChannel;
import de.okrumnow.ebc.impl.JoinPinChannel;
import de.okrumnow.ebc.parts.Cache;
import de.okrumnow.ebc.parts.ReadonlyCache;

public class UpcaseBoard {

    private InChannel<String, String> request;

    public InChannel<String, String> Request() {
        return request;
    }

    public UpcaseBoard() {
        // creating the parts
        UpcaseFilter upcase = new UpcaseFilterImpl();
        Cache<String, String> cache = new ReadonlyCache<String, String>();

        // extend the open pins to the outside
        request = new ExtensionInChannel<String, String>(cache.Get());

        // plumbing the echo board
        cache.Request().connect(new JoinPinChannel<String, String>(upcase.Request(), upcase.Response()));
    }
}