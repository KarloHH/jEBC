package de.okrumnow.ebc.proof;

import de.okrumnow.ebc.InPin;
import de.okrumnow.ebc.OutPin;
import de.okrumnow.ebc.impl.SingleOutPin;

public class UpcaseFilterImpl implements UpcaseFilter {

    private InPin<String> requestPin = new InPin<String>() {

        @Override
        public void receive(String message) {
            responsePin.transmit(message.toUpperCase());
        }

    };
    private OutPin<String> responsePin = new SingleOutPin<String>();

    @Override
    public InPin<String> Request() {
        return requestPin;
    }

    @Override
    public OutPin<String> Response() {
        return responsePin;
    }

}
