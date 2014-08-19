package com.noveogroup.tulupov.guestbook.database.dao.impl;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import com.noveogroup.tulupov.guestbook.database.DatabaseContract;
import com.noveogroup.tulupov.guestbook.database.dao.GuestbookEntryDao;
import com.noveogroup.tulupov.guestbook.model.GuestbookEntry;
import lombok.extern.slf4j.Slf4j;

import java.sql.SQLException;
import java.util.List;

/**
 * Guestbook entity dao.
 */
@Slf4j
public class GuestbookEntryDaoImpl extends BaseDaoImpl<GuestbookEntry, Integer> implements GuestbookEntryDao {
    private static volatile GuestbookEntryDao instance;

    public GuestbookEntryDaoImpl(final ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, GuestbookEntry.class);
    }

    public static GuestbookEntryDao getInstance() {
        return instance;
    }

    public static void init(final ConnectionSource connectionSource) {
        if (instance == null) {
            synchronized (GuestbookEntryDaoImpl.class) {
                if (instance == null) {
                    try {
                        instance = new GuestbookEntryDaoImpl(connectionSource);
                    } catch (SQLException e) {
                        log.error("Can't instantiate GuestbookEntryDaoImpl", e);
                    }
                }
            }
        }

    }

    @Override
    public List<GuestbookEntry> getEntries(final long offset, final long limit) throws SQLException {

        return query(queryBuilder().offset(offset).limit(limit)
                .orderBy(DatabaseContract.GuestbookEntry.ID, false).prepare());
    }

    @Override
    public long getCount() throws SQLException {

        return countOf();
    }
}
