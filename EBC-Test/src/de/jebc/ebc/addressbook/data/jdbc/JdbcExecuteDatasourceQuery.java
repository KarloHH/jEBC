package de.jebc.ebc.addressbook.data.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import de.jebc.ebc.InPin;
import de.jebc.ebc.OutPin;
import de.jebc.ebc.OutTrigger;
import de.jebc.ebc.addressbook.data.ConnectionFactory;
import de.jebc.ebc.addressbook.data.Query;
import de.jebc.ebc.addressbook.data.Resultset;
import de.jebc.ebc.impl.BroadcastOutTrigger;
import de.jebc.ebc.impl.SingleOutPin;

public class JdbcExecuteDatasourceQuery implements ExecuteDatasource {

    private InPin<Query> startQueryPin = new InPin<Query>() {

        @Override
        public void receive(Query message) {
            processQuery(message);
        }
    };
    private OutPin<Resultset> resultQueryPin = new SingleOutPin<Resultset>();
    private InPin<Query> startCommandPin = new InPin<Query>() {

        @Override
        public void receive(Query message) {
            processCommand(message);
        }
    };
    private OutTrigger commandDonePin = new BroadcastOutTrigger();
    private InPin<Query> startIdentityPin = new InPin<Query>() {

        @Override
        public void receive(Query message) {
            processIdentity(message);
        }
    };
    private OutPin<Integer> resultIdentityPin = new SingleOutPin<Integer>();
    private final ConnectionFactory factory;

    public JdbcExecuteDatasourceQuery(ConnectionFactory connection) {
        this.factory = connection;
    }

    private void processQuery(final Query query) {
        Connection conn = factory.getConnection();
        String command = generateCommand(query);
        ResultSet rs = null;
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(command);
            Resultset result = new JdbcResultsetFacade(rs);
            ResultQuery().send(result);
        } catch (SQLException e) {
            // TODO exception handling
            e.printStackTrace();
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (stmt != null)
                    stmt.close();
            } catch (SQLException e2) {
                // TODO exception handling
                e2.printStackTrace();
            }
        }
    }

    protected void processIdentity(final Query query) {
        Connection conn = factory.getConnection();
                String command = generateCommand(query);
                ResultSet rs = null;
                Statement stmt = null;
                int result = 0;
                try {
                    stmt = conn.createStatement();
                    stmt.execute(command);
                    rs = stmt.getGeneratedKeys();
                    while (rs.next()) {
                        result = rs.getInt(1);
                    }
                    ResultIdentity().send(result);
                } catch (SQLException e) {
                    // TODO exception handling
                    e.printStackTrace();
                } finally {
                    try {
                        if (rs != null)
                            rs.close();
                        if (stmt != null)
                            stmt.close();
                    } catch (SQLException e2) {
                        // TODO exception handling
                        e2.printStackTrace();
                    }
                }
            }

    private void processCommand(final Query query) {
        Connection conn = factory.getConnection();
                String command = generateCommand(query);
                Statement stmt = null;
                try {
                    stmt = conn.createStatement();
                    stmt.execute(command);
                    CommandDone().send();
                } catch (SQLException e) {
                    // TODO exception handling
                    e.printStackTrace();
                } finally {
                    try {
                        if (stmt != null)
                            stmt.close();
                    } catch (SQLException e2) {
                        // TODO exception handling
                        e2.printStackTrace();
                    }
                }
            }

    private String generateCommand(Query query) {
        return query.getCommand();
    }

    @Override
    public InPin<Query> StartQuery() {
        return startQueryPin;
    }

    @Override
    public OutPin<Resultset> ResultQuery() {
        return resultQueryPin;
    }

    @Override
    public InPin<Query> StartCommand() {
        return startCommandPin;
    }

    @Override
    public OutTrigger CommandDone() {
        return commandDonePin;
    }

    @Override
    public InPin<Query> StartIdentity() {
        return startIdentityPin;
    }

    @Override
    public OutPin<Integer> ResultIdentity() {
        return resultIdentityPin;
    }

}
