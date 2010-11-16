package de.jebc.ebc.impl;

import de.jebc.ebc.InPin;
import de.jebc.ebc.OutPin;

public abstract class Filter<T1, T2> {

	private OutPin<T2> filterOut = new SingleOutPin<T2>();
	private InPin<T1> filterIn = new InPin<T1>() {

		@Override
		public void receive(T1 message) {
			T2 result = filter(message);
			filterOut.send(result);
		}
	};

    public OutPin<T2> out() {
	    return filterOut;
	}
	
    public InPin<T1> in() {
	    return filterIn;
	}
    
	protected abstract T2 filter(T1 message);

}
