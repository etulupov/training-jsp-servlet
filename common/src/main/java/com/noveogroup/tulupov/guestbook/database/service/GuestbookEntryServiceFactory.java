package com.noveogroup.tulupov.guestbook.database.service;


import com.noveogroup.tulupov.guestbook.database.dao.GuestbookEntryDao;
import com.noveogroup.tulupov.guestbook.database.service.impl.GuestbookEntryServiceImpl;
import com.noveogroup.tulupov.guestbook.util.AbstractFactory;

/**
 * GusetbookEntry service factory.
 */
public final class GuestbookEntryServiceFactory extends AbstractFactory<GuestbookEntryService, GuestbookEntryDao> {
    private static volatile GuestbookEntryServiceFactory instance;

    public GuestbookEntryServiceFactory() {
        super(GuestbookEntryServiceImpl.class, GuestbookEntryDao.class);
    }

    public static GuestbookEntryServiceFactory getInstance() {
        GuestbookEntryServiceFactory result = instance;
        if (result == null) {
            synchronized (GuestbookEntryServiceFactory.class) {
                result = instance;
                if (result == null) {
                    instance = new GuestbookEntryServiceFactory();
                    result = instance;
                }
            }
        }
        return result;
    }
}
