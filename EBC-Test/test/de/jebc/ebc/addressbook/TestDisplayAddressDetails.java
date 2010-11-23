package de.jebc.ebc.addressbook;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import org.junit.Test;

import de.jebc.ebc.InPin;
import de.jebc.ebc.QueryInPin;
import de.jebc.ebc.addressbook.activities.DisplayAddressDetails;
import de.jebc.ebc.addressbook.data.ExecuteDatasourceQuery;
import de.jebc.ebc.addressbook.data.jdbc.JdbcExecuteDatasourceQuery;
import de.jebc.ebc.addressbook.domain.AddressCategory;
import de.jebc.ebc.addressbook.domain.addressdetails.Address;
import de.jebc.ebc.addressbook.domain.baseadresses.BaseAddressData;

public class TestDisplayAddressDetails {

    protected Address result;

    @Test
    public void readAddress() throws Exception {

        BaseAddressData data = new BaseAddressData(1, new AddressCategory(""),
                "");

        final Connection conn = prepareDatabase();
        JdbcExecuteDatasourceQuery execute = new JdbcExecuteDatasourceQuery();

        DisplayAddressDetails sut = new DisplayAddressDetails(execute);
        sut.Connection().connect(new QueryInPin<Object, Connection>() {

            @Override
            public void receive(Object message, InPin<Connection> response) {
                response.receive(conn);
            }
        });
        sut.result().connect(new InPin<Address>() {
            
            @Override
            public void receive(Address message) {
                result = message;
            }
        });
        
        sut.start().receive(data);
        
        assertEquals(1, result.getId());
        assertEquals(new AddressCategory("Büro"), result.getCategory());
        assertEquals("Name", result.getName());
        assertEquals("Vorname", result.getGivenName());
        assertEquals("20000", result.getZipCode());
        assertEquals("Hamburg", result.getCity());
        assertEquals("ABC-Straße 1", result.getStreet());
        assertEquals("Deutschland", result.getCountry());
    }

    private Connection prepareDatabase() throws Exception {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection("jdbc:sqlite::memory:");
        Statement stmt = conn.createStatement();
        stmt.executeUpdate("CREATE TABLE Adressen (ID INTEGER, Category TEXT, Name TEXT, GivenName TEXT,"
                + "ZipCode TEXT, City TEXT, Street TEXT, Country TEXT);");
        stmt.executeUpdate("INSERT INTO Adressen VALUES (1, 'Büro', 'Name', 'Vorname',"
                + "'20000', 'Hamburg', 'ABC-Straße 1', 'Deutschland')");
        return conn;
    }
}
