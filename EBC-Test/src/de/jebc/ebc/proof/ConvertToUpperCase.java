package de.jebc.ebc.proof;

import de.jebc.ebc.InPin;
import de.jebc.ebc.OutPin;

public interface ConvertToUpperCase {

    public InPin<String> request();
    public OutPin<String> response();

}