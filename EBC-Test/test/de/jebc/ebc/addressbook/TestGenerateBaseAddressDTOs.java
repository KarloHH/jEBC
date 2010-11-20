package de.jebc.ebc.addressbook;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import de.jebc.ebc.InPin;
import de.jebc.ebc.addressbook.data.Resultset;
import de.jebc.ebc.addressbook.data.baseadresses.BaseAddressData;
import de.jebc.ebc.addressbook.data.baseadresses.GenerateBaseAddressDTOs;

public class TestGenerateBaseAddressDTOs {

    protected List<BaseAddressData> result;

    @Test public void generate() {
        
        Resultset data = createResultset();
        GenerateBaseAddressDTOs sut = new GenerateBaseAddressDTOs();
        sut.addresses().connect(new InPin<List<BaseAddressData>>() {
            
            @Override
            public void receive(List<BaseAddressData> message) {
                result = message;
                
            }
        });
        sut.start().receive(data);
        assertEquals(3, result.size());
        assertEquals("VN1", result.get(0).getGivenName());
    }

    private Resultset createResultset() {
        return new TestResultset(new String[]{
                "ID", "Category", "GivenName", "Name"
        }, 
        new Object[][]{
                new Object[]{1, "Büro", "VN1", "NM1"},
                new Object[]{2, "Privat", "VN2", "NM2"},
                new Object[]{3, "Büro", "VN3", "NM3"}
        });
    }
}
