package com.linguaclassica.teacher;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;

public class ViewNotifPanel extends Panel {
	private static final long serialVersionUID = 1L;

	ViewNotifPanel(String id){
		super(id);
		
		add(new Label("newlabel","Hi there notifications!"));
	}

}
