package com.linguaclassica.shared;

import java.sql.Date;
import java.util.List;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.extensions.markup.html.repeater.tree.DefaultNestedTree;
import org.apache.wicket.extensions.markup.html.repeater.tree.content.Folder;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.linguaclassica.access.LMSSession;
import com.linguaclassica.client.ClientVideoPage;
import com.linguaclassica.entity.EntityAssignmentModel;
import com.linguaclassica.entity.EntityAssignmentSequencesModel;
import com.linguaclassica.entity.EntityClientSequenceProgressModel;
import com.linguaclassica.entity.EntityContentItemsModel;
import com.linguaclassica.entity.EntityMessageModel;
import com.linguaclassica.entity.EntityUserModel;
import com.linguaclassica.model.ContentItemsModel;
import com.linguaclassica.model.UserModel;
import com.linguaclassica.model.PermissionModel.Permission;
import com.linguaclassica.repository.ClientSequenceProgressRepository;
import com.linguaclassica.repository.ClientSequenceUsageRepository;
import com.linguaclassica.service.AssignmentSequencesService;
import com.linguaclassica.service.AssignmentService;
import com.linguaclassica.service.ClientAssignmentsService;
import com.linguaclassica.service.ContentItemsService;
import com.linguaclassica.service.MessageService;
import com.linguaclassica.service.NotificationService;
import com.linguaclassica.service.PermissionService;
import com.linguaclassica.service.UserService;
import com.linguaclassica.service.VideoService;

public class CommonOverviewPage extends SharedOverviewPage
{
	private static final long serialVersionUID = 1L;
	
	@SpringBean
	NotificationService notificationService;
	
	@SpringBean
	MessageService messageService;
	
	@SpringBean
	UserService userService;
	
	@SpringBean
	PermissionService permissionService;
	
	@SpringBean
	VideoService videoService;

	@SpringBean
	AssignmentService assignmentService;

	@SpringBean
	ClientAssignmentsService clientAssignmentsService;

	@SpringBean
	AssignmentSequencesService assignmentSequenceService;

	@SpringBean
	ContentItemsService contentItemsService;

	@SpringBean
	ClientSequenceProgressRepository clientSequenceProgressRepository;

	@SpringBean
	ClientSequenceUsageRepository clientSequenceUsageRepository;

	// Active user
	private final UserModel userModel = LMSSession.get().getUser(getRequest());
	public List<TreeData> assignmentsTreeList = new ArrayList<>();

	private Logger logger = LogManager.getLogger(CommonOverviewPage.class);
	List<EntityMessageModel> messageList;
	
	public CommonOverviewPage()
	{
		super(false);	
		
		Integer userId = userModel.getId();
		
		// H1 label
		Integer upermid = LMSSession.get().getCurrentPermission();
		Permission upermission = permissionService.getModel(upermid).getPermission();
		String labelid = "commonoverviewhdr";
		if (upermission == Permission.CLIENT)
		{
			labelid = "cloverviewhdr";
		}
		else if (upermission == Permission.CONSULTANT)
		{
			labelid = "cooveriewhdr";
		}
		add(new Label("coverviewhl", new ResourceModel(labelid)));

		// Get incomplete assignments back and put in a Tree
		List<Integer> clientOrgAssignmentsIntegerList = clientAssignmentsService.getListOfAssignmentIdsForClientOrg(userId, currentOrg.getId());
		List<EntityAssignmentModel> assignmentEntityList = assignmentService.getAssignmentsList(clientOrgAssignmentsIntegerList);

		logger.debug("clientOrgAssignmentsIntegerList  = " + clientOrgAssignmentsIntegerList);		
		
		// LOOP OVER EACH ASSIGNMENT FOR THIS CLIENT
		for (int assignmentIndex=0; assignmentIndex < assignmentEntityList.size(); assignmentIndex++)
		{
			// FOR THIS ASSIGNMENT  (assignmentEntity)
			EntityAssignmentModel assignmentEntity = assignmentEntityList.get(assignmentIndex);
			Integer clientAssignmentId = clientAssignmentsService.getClientAssignmentId(userId, assignmentEntity.getId());
			
			// GET THE ASSIGNMENT DUE DATE AND OVERALL COMPLETED STATUS
			Date dueDate = clientAssignmentsService.getClientAssignmentDuedate(userId, assignmentEntity.getId());
			String assignmentStatus = clientAssignmentsService.getClientAssignmentStatus(userId, assignmentEntity.getId());
			boolean assignmentComplete = clientAssignmentsService.isClientAssignmentComplete(userId, assignmentEntity.getId());
            logger.debug("assignmentStatus:  " + assignmentStatus + " assignmentComplete: " + assignmentComplete);
            
            if(!assignmentComplete)
            {
						
				List<EntityAssignmentSequencesModel> assignmentSegmentList = assignmentSequenceService.getAssignmentSequences(assignmentEntity.getId());
				
				// add assignment to the tree at the root level.
				TreeData assignmentBranch = new TreeData("\"" + assignmentEntity.getName() + "\" - " + " due " + dueDate + " - " + assignmentStatus);
				assignmentsTreeList.add(assignmentBranch);
	
				// GET incomplete VIDEO/EXAM SEGMENTS FOR THIS ASSIGNMENT
	
				for (int segmentIndex=0; segmentIndex < assignmentSegmentList.size(); segmentIndex++)
				{
					// FOR EACH SEGMENT
					EntityAssignmentSequencesModel assignmentSegment = assignmentSegmentList.get(segmentIndex);
					
					Integer sequenceNumber = assignmentSegment.getId();
					EntityContentItemsModel contentItem = contentItemsService.getOne(assignmentSegment.getContentId());
					String contentName = contentItem.getName();
	
					EntityClientSequenceProgressModel segmentProgress = clientSequenceProgressRepository.getOneByClientAssignIdAndSeqId(clientAssignmentId, sequenceNumber);
	
					Boolean segmentStatusBool = (segmentProgress == null) ? false : segmentProgress.getCompleted();
		            logger.debug("Outside check segmentIndex: " + segmentIndex + " segmentStatusBool:  " + segmentStatusBool);
		            
		            // ONLY ADD INCOMPLETE SEGMENTS INTO THE TREE
		            if(!segmentStatusBool)
		            {
						String segmentInfo = "\"" + contentName + "\"" + "  " + "not completed";					
						// add the segment info to the assignment node
						TreeData segmentBranch = new TreeData(assignmentBranch, segmentInfo, assignmentSegment);
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
		             * IN OVERVIEW PAGE, MAKE IT UN-CLICKABLE. 
		             */ 
		            @Override 
		            protected boolean isClickable() 
		            { 
		            	model.getObject().getId();
		            	EntityAssignmentSequencesModel assignmentSegment = null;
                        if ((assignmentSegment = model.getObject().getAssignmentSegment()) != null)
                        {
                        	return true;
                        }
                        return false;
		            } 

		            @Override 
		            protected boolean isSelected() 
		            { 
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
                                	Integer clientAssignmentId = clientAssignmentsService.getClientAssignmentId(userId, assignmentSegment.getAssignmentId());
                                	setResponsePage(new ClientVideoPage(assignmentSegment, clientAssignmentId));
                                }
                        }
                        else
                        {
                            logger.debug("clicked treedata for nodeName=" + model.getObject().getId() + 
                            		", className= " + model.getObject().getClass());
                        }
		            } 
		        }; 
		    } 
		});		

/*		
		List<EntityVideoModel> videoList = videoService.getListOfVideoidsByOrgid(currentOrg.getId());
		ListView<EntityVideoModel> videosList = new ListView<EntityVideoModel>("videolist",videoList)
		{
			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(ListItem<EntityVideoModel> item)
			{
				EntityVideoModel videos = (EntityVideoModel) item.getModelObject();
				logger.debug("populate video id " + videos.getVideoid());
				item.add(new Label("videotitle", videos.getVideotitle()));
				item.add(new Link<Object>("videolink")
				{
					private static final long serialVersionUID = 1L;

					@Override
					public void onClick()
					{
						logger.info("video onClick " + videos.getVideoid());
						if (!videos.getVideoid().equals("videoid"))
						{
							setResponsePage(new ClientVideoPage(videos.getVideoid()));
						}
						else
						{
							// TODO Handle this error
							Session.get().info("no id for this video");
						}
					}
										
				});
			}
		};
		add(videosList);
*/

		// Other Resources block
		WebMarkupContainer wmcOther = new WebMarkupContainer("otherrectraining")
		{
			private static final long serialVersionUID = 1L;
			
			public boolean isVisible()
			{
				Integer permid = LMSSession.get().getCurrentPermission();
				Permission permission = permissionService.getModel(permid).getPermission();
				return permission == Permission.CLIENT;
			}
			
		};
		add(wmcOther);

				
		// Messages block
		labelid = "commonmsghdr";
		if (upermission == Permission.CLIENT)
		{
			labelid = "clovermsghdr";
		}
		else if (upermission == Permission.CONSULTANT)
		{
			labelid = "coovermsghdr";
		}
		add(new Label("covermessgeshl", new ResourceModel(labelid)));

		messageList = messageService.findUnreadMessagesByToId(userId, currentOrg.getId());
		ListView<EntityMessageModel> messageView = new ListView<EntityMessageModel>("messageview", messageList)
		{
			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(ListItem<EntityMessageModel> item)
			{
				EntityMessageModel messages = item.getModelObject();
				Integer fromId = messages.getFromId();
				EntityUserModel user = (EntityUserModel) userService.findUserById(fromId);
				String firstName = user.getFirstName();
				String lastName = user.getLastName();
				String creationDate = messages.getCreateDate().toString();
				item.add(new Label("subject", item.getModelObject().getSubject()));
				item.add(new Label("from", firstName + " " + lastName));
				item.add(new Label("createdate", creationDate));
				item.add(new AjaxEventBehavior("click")
				{
					private static final long serialVersionUID = 1L;

					@Override
				    protected void onEvent(AjaxRequestTarget target)
					{
				    	EntityMessageModel messageModel = item.getModelObject();
				    	logger.info("messaging onEvent " + messageModel.getId());
				    	setResponsePage(new MessagingPage(messageModel, user));
				    }
				});
			}
			
		    @Override
		    public boolean isVisible()
		    {
		        return !messageList.isEmpty();
		    }
		};
		add(messageView);
		
		// Show "no unread messages" if messageList is empty
		add(new Label("nomsgs", getString("nomessl"))
		{
			private static final long serialVersionUID = -7953993968526859424L;

			@Override
		    public boolean isVisible()
		    {
		        return messageList.isEmpty();
		    }
		});
	}
}
