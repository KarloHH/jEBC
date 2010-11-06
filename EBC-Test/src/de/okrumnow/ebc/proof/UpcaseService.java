package de.okrumnow.ebc.proof;

import de.okrumnow.ebc.InPin;
import de.okrumnow.ebc.OutPin;

public interface UpcaseService {

    public InPin<String> Request();

    public OutPin<String> Result();

}