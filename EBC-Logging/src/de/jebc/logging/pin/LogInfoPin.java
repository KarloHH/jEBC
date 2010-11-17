package de.jebc.logging.pin;

import org.slf4j.Logger;

import de.jebc.logging.AbstractLogPin;

public abstract class LogInfoPin<T> extends AbstractLogPin<T> {

    public LogInfoPin(Logger log) {
        super(log);
    }

    @Override
    protected abstract String getLogMessage(T message);

    @Override
    protected boolean enabled() {
        return log.isInfoEnabled();
    }

    @Override
    protected void log(String text) {
        log.info(text);
    }

}
