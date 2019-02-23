package com.linguaclassica.client;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.wicket.Component;
import org.apache.wicket.extensions.markup.html.repeater.tree.DefaultNestedTree;
import org.apache.wicket.extensions.markup.html.repeater.tree.content.Folder;
import org.apache.wicket.extensions.markup.html.repeater.tree.theme.HumanTheme;
import org.apache.wicket.extensions.markup.html.repeater.tree.theme.WindowsTheme;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.resource.ResourceReference;
import org.apache.wicket.spring.injection.annot.SpringBean;
import com.linguaclassica.access.LMSSession;
import com.linguaclassica.entity.EntityAssignmentModel;
import com.linguaclassica.entity.EntityAssignmentSequencesModel;
import com.linguaclassica.entity.EntityClientSequenceProgressModel;
import com.linguaclassica.entity.EntityClientSequenceUsageModel;
import com.linguaclassica.entity.EntityContentItemsModel;
import com.linguaclassica.model.ContentItemsModel;
import com.linguaclassica.model.UserModel;
import com.linguaclassica.repository.ClientSequenceProgressRepository;
import com.linguaclassica.repository.ClientSequenceUsageRepository;
import com.linguaclassica.service.AssignmentSequencesService;
import com.linguaclassica.service.AssignmentService;
import com.linguaclassica.service.ClientAssignmentsService;
import com.linguaclassica.service.ContentItemsService;
import com.linguaclassica.service.VideoService;
import com.linguaclassica.shared.TreeData;
import com.linguaclassica.shared.TreeDataProvider;
import com.linguaclassica.shared.PrivateBasePage;

public class AssignmentsPage extends PrivateBasePage
{
	private static final long serialVersionUID = 1L;
	
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
	
	@SpringBean
	VideoService videoService;
	
	// Active user
	private final UserModel userModel = LMSSession.get().getUser(getRequest());
	public List<TreeData> assignmentsTreeList = new ArrayList<>();

	private Logger logger = LogManager.getLogger(AssignmentsPage.class);
	
	public AssignmentsPage()
	{
		super();
		
		logger.debug("AssignmentsPage");
		
		String myaccountUsername = userModel.getFirstName() + " " + userModel.getLastName();
		add(new Label("username", myaccountUsername));

		Integer clientId = userModel.getId();
		
		// GET ASSIGNMENT LIST FOR THIS CLIENT/ORG  (assignmentEntityList)
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
			TreeData assignmentBranch = new TreeData("\"" + assignmentEntity.getName() + "\" - " + " due " + dueDate + " - " + assignmentStatus);
			assignmentsTreeList.add(assignmentBranch);

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
				TreeData segmentBranch = new TreeData(assignmentBranch, segmentInfo, assignmentSegment);

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

		TreeDataProvider clientsTreeListProvider = new TreeDataProvider(assignmentsTreeList);
		
		DefaultNestedTree<TreeData> assignmentsTree = null;
		add(assignmentsTree = new DefaultNestedTree<TreeData>("tree", clientsTreeListProvider)
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
		            	model.getObject().getId();
		            	EntityAssignmentSequencesModel assignmentSegment = null;
                        if ((assignmentSegment = model.getObject().getAssignmentSegment()) != null)
                        {
                                // This node contains an assignment segment that is clickable.
                        		return true;
                        }

		            	return false; 
		            } 

		            @Override 
		            protected void onClick(org.apache.wicket.ajax.AjaxRequestTarget target) 
		            { 
		            	model.getObject().getId();
		            	EntityAssignmentSequencesModel assignmentSegment = null;
                        if ((assignmentSegment = model.getObject().getAssignmentSegment()) != null)
                        {
                                // This node contains an assignment segment.
                                logger.debug("clicked treedata for assignment segment:  " + model.getObject().toString());

                                EntityContentItemsModel assignmentSegmentContent = contentItemsService.getOne(assignmentSegment.getContentId());
                                if (assignmentSegmentContent.getTypeId() == ContentItemsModel.ContentTypes.VIDEO)
                                {
                                	logger.debug("VIDEO assignmentSegment, contentType = " + assignmentSegmentContent.getTypeId());
                                	Integer clientAssignmentId = clientAssignmentsService.getClientAssignmentId(clientId, assignmentSegment.getAssignmentId());
                                	setResponsePage(new ClientVideoPage(assignmentSegment, clientAssignmentId));
                                }
                        }
                        else
                        {
                            logger.debug("clicked treedata for nodeName=" + model.getObject().getId() + 
                            		", className= " + model.getObject().getClass());
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
		
		assignmentsTree.add(new WindowsTheme());
	}
}
