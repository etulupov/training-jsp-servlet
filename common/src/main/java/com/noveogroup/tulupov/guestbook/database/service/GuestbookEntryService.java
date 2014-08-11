package com.noveogroup.tulupov.guestbook.database.service;


import com.noveogroup.tulupov.guestbook.model.GuestbookEntry;

import java.sql.SQLException;
import java.util.List;

public interface GuestbookEntryService {
    long getCount();

    List<GuestbookEntry> getEntries(long offset, long count);

    void create(GuestbookEntry entry);
}
