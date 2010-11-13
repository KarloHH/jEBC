package de.jebc.logging.channel;

import org.slf4j.Logger;

import de.jebc.logging.LogChannel;

public abstract class LogErrorChannel<T1, T2> extends LogChannel<T1, T2> {

    public LogErrorChannel(Logger log) {
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
