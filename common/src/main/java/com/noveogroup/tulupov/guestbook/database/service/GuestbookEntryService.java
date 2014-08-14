package com.noveogroup.tulupov.guestbook.database.service;


import com.noveogroup.tulupov.guestbook.model.GuestbookEntry;

import java.util.List;

/**
 * GusetbookEntry service.
 */
public interface GuestbookEntryService {
    long getCount();

    List<GuestbookEntry> getEntries(long offset, long count);

    void create(GuestbookEntry entry);
}
