package com.noveogroup.tulupov.guestbook.util;


import com.noveogroup.tulupov.guestbook.model.Page;

import java.util.ArrayList;
import java.util.List;

/**
 * Pagination utils.
 */
public final class PaginationUtils {
    private PaginationUtils() {
        throw new UnsupportedOperationException();
    }

    public static List<Page> paginize(final long total, final long limit, final long current) {
        if (total == 0) {
            return null;
        }

        final List<Page> pages = new ArrayList<Page>();

        final long pageCount = (total + limit - 1) / limit;

        Page page = new Page();
        page.setNumber(Math.max(current - 1, 0));
        page.setTitle("&laquo;");
        page.setActive(false);
        page.setDisabled(current == 0);
        pages.add(page);

        for (int i = 0; i < pageCount; i++) {
            page = new Page();
            page.setNumber(i);
            page.setTitle(String.valueOf(i + 1));
            page.setActive(i == current);
            pages.add(page);
        }

        page = new Page();
        page.setNumber(Math.min(current + 1, pageCount - 1));
        page.setTitle("&raquo;");
        page.setActive(false);
        page.setDisabled(current == pageCount - 1);
        pages.add(page);

        return pages;
    }
}
