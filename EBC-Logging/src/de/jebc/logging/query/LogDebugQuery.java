package de.jebc.logging.query;

import org.slf4j.Logger;

import de.jebc.logging.AbstractLogQuery;

public abstract class LogDebugQuery<T1, T2> extends AbstractLogQuery<T1, T2> {

    public LogDebugQuery(Logger log) {
        super(log);
    }

    @Override
    protected boolean enabled() {
        return log.isDebugEnabled();
    }

    @Override
    protected void log(String text) {
        log.debug(text);
    }
}
