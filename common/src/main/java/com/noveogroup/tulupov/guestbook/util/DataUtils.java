package com.noveogroup.tulupov.guestbook.util;

import com.j256.ormlite.support.ConnectionSource;
import com.noveogroup.tulupov.guestbook.database.dao.GuestbookEntryDao;
import com.noveogroup.tulupov.guestbook.database.dao.GuestbookEntryDaoFactory;
import com.noveogroup.tulupov.guestbook.database.service.GuestbookEntryService;
import com.noveogroup.tulupov.guestbook.database.service.GuestbookEntryServiceFactory;
import com.noveogroup.tulupov.guestbook.model.GuestbookEntry;
import lombok.extern.slf4j.Slf4j;

/**
 * Data utils.
 */
@Slf4j
public final class DataUtils {
    private static final int ITEMS_COUNT = 50;

    private DataUtils() {

    }

    public static void fillGuestbookEntryIfNotExists(final ConnectionSource connectionSource) {
        final GuestbookEntryDao guestbookEntryDao = GuestbookEntryDaoFactory.getInstance().get(connectionSource);
        final GuestbookEntryService guestbookEntryService =
                GuestbookEntryServiceFactory.getInstance().get(guestbookEntryDao);
        try {
            log.debug("Check table " + guestbookEntryService.getCount());
            if (guestbookEntryService.getCount() == 0) {
                for (int i = 0; i < ITEMS_COUNT; i++) {
                    final GuestbookEntry entry = new GuestbookEntry();

                    entry.setFirstName("Vasya");
                    entry.setLastName("Pupkin");
                    entry.setEmail("vasya.pupkin@gmail.com");
                    entry.setMessage("Test message #" + i);
                    entry.setUserAgent("Cool browser");

                    guestbookEntryService.create(entry);
                }
            }
        } catch (Exception e) {
            log.error("Error", e);
        }
    }
}
