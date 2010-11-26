package de.jebc.ebc.addressbook.domain.baseadresses;

import de.jebc.ebc.InTrigger;
import de.jebc.ebc.OutPin;
import de.jebc.ebc.addressbook.data.Query;
import de.jebc.ebc.impl.SingleOutPin;

public class GenerateQueryForAllBaseAdresses {

    private OutPin<Query> resultPin = new SingleOutPin<Query>();
    private InTrigger startPin = new InTrigger() {

        @Override
        public void receive() {
            Query queryCommand = new Query(
                    "SELECT ID, Category, Name, GivenName FROM Adressen");
            Result().send(queryCommand);
        }
    };

    public InTrigger Start() {
        return startPin;
    }

    public OutPin<Query> Result() {
        return resultPin;
    }
}
