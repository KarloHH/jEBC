package de.jebc.ebc.proof;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.jebc.ebc.ChannelFilter;
import de.jebc.ebc.InChannel;
import de.jebc.ebc.impl.AbstractBoard;
import de.jebc.ebc.parts.Cache;
import de.jebc.ebc.parts.ReadonlyCache;
import de.jebc.logging.LogChannelMonitor;

public class UpcaseBoard extends AbstractBoard {

	private Logger log = LoggerFactory.getLogger(UpcaseBoard.class);
    private InChannel<String, String> request;

    public InChannel<String, String> request() {
        return request;
    }

    public UpcaseBoard() {
        // creating the parts
        ConvertToUpperCase upcase = new ConvertToUpperCaseImpl();
        Cache<String, String> cache = new ReadonlyCache<String, String>();

        ChannelFilter<String, String> loggerBehindCache = getBehindCacheLogger();
        ChannelFilter<String, String> loggerFrontCache = getFrontCacheLogger();
        
        // extend the open pins to the outside
        connect(loggerFrontCache.out(), cache.get());
        request = extend(loggerFrontCache.in());

        // plumbing the echo board
        monitor(cache.request(), join(upcase.request(), upcase.response()), with(loggerBehindCache));
    }

    private LogChannelMonitor<String, String> getBehindCacheLogger() {
        return new LogChannelMonitor<String, String>(log) {

            @Override
            protected String getRequest() {
                return "Cache is requesting a value for {}";
            }

            @Override
            protected String getResponse() {
                return "Cache gets value {}";
            }
        };
    }
    private LogChannelMonitor<String, String> getFrontCacheLogger() {
        return new LogChannelMonitor<String, String>(log) {

            @Override
            protected String getRequest() {
                return "Cache is requested for a value for {}";
            }

            @Override
            protected String getResponse() {
                return "Cache returns value {}";
            }
        };
    }
}