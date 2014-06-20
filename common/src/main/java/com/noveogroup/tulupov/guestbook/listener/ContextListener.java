package com.noveogroup.tulupov.guestbook.listener;

import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.noveogroup.tulupov.guestbook.model.GuestbookEntry;
import com.noveogroup.tulupov.guestbook.util.Config;
import org.apache.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;

@WebListener
public class ContextListener implements ServletContextListener {
    private static final Logger LOGGER = Logger.getLogger(ContextListener.class);

    public static final String CONNECTION_SOURCE = "connection_source";

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext context = servletContextEvent.getServletContext();

        try {
            Config config = Config.getInstance();

            JdbcPooledConnectionSource connectionSource = new JdbcPooledConnectionSource(config.getJdbcUrl(), config.getJdbcUser(), config.getJdbcPassword());

            TableUtils.createTableIfNotExists(connectionSource, GuestbookEntry.class);

            context.setAttribute(CONNECTION_SOURCE, connectionSource);
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
