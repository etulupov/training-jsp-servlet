package com.noveogroup.tulupov.guestbook.database;

/**
 * Database contract.
 */
public class DatabaseContract {
    /**
     * Guestbook entity contract.
     */
    public static class  GuestbookEntry {
        public static final String TABLE_NAME = "GuestbookEntry";
        public static final String ID = "id";
        public static final String FIRST_NAME = "first_name";
        public static final String LAST_NAME = "last_name";
        public static final String EMAIL = "email";
        public static final String USER_AGENT = "user_agent";
        public static final String MESSAGE = "message";
    }
}
