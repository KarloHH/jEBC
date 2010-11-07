package de.okrumnow.ebc.proof;

import de.okrumnow.ebc.InPin;
import de.okrumnow.ebc.ServiceInPin;

public class UpcaseServiceImpl implements UpcaseService {

    private ServiceInPin<String, String> requestPin = new ServiceInPin<String, String>() {

        @Override
        public void receive(String message, InPin<String> response) {
            response.receive(message.toUpperCase());
        }

    };

    @Override
    public ServiceInPin<String, String> Request() {
        return requestPin;
    }

}
