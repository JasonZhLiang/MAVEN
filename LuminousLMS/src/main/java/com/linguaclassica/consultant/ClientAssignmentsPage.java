package com.linguaclassica.consultant;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.wicket.Session;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.CheckBoxMultipleChoice;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.util.string.StringValue;

import com.linguaclassica.access.LMSSession;
import com.linguaclassica.entity.EntityAssignmentModel;
import com.linguaclassica.model.UserModel;
import com.linguaclassica.shared.PrivateBasePage;
import com.linguaclassica.service.UserService;
import com.linguaclassica.service.AssignmentService;
import com.linguaclassica.service.ClientAssignmentsService;

public class ClientAssignmentsPage extends PrivateBasePage
{
	private static final long serialVersionUID = 1L;
	
	@SpringBean
	UserService userService;

	@SpringBean
	AssignmentService assignmentService;

	@SpringBean
	ClientAssignmentsService clientAssignmentsService;

	private Logger logger = LogManager.getLogger(ClientAssignmentsPage.class);
	List<EntityAssignmentModel> orgAssignmentList;
	
	public ClientAssignmentsPage(PageParameters parameters)
	{
		super(parameters);
		
		// The parameter should contain a message
		StringValue sv = parameters.get("userid");
		if (sv != null)
		{
			Integer userId = sv.toInteger();
			if (userId !=null)
			{
				AssignmentsForm form = new AssignmentsForm(userId);
				add(form);	  
				return;
			}
		}

		// The parameter was defective
		AssignmentsForm form = new AssignmentsForm("Incorrect message parameter passed.");
		add(form);	  
	}
	
	class AssignmentsForm extends Form<Object>
	{
		private static final long serialVersionUID = 1L;

		List<Integer> clientAssignmentsList;
		List<EntityAssignmentModel> originalList;
		List<EntityAssignmentModel> updatedList;

		UserModel clientUserModel;
		String clientName = null;

		
		public AssignmentsForm(Integer userid)
		{
			super("assignmentsf");

			clientUserModel = userService.findUserById(userid);
			clientName = clientUserModel.getFirstName() + " " + clientUserModel.getLastName();
			
			logger.debug("ClientAssignmentsPage:  " + clientName);

			add(new Label("clientname", clientName));

			orgAssignmentList = assignmentService.getOrgAssignmentsList(currentOrg.getId());

			for (int orgAssignmentIndex=0; orgAssignmentIndex < orgAssignmentList.size(); orgAssignmentIndex++)
			{
				EntityAssignmentModel orgAssignmentEntity = orgAssignmentList.get(orgAssignmentIndex);
					
				logger.debug("available assignments: " + orgAssignmentEntity.getName());
			}
		
			// updatedList can change later when the user modifies the assignment list and
			// clicks the submit button.  It will reflect the assignment list at the instant
			// the user clicks submit.
			clientAssignmentsList = clientAssignmentsService.getListOfClientAssignmentIdsForClient(clientUserModel.getId());
			
			updatedList  = assignmentService.getAssignmentsList(clientAssignmentsList);
			
			// originalList contains the assignment list as initially displayed to the user
			originalList = assignmentService.getAssignmentsList(clientAssignmentsList);
			
 			final CheckBoxMultipleChoice<EntityAssignmentModel> listAssignments =
                    new CheckBoxMultipleChoice<EntityAssignmentModel>(
                    		"choices", 
                    		new PropertyModel<List<EntityAssignmentModel>>(this, "updatedList"),
                    		orgAssignmentList,
                    		new ChoiceRenderer<EntityAssignmentModel>("name", "id"));
			
			listAssignments.setSuffix("<br>");

			add(listAssignments);
		}

		public AssignmentsForm(String errorMessage)
		{
			super("assignmentsf");
			
			add(new Label("messagel", errorMessage));
		}
		
		@Override
		protected void onSubmit() 
		{
			// ------  generate a list of added assignments IDs and a list  ----------------------------
			// ------  of deleted assignment IDs for this client.
			ArrayList<Integer> addedIds = new ArrayList<Integer>();
			ArrayList<Integer> deletedIds = new ArrayList<Integer>();

			// generate list of added assignment IDs
			for (int i=0; i < updatedList.size(); i++)
			{
				// any checked assignment in the list at the time the submit button is clicked is
				// considered to be in the added list.  (Note: if the assignment was originally checked
				// ie, in the original list, it will be removed from the added list below.)
				addedIds.add(updatedList.get(i).getId());
				logger.debug("ADDED   - " + updatedList.get(i).getId() + " " + updatedList.get(i).getName());
			}

			// generate list of deleted assignment IDs
			for (int i=0; i < originalList.size(); i++)
			{
				Integer currentId = originalList.get(i).getId();
				if (addedIds.contains(currentId))
				{
					// the assignment ID was also in the original displayed list, so this means it was 
					// not added or deleted and should be removed from the added list.
					int index = addedIds.indexOf(currentId);
					addedIds.remove(index);
					logger.debug("NULL    - " + currentId + " " + originalList.get(i).getName());
				}
				else
				{
					// this assignment ID is not in the current updated list, but was in the original list
					// so it was unchecked and must be deleted.
					deletedIds.add(currentId);
					logger.debug("DELETED - " + currentId + " " + originalList.get(i).getName());
				}
			}
			
			logger.debug("addedIds.size()   = " + addedIds.size());
			logger.debug("deletedIds.size() = " + deletedIds.size());

			// ------  Perform addition or deletion of assignments as determined above  ----------------
			for (Integer addId : addedIds)
			{
				clientAssignmentsService.addAssignment(currentOrg.getId(), clientUserModel.getId(), addId);
			}
			for (Integer deleteId : deletedIds)
			{
				try {
					clientAssignmentsService.removeAssignment(currentOrg.getId(), clientUserModel.getId(), deleteId);
				}
				catch (Exception e) {
					logger.debug("removeAssignment exception e= " + e);
					Session.get().error("Cannot delete selected assignment.  Client may have already started the assignment.  Please check usage records.");
					return;
				}
			}
			
			// ------  Display the clients page  -------------------------------------------------------
			setResponsePage(ConsultantClientsPage.class);
		}
	}
}
