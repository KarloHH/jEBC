package de.jebc.ebc.addressbook;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.swing.tree.TreeModel;

import org.junit.Test;

import de.jebc.ebc.InPin;
import de.jebc.ebc.QueryInPin;
import de.jebc.ebc.addressbook.activities.DisplayTreeOfAllAdresses;
import de.jebc.ebc.addressbook.data.jdbc.JdbcExecuteDatasourceQuery;

public class TestDisplayTreeOfAllAdresses {

    protected TreeModel result = null;

    @Test
    public void testIntegration() throws Exception {

        final Connection conn = prepareDatabase();
        JdbcExecuteDatasourceQuery execute = new JdbcExecuteDatasourceQuery();

        DisplayTreeOfAllAdresses sut = new DisplayTreeOfAllAdresses(execute);

        sut.tree().connect(new InPin<TreeModel>() {

            @Override
            public void receive(TreeModel message) {
                result = message;
            }
        });

        sut.connection().connect(new QueryInPin<Object, Connection>() {

            @Override
            public void receive(Object message, InPin<Connection> response) {
                response.receive(conn);
            }
        });

        sut.start().receive(null);

        assertNotNull(result);
    }

    private Connection prepareDatabase() throws Exception {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection("jdbc:sqlite::memory:");
        Statement stmt = conn.createStatement();
        stmt.executeUpdate("CREATE TABLE Adressen (ID INTEGER, Category TEXT, Name TEXT, GivenName TEXT);");
        stmt.executeUpdate("INSERT INTO Adressen VALUES (1, 'Büro', 'Name', 'Vorname')");
        return conn;
    }
}
