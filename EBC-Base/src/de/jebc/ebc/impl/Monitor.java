package de.jebc.ebc.impl;

import de.jebc.ebc.InPin;
import de.jebc.ebc.OutPin;

public abstract class Monitor<T> {

	private OutPin<T> filterOut = new SingleOutPin<T>();
	private InPin<T> filterIn = new InPin<T>() {

		@Override
		public void receive(T message) {
			inspect(message);
			filterOut.send(message);
		}
	};

    public OutPin<T> out() {
	    return filterOut;
	}
	
    public InPin<T> in() {
	    return filterIn;
	}
	protected abstract void inspect(T message);

}
