package de.jebc.ebc.addressbook.data.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import de.jebc.ebc.InPin;
import de.jebc.ebc.QueryOutPin;
import de.jebc.ebc.addressbook.data.ExecuteDatasourceQuery;
import de.jebc.ebc.addressbook.data.Query;
import de.jebc.ebc.addressbook.data.Resultset;
import de.jebc.ebc.impl.ProcessImpl;
import de.jebc.ebc.impl.QueryPinImpl;

public class JdbcExecuteDatasourceQuery extends ProcessImpl<Query, Resultset> implements ExecuteDatasourceQuery {

    private QueryOutPin<Object, Connection> connectionQuery = new QueryPinImpl<Object, Connection>();

    @Override
    protected void process(final Query query) {
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
                Result().send(result);
            }

            private String generateCommand(Query query) {
                return query.getCommand();
            }
        });
    }
    /*
     * (non-Javadoc)
     * 
     * @see
     * de.jebc.ebc.addressbook.data.jdbc.ExecuteDatasourceQuery#GetConnection()
     */
    @Override
    public QueryOutPin<Object, Connection> Connection() {
        return connectionQuery;
    }

}
