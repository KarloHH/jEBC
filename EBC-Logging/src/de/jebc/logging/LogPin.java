package de.jebc.logging;

import org.slf4j.Logger;

import de.jebc.ebc.impl.AbstractPinMonitor;

public abstract class LogPin<T> extends AbstractPinMonitor<T> {

	
	private final Logger log;

	public LogPin(Logger log) {
		this.log = log;
		
	}

	@Override
	protected void inspect(T message) {
		log.info(getMessage(), getString(message));
	}

	protected String getString(T message) {
		return message.toString();
	}

	protected abstract String getMessage();


}
