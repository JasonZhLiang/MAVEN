package com.linguaclassica.shared;

import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.linguaclassica.access.AlphPlusSession;
import com.linguaclassica.entity.EntityTermModel;
import com.linguaclassica.service.TermService;

public class TermSelectPanel extends Panel
{
	private static final long serialVersionUID = 1L;

	@SpringBean
	protected TermService termService;
	
    protected AlphPlusSession session = AlphPlusSession.get();
	private EntityTermModel destinationTerm;
	
	public TermSelectPanel(String id, String title)
	{
		super(id);

		List<EntityTermModel> termsList;
		termsList = retrieveTerms(session.getCurrentInstitution(), session.getUser(getRequest()).getId());
		
		/* Set initial selection for dropdown */
		destinationTerm = null;
		int initialId = session.getUserPageTermId();
		if (initialId == -1)
		{
			if (!termsList.isEmpty())
			{
				destinationTerm = termsList.get(0);
				session.setUserPageTermId(destinationTerm.getId());
			}
		}
		else
		{
			for (EntityTermModel term : termsList)
			{
				if (term.getId() == initialId)
				{
					destinationTerm = term;
					break;
				}
			}
		}

		add (new Label("titlelabel", title));
		
		final DropDownChoice<EntityTermModel> termselect = new DropDownChoice<EntityTermModel>(
				"selectterm",
				new PropertyModel<EntityTermModel>(this, "destinationTerm"), 
				termsList, 
				new ChoiceRenderer<EntityTermModel>("termname", "id") )
		{
			private static final long serialVersionUID = 1L;
			
			protected boolean wantOnSelectionChangedNotifications()
			{
				return true;
			}
			
			protected void onSelectionChanged(final EntityTermModel newSelection)
			{
				System.out.format("onSelectionChanged(%s)%n", newSelection.getTermname());
				session.setUserPageTermId(destinationTerm.getId());

				/* Reset class selection */
				session.setSelectedClasses(null);
				session.setSelectedClassIDs(null);

				setResponsePage(getPage().getClass());
			}
		};
		termselect.setNullValid(false);
		termselect.setRequired(true);
		
		add(termselect);
	}

	public TermSelectPanel(String id, IModel<?> model)
	{
		super(id, model);
	}

	/* Over-ride this if you want a different term list */
	protected List<EntityTermModel> retrieveTerms(int instId, int userId)
	{
		return termService.getTermsByInstTeacher(instId, userId);
	}
}
