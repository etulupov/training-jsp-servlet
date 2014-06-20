package com.noveogroup.tulupov.guestbook.database.dao.impl;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;

import com.noveogroup.tulupov.guestbook.database.DatabaseContract;
import com.noveogroup.tulupov.guestbook.model.GuestbookEntry;
import com.noveogroup.tulupov.guestbook.database.dao.GuestbookEntryDao;

import java.sql.SQLException;
import java.util.List;

public class GuestbookEntryDaoImpl extends BaseDaoImpl<GuestbookEntry, Integer> implements GuestbookEntryDao {

    public GuestbookEntryDaoImpl(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, GuestbookEntry.class);
    }

    @Override
    public List<GuestbookEntry> getEntries(long offset, long limit) throws SQLException {

        return query(queryBuilder().offset(offset).limit(limit).orderBy(DatabaseContract.GuestbookEntry.ID, false).prepare());
    }

    @Override
    public long getCount() throws SQLException {

        return countOf();
    }
}
