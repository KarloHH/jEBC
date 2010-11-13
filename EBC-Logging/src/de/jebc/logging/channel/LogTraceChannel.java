package de.jebc.logging.channel;

import org.slf4j.Logger;

import de.jebc.logging.LogChannel;

public abstract class LogTraceChannel<T1, T2> extends LogChannel<T1, T2> {

    public LogTraceChannel(Logger log) {
        super(log);
    }
    
    @Override
    protected boolean enabled() {
        return log.isTraceEnabled();
    }

    @Override
    protected void log(String text, String param) {
        log.trace(text, param);
    }
}
