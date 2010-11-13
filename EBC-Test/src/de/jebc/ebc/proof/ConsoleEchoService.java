package de.jebc.ebc.proof;

import de.jebc.ebc.InPin;

public class ConsoleEchoService<T> {

    public InPin<T> echo() {
        return echoPin;
    }

    private InPin<T> echoPin = new InPin<T>() {

        @Override
        public void receive(T message) {
            System.out.println(message.toString());
        }
    };
}
