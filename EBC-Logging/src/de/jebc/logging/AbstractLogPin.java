package de.jebc.logging;

import org.slf4j.Logger;

import de.jebc.ebc.impl.Monitor;

public abstract class AbstractLogPin<T> extends Monitor<T> {

    protected final Logger log;

    public AbstractLogPin(Logger log) {
        this.log = log;

    }

    public void log(T message) {
        if (enabled()) {
            log(getLogMessage(message));
        }
    }

    @Override
    protected void inspect(T message) {
        log(message);
    }

    protected abstract String getLogMessage(T message);

    protected abstract boolean enabled();

    protected abstract void log(String text);

}
