package de.jebc.ebc.addressbook.data;

public class Query {

    private final String viewname;
    private final String[] columns;

    public Query(String viewname, String[] columns) {
        this.viewname = viewname;
        this.columns = columns;
    }

    public String getViewname() {
        return viewname;
    }

    public String[] getColumns() {
        return columns;

    }

}
