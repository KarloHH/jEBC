package de.jebc.logging;

import org.slf4j.Logger;

import de.jebc.ebc.impl.AbstractChannelMonitor;

public abstract class LogChannel<T1, T2> extends
		AbstractChannelMonitor<T1, T2> {

	
	protected final Logger log;

	public LogChannel(Logger log) {
		this.log = log;
	}

	@Override
	protected void inspectRequest(T1 message) {
		if (enabled()) {
            log(getRequest(), getStringRequest(message));
        }
	}

	protected String getStringRequest(T1 message) {
		return message.toString();
	}

	protected abstract String getRequest();

	@Override
	protected void inspectResponse(T2 message) {
		if (enabled()) {
            log(getResponse(), getStringResponse(message));
        }
	}

	protected String getStringResponse(T2 message) {
		return message.toString();
	}

	protected abstract String getResponse();

	protected abstract boolean enabled();
	protected abstract void log(String text, String param);
}
