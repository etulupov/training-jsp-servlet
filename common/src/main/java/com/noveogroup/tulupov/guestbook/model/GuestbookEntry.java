package com.noveogroup.tulupov.guestbook.model;


import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static com.noveogroup.tulupov.guestbook.database.DatabaseContract.GuestbookEntry.*;

/**
 * Guestbook entry.
 */
@DatabaseTable(tableName = TABLE_NAME)
public class GuestbookEntry {
    @Getter
    @DatabaseField(columnName = ID, generatedId = true)
    private int id;

    @Getter
    @Setter
    @DatabaseField(columnName = FIRST_NAME, canBeNull = false)
    @Size(min = 3, max = 50, message = "invalid_size_first_name")
    @NotNull(message = "first_name_empty")
    private String firstName;

    @Getter
    @Setter
    @DatabaseField(columnName = LAST_NAME, canBeNull = false)
    @Size(min = 3, max = 50, message = "invalid_size_last_name")
    @NotNull(message = "last_name_empty")
    private String lastName;

    @Getter
    @Setter
    @DatabaseField(columnName = EMAIL, canBeNull = false)
    @Size(max = 100, message = "too_large_email")
    @Email
    @NotNull(message = "email_empty")
    private String email;

    @Getter
    @Setter
    @DatabaseField(columnName = USER_AGENT, canBeNull = false)
    @NotNull(message = "user_agent_empty")
    private String userAgent;

    @Getter
    @Setter
    @DatabaseField(columnName = MESSAGE, canBeNull = false)
    @Size(min = 3, max = 1000, message = "invalid_size_message")
    @NotNull(message = "message_empty")
    private String message;

}
