package de.jebc.ebc.impl;

import de.jebc.ebc.ChannelMonitor;
import de.jebc.ebc.InChannel;
import de.jebc.ebc.InPin;
import de.jebc.ebc.OutChannel;

public abstract class AbstractChannelMonitor<T1, T2> implements
		ChannelMonitor<T1, T2> {

	private OutChannel<T1, T2> monitorOut = new OutChannelImpl<T1, T2>();
	private InChannel<T1, T2> monitorIn = new InChannel<T1, T2>() {

		@Override
		public void receive(T1 message, final InPin<T2> originalResponse) {
			inspectRequest(message);
			monitorOut.transmit(message, new InPin<T2>() {

				@Override
				public void receive(T2 message) {
					inspectResponse(message);
					originalResponse.receive(message);
				}
			});
		}
	};
	

	@Override
    public OutChannel<T1, T2> MonitorOut() {
	    return monitorOut;
	}
	
	@Override
    public InChannel<T1, T2> MonitorIn() {
	    return monitorIn;
	}
	protected abstract void inspectRequest(T1 message);
	protected abstract void inspectResponse(T2 message);

}
