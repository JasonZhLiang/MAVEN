package com.linguaclassica.student;

import org.apache.wicket.request.mapper.parameter.PageParameters;
import com.linguaclassica.shared.DetailedResultsPanel;

public class DetailedResultsPage extends StudentBasePage {

	private static final long serialVersionUID = 1L;

	public DetailedResultsPage(PageParameters params) {
		super();
		System.out.println("DetailedResultsPage(..)");
		
		DetailedResultsPanel drp = new DetailedResultsPanel("DetailedResultsPanel", "RESULTS", params);
		add(drp);
	}
}

