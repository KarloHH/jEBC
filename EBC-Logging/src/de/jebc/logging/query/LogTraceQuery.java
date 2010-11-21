package de.jebc.logging.query;

import org.slf4j.Logger;

import de.jebc.logging.AbstractLogQuery;

public abstract class LogTraceQuery<T1, T2> extends AbstractLogQuery<T1, T2> {

    public LogTraceQuery(Logger log) {
        super(log);
    }

    @Override
    protected boolean enabled() {
        return log.isTraceEnabled();
    }

    @Override
    protected void log(String text) {
        log.trace(text);
    }
}
