package de.jebc.ebc.addressbook;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import org.junit.Test;

import de.jebc.ebc.InPin;
import de.jebc.ebc.QueryInPin;
import de.jebc.ebc.addressbook.data.ExecuteDatasourceQuery;
import de.jebc.ebc.addressbook.data.Query;
import de.jebc.ebc.addressbook.data.Resultset;
import de.jebc.ebc.addressbook.data.jdbc.JdbcExecuteDatasourceQuery;

public class TestExecuteDatasourceQuery {

    protected Resultset result;

    @Test
    public void query() throws Exception {

        final Connection conn = getConnection();
        Query query = new Query("SELECT ID, Name FROM Adressen");

        ExecuteDatasourceQuery sut = new JdbcExecuteDatasourceQuery();

        sut.Result().connect(new InPin<Resultset>() {

            @Override
            public void receive(Resultset message) {
                result = message;
            }
        });

        sut.Connection().connect(new QueryInPin<Object, Connection>() {

            @Override
            public void receive(Object message, InPin<Connection> response) {
                response.receive(conn);
            }
        });

        sut.Start().receive(query);

        assertTrue(result.next());
        assertEquals(1, result.getInt("ID"));
        assertEquals("Name", result.getString("Name"));
    }

    private Connection getConnection() throws Exception {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection("jdbc:sqlite::memory:");
        Statement stmt = conn.createStatement();
        stmt.executeUpdate("CREATE TABLE Adressen (ID INTEGER, Name TEXT);");
        stmt.executeUpdate("INSERT INTO Adressen VALUES (1, 'Name')");
        return conn;
    }
}
