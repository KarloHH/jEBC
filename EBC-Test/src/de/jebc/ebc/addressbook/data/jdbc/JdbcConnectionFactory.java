package de.jebc.ebc.addressbook.data.jdbc;

import java.sql.Connection;

import de.jebc.ebc.addressbook.data.ConnectionFactory;

public class JdbcConnectionFactory implements ConnectionFactory {

    private Connection connection;

    public JdbcConnectionFactory(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Connection getConnection() {
        return connection;
    }

}
