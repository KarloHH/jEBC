package de.okrumnow.ebc.proof;

import de.okrumnow.ebc.InPin;
import de.okrumnow.ebc.OutPin;

public interface UpcaseFilter {

    public InPin<String> Request();
    public OutPin<String> Response();

}