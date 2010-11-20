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

public class ExecuteDatasourceQuery {

    private OutPin<Resultset> resultPin = new SingleOutPin<Resultset>();
    private QueryOutPin<Object, Connection> connectionQuery = new QueryPinImpl<Object, Connection>();
    private InPin<Query> queryPin = new InPin<Query>() {

        @Override
        public void receive(final Query query) {
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
                    StringBuilder sb = new StringBuilder("SELECT ");
                    String[] columns = query.getColumns();
                    sb.append(columns[0]);
                    for (int i = 1; i < columns.length; i++) {
                        sb.append(", ").append(columns[i]);
                    }
                    sb.append(" FROM ").append(query.getViewname());
                    return sb.toString();
                }
            });
        }
    };

    public OutPin<Resultset> Result() {
        return resultPin;
    }

    public QueryOutPin<Object, Connection> GetConnection() {
        return connectionQuery;
    }

    public InPin<Query> Query() {
        return queryPin;
    }

}
