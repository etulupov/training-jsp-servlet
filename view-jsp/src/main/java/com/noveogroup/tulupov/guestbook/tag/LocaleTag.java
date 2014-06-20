package com.noveogroup.tulupov.guestbook.tag;


import lombok.Setter;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.util.ResourceBundle;

public class LocaleTag extends SimpleTagSupport {
    @Setter
    private String key;

    @Override
    public void doTag() throws JspException, IOException {
        PageContext context = (PageContext) getJspContext();
        ResourceBundle resourceBundle = (ResourceBundle)  context.getRequest().getAttribute("lang");
        JspWriter out = getJspContext().getOut();
        out.print(resourceBundle.getString(key));
    }
}
