package de.jebc.logging.channel;

import org.slf4j.Logger;

import de.jebc.logging.LogChannel;

public abstract class LogWarnChannel<T1, T2> extends LogChannel<T1, T2> {

    public LogWarnChannel(Logger log) {
        super(log);
    }
    
    @Override
    protected boolean enabled() {
        return log.isWarnEnabled();
    }

    @Override
    protected void log(String text, String param) {
        log.warn(text, param);
    }
}
