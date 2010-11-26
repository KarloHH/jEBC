package de.jebc.ebc.addressbook;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import org.junit.Test;

import de.jebc.ebc.InPin;
import de.jebc.ebc.QueryInPin;
import de.jebc.ebc.addressbook.data.jdbc.JdbcExecuteDatasourceQuery;
import de.jebc.ebc.addressbook.domain.AddressCategory;
import de.jebc.ebc.addressbook.domain.addressdetails.Address;

public class TestSaveNewAddressData {

    protected boolean completed;
    protected Address result;

    @Test
    public void save() throws Exception {

        Address data = new Address(0, new AddressCategory("Büro"), "Name",
                "Vorname", "20000", "City", "Straße", "Land");
        final Connection conn = prepareDatabase();

        SaveNewAddressData sut = new SaveNewAddressData(new JdbcExecuteDatasourceQuery());

        sut.Connection().connect(new QueryInPin<Object, Connection>() {
            
            @Override
            public void receive(Object message, InPin<Connection> response) {
                response.receive(conn);
            }
        });
        sut.Completed().connect(new InPin<Object>() {

            @Override
            public void receive(Object message) {
                completed = true;
            }
        });
        sut.Result().connect(new InPin<Address>() {

            @Override
            public void receive(Address message) {
                result = message;
            }
        });
        
        sut.Start().receive(data);
        
        assertEquals(1, result.getId());
        assertTrue(completed);
    }

    private Connection prepareDatabase() throws Exception {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection("jdbc:sqlite::memory:");
        Statement stmt = conn.createStatement();
        stmt.executeUpdate("CREATE TABLE Adressen (ID INTEGER PRIMARY KEY AUTOINCREMENT, Category TEXT, Name TEXT, GivenName TEXT,"
                + "ZipCode TEXT, City TEXT, Street TEXT, Country TEXT);");
        return conn;
    }

}
