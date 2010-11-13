package de.jebc.logging.channel;

import org.slf4j.Logger;

import de.jebc.logging.LogChannel;

public abstract class LogInfoChannel<T1, T2> extends LogChannel<T1, T2> {

    public LogInfoChannel(Logger log) {
        super(log);
    }
    
    @Override
    protected boolean enabled() {
        return log.isInfoEnabled();
    }

    @Override
    protected void log(String text, String param) {
        log.info(text, param);
    }
}
