package com.linguaclassica.instadmin;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.linguaclassica.access.AlphPlusSession;
import com.linguaclassica.entity.EntityClassModel;
import com.linguaclassica.entity.EntityTermModel;
import com.linguaclassica.service.ClassService;
import com.linguaclassica.service.TermService;
import com.linguaclassica.service.UserService;

public class TermsListPage extends InstAdminBasePage
{
	private static final long serialVersionUID = 1L;
	
	@SpringBean
	TermService termService;
	
	@SpringBean
	UserService userService;
	
	@SpringBean
	ClassService classService;
	
	private Integer institutionId;

	public TermsListPage()
	{
		institutionId = AlphPlusSession.get().getCurrentInstitution();
		
		permitPreferenceSelection();

		// Create the form
		TermsForm form = new TermsForm();
		add(form);
	}
	
	class TermsForm extends Form<Object>
	{
		private static final long serialVersionUID = 1L;
		
		public TermsForm()
		{
			super("termlistform");
			
			// Add of the the terms to the table
			java.sql.Date now = new java.sql.Date(Calendar.getInstance().getTime().getTime());
			List<EntityTermModel> termEntities;
			termEntities = termService.getListOfTermsByInstId(institutionId, now, 
					AlphPlusSession.get().getDateRange());
			
			add(new ListView<EntityTermModel>("termitem", termEntities)
			{
				private static final long serialVersionUID = 1L;

				protected void populateItem(ListItem<EntityTermModel> item)
				{
					final EntityTermModel oneterm = item.getModelObject();
					
					// Add the data columns
					item.add(new Label("termnameLab", oneterm.getTermname()));
					item.add(new Label("startdateLab", oneterm.getStartDate()));
					item.add(new Label("enddateLab", oneterm.getEndDate()));
					item.add(new Label("updatedatelab", oneterm.getUpdateDate()));

					// Add the edit button
					Button edittermButton = new Button("edittermlink")
					{
						private static final long serialVersionUID = 1L;
						
						@Override
						public boolean isEnabled()
						{
							// Enable current and future terms
							LocalDate enddate = oneterm.getEndDate().toLocalDate();
							LocalDate today = LocalDate.now();
							return super.isEnabled() && today.compareTo(enddate) <= 0;
						}
						
						public void onSubmit()
						{
							setResponsePage(new TermPage(oneterm));
						}
					};
					item.add(edittermButton);
					
					Button deletetermbutton = new Button("deletetermlink")
					{
						private static final long serialVersionUID = 1L;
						
						public void onSubmit()
						{
							System.out.println("TermsForm.onSubmit delete termId = " + oneterm.getId());
							
							List<EntityClassModel> classList = new ArrayList<EntityClassModel>();
							classList = classService.getListOfClassesByTermId(oneterm.getId());
							if (classList.isEmpty())
							{
								// Term does not have any classes
								final JDialog dialog = new JDialog();
								dialog.setAlwaysOnTop(true);
								String messageStr = String.format("Delete the term '%s'?", oneterm.getTermname());
								int option = JOptionPane.showConfirmDialog(dialog, messageStr, "Confirmation", JOptionPane.YES_NO_OPTION);
								if (option == 0)
								{
									// Selected Yes
									System.out.println("Yes");
									termService.deleteTermById(oneterm.getId());
									System.out.println("TermsForm.onSubmit deleted termId = " + oneterm.getId());
									
									// Refresh the page
									setResponsePage(TermsListPage.class);
								}
								else
								{
									// Selected No
									System.out.println("No");
								}
							}
							else
							{
								// Term has classes
								final JDialog dialog = new JDialog();
								dialog.setAlwaysOnTop(true);
								String messageStr = String.format("The term '%s' has 1 class. It must be deleted first.", oneterm.getTermname());
								if (classList.size() > 1)
								{
									messageStr = String.format("The term '%s' has %d classes. They must be deleted first.", oneterm.getTermname(), classList.size());
								}
								JOptionPane.showMessageDialog(dialog, messageStr);
							}
						}
					};
					item.add(deletetermbutton);
				}
			});
		}
	}

}
