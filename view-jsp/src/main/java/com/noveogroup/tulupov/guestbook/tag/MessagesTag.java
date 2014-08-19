package com.noveogroup.tulupov.guestbook.tag;


import lombok.Setter;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.io.InputStream;
import java.util.ResourceBundle;

/**
 * Localizes text.
 */
public class MessagesTag extends SimpleTagSupport {
    private static final String ENTRIES = "entries";
    private static final String LANGUAGE = "lang";
    @Override
    public void doTag() throws JspException, IOException {
        final PageContext context = (PageContext) getJspContext();
        final JspWriter out = getJspContext().getOut();

        final VelocityEngine velocityEngine = new VelocityEngine();
        velocityEngine.setProperty("resource.loader", "class");
        velocityEngine.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        velocityEngine.init();

        final VelocityContext velocityContext = new VelocityContext();
        velocityContext.put(ENTRIES, context.getRequest().getAttribute(ENTRIES));
        velocityContext.put(LANGUAGE, context.getRequest().getAttribute(LANGUAGE));

        final Template template = velocityEngine.getTemplate("messages.vm");
        template.merge(velocityContext, out);
    }
}
