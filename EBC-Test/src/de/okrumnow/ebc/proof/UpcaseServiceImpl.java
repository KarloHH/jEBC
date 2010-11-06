package de.okrumnow.ebc.proof;

import de.okrumnow.ebc.InPin;
import de.okrumnow.ebc.OutPin;
import de.okrumnow.ebc.impl.SingleOutPin;

public class UpcaseServiceImpl implements UpcaseService {

    private InPin<String> requestPin = new InPin<String>() {

        @Override
        public void receive(String message) {
            resultPin.transmit(message.toUpperCase());
        }
    };
    private OutPin<String> resultPin = new SingleOutPin<String>();

    @Override
    public InPin<String> Request() {
        return requestPin;
    }

    @Override
    public OutPin<String> Result() {
        return resultPin;
    }

}
