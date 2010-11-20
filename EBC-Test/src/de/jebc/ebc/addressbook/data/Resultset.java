package de.jebc.ebc.addressbook.data;

public interface Resultset {
    boolean next();
    int getInt(String columnName);
    String getString(String columnName);
}
