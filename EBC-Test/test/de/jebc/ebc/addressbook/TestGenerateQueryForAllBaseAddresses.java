package de.jebc.ebc.addressbook;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import de.jebc.ebc.InPin;
import de.jebc.ebc.addressbook.data.baseadresses.Query;
import de.jebc.ebc.addressbook.data.baseadresses.GenerateQueryForAllBaseAdresses;

public class TestGenerateQueryForAllBaseAddresses {

    protected boolean queried = false;
    protected Query result = null;

    @Test public void query() {

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
        assertEquals("Adressen", result.getViewname());
        assertEquals(4, result.getColumns().length);
    }

}
