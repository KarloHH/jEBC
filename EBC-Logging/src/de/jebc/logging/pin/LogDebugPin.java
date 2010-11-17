package de.jebc.logging.pin;

import org.slf4j.Logger;

import de.jebc.logging.AbstractLogPin;

public abstract class LogDebugPin<T> extends AbstractLogPin<T> {

    public LogDebugPin(Logger log) {
        super(log);
    }

    @Override
    protected abstract String getLogMessage(T message);

    @Override
    protected boolean enabled() {
        return log.isDebugEnabled();
    }

    @Override
    protected void log(String text) {
        log.debug(text);
    }

}
