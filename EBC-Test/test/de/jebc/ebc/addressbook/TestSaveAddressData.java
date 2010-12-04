package de.jebc.ebc.addressbook;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

import de.jebc.ebc.InTrigger;
import de.jebc.ebc.addressbook.activities.SaveAddressData;
import de.jebc.ebc.addressbook.data.ConnectionFactory;
import de.jebc.ebc.addressbook.data.jdbc.JdbcExecuteDatasourceQuery;
import de.jebc.ebc.addressbook.domain.AddressCategory;
import de.jebc.ebc.addressbook.domain.addressdetails.Address;

public class TestSaveAddressData {

    private ConnectionFactory conn;
    protected Address result;
    protected boolean done;

    @Test
    public void save() throws Exception {

        conn = prepareDatabase();
        Address address = new Address(1, new AddressCategory("Büro"), "Name",
                "Vorname", "22222", "Ort", "Straße 1", "Land");

        SaveAddressData sut = new SaveAddressData(
                new JdbcExecuteDatasourceQuery(conn));

        sut.Result().connect(new InTrigger() {

            @Override
            public void receive() {
                done = true;
            }
        });

        sut.Start().receive(address);

        assertTrue(done);
        assertEquals(address.getName(), getAddressName(1));
    }

    private Object getAddressName(int i) throws SQLException {
        Statement stmt = conn.getConnection().createStatement();
        ResultSet rs = stmt
                .executeQuery("SELECT Name FROM Adressen WHERE ID = " + i);
        rs.next();
        String result = rs.getString(1);
        return result;
    }

    private ConnectionFactory prepareDatabase() throws Exception {
        Class.forName("org.sqlite.JDBC");
        final Connection conn = DriverManager
                .getConnection("jdbc:sqlite::memory:");
        Statement stmt = conn.createStatement();
        stmt.executeUpdate("CREATE TABLE Adressen (ID INTEGER, Category TEXT, Name TEXT, GivenName TEXT,"
                + "ZipCode TEXT, City TEXT, Street TEXT, Country TEXT);");
        stmt.executeUpdate("INSERT INTO Adressen VALUES (1, 'Privat', 'Nachname', 'Vorname',"
                + "'20000', 'Hamburg', 'ABC-Straße 1', 'Deutschland')");
        return new ConnectionFactory() {

            @Override
            public Connection getConnection() {
                return conn;
            }
        };
    }

}
