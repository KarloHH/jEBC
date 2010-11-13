package de.jebc.logging.channel;

import org.slf4j.Logger;

import de.jebc.logging.LogChannel;

public abstract class LogDebugChannel<T1, T2> extends LogChannel<T1, T2> {

    public LogDebugChannel(Logger log) {
        super(log);
    }

    @Override
    protected boolean enabled() {
        return log.isDebugEnabled();
    }
    
    @Override
    protected void log(String text, String param) {
        log.debug(text, param);
    }
}
