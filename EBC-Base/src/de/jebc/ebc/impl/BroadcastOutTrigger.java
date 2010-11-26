package de.jebc.ebc.impl;

import java.util.ArrayList;
import java.util.List;

import de.jebc.ebc.InTrigger;
import de.jebc.ebc.OutTrigger;

public class BroadcastOutTrigger implements OutTrigger {

    private List<InTrigger> pins = new ArrayList<InTrigger>();

    @Override
    public void connect(InTrigger pin) {
        if (!pins.contains(pin)) {
            pins.add(pin);
        }
    }

    @Override
    public void send() {
        for (InTrigger pin : pins) {
            pin.receive();
        }
    }

}
