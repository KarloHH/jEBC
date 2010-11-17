package de.jebc.logging.query;

import org.slf4j.Logger;

import de.jebc.logging.AbstractLogQuery;

public abstract class LogInfoQuery<T1, T2> extends AbstractLogQuery<T1, T2> {

    public LogInfoQuery(Logger log) {
        super(log);
    }
    
    @Override
    protected boolean enabled() {
        return log.isInfoEnabled();
    }

    @Override
    protected void log(String text) {
        log.info(text);
    }
}
