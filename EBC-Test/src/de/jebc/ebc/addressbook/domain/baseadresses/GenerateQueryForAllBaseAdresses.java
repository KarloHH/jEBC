package de.jebc.ebc.addressbook.domain.baseadresses;

import de.jebc.ebc.addressbook.data.Query;
import de.jebc.ebc.impl.ProcessImpl;

public class GenerateQueryForAllBaseAdresses extends ProcessImpl<Object, Query> {

    @Override
    protected void process(Object dummy) {
        Query queryCommand = new Query(
                "SELECT ID, Category, Name, GivenName FROM Adressen");
        Result().send(queryCommand);
    }
}
