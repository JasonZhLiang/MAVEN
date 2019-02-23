package com.linguaclassica.access;

import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.Panel;

@SuppressWarnings("serial")
public class MenuLinkItemFrench extends Panel {

        public MenuLinkItemFrench(String id, BookmarkablePageLink<?> pageLink, boolean shouldBeActive) {
                super(id);
                add(pageLink);
                if (shouldBeActive) {
                        add(new AttributeAppender("class", " active "));
                }
        }
}