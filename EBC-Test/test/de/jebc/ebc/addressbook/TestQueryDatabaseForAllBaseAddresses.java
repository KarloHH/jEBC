package de.jebc.ebc.addressbook;

import static org.junit.Assert.*;

import org.junit.Test;

import de.jebc.ebc.InPin;
import de.jebc.ebc.QueryInPin;
import de.jebc.ebc.addressbook.data.Resultset;
import de.jebc.ebc.addressbook.data.baseadresses.QueryDatabaseForAllBaseAdresses;

public class TestQueryDatabaseForAllBaseAddresses {

    protected boolean queried = false;
    protected Resultset result = null;

    @Test public void query() {

        final Resultset data = getResultset();
        
        QueryDatabaseForAllBaseAdresses sut = new QueryDatabaseForAllBaseAdresses();
        sut.queryDatabase().connect(new QueryInPin<String, Resultset>() {
            
            @Override
            public void receive(String message, InPin<Resultset> response) {
                response.receive(data);
                queried = true;
            }
        });
        sut.result().connect(new InPin<Resultset>() {

            @Override
            public void receive(Resultset message) {
                result = message;
            }
        });
        
        sut.start().receive(null);
        
        assertTrue(queried);
        assertEquals(data, result);

    }

    private Resultset getResultset() {
        return new Resultset() {
        };
    }
}
