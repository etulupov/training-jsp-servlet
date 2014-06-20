package com.noveogroup.tulupov.guestbook.util;


public class StringUtils {

    private StringUtils() {
        throw new UnsupportedOperationException();
    }

    public static String replaceEach(String text, String[] find, String[] replacement) {
        String s = text;

        for (int i = 0; i < find.length; i++) {
            s = s.replace(find[i], replacement[i]);
        }

        return s;
    }

    public static String convertLineBreaksToHtml(String s) {

        return s.replace("\n", "<br />");
    }

    public static String escapeHtml(String s) {
        return replaceEach(s, new String[]{"&", "<", ">", "\"", "'", "/" },
                new String[]{"&amp;", "&lt;", "&gt;", "&quot;", "&#x27;", "&#x2F;" });
    }
}
