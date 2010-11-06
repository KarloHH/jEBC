package de.okrumnow.ebc.proof;

import de.okrumnow.ebc.InPin;
import de.okrumnow.ebc.OutPin;
import de.okrumnow.ebc.impl.ExtensionInPin;
import de.okrumnow.ebc.impl.ExtensionOutPin;
import de.okrumnow.ebc.parts.Cache;
import de.okrumnow.ebc.parts.ReadonlyCache;

public class UpcaseBoard {

    private InPin<String> request;
    private OutPin<String> response;

    public OutPin<String> Response() {
        return response;
    }

    public InPin<String> Request() {
        return request;
    }

    public UpcaseBoard() {
        // creating the parts
        UpcaseService upcase = new UpcaseServiceImpl();
        Cache<String, String> cache = new ReadonlyCache<String, String>();

        // extend the open pins to the outside
        request = new ExtensionInPin<String>(cache.GetValue());
        response = new ExtensionOutPin<String>(cache.ReturnValue());

        // plumbing the echo board
        cache.RequestValue().connect(upcase.Request());
        upcase.Result().connect(cache.ReceiveValue());

    }
}