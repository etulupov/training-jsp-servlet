package com.noveogroup.tulupov.guestbook.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents the form.
 */
public class Form {
    private static final String CSS_HAS_ERROR = "has-error";
    private static final String CSS_NONE = "";

    private Map<String, String> errors = new HashMap<>();

    public void put(final String field, final String error) {
        errors.put(field, error);
    }

    public boolean hasError(final String field) {
        return errors.containsKey(field);
    }

    public String getErrorCss(final String field) {
        return hasError(field) ? CSS_HAS_ERROR : CSS_NONE;
    }

    public String getErrorMessage(final String field) {
        return errors.get(field);
    }
}
