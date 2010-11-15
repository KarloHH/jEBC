package de.jebc.logging.pin;

import org.slf4j.Logger;

import de.jebc.logging.AbstractLogPin;

public abstract class LogTracePin<T> extends AbstractLogPin<T> {

    public LogTracePin(Logger log) {
        super(log);
    }

    @Override
    protected abstract String getMessage();

    @Override
    protected boolean enabled() {
        return log.isTraceEnabled();
    }

    @Override
    protected void log(String text, String param) {
        log.trace(text, param);
    }

}
