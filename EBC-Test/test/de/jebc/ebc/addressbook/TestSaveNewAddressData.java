package de.jebc.ebc.addressbook;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import org.junit.Test;

import de.jebc.ebc.InPin;
import de.jebc.ebc.InTrigger;
import de.jebc.ebc.addressbook.activities.SaveNewAddressData;
import de.jebc.ebc.addressbook.data.ConnectionFactory;
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
        final ConnectionFactory conn = prepareDatabase();

        SaveNewAddressData sut = new SaveNewAddressData(
                new JdbcExecuteDatasourceQuery(conn));

        sut.Completed().connect(new InTrigger() {

            @Override
            public void receive() {
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

    private ConnectionFactory prepareDatabase() throws Exception {
        Class.forName("org.sqlite.JDBC");
        final Connection conn = DriverManager
                .getConnection("jdbc:sqlite::memory:");
        Statement stmt = conn.createStatement();
        stmt.executeUpdate("CREATE TABLE Adressen (ID INTEGER PRIMARY KEY AUTOINCREMENT, Category TEXT, Name TEXT, GivenName TEXT,"
                + "ZipCode TEXT, City TEXT, Street TEXT, Country TEXT);");
        return new ConnectionFactory() {

            @Override
            public Connection getConnection() {
                return conn;
            }
        };
    }

}
