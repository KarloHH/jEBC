package de.jebc.logging;

import org.slf4j.Logger;

import de.jebc.ebc.impl.Monitor;

public abstract class AbstractLogPin<T> extends Monitor<T> {

	
	protected final Logger log;

	public AbstractLogPin(Logger log) {
		this.log = log;
		
	}

	@Override
	protected void inspect(T message) {
		if (enabled()) {
		    log(getMessage(), getString(message));
		}
	}

	protected String getString(T message) {
		return message.toString();
	}

	protected abstract String getMessage();

	protected abstract boolean enabled();
	
	protected abstract void log(String text, String param);

}
