package com.noveogroup.tulupov.guestbook.util;

/**
 * String utils.
 */
public final class StringUtils {

    private StringUtils() {
        throw new UnsupportedOperationException();
    }

    public static String replaceEach(final String text, final String[] find, final String[] replacement) {
        String s = text;

        for (int i = 0; i < find.length; i++) {
            s = s.replace(find[i], replacement[i]);
        }

        return s;
    }

    public static String convertLineBreaksToHtml(final String s) {

        return s.replace("\n", "<br />");
    }

    public static String escapeHtml(final String s) {
        return replaceEach(s, new String[]{"&", "<", ">", "\"", "'", "/"},
                new String[]{"&amp;", "&lt;", "&gt;", "&quot;", "&#x27;", "&#x2F;"});
    }
}
