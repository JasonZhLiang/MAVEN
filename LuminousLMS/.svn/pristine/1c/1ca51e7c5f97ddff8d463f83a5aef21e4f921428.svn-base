package com.linguaclassica.access;

import org.apache.wicket.MetaDataKey;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.Panel;

@SuppressWarnings("serial")
public class MenuLinkItem extends Panel
{
	public static final MetaDataKey<String> ATTR = new MetaDataKey<String>() { };

	public MenuLinkItem(String id, BookmarkablePageLink<?> pageLink, boolean shouldBeActive)
	{
		super(id);
		add(pageLink);
		if (shouldBeActive)
		{
			add(new AttributeAppender("class", " active "));
		}
	}
}