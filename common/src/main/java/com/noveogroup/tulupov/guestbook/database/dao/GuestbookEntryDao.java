package com.noveogroup.tulupov.guestbook.database.dao;

import com.j256.ormlite.dao.Dao;
import com.noveogroup.tulupov.guestbook.model.GuestbookEntry;

import java.sql.SQLException;
import java.util.List;

public interface GuestbookEntryDao extends Dao<GuestbookEntry, Integer> {
    long getCount() throws SQLException;

    List<GuestbookEntry> getEntries(long offset, long count) throws SQLException;
}
