package de.jebc.ebc.addressbook.data.jdbc;

import de.jebc.ebc.addressbook.data.DataException;
import de.jebc.ebc.addressbook.data.Resultset;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcResultsetFacade implements Resultset {

    private final ResultSet rs;

    public JdbcResultsetFacade(ResultSet rs) {
        this.rs = rs;
    }

    @Override
    public boolean next() throws DataException {
        try {
            return rs.next();
        } catch (SQLException e) {
            throw new DataException(e);
        }
    }

    @Override
    public int getInt(String columnName) throws DataException {
        try {
            return rs.getInt(columnName);
        } catch (SQLException e) {
            throw new DataException(e);
        }
    }

    @Override
    public String getString(String columnName) throws DataException {
        try {
            return rs.getString(columnName);
        } catch (SQLException e) {
            throw new DataException(e);
        }
    }

}
