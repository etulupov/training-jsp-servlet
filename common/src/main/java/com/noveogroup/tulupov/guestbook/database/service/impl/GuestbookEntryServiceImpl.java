package com.noveogroup.tulupov.guestbook.database.service.impl;

import com.noveogroup.tulupov.guestbook.database.dao.GuestbookEntryDao;
import com.noveogroup.tulupov.guestbook.database.dao.impl.GuestbookEntryDaoImpl;
import com.noveogroup.tulupov.guestbook.database.service.GuestbookEntryService;
import com.noveogroup.tulupov.guestbook.database.service.ServiceException;
import com.noveogroup.tulupov.guestbook.database.service.ValidationException;
import com.noveogroup.tulupov.guestbook.model.GuestbookEntry;
import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

/**
 * GusetbookEntry service.
 */
@Slf4j
public class GuestbookEntryServiceImpl implements GuestbookEntryService {
    private static volatile GuestbookEntryService instance;
    private GuestbookEntryDao dao;

    public GuestbookEntryServiceImpl(final GuestbookEntryDao dao) {
        this.dao = dao;
    }

    public static GuestbookEntryService getInstance() {
        GuestbookEntryService result = instance;
        if (result == null) {
            synchronized (GuestbookEntryServiceImpl.class) {
                result = instance;
                if (result == null) {
                    instance = new GuestbookEntryServiceImpl(GuestbookEntryDaoImpl.getInstance());
                    result = instance;
                }
            }
        }
        return result;
    }

    @Override
    public long getCount() {
        try {
            return dao.getCount();
        } catch (SQLException e) {
            log.error("Error get count", e);
            throw new ServiceException("Get count error", e);
        }
    }

    @Override
    public List<GuestbookEntry> getEntries(final long offset, final long count) {
        try {
            return dao.getEntries(offset, count);
        } catch (SQLException e) {
            log.error("Error list", e);
            throw new ServiceException("Cannot get GuestbookEntries", e);
        }
    }

    public void create(final GuestbookEntry entry) {
        try {
            final ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
            final Validator validator = vf.getValidator();

            final Set<ConstraintViolation<GuestbookEntry>> constraintViolations = validator
                    .validate(entry);

            if (constraintViolations.isEmpty()) {
                dao.create(entry);
            } else {
                throw new ValidationException(constraintViolations);
            }
        } catch (SQLException e) {
            log.error("Error create", e);
            throw new ServiceException("Cannot create GuestbookEntry", e);
        }
    }
}
