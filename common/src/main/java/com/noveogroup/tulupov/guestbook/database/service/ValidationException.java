package com.noveogroup.tulupov.guestbook.database.service;


import com.noveogroup.tulupov.guestbook.model.GuestbookEntry;
import lombok.Getter;

import javax.validation.ConstraintViolation;
import java.util.Set;


/**
 * Validation exception.
 */
public class ValidationException extends RuntimeException {
    @Getter
    private Set<ConstraintViolation<GuestbookEntry>> results;

    public ValidationException(Set<ConstraintViolation<GuestbookEntry>> results) {
        this.results = results;
    }
}
