package com.linguaclassica.instadmin;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.util.string.StringValue;

import com.linguaclassica.access.AlphPlusSession;
import com.linguaclassica.entity.EntityTermModel;
import com.linguaclassica.model.PermissionModel.Permission;
import com.linguaclassica.service.ClassService;
import com.linguaclassica.service.TermService;
import com.linguaclassica.service.UserService;

public class TermedUsersListPage extends InstAdminBasePage
{
	private static final long serialVersionUID = 1L;
	
	@SpringBean
	TermService termService;
	
	@SpringBean
	UserService userService;
	
	@SpringBean
	ClassService classService;
	
	private Integer institutionId;
	private Permission permission;
	
	public TermedUsersListPage()
	{
		permission = Permission.STUDENT;

		buildPage();
	}
	
	public TermedUsersListPage(PageParameters params)
	{
		StringValue sv = params.get("usertype");
		if (sv != null)
		{
			String usertype = sv.toString();
			if (usertype !=null)
			{
				permission = Permission.valueOf(usertype);
			}
		}

		buildPage();
	}

	private void buildPage()
	{
		institutionId = AlphPlusSession.get().getCurrentInstitution();

		// Create the form
		TermsForm form = new TermsForm();
		add(form);
	}

	class TermsForm extends Form<Object>
	{
		private static final long serialVersionUID = 1L;
		
		public TermsForm()
		{
			super("userscountform");
			
			// Add of the the terms to the table
			List<EntityTermModel> termEntities = new ArrayList<EntityTermModel>();
			termEntities = termService.getListOfTermsByInstId(institutionId);
			
			add(new ListView<EntityTermModel>("termitem", termEntities)
			{
				private static final long serialVersionUID = 1L;
				
				protected void populateItem(ListItem<EntityTermModel> item)
				{
					int termid = item.getModelObject().getId();
					List<Integer> userIdList = new ArrayList<Integer>();
					if (permission == Permission.STUDENT)
					{
						userIdList = termService.getListOfStudentIdsByTermId(termid);
					}
					else if (permission == Permission.TEACHER)
					{
						userIdList = termService.getListOfTeacherIdsByTermId(termid);
					}
					else if (permission == Permission.TA)
					{
						userIdList = termService.getListOfTaIdsByTermId(termid);
					}
					final Integer userListSize = userIdList.size();

					// Add the data columns
					item.add(new Label("termnameLab", item.getModelObject().getTermname()));
					item.add(new Label("usercountLab", userIdList.size()));
					
					// Add the view button
					Button viewlistButton = new Button("viewlink")
					{
						private static final long serialVersionUID = 1L;
						
						@Override
						public boolean isEnabled()
						{
							// Disable when empty
							return super.isEnabled() && (userListSize > 0);
						}
						
						public void onSubmit()
						{
							setResponsePage(new UsersListPage(termid, permission));
						}
					};
					item.add(viewlistButton);
				}

			});
		}
	}
}
