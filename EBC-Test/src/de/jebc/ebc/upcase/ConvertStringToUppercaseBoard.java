package de.jebc.ebc.upcase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.jebc.ebc.ServicePin;
import de.jebc.ebc.impl.QueryMonitor;
import de.jebc.ebc.impl.Board;
import de.jebc.ebc.parts.Cache;
import de.jebc.ebc.parts.ReadonlyCache;
import de.jebc.logging.query.LogDebugQuery;

public class ConvertStringToUppercaseBoard extends Board {

	private Logger log = LoggerFactory.getLogger(ConvertStringToUppercaseBoard.class);
    private ServicePin<String, String> request;

    public ServicePin<String, String> request() {
        return request;
    }

    public ConvertStringToUppercaseBoard() {
        // creating the parts
        ConvertToUpperCase upcase = new ConvertToUpperCaseImpl();
        Cache<String, String> cache = new ReadonlyCache<String, String>();

        QueryMonitor<String, String> loggerBehindCache = getBehindCacheLogger();
        QueryMonitor<String, String> loggerFrontCache = getFrontCacheLogger();
        
        // extend the open pins to the outside
        connect(loggerFrontCache.out(), cache.get());
        request = extend(loggerFrontCache.in());

        // plumbing the echo board
        monitor(cache.request(), join(upcase.request(), upcase.response()), with(loggerBehindCache));
    }

    private QueryMonitor<String, String> getBehindCacheLogger() {
        return new LogDebugQuery<String, String>(log) {

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
    private QueryMonitor<String, String> getFrontCacheLogger() {
        return new LogDebugQuery<String, String>(log) {

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