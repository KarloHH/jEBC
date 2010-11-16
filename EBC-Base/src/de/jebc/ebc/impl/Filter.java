package de.jebc.ebc.impl;

import de.jebc.ebc.InPin;
import de.jebc.ebc.OutPin;

public abstract class Filter<TInput, TOutput> {

	private OutPin<TOutput> filterOut = new SingleOutPin<TOutput>();
	private InPin<TInput> filterIn = new InPin<TInput>() {

		@Override
		public void receive(TInput message) {
			TOutput result = filter(message);
			filterOut.send(result);
		}
	};

    public OutPin<TOutput> out() {
	    return filterOut;
	}
	
    public InPin<TInput> in() {
	    return filterIn;
	}
    
	protected abstract TOutput filter(TInput message);

}
