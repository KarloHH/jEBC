package de.jebc.ebc.upcase;

import de.jebc.ebc.InPin;
import de.jebc.ebc.OutPin;
import de.jebc.ebc.impl.SingleOutPin;

public class ConvertToUpperCaseImpl implements ConvertToUpperCase {

    private InPin<String> requestPin = new InPin<String>() {

        @Override
        public void receive(String message) {
            responsePin.send(message.toUpperCase());
        }

    };
    private OutPin<String> responsePin = new SingleOutPin<String>();

    @Override
    public InPin<String> request() {
        return requestPin;
    }

    @Override
    public OutPin<String> response() {
        return responsePin;
    }

}
