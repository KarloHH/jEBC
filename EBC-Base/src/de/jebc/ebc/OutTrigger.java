package de.jebc.ebc;

public interface OutTrigger {
    void connect(InTrigger in);
    void send();
}
