package de.jebc.ebc.impl;

import de.jebc.ebc.InPin;
import de.jebc.ebc.OutPin;
import de.jebc.ebc.PinMonitor;

public abstract class AbstractPinMonitor<T> implements PinMonitor<T> {

	private OutPin<T> monitorOut = new SingleOutPin<T>();
	private InPin<T> monitorIn = new InPin<T>() {

		@Override
		public void receive(T message) {
			inspect(message);
			monitorOut.transmit(message);
		}
	};

	@Override
    public OutPin<T> MonitorOut() {
	    return monitorOut;
	}
	
	@Override
    public InPin<T> MonitorIn() {
	    return monitorIn;
	}
	protected abstract void inspect(T message);

}
