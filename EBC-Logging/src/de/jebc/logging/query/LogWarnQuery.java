package de.jebc.logging.query;

import org.slf4j.Logger;

import de.jebc.logging.AbstractLogQuery;

public abstract class LogWarnQuery<T1, T2> extends AbstractLogQuery<T1, T2> {

    public LogWarnQuery(Logger log) {
        super(log);
    }

    @Override
    protected boolean enabled() {
        return log.isWarnEnabled();
    }

    @Override
    protected void log(String text) {
        log.warn(text);
    }
}
