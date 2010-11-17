package de.jebc.logging;

import org.slf4j.Logger;

import de.jebc.ebc.impl.QueryMonitor;

public abstract class AbstractLogQuery<T1, T2> extends
		QueryMonitor<T1, T2> {

	
	protected final Logger log;

	public AbstractLogQuery(Logger log) {
		this.log = log;
	}

	@Override
	protected void inspectRequest(T1 message) {
		if (enabled()) {
            log(getLogMessageForRequest(message));
        }
	}

	protected abstract String getLogMessageForRequest(T1 message);

	@Override
	protected void inspectResponse(T2 message) {
		if (enabled()) {
            log(getLogMessageForResponse(message));
        }
	}

	protected abstract String getLogMessageForResponse(T2 message);

	protected abstract boolean enabled();
	protected abstract void log(String text);
}
