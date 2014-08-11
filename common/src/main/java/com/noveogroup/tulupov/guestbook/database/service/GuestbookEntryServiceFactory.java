package com.noveogroup.tulupov.guestbook.database.service;


import com.noveogroup.tulupov.guestbook.database.dao.GuestbookEntryDao;
import com.noveogroup.tulupov.guestbook.database.service.impl.GuestbookEntryServiceImpl;
import com.noveogroup.tulupov.guestbook.util.AbstractFactory;

/**
 * GusetbookEntry service factory.
 */
public final class GuestbookEntryServiceFactory extends AbstractFactory<GuestbookEntryService, GuestbookEntryDao> {
    private static GuestbookEntryServiceFactory INSTANCE;

    public static GuestbookEntryServiceFactory getInstance() {
        GuestbookEntryServiceFactory result = INSTANCE;
        if (result == null) {
            synchronized (GuestbookEntryServiceFactory.class) {
                result = INSTANCE;
                if (result == null) {
                    INSTANCE = result = new GuestbookEntryServiceFactory();
                }
            }
        }
        return result;
    }

    public GuestbookEntryServiceFactory() {
        super(GuestbookEntryServiceImpl.class, GuestbookEntryDao.class);
    }
}
