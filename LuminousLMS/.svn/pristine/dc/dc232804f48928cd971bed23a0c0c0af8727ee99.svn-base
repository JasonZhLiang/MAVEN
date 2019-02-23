package com.linguaclassica.officeadmin;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormChoiceComponentUpdatingBehavior;
import org.apache.wicket.extensions.markup.html.repeater.tree.DefaultNestedTree;
import org.apache.wicket.extensions.markup.html.repeater.tree.content.Folder;
import org.apache.wicket.extensions.markup.html.repeater.tree.theme.WindowsTheme;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.linguaclassica.entity.EntityAssignmentModel;
import com.linguaclassica.entity.EntityAssignmentSequencesModel;
import com.linguaclassica.entity.EntityClientSequenceProgressModel;
import com.linguaclassica.entity.EntityClientSequenceUsageModel;
import com.linguaclassica.entity.EntityContentItemsModel;
import com.linguaclassica.entity.EntityUserModel;
import com.linguaclassica.model.PermissionModel.Permission;
import com.linguaclassica.repository.ClientSequenceProgressRepository;
import com.linguaclassica.repository.ClientSequenceUsageRepository;
import com.linguaclassica.service.AssignmentSequencesService;
import com.linguaclassica.service.AssignmentService;
import com.linguaclassica.service.ClientAssignmentsService;
import com.linguaclassica.service.ContentItemsService;
import com.linguaclassica.service.UserService;
import com.linguaclassica.shared.TreeData;
import com.linguaclassica.shared.TreeDataProvider;
import com.linguaclassica.shared.PrivateBasePage;
import com.linguaclassica.shared.SelectionMultiCheck;
//import com.linguaclassica.shared.SelectionMultiCheck;

public class OfficeAdminConsultantsPage extends PrivateBasePage
{
	private static final long serialVersionUID = 1L;
	
		
	@SpringBean
	UserService userService;
	
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

	SelectionMultiCheck<String> checkboxes;
	
	private Logger logger = LogManager.getLogger(OfficeAdminConsultantsPage.class);
	
	public List<TreeData> consultantsTreeList = new ArrayList<>();
	public static List<EntityUserModel> consultantIdList = null; 
	final static List<String> userStatusList = Arrays.asList(new String[] {
			"Active", "Temporary Inactive", "Archived"});

	public OfficeAdminConsultantsPage()
	{
		super();
		
		logger.debug("OfficeAdminConsultantsPage");
		
		////////////////////
		checkboxes = new SelectionMultiCheck<String>(userStatusList); 
		// same as in User page
		checkboxes.setRequired(true);
		//do not put this add AjaxFormChoiceComponentUpdatingBehavior call into Initialize function - it will create a long update loop
		checkboxes.add( new AjaxFormChoiceComponentUpdatingBehavior() {
					private static final long serialVersionUID = 1L;
					
					@Override
					protected void onUpdate(AjaxRequestTarget target) {					
						checkboxes.ResetChoices();	
						PopulateTree();
						target.add(	target.getPage().get("classcheckboxes"));
						target.add(	target.getPage().get("tree"));
					}	
					//to use with setRequired(true)
					@Override
					protected void onError(AjaxRequestTarget target,
					           RuntimeException e){		
						if(e == null){//in case of validation mishap when no boxes are checked
							checkboxes.clearSelection();
							onUpdate(target);	
						}
						
					}	
				
				});

		add(checkboxes.setOutputMarkupId(true));
		///////////////
		
		logger.debug("1");
		
		// TODO: change '2' in parameter list below to some equivalent of Permission.CONSULTANT
		consultantIdList = userService.getListOfUsersByPermission(currentOrg.getId(), Permission.CONSULTANT);
		PopulateTree();
		
		logger.debug("2");

		TreeDataProvider consultantsTreeListProvider = new TreeDataProvider(consultantsTreeList);
		
		DefaultNestedTree<TreeData> defaultNestedTree = new DefaultNestedTree<TreeData>("tree", consultantsTreeListProvider)
		{
			private static final long serialVersionUID = 1L;

			/**
			 * To use a custom component for the representation of a node's content we would
			 * override this method.
			 */

			// // Below newContentComponent is useful for display of data only 
			//
			// @Override
			// protected Component newContentComponent(String id, IModel<TreeData> node)
			// {
			// 	return super.newContentComponent(id, node);
			// }
			
			// Below newContentComponent is useful for clickable data (to launch other pages, etc) 
			//
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
		            	TreeData td = model.getObject();

		            	if (td == null)
		            	{
		            		logger.error("model object is null");
		            		return false;
		            	}
		            	
		            	if (td.getUser() != null)
		            	{
		            		return true;
		            	}
		                return false; 
		            } 

		            @Override 
		            protected void onClick(org.apache.wicket.ajax.AjaxRequestTarget target) 
		            { 
		            	TreeData td = model.getObject();

		            	if (td == null)
		            	{
		            		logger.error("model object is null");
		            		return;
		            	}
		            	
		            	if (td.getUser() != null)
		            	{
		            		// This node contains a client user. 
			            	logger.debug("clicked treedata for user:  " + model.getObject().toString());

			            	int clientId = td.getUser().getId();
			            	PageParameters paramEditClient = new PageParameters();
			            	//no parent means the top of the branch i.e Consultant
			            	paramEditClient.add("usertype", (td.getParent() == null ?Permission.CONSULTANT :Permission.CLIENT) );
							paramEditClient.add("userid",clientId);
							setResponsePage(UserCreatePage.class, paramEditClient);
		            	}
                        else if (td.getAssignment() != null)
                        {
                                // This node contains an assignment.
                                logger.debug("clicked treedata for assignment:  " + model.getObject().toString());

                                // do nothing at the moment.
                        }
                        else
                        {
                                // User and assignment are null for this node.  Do nothing at this point.
                                // Maybe later, if the node is identified, we could
                                // display other related information.
                                logger.debug("clicked treedata for other node type:  " + model.getObject().toString());
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
		};
		
		//defaultNestedTree.add(new HumanTheme());
		defaultNestedTree.add(new WindowsTheme());

		logger.debug("2");
		
		add(defaultNestedTree.setOutputMarkupId(true));
		//////////////////////////
		Boolean  show = true;
		
		List<EntityUserModel> clientEntityList = null;
		clientEntityList = userService.getListOfUnassignedActiveClients(currentOrg.getId());
		//n order to avoid problems in case of empty list - create an item
		if(clientEntityList == null || clientEntityList.isEmpty()){
			clientEntityList.add(new EntityUserModel(0, "No", "Clients", "","",0));
			show = false;
		}
		
		WebMarkupContainer wmc = new WebMarkupContainer("div_uclients");
		
		logger.debug("3");
		wmc.add (new Label("clients", "Unassigned Clients"));
		
		ListView<EntityUserModel> clientList = new ListView<EntityUserModel>("clientlist",clientEntityList)
		{
			private static final long serialVersionUID = 1L;
			@Override
			protected void populateItem(ListItem<EntityUserModel> item)
			{
				EntityUserModel client = (EntityUserModel) item.getModelObject();
				Link<Object> link = new Link<Object>("clientname"){
					private static final long serialVersionUID = 1L;
					@Override
					public void onClick() {
						PageParameters paramEditClient = new PageParameters();
						paramEditClient.add("usertype", Permission.CLIENT);
						paramEditClient.add("userid",client.getId());
						setResponsePage(UserCreatePage.class, paramEditClient);
					}			
				};
				link.add(new Label("clientlabel",client.getFirstName()+ " " + client.getLastName()));
				
				item.add(link);
				
			}
		};
		wmc.add(clientList);
		logger.debug("4");
		add(wmc.setVisible(show));
	}
	
	private void PopulateTree(){
		
		if(!consultantsTreeList.isEmpty()){
			consultantsTreeList.clear();
		}
		logger.debug("5");
		
		int lengthList = 0;// in case there is no recordsd
		if(consultantIdList !=null && !consultantIdList.isEmpty())
			lengthList = consultantIdList.size();
		List <String> selection = checkboxes.getSelection();
		// Loop over the consultants in this organization, and add the client/assignment info for each consultant
		for (int i=0; i < lengthList; i++)
		{
			EntityUserModel consultantEntity = consultantIdList.get(i);
			
			for(String status:selection){
				logger.debug("6");
				if(userStatusList.get(consultantEntity.getStatus()).equals(status)){
					
					// add consultant as a new root in the tree provider
					TreeData consultant = new TreeData(consultantEntity.getFirstName() + " " + consultantEntity.getLastName(),consultantEntity);
					logger.debug("7");
								
					List<EntityUserModel> clientEntityList = userService.getListOfClientsOfAConsultant(currentOrg.getId(), consultantEntity.getId());
					int listLenght = 0;
					if(clientEntityList != null && !clientEntityList.isEmpty())
						listLenght = clientEntityList.size();
					logger.debug("8");
					// loop over the clients for this consultant, and add the assignment info for each client
					for (int clientIndex=0; clientIndex < listLenght; clientIndex++)
					{
						EntityUserModel clientEntity = clientEntityList.get(clientIndex);
						Integer clientId = clientEntity.getId();
						logger.debug("9");
						// add client to the consultant branch
						TreeData clientBranch = new TreeData(consultant, clientEntity.getFirstName() + " " + clientEntity.getLastName(),clientEntity);
			
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
							TreeData assignmentBranch = new TreeData(clientBranch, "\"" + assignmentEntity.getName() + "\" - " + " due " + dueDate + " - " + assignmentStatus);

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
					
					
					
					
					}
					
					consultantsTreeList.add(consultant);
					break;
				}
			}
		}
	}
}
