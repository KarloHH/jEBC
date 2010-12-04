package de.jebc.ebc.addressbook;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.Test;

import de.jebc.ebc.InPin;
import de.jebc.ebc.InTrigger;
import de.jebc.ebc.addressbook.data.ConnectionFactory;
import de.jebc.ebc.addressbook.data.DataException;
import de.jebc.ebc.addressbook.data.Query;
import de.jebc.ebc.addressbook.data.Resultset;
import de.jebc.ebc.addressbook.data.jdbc.ExecuteDatasource;
import de.jebc.ebc.addressbook.data.jdbc.JdbcExecuteDatasourceQuery;

public class TestExecuteDatasourceQuery {

    protected Resultset result;
    private boolean done;

    @Test
    public void query() throws Exception {

        final ConnectionFactory conn = getConnection();
        Query query = new Query("SELECT ID, Name FROM Adressen");

        ExecuteDatasource sut = new JdbcExecuteDatasourceQuery(conn);

        sut.ResultQuery().connect(new InPin<Resultset>() {

            @Override
            public void receive(Resultset result) {
                try {
                    assertTrue(result.next());
                    assertEquals(1, result.getInt("ID"));
                    assertEquals("Name", result.getString("Name"));
                    done = true;
                } catch (DataException e) {
                    throw new AssertionError();
                }
            }
        });

        sut.StartQuery().receive(query);

        assertTrue(done);
    }

    @Test
    public void command() throws Exception {
        final ConnectionFactory conn = getConnection();
        Query query = new Query("DELETE FROM Adressen");

        ExecuteDatasource sut = new JdbcExecuteDatasourceQuery(conn);
        done = false;

        sut.CommandDone().connect(new InTrigger() {

            @Override
            public void receive() {
                done = true;
            }
        });

        sut.StartCommand().receive(query);

        assertTrue(done);
        assertTrue(addressDeleted(conn));

    }

    private ConnectionFactory getConnection() throws Exception {
        Class.forName("org.sqlite.JDBC");
        final Connection conn = DriverManager
                .getConnection("jdbc:sqlite::memory:");
        Statement stmt = conn.createStatement();
        stmt.executeUpdate("CREATE TABLE Adressen (ID INTEGER, Name TEXT);");
        stmt.executeUpdate("INSERT INTO Adressen VALUES (1, 'Name')");
        return new ConnectionFactory() {

            @Override
            public Connection getConnection() {
                return conn;
            }
        };
    }

    private boolean addressDeleted(ConnectionFactory conn) throws Exception {
        Statement stmt = conn.getConnection().createStatement();
        ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM Adressen");
        rs.next();
        int count = rs.getInt(1);
        return count == 0;
    }
}
