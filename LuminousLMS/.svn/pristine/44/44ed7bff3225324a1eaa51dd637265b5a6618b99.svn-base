package com.linguaclassica.consultant;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.wicket.Component;
import org.apache.wicket.extensions.markup.html.repeater.tree.DefaultNestedTree;
import org.apache.wicket.extensions.markup.html.repeater.tree.content.Folder;
import org.apache.wicket.extensions.markup.html.repeater.tree.theme.WindowsTheme;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import com.linguaclassica.access.LMSSession;
import com.linguaclassica.entity.EntityAssignmentModel;
import com.linguaclassica.entity.EntityAssignmentSequencesModel;
import com.linguaclassica.entity.EntityClientSequenceProgressModel;
import com.linguaclassica.entity.EntityClientSequenceUsageModel;
import com.linguaclassica.entity.EntityContentItemsModel;
import com.linguaclassica.entity.EntityUserModel;
import com.linguaclassica.model.UserModel;
import com.linguaclassica.officeadmin.OfficeAdminConsultantsPage;
import com.linguaclassica.repository.ClientSequenceProgressRepository;
import com.linguaclassica.repository.ClientSequenceUsageRepository;
import com.linguaclassica.repository.OrgUserPermissionRepository;
import com.linguaclassica.repository.UserRepository;
import com.linguaclassica.service.AssignmentSequencesService;
import com.linguaclassica.service.AssignmentService;
import com.linguaclassica.service.ClientAssignmentsService;
import com.linguaclassica.service.ContentItemsService;
import com.linguaclassica.shared.TreeData;
import com.linguaclassica.shared.TreeDataProvider;
import com.linguaclassica.shared.PrivateBasePage;

public class ConsultantClientsPage extends PrivateBasePage
{
	private static final long serialVersionUID = 1L;
	
	@SpringBean
	OrgUserPermissionRepository oupRepository;
	
	@SpringBean
	UserRepository userRepository;
	
	@SpringBean
	ClientAssignmentsService clientAssignmentsService;

	@SpringBean
	AssignmentService assignmentService;

	@SpringBean
	AssignmentSequencesService assignmentSequenceService;
	
	@SpringBean
	ClientSequenceProgressRepository clientSequenceProgressRepository;
	
	@SpringBean
	ClientSequenceUsageRepository clientSequenceUsageRepository;
	
	@SpringBean
	ContentItemsService contentItemsService;
	
	// Active user
	private final UserModel userModel = LMSSession.get().getUser(getRequest());
	public List<TreeData> clientsTreeList = new ArrayList<>();

	private Logger logger = LogManager.getLogger(OfficeAdminConsultantsPage.class);
	
	public ConsultantClientsPage()
	{
		super();
		
		logger.debug("ConsultantClientsPage");
		
		Integer consultantId = userModel.getId();
			
		List<EntityUserModel> clientEntityList = userRepository.getListOfClientsOfAConsultant(currentOrg.getId(), consultantId);

		// LOOP THROUGH THE CLIENTS
		for (int clientIndex=0; clientIndex < clientEntityList.size(); clientIndex++)
		{
			// FOR THIS CLIENT
			EntityUserModel clientEntity = clientEntityList.get(clientIndex);
			Integer clientId = clientEntity.getId();
				
			// add client to the consultant branch
			TreeData clientBranch = new TreeData(clientEntity.getFirstName() + " " + clientEntity.getLastName(), clientEntity);

			// GET ASSIGNMENT LIST  (assignmentEntityList)
			List<Integer> clientOrgAssignmentsIntegerList = clientAssignmentsService.getListOfAssignmentIdsForClientOrg(clientId, currentOrg.getId());
			List<EntityAssignmentModel> assignmentEntityList = assignmentService.getAssignmentsList(clientOrgAssignmentsIntegerList);

			logger.debug("clientOrgAssignmentsIntegerList  = " + clientOrgAssignmentsIntegerList);		
			
			// LOOP OVER EACH ASSIGNMENT FOR THIS CLIENT
			for (int assignmentIndex=0; assignmentIndex < assignmentEntityList.size(); assignmentIndex++)
			{ 
				// FOR THIS ASSIGNMENT  (assignmentEntity)
				EntityAssignmentModel assignmentEntity = assignmentEntityList.get(assignmentIndex);
				Integer clientAssignmentId = clientAssignmentsService.getClientAssignmentId(clientId, assignmentEntity.getId());
				
				// GET THE ASSIGNMENT DUE DATE AND OVERALL COMPLETED STATUS
				//TODO: support due date below 
				Date dueDate = clientAssignmentsService.getClientAssignmentDuedate(clientId, assignmentEntity.getId());
				String assignmentStatus = clientAssignmentsService.getClientAssignmentStatus(clientId, assignmentEntity.getId());
				
				List<EntityAssignmentSequencesModel> assignmentSegmentList = assignmentSequenceService.getAssignmentSequences(assignmentEntity.getId());
				
				// add assignment to the tree at the root level.
				TreeData assignmentBranch = new TreeData(clientBranch, "\"" + assignmentEntity.getName() + "\" - "  + " due " + dueDate + " - " + assignmentStatus);

				// GET VIDEO/EXAM SEGMENTS FOR THIS ASSIGNMENT

				for (int segmentIndex=0; segmentIndex < assignmentSegmentList.size(); segmentIndex++)
				{
					// FOR EACH SEGMENT
					EntityAssignmentSequencesModel assignmentSegment = assignmentSegmentList.get(segmentIndex);
					
					Integer sequenceNumber = assignmentSegment.getId();
					EntityContentItemsModel contentItem = contentItemsService.getOne(assignmentSegment.getContentId());
					String contentName = contentItem.getName();

					EntityClientSequenceProgressModel segmentProgress = clientSequenceProgressRepository.getOneByClientAssignIdAndSeqId(clientAssignmentId, sequenceNumber);

					Boolean segmentStatusBool = (segmentProgress == null) ? false : segmentProgress.getCompleted();

					String segmentInfo = "\"" + contentName + "\"" + "  " + ((segmentStatusBool == true) ? "completed" : "not completed");
					
					// add the segment info to the assignment node
					TreeData segmentBranch = new TreeData(assignmentBranch, segmentInfo);

					// GET ALL USAGE RECORDS FOR THIS SEGMENT
					if (segmentProgress != null)
					{
						Integer clientSequenceId = segmentProgress.getId();								
						List<EntityClientSequenceUsageModel> usageList = clientSequenceUsageRepository.getListOfClientSequenceUsageModel(clientSequenceId);

						if (usageList != null)
						{
							for (int listIndex=0; listIndex < usageList.size(); listIndex++)
							{
								EntityClientSequenceUsageModel usageEntity = usageList.get(listIndex);
								String usageInfo = usageEntity.getStartTime().toString() + " login, duration " + 
										String.format("%02d", (usageEntity.getDuration() / 3600)) + ":" +
										String.format("%02d", (usageEntity.getDuration() / 60)) + ":" +
										String.format("%02d", (usageEntity.getDuration() % 60));
							
								// add the usage info to the segment node
								TreeData sequenceUsageBranch = new TreeData(segmentBranch, usageInfo);
							}
						}
					}
				}
			}
			
			clientsTreeList.add(clientBranch);
		}
			
		TreeDataProvider clientsTreeListProvider = new TreeDataProvider(clientsTreeList);
		
		DefaultNestedTree<TreeData> clientsTree = null;
		add(clientsTree = new DefaultNestedTree<TreeData>("tree", clientsTreeListProvider)
		{
			private static final long serialVersionUID = 1L;

			@Override 
		    protected Component newContentComponent(String id, IModel<TreeData> model) 
		    { 
		        return new Folder<TreeData>(id, this, model) 
		        { 
					private static final long serialVersionUID = 1L;

					/** 
		             * Always clickable. 
		             */ 
		            @Override 
		            protected boolean isClickable() 
		            { 
		            	// any node that is clickable should be checked below and true returned so that the
		            	// proper clickable hyperlink is shown to the user.
		            	if (model.getObject().getUser() != null)
		            	{
		            		// This node contains a client user and is clickable
		            		return true;
		            	}
                        else if (model.getObject().getAssignment() != null)
                        {
                                // Assignment node - not clickable
                        }
                        else
                        {
                                // other node type - not clickable
                        }
		                return false; 
		            } 

		            @Override 
		            protected void onClick(org.apache.wicket.ajax.AjaxRequestTarget target) 
		            { 
		            	if (model.getObject().getUser() != null)
		            	{
		            		// This node contains a client user. 
			            	logger.debug("clicked treedata for user:  " + model.getObject().toString());

			            	PageParameters params = new PageParameters();
		            		params.add("userid", model.getObject().getUser().getId());
		            		setResponsePage(ClientAssignmentsPage.class, params);
		            	}
                        else if (model.getObject().getAssignment() != null)
                        {
                                // This node contains an assignment.
                                logger.debug("clicked treedata for assignment:  " + model.getObject().toString());

                                // do nothing.  Assumption currently is that the Consultant should not be able
                                // to launch the assignment/video from the clients menu.  Consultants can view 
                                // assignments/videos from the training materials page.
                        }
                        else
                        {
                                // User and assignment are null for this node.  Do nothing at this point.
                                // Maybe later, if the node is identified, we could
                                // display other related information.
                                logger.debug("clicked treedata for other:  " + model.getObject().toString());
                        }
		            } 

		            @Override 
		            protected boolean isSelected() 
		            { 
		                // get selected status somewhere 
		                return false; 
		            } 
		        }; 
		    } 
		});
		
		//clientsTree.add(new HumanTheme());
		clientsTree.add(new WindowsTheme());
	}
	
}
