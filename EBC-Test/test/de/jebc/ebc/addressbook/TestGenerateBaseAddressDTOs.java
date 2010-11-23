package de.jebc.ebc.addressbook;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import de.jebc.ebc.InPin;
import de.jebc.ebc.addressbook.data.Resultset;
import de.jebc.ebc.addressbook.domain.baseadresses.BaseAddressData;
import de.jebc.ebc.addressbook.domain.baseadresses.GenerateBaseAddressDTOs;

public class TestGenerateBaseAddressDTOs {

    protected List<BaseAddressData> result;

    @Test
    public void generate() {

        Resultset data = createResultset();
        GenerateBaseAddressDTOs sut = new GenerateBaseAddressDTOs();
        sut.Result().connect(new InPin<List<BaseAddressData>>() {

            @Override
            public void receive(List<BaseAddressData> message) {
                result = message;

            }
        });
        sut.Start().receive(data);
        assertEquals(3, result.size());
        assertEquals("NM1, VN1", result.get(0).getName());
    }

    private Resultset createResultset() {
        return new ResultsetImplForTesting(new String[] { "ID", "Category",
                "GivenName", "Name" }, new Object[][] {
                new Object[] { 1, "Büro", "VN1", "NM1" },
                new Object[] { 2, "Privat", "VN2", "NM2" },
                new Object[] { 3, "Büro", "VN3", "NM3" } });
    }
}
