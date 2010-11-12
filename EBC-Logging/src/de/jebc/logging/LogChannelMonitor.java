package de.jebc.logging;

import org.slf4j.Logger;

import de.jebc.ebc.impl.AbstractChannelMonitor;

public abstract class LogChannelMonitor<T1, T2> extends
		AbstractChannelMonitor<T1, T2> {

	
	private final Logger log;

	public LogChannelMonitor(Logger log) {
		this.log = log;
	}

	@Override
	protected void inspectRequest(T1 message) {
		log.info(getRequest(), getStringRequest(message));
	}

	protected String getStringRequest(T1 message) {
		return message.toString();
	}

	protected abstract String getRequest();

	@Override
	protected void inspectResponse(T2 message) {
		log.info(getResponse(), getStringResponse(message));
	}

	protected String getStringResponse(T2 message) {
		return message.toString();
	}

	protected abstract String getResponse();

}
