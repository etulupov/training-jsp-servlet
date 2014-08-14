package com.noveogroup.tulupov.guestbook.database.dao;


import com.j256.ormlite.support.ConnectionSource;
import com.noveogroup.tulupov.guestbook.database.dao.impl.GuestbookEntryDaoImpl;
import com.noveogroup.tulupov.guestbook.util.AbstractFactory;

/**
 * GusetbookEntry dao factory.
 */
public final class GuestbookEntryDaoFactory extends AbstractFactory<GuestbookEntryDao, ConnectionSource> {
    private static volatile GuestbookEntryDaoFactory instance;

    public GuestbookEntryDaoFactory() {
        super(GuestbookEntryDaoImpl.class, ConnectionSource.class);
    }

    public static GuestbookEntryDaoFactory getInstance() {
        GuestbookEntryDaoFactory result = instance;
        if (result == null) {
            synchronized (GuestbookEntryDaoFactory.class) {
                result = instance;
                if (result == null) {
                    instance = new GuestbookEntryDaoFactory();
                    result = instance;
                }
            }
        }
        return result;
    }
}
