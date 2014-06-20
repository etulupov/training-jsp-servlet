package com.noveogroup.tulupov.guestbook.model;


import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import static com.noveogroup.tulupov.guestbook.database.DatabaseContract.GuestbookEntry.*;

@DatabaseTable(tableName = TABLE_NAME)
public class GuestbookEntry {
    @Getter
    @DatabaseField(columnName = ID, generatedId = true)
    private int id;

    @Getter
    @Setter
    @DatabaseField(columnName = FIRST_NAME, canBeNull = false)
    @Size(min=3, max=50, message = "invalid_size_first_name")
    @NotNull(message = "first_name_empty")
    private String firstName;

    @Getter
    @Setter
    @DatabaseField(columnName = LAST_NAME, canBeNull = false)
    @Size(min=3, max=50, message = "invalid_size_last_name")
    @NotNull(message = "last_name_empty")
    private String lastName;

    @Getter
    @Setter
    @DatabaseField(columnName = EMAIL, canBeNull = false)
    @Size(max=100, message = "too_large_email")
    @Pattern(message = "email_wrong",regexp = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"" +
            "(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])" +
            "*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0" +
            "-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\" +
            "x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")
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
    @Size(min=3, max=1000, message = "invalid_size_message")
    @NotNull(message = "message_empty")
    private String message;

}
