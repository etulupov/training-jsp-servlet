package com.noveogroup.tulupov.guestbook.listener;

import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.noveogroup.tulupov.guestbook.model.GuestbookEntry;
import com.noveogroup.tulupov.guestbook.util.Config;
import com.noveogroup.tulupov.guestbook.util.DataUtils;
import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Context listener.
 */
@WebListener
public class ContextListener implements ServletContextListener {
    public static final String CONNECTION_SOURCE = "connection_source";

    private static final Logger LOGGER = Logger.getLogger(ContextListener.class);

    @Override
    public void contextInitialized(final ServletContextEvent servletContextEvent) {
        final ServletContext context = servletContextEvent.getServletContext();

        try {
            final Config config = Config.getInstance();

            final JdbcPooledConnectionSource connectionSource =
                    new JdbcPooledConnectionSource(config.getJdbcUrl(), config.getJdbcUser(), config.getJdbcPassword());

            TableUtils.createTableIfNotExists(connectionSource, GuestbookEntry.class);

            DataUtils.fillGuestbookEntryIfNotExists(connectionSource);

            context.setAttribute(CONNECTION_SOURCE, connectionSource);
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }

    @Override
    public void contextDestroyed(final ServletContextEvent servletContextEvent) {

    }
}
