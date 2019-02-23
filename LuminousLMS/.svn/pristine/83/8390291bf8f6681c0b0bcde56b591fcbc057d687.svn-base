package com.linguaclassica.shared;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.extensions.markup.html.repeater.tree.DefaultNestedTree;
import org.apache.wicket.extensions.markup.html.repeater.tree.content.Folder;
import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.linguaclassica.access.LMSSession;
import com.linguaclassica.consultant.ConsultantVideoPage;
import com.linguaclassica.entity.EntityAssignmentModel;
import com.linguaclassica.entity.EntityAssignmentSequencesModel;
import com.linguaclassica.entity.EntityContentItemsModel;
import com.linguaclassica.entity.EntityVideoModel;
import com.linguaclassica.model.ContentItemsModel;
import com.linguaclassica.service.AssignmentSequencesService;
import com.linguaclassica.service.AssignmentService;
import com.linguaclassica.service.ContentItemsService;
import com.linguaclassica.service.VideoService;
import com.linguaclassica.shared.PrivateBasePage;
import com.linguaclassica.shared.TreeData;
import com.linguaclassica.shared.TreeDataProvider;

public class TrainingMaterialsPage extends PrivateBasePage
{
	private static final long serialVersionUID = 1L;
	
	@SpringBean
	VideoService videoService;
	
	@SpringBean
	AssignmentService assignmentService;
	
	@SpringBean
	AssignmentSequencesService assignmentSequenceService;
	
	@SpringBean
	ContentItemsService contentItemsService;	
	
	public TrainingMaterialsPage() {
		super();
	
		buildTree("assistedtree", currentOrg.getId(), true);
		buildTree("othertree", currentOrg.getId(), false);
	}

	public void buildTree(String treeid, int orgid, Boolean assisted)
	{
		List<TreeData> assignmentsTreeList = new ArrayList<>();
		List<EntityAssignmentModel> assignments = assignmentService.getOrgAssignmentsList(currentOrg.getId(), assisted);

		for (EntityAssignmentModel asgn : assignments)
		{
			TreeData assignmentBranch = new TreeData(asgn.getName(), asgn);
			assignmentsTreeList.add(assignmentBranch);
			
			List<EntityContentItemsModel> contentList =
					contentItemsService.getContentItemsForAnAssignment(asgn.getId());
			
			int seqNum = 0;
			for (EntityContentItemsModel content : contentList)
			{
            	if(content.getTypeId() != ContentItemsModel.ContentTypes.QUIZ)
            		new TreeData(assignmentBranch, content.getName(), content, seqNum);
				seqNum++;
			}
		}
		TreeDataProvider treeListProvider = new TreeDataProvider(assignmentsTreeList);
		
		add(new DefaultNestedTree<TreeData>(treeid, treeListProvider)
		{
			private static final long serialVersionUID = 46462234788L;

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
		            	EntityContentItemsModel content = model.getObject().getContent();
		            	if (content != null)
                        {
		            		return true;
                        }
		            	
		            	return false;
		            } 

		            @Override 
		            protected void onClick(org.apache.wicket.ajax.AjaxRequestTarget target) 
		            {
		            	EntityContentItemsModel content = model.getObject().getContent();
		            	if (content != null)
                        {
                        	if(content.getTypeId() == ContentItemsModel.ContentTypes.VIDEO)
                        	{
                        		EntityVideoModel video = videoService.getFromContentId(content.getId());                        		
                        		
                        		if (video != null)
                        		{
                            		setResponsePage(new ConsultantVideoPage(content.getName(), video.getVideoid(),
                            				video.getTranscript()));
                        		}
                        	}
                        }
		            }
		        };
		    }
		});
	}
}