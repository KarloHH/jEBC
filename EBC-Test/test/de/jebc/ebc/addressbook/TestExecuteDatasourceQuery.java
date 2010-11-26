package de.jebc.ebc.addressbook;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.Test;

import de.jebc.ebc.InPin;
import de.jebc.ebc.InTrigger;
import de.jebc.ebc.QueryInPin;
import de.jebc.ebc.addressbook.data.Query;
import de.jebc.ebc.addressbook.data.Resultset;
import de.jebc.ebc.addressbook.data.jdbc.ExecuteDatasource;
import de.jebc.ebc.addressbook.data.jdbc.JdbcExecuteDatasourceQuery;

public class TestExecuteDatasourceQuery {

    protected Resultset result;
    private boolean done;

    @Test
    public void query() throws Exception {

        final Connection conn = getConnection();
        Query query = new Query("SELECT ID, Name FROM Adressen");

        ExecuteDatasource sut = new JdbcExecuteDatasourceQuery();

        sut.ResultQuery().connect(new InPin<Resultset>() {

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

        sut.StartQuery().receive(query);

        assertTrue(result.next());
        assertEquals(1, result.getInt("ID"));
        assertEquals("Name", result.getString("Name"));
    }
    
    @Test public void command() throws Exception {
        final Connection conn = getConnection();
        Query query = new Query("DELETE FROM Adressen");
        
        ExecuteDatasource sut = new JdbcExecuteDatasourceQuery();
        done = false;
        
        sut.CommandDone().connect(new InTrigger() {
            
            @Override
            public void receive() {
                done = true;
            }
        });

        sut.Connection().connect(new QueryInPin<Object, Connection>() {

            @Override
            public void receive(Object message, InPin<Connection> response) {
                response.receive(conn);
            }
        });
        
        sut.StartCommand().receive(query);
        
        assertTrue(done);
        assertTrue(addressDeleted(conn));

    }

    private Connection getConnection() throws Exception {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection("jdbc:sqlite::memory:");
        Statement stmt = conn.createStatement();
        stmt.executeUpdate("CREATE TABLE Adressen (ID INTEGER, Name TEXT);");
        stmt.executeUpdate("INSERT INTO Adressen VALUES (1, 'Name')");
        return conn;
    }

    private boolean addressDeleted(Connection conn) throws Exception {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM Adressen");
        rs.next();
        int count = rs.getInt(1);
        return count == 0;
    }
}
