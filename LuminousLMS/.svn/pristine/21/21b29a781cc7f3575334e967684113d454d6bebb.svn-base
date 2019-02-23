package com.linguaclassica.access;

import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.IHeaderContributor;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.RepeatingView;

import java.util.Collection;

@SuppressWarnings("serial")
public class MenuDropdownItem extends Panel implements IHeaderContributor
{
	private WebMarkupContainer itemContainer;
	private RepeatingView repeatingView;
	
    public MenuDropdownItem(String id, MenuItemEnum currentMenuItem,
    	Collection<BookmarkablePageLink<?>> linksInMenuItem, boolean shouldBeActive)
    {
        super(id);
        
        // Create the primary menu
        itemContainer = new WebMarkupContainer("itemContainer");
        if (shouldBeActive)
        {
        	itemContainer.add(new AttributeAppender("class", " inactive "));
        	itemContainer.setMarkupId(currentMenuItem.name().toLowerCase() + "_midl");
        }

        itemContainer.add(new Label("midmenul", currentMenuItem.getLabel()));

        // Create the sub-menus
        repeatingView = new RepeatingView("itemLinks");
        for (BookmarkablePageLink<?> link : linksInMenuItem)
        {
        	MenuLinkItem menuLinkItem = new MenuLinkItem(repeatingView.newChildId(), link, false);
            menuLinkItem.setVisible(true);
            repeatingView.add(menuLinkItem);
        }
        itemContainer.add(repeatingView);
		
        add(itemContainer);
    }
}