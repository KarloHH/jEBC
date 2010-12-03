package de.jebc.ebc.addressbook;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import de.jebc.ebc.InPin;
import de.jebc.ebc.QueryInPin;

public class ConfigureConnection {

    protected Connection connection;
    private QueryInPin<Object, Connection> provideConnection = new QueryInPin<Object, Connection>() {

        @Override
        public void receive(Object message, InPin<Connection> response) {
            response.receive(connection);
        }
    };

    public ConfigureConnection(String[] args) throws Exception {
        Class.forName("org.sqlite.JDBC");
        String dbname = args.length > 0 ? args[0] : "/home/olaf/test.db";
        connection = DriverManager.getConnection("jdbc:sqlite:" + dbname);
        checkTables();
    }

    private void checkTables() throws Exception {
        Statement stmt = connection.createStatement();
        stmt.execute("CREATE TABLE IF NOT EXISTS Adressen (ID INTEGER PRIMARY KEY AUTOINCREMENT, Category TEXT, Name TEXT, GivenName TEXT,"
                + "ZipCode TEXT, City TEXT, Street TEXT, Country TEXT);");
    }

    public QueryInPin<Object, Connection> ProvideConnection() {
        return provideConnection;
    }
}
