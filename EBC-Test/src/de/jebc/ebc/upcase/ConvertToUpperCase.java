package de.jebc.ebc.upcase;

import de.jebc.ebc.InPin;
import de.jebc.ebc.OutPin;

public interface ConvertToUpperCase {

    public InPin<String> request();
    public OutPin<String> response();

}