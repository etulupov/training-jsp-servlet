package com.noveogroup.tulupov.guestbook.database.dao;


import com.j256.ormlite.support.ConnectionSource;
import com.noveogroup.tulupov.guestbook.database.dao.impl.GuestbookEntryDaoImpl;
import com.noveogroup.tulupov.guestbook.util.AbstractFactory;

/**
 * GusetbookEntry dao factory.
 */
public final class GuestbookEntryDaoFactory extends AbstractFactory<GuestbookEntryDao, ConnectionSource> {
    private static GuestbookEntryDaoFactory INSTANCE;

    public static GuestbookEntryDaoFactory getInstance() {
        GuestbookEntryDaoFactory result = INSTANCE;
        if (result == null) {
            synchronized (GuestbookEntryDaoFactory.class) {
                result = INSTANCE;
                if (result == null) {
                    INSTANCE = result = new GuestbookEntryDaoFactory();
                }
            }
        }
        return result;
    }

    public GuestbookEntryDaoFactory() {
        super(GuestbookEntryDaoImpl.class, ConnectionSource.class);
    }
}
