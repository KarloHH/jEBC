package de.okrumnow.ebc.proof;

import de.okrumnow.ebc.InPin;

public class ConsoleEchoService<T> {

    public InPin<T> Echo() {
        return echoPin;
    }

    private InPin<T> echoPin = new InPin<T>() {

        @Override
        public void receive(T message) {
            System.out.println(message.toString());
        }
    };
}
