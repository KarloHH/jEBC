package de.jebc.logging.pin;

import org.slf4j.Logger;

import de.jebc.logging.AbstractLogPin;

public abstract class LogTracePin<T> extends AbstractLogPin<T> {

    public LogTracePin(Logger log) {
        super(log);
    }

    @Override
    protected abstract String getLogMessage(T message);

    @Override
    protected boolean enabled() {
        return log.isTraceEnabled();
    }

    @Override
    protected void log(String text) {
        log.trace(text);
    }

}
