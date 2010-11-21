package de.jebc.ebc.upcase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.jebc.ebc.QueryInPin;
import de.jebc.ebc.impl.Filter;
import de.jebc.ebc.impl.QueryMonitor;
import de.jebc.ebc.impl.Board;
import de.jebc.ebc.parts.Cache;
import de.jebc.ebc.parts.ReadonlyCache;
import de.jebc.logging.query.LogDebugQuery;

public class ConvertStringToUppercaseBoard extends Board {

    private Logger log = LoggerFactory
            .getLogger(ConvertStringToUppercaseBoard.class);
    private QueryInPin<String, String> request;

    public QueryInPin<String, String> request() {
        return request;
    }

    public ConvertStringToUppercaseBoard() {
        // creating the parts
        Filter<String, String> upcase = new ConvertToUpperCase();
        Cache<String, String> cache = new ReadonlyCache<String, String>();

        QueryMonitor<String, String> loggerBehindCache = getBehindCacheLogger();
        QueryMonitor<String, String> loggerFrontCache = getFrontCacheLogger();

        // extend the open pins to the outside
        connect(loggerFrontCache.out(), cache.get());
        request = extend(loggerFrontCache.in());

        // plumbing the echo board
        monitor(cache.request(), join(upcase.in(), upcase.out()),
                with(loggerBehindCache));
    }

    private QueryMonitor<String, String> getBehindCacheLogger() {
        return new LogDebugQuery<String, String>(log) {

            @Override
            protected String getLogMessageForRequest(String message) {
                return "Cache is requesting a value for " + message;
            }

            @Override
            protected String getLogMessageForResponse(String message) {
                return "Cache gets value " + message;
            }
        };
    }

    private QueryMonitor<String, String> getFrontCacheLogger() {
        return new LogDebugQuery<String, String>(log) {

            @Override
            protected String getLogMessageForRequest(String message) {
                return "Cache is requested for a value for " + message;
            }

            @Override
            protected String getLogMessageForResponse(String message) {
                return "Cache returns value " + message;
            }
        };
    }
}