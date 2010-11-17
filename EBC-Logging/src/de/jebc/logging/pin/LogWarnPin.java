package de.jebc.logging.pin;

import org.slf4j.Logger;

import de.jebc.logging.AbstractLogPin;

public abstract class LogWarnPin<T> extends AbstractLogPin<T> {

    public LogWarnPin(Logger log) {
        super(log);
    }

    @Override
    protected abstract String getLogMessage(T message);

    @Override
    protected boolean enabled() {
        return log.isWarnEnabled();
    }

    @Override
    protected void log(String text) {
        log.warn(text);
    }

}
