package de.jebc.ebc.impl;

import de.jebc.ebc.InPin;
import de.jebc.ebc.QueryPin;
import de.jebc.ebc.ServicePin;

public abstract class QueryMonitor<T1, T2>  {

	private QueryPin<T1, T2> monitorOut = new QueryPinImpl<T1, T2>();
	private ServicePin<T1, T2> monitorIn = new ServicePin<T1, T2>() {

		@Override
		public void receive(T1 message, final InPin<T2> originalResponse) {
			inspectRequest(message);
			monitorOut.send(message, new InPin<T2>() {

				@Override
				public void receive(T2 message) {
					inspectResponse(message);
					originalResponse.receive(message);
				}
			});
		}
	};
	

    public QueryPin<T1, T2> out() {
	    return monitorOut;
	}
	
    public ServicePin<T1, T2> in() {
	    return monitorIn;
	}
	protected abstract void inspectRequest(T1 message);
	protected abstract void inspectResponse(T2 message);

}
