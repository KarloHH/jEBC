package de.okrumnow.ebc.proof;

import de.okrumnow.ebc.InChannel;
import de.okrumnow.ebc.impl.AbstractBoard;
import de.okrumnow.ebc.parts.Cache;
import de.okrumnow.ebc.parts.ReadonlyCache;

public class UpcaseBoard extends AbstractBoard {

    private InChannel<String, String> request;

    public InChannel<String, String> Request() {
        return request;
    }

    public UpcaseBoard() {
        // creating the parts
        ConvertToUpperCase upcase = new ConvertToUpperCaseImpl();
        Cache<String, String> cache = new ReadonlyCache<String, String>();

        // extend the open pins to the outside
        request = extend(cache.Get());

        // plumbing the echo board
        connect(cache.Request(), with(join(upcase.Request(), upcase.Response())));
    }
}