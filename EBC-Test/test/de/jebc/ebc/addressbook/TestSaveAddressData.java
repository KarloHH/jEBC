package de.jebc.ebc.addressbook;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

import de.jebc.ebc.InPin;
import de.jebc.ebc.QueryInPin;
import de.jebc.ebc.addressbook.data.jdbc.JdbcExecuteDatasourceQuery;
import de.jebc.ebc.addressbook.domain.AddressCategory;
import de.jebc.ebc.addressbook.domain.addressdetails.Address;

public class TestSaveAddressData {

    private Connection conn;
    protected Address result;
    protected boolean done;

    @Test public void save() throws Exception {
        
        conn = prepareDatabase();
        Address address = new Address(1, new AddressCategory("Büro"), "Name", "Vorname", "22222", "Ort", "Straße 1", "Land");
        
        SaveAddressData sut = new SaveAddressData(new JdbcExecuteDatasourceQuery());
        
        sut.Connection().connect(new QueryInPin<Object, Connection>() {

            @Override
            public void receive(Object message, InPin<Connection> response) {
                response.receive(conn);
            }
        });
        sut.Result().connect(new InPin<Object>() {

            @Override
            public void receive(Object message) {
                done = true;
            }
        });
        
        sut.Start().receive(address);
        
        assertTrue(done);
        assertEquals(address.getName(), getAddressName(1));
    }

    private Object getAddressName(int i) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT Name FROM Adressen WHERE ID = " + i);
        rs.next();
        String result = rs.getString(1);
        return result;
    }

    private Connection prepareDatabase() throws Exception {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection("jdbc:sqlite::memory:");
        Statement stmt = conn.createStatement();
        stmt.executeUpdate("CREATE TABLE Adressen (ID INTEGER, Category TEXT, Name TEXT, GivenName TEXT,"
                + "ZipCode TEXT, City TEXT, Street TEXT, Country TEXT);");
        stmt.executeUpdate("INSERT INTO Adressen VALUES (1, 'Privat', 'Nachname', 'Vorname',"
                + "'20000', 'Hamburg', 'ABC-Straße 1', 'Deutschland')");
        return conn;
    }

}
