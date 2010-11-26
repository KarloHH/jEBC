package de.jebc.ebc.addressbook;

import java.sql.Connection;
import java.sql.DriverManager;

import de.jebc.ebc.InPin;
import de.jebc.ebc.QueryInPin;

public class Configuration {

    protected Connection connection;
    private QueryInPin<Object, Connection> provideConnection = new QueryInPin<Object, Connection>() {

        @Override
        public void receive(Object message, InPin<Connection> response) {
            response.receive(connection);
        }
    };

    public Configuration(String[] args) throws Exception {
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:" + args[0]);
    }

    public QueryInPin<Object, Connection> ProvideConnection() {
        return provideConnection;
    }
}
