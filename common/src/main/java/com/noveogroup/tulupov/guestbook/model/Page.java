package com.noveogroup.tulupov.guestbook.model;


import lombok.Getter;
import lombok.Setter;

/**
 * Page model.
 */
public class Page {
    @Getter
    @Setter
    private boolean active;

    @Getter
    @Setter
    private boolean disabled;

    @Getter
    @Setter
    private long number;

    @Getter
    @Setter
    private String title;

}
