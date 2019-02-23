package com.linguaclassica.access;

import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.IHeaderContributor;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.request.resource.JavaScriptResourceReference;

import java.util.Collection;

@SuppressWarnings("serial")
public class MenuDropdownItem extends Panel implements IHeaderContributor{
	
	// New code for javascript
	private WebMarkupContainer itemContainer;
	private RepeatingView repeatingView;
	
    public MenuDropdownItem(String id, MenuItemEnum currentMenuItem,
    	Collection<BookmarkablePageLink<?>> linksInMenuItem, boolean shouldBeActive) {
        super(id);
        
        itemContainer = new WebMarkupContainer("itemContainer");
        if (shouldBeActive) {
        	itemContainer.add(new AttributeAppender("class", " inactive "));
        }
        
        itemContainer.add(new Label("label", currentMenuItem.getLabel()));
        repeatingView = new RepeatingView("itemLinks");

        for (BookmarkablePageLink<?> link : linksInMenuItem) {
        	MenuLinkItem menuLinkItem = new MenuLinkItem(repeatingView.newChildId(), link, false);
                        
            menuLinkItem.setVisible(true);
 
            repeatingView.add(menuLinkItem);
        }

        itemContainer.add(repeatingView);
		
        add(itemContainer);
    }
    /*
    @Override
	public void renderHead(IHeaderResponse response){
		response.renderJavaScriptReference("http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js");
		response.renderJavaScriptReference("http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.1/jquery-ui.min.js");        
	    response.renderCSSReference("http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.1/themes/base/jquery-ui.css");
	    
	    response.renderJavaScript("" + "$(document).ready(function() {"
	    		+ "$('#" + itemContainer.getMarkupId(true) + "').slideToggle(1000);"
	    		+ "});", "fadeOutFunction");
 
	    response.renderJavaScript("" + "$(document).click(function() {"
	    		+ "$('#" + repeatingView.getMarkupId(true) + "').slideToggle(1000);"
	    		+ "});", "fadeOutFunction");
	    
	}*/

}