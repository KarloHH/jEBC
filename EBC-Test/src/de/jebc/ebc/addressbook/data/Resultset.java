package de.jebc.ebc.addressbook.data;

public interface Resultset {
    boolean next() throws DataException;
    int getInt(String columnName) throws DataException;
    String getString(String columnName) throws DataException;
}
