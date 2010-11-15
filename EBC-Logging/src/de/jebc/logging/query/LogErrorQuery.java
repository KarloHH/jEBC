package de.jebc.logging.query;

import org.slf4j.Logger;

import de.jebc.logging.AbstractLogQuery;

public abstract class LogErrorQuery<T1, T2> extends AbstractLogQuery<T1, T2> {

    public LogErrorQuery(Logger log) {
        super(log);
    }

    @Override
    protected boolean enabled() {
        return log.isErrorEnabled();
    }
    
    @Override
    protected void log(String text, String param) {
        log.error(text, param);
    }
}
