package com.linguaclassica.access;

import java.io.Serializable;
import java.util.Collection;

import org.apache.wicket.Page;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.google.common.base.Preconditions;
import com.google.common.collect.Iterables;
import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
import com.linguaclassica.access.MenuDropdownItem;
import com.linguaclassica.access.MenuItemEnum;
import com.linguaclassica.access.MenuLinkItem;


public class NavBarPanel extends Panel
{
	private static final long serialVersionUID = 1L;

	public NavBarPanel(final Builder builder)
	{
		super(builder.id);

		RepeatingView repeatingView = new RepeatingView("menuItems");
		for (MenuItemEnum item : builder.linksMap.keySet())
		{
			boolean shouldBeActive = item.equals(builder.activeMenuItem);
			shouldBeActive = true;
			Collection<BookmarkablePageLink<?>> linksInThisMenuItem = builder.linksMap.get(item);
			if (linksInThisMenuItem.size() == 1)
			{
				BookmarkablePageLink<?> pageLink = Iterables.get(linksInThisMenuItem, 0);
				MenuLinkItem menuLinkItem = new MenuLinkItem(repeatingView.newChildId(), pageLink, shouldBeActive);
				repeatingView.add(menuLinkItem);
			}
			else
			{
				repeatingView.add(new MenuDropdownItem(repeatingView.newChildId(), item, linksInThisMenuItem, shouldBeActive));
			}
		}
		add(repeatingView);

		ExternalLink logoutLink = new ExternalLink("logout_link", "/j_spring_security_logout");
		logoutLink.setContextRelative(true);
		add(logoutLink);
	}

	public static class Builder implements Serializable
	{
		private static final long serialVersionUID = 1L;

		private String id;
        private Class<? extends Page> exitPage;
        private String applicationName;
        private MenuItemEnum activeMenuItem;

		private Multimap<MenuItemEnum, BookmarkablePageLink<?>> linksMap = LinkedHashMultimap.create();
		
		public Builder(String sid, Class<? extends Page> cpage, String sappName, MenuItemEnum activeMI)
		{
			id = sid;
			exitPage = cpage;
			applicationName = sappName;
			activeMenuItem = activeMI;
		}
		
		public Builder(String sid, Class<? extends Page> cpage)
		{
			id = sid;
			exitPage = cpage;
			applicationName = "";
			activeMenuItem = MenuItemEnum.NONE;
		}

		public Builder withMenuItem(MenuItemEnum menuItem, Class<? extends Page> pageToLink)
		{
			Preconditions.checkState(linksMap.containsKey(menuItem) == false, "Builder already contains " + menuItem +
					". Please use withMenuItemInDropdown if you need many links in one menu item");
			BookmarkablePageLink<Page> link = new BookmarkablePageLink<Page>("link", pageToLink);
			link.setBody(new Model<String>(menuItem.getLabel()));
            
            linksMap.put(menuItem, link);
            return this;
        }

		public Builder withMenuItem(MenuItemEnum menuItem, Class<? extends Page> pageToLink, PageParameters parameters)
		{
			Preconditions.checkState(linksMap.containsKey(menuItem) == false, "Builder already contains " + menuItem +
					". Please use withMenuItemInDropdown if you need many links in one menu item");

			BookmarkablePageLink<Page> link = new BookmarkablePageLink<Page>("link", pageToLink, parameters);
			link.setBody(new Model<String>(menuItem.getLabel()));
            linksMap.put(menuItem, link);
            return this;
        }

		public Builder withMenuItemAsDropdown(MenuItemEnum menuItem, Class<? extends Page> pageToLink, String label)
		{
			BookmarkablePageLink<Page> link = new BookmarkablePageLink<Page>("link", pageToLink);
			link.setBody(new Model<String>(label));
			linksMap.put(menuItem, link);
			return this;
		}

		public Builder withMenuItemAsDropdown(MenuItemEnum menuItem, Class<? extends Page> pageToLink, String label, PageParameters parameters)
		{
			BookmarkablePageLink<Page> link = new BookmarkablePageLink<Page>("link", pageToLink, parameters);
			link.setBody(new Model<String>(label));
			linksMap.put(menuItem, link);
			return this;
		}
		
		public NavBarPanel build()
		{
            return new NavBarPanel(this);
        }
	}
}
