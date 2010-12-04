package de.jebc.ebc.addressbook.domain.baseadresses;

import de.jebc.ebc.InPin;
import de.jebc.ebc.OutPin;
import de.jebc.ebc.addressbook.data.Query;
import de.jebc.ebc.impl.SingleOutPin;

public class GenerateQueryForAllBaseAdresses {

    private OutPin<Query> resultPin = new SingleOutPin<Query>();
    private InPin<Void> startPin = new InPin<Void>() {

        @Override
        public void receive(Void v) {
            Query queryCommand = new Query(
                    "SELECT ID, Category, Name, GivenName FROM Adressen");
            Result().send(queryCommand);
        }
    };

    public InPin<Void> Start() {
        return startPin;
    }

    public OutPin<Query> Result() {
        return resultPin;
    }
}
