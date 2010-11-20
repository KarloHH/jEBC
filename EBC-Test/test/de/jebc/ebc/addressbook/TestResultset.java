package de.jebc.ebc.addressbook;

import de.jebc.ebc.addressbook.data.Resultset;

public class TestResultset implements Resultset {

    private String[] columnNames;
    private Object[][] rows;
    private int rowIndex;
    
    public TestResultset(String[] columnNames, Object[][] rows) {
        this.columnNames = columnNames;
        this.rows = rows;
        rowIndex = -1;
    }

    @Override
    public boolean next() {
        rowIndex++;
        return rowIndex < rows.length;
    }

    @Override
    public int getInt(String columnName) {
        int idx = getIndex(columnName);
        return (Integer) rows[rowIndex][idx];
    }

    @Override
    public String getString(String columnName) {
        int idx = getIndex(columnName);
        return (String) rows[rowIndex][idx];
    }

    private int getIndex(String columnName) {
        for (int i = 0; i < columnNames.length; i++) {
            String name = columnNames[i];
            if (name.equals(columnName))
                return i;
        }
        throw new IllegalArgumentException();
    }

}
