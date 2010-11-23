package de.jebc.ebc.addressbook;

import static org.junit.Assert.*;

import org.junit.Test;

import de.jebc.ebc.InPin;
import de.jebc.ebc.addressbook.data.Query;
import de.jebc.ebc.addressbook.domain.baseadresses.GenerateQueryForAllBaseAdresses;

public class TestGenerateQueryForAllBaseAddresses {

    protected boolean queried = false;
    protected Query result = null;

    @Test
    public void query() {

        GenerateQueryForAllBaseAdresses sut = new GenerateQueryForAllBaseAdresses();
        sut.accessDatasource().connect(new InPin<Query>() {

            @Override
            public void receive(Query message) {
                result = message;
                queried = true;
            }
        });
        sut.start().receive(null);

        assertTrue(queried);
    }

}
