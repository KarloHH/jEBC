package de.jebc.ebc.addressbook.data;

public class Query {

    private final String command;

    public Query(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }
}
