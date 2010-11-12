package de.jebc.ebc.proof;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.jebc.ebc.InChannel;
import de.jebc.ebc.impl.AbstractBoard;
import de.jebc.ebc.parts.Cache;
import de.jebc.ebc.parts.ReadonlyCache;
import de.jebc.logging.LogChannelMonitor;

public class UpcaseBoard extends AbstractBoard {

	private Logger log = LoggerFactory.getLogger(UpcaseBoard.class);
    private InChannel<String, String> request;

    public InChannel<String, String> Request() {
        return request;
    }

    public UpcaseBoard() {
        // creating the parts
        ConvertToUpperCase upcase = new ConvertToUpperCaseImpl();
        Cache<String, String> cache = new ReadonlyCache<String, String>();

        LogChannelMonitor<String, String> requestToService = new LogChannelMonitor<String, String>(log) {

			@Override
			protected String getRequest() {
				return "Cache is requesting a value for {}";
			}

			@Override
			protected String getResponse() {
				return "Cache gets value {}";
			}
		};
        
        // extend the open pins to the outside
        request = extend(cache.Get());

        // plumbing the echo board
        monitor(cache.Request(), join(upcase.Request(), upcase.Response()), with(requestToService));
    }
}