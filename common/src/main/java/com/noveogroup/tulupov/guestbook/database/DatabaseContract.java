package com.noveogroup.tulupov.guestbook.database;

public interface DatabaseContract {
    public interface GuestbookEntry {
        String TABLE_NAME = "GuestbookEntry";
        String ID = "id";
        String FIRST_NAME = "first_name";
        String LAST_NAME = "last_name";
        String EMAIL = "email";
        String USER_AGENT = "user_agent";
        String MESSAGE = "message";
    }
}
