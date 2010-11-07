package de.okrumnow.ebc.proof;

import de.okrumnow.ebc.InPin;
import de.okrumnow.ebc.OutPin;
import de.okrumnow.ebc.ServiceInPin;

public class JoinServicePin<T1, T2> implements ServiceInPin<T1, T2> {

    private InPin<T1> inPin;
    private InPin<T2> response;

    public JoinServicePin(InPin<T1> in, OutPin<T2> out) {
        inPin = in;
        out.connect(new InPin<T2>() {
            
            @Override
            public void receive(T2 message) {
                response.receive(message);
            }
        });
    }

    @Override
    public void receive(T1 message, InPin<T2> response) {
        this.response = response;
        inPin.receive(message);
    }

}
