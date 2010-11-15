package de.jebc.logging.pin;

import org.slf4j.Logger;

import de.jebc.logging.AbstractLogPin;

public abstract class LogErrorPin<T> extends AbstractLogPin<T> {

    public LogErrorPin(Logger log) {
        super(log);
    }

    @Override
    protected abstract String getMessage();

    @Override
    protected boolean enabled() {
        return log.isErrorEnabled();
    }

    @Override
    protected void log(String text, String param) {
        log.error(text, param);
    }

}
