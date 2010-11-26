package de.jebc.ebc.addressbook.data.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import de.jebc.ebc.InPin;
import de.jebc.ebc.OutPin;
import de.jebc.ebc.QueryOutPin;
import de.jebc.ebc.addressbook.data.Query;
import de.jebc.ebc.addressbook.data.Resultset;
import de.jebc.ebc.impl.QueryPinImpl;
import de.jebc.ebc.impl.SingleOutPin;

public class JdbcExecuteDatasourceQuery implements ExecuteDatasource {

    private QueryOutPin<Object, Connection> connectionQuery = new QueryPinImpl<Object, Connection>();
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
    private OutPin<Object> commandDonePin = new SingleOutPin<Object>();

    private void processQuery(final Query query) {
        connectionQuery.send(null, new InPin<Connection>() {

            @Override
            public void receive(Connection conn) {
                String command = generateCommand(query);
                ResultSet rs = null;
                try {
                    Statement stmt = conn.createStatement();
                    rs = stmt.executeQuery(command);
                } catch (SQLException e) {
                    // TODO exception handling
                    e.printStackTrace();
                }
                Resultset result = new JdbcResultsetFacade(rs);
                ResultQuery().send(result);
            }

        });
    }

    private void processCommand(final Query query) {
        connectionQuery.send(null, new InPin<Connection>() {

            @Override
            public void receive(Connection conn) {
                String command = generateCommand(query);
                try {
                    Statement stmt = conn.createStatement();
                    stmt.execute(command);
                } catch (SQLException e) {
                    // TODO exception handling
                    e.printStackTrace();
                }
                CommandDone().send(null);
            }

        });
    }

    private String generateCommand(Query query) {
        return query.getCommand();
    }
    @Override
    public QueryOutPin<Object, Connection> Connection() {
        return connectionQuery;
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
    public OutPin<Object> CommandDone() {
        return commandDonePin;
    }

}
