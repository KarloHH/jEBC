package de.jebc.ebc.upcase;

import de.jebc.ebc.impl.Filter;

public class ConvertToUpperCase extends Filter<String, String> {

    @Override
    protected String filter(String message) {
        return message.toUpperCase();
    }

}
