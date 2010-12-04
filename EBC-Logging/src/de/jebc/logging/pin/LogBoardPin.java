package de.jebc.logging.pin;

import de.jebc.ebc.impl.Monitor;
import de.jebc.logging.AbstractLogPin;

public class LogBoardPin<T> extends Monitor<T> {

    private final Iterable<? extends AbstractLogPin<T>> loggers;

    public LogBoardPin(Iterable<? extends AbstractLogPin<T>> loggers) {
        this.loggers = loggers;
    }

    @Override
    protected void inspect(T message) {
        for (AbstractLogPin<T> logger : loggers) {
            logger.log(message);
        }
    }

}
