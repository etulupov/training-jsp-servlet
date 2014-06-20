package com.noveogroup.tulupov.guestbook.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.concurrent.atomic.AtomicInteger;

@WebListener
public class SessionListener implements HttpSessionListener {
    private static AtomicInteger activeSessions = new AtomicInteger();

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        activeSessions.incrementAndGet();
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        if (activeSessions.get() > 0) {
            activeSessions.decrementAndGet();
        }
    }

    public static int getActiveSessions() {
        return activeSessions.get();
    }
}
