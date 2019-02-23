package com.linguaclassica.teacher;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.extensions.markup.html.repeater.tree.DefaultNestedTree;
import org.apache.wicket.extensions.markup.html.repeater.tree.ITreeProvider;
import org.apache.wicket.extensions.markup.html.repeater.tree.table.TreeColumn;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.googlecode.wicket.jquery.ui.form.button.ConfirmButton;
import com.linguaclassica.entity.EntityClassModel;
import com.linguaclassica.access.AlphPlusSession;
//import com.linguaclassica.access.ContentComponent;
import com.linguaclassica.access.TreeExpansion;
import com.linguaclassica.entity.EntityAssignmentModel;
import com.linguaclassica.model.ModelFactory;
import com.linguaclassica.model.UserModel;
import com.linguaclassica.service.AssignmentService;
import com.linguaclassica.service.ClassService;
import com.linguaclassica.service.UserService;

// TEMPORARY FILE  (Oct 2017)
// This file and corresponding html file is for tree view investigation, 
// and should be removed once the investigation is completed and working code 
// is integrated into the teacher assignments page (TeacherAssignmentsPage.java
// and html).

/**
 * The page where teachers can choose to create, read, update
 * and delete assignments from their selected listed classes.
 * @author Kristina Wirtanen
 * @Copyright("2015 Lingua Classica")
 */

public class TeacherAssignmentsPageTemp extends TeacherBasePage {
	private static final long serialVersionUID = 1L;
	
	@SpringBean
	private AssignmentService assignmentService;
	
	@SpringBean
	private UserService userService;
	
	@SpringBean
	private ClassService classService;
	
	//public AjaxRequestHandler ajaxHandler = new AjaxRequestHandler(this);
	
	ModelFactory modelFactory;
	
	List<EntityClassModel> classSelection;
	
	List<String> classnameList;
	
	List<String> classesList;
	
	ArrayList<String> classesSelected;
	
	ArrayList<String> selClassesSelected;
	
	List<Integer> classIdList;
	
	List<EntityClassModel> classList;	
	
	String classListStr;
	
	private final UserModel userModel = AlphPlusSession.get().getUser(getRequest());
	
	private List<IColumn<EntityClassModel, String>> classColumn = new ArrayList();
	
	private ITreeProvider<EntityClassModel> provider = new TreeProviderEntityClass();
	
	public List<EntityClassModel> publicClassList;
	
	public TeacherAssignmentsPageTemp()
	{
		super();

		classSelection = AlphPlusSession.get().getSelectedClasses();
		
		AnnouncementForm annform = new AnnouncementForm();
		add(annform);

    }

	class AnnouncementForm extends Form<Object>
	{
		private static final long serialVersionUID = 1L;

		TreeExpansion<String> tableTrees;
		
		@SuppressWarnings("unchecked")
		AnnouncementForm()
		{
		
		super("teacherAssignments");
			
		tableTrees = new TreeExpansion<String>("teacherAssignments", userModel.getId(), TreeExpansion.DISPLAY);
		//add(tableTrees);
			
		System.out.println("TeacherAssignmentsPage: beginning of annocuncement" );		
		
		// Creates string of selected classes
		// String to be displayed at the top of the panel
		if(classSelection!=null && classSelection.size()!=0){
			classListStr = classSelection.get(0).getClassname();
			for(int i = 1; i < classSelection.size(); i++){
				classListStr+=", " + classSelection.get(i).getClassname();
			}
		}else{
			classListStr = "No classes selected";
		}
		
		// Adds the names of the selected classes to the panel
		String labelstr = "Classes selected:  " + classListStr; 
		add(new Label("classesSelected",labelstr));  
		
		classList = tableTrees.getSelection();
		publicClassList = classList;
		
		/*for(int i = 0; i < classList.size(); i++){
			System.out.println(classList.get(i).getClassname());
			classColumn.add(new PropertyColumn<EntityClassModel, String>(Model.of(classList.get(i).getClassname()), "classListAt"+i));
			System.out.println(((PropertyColumn<EntityClassModel, String>) classColumn.get(i)).getPropertyExpression());
		}*/
		
		
		classColumn.add(new PropertyColumn<EntityClassModel, String>(Model.of(classList.get(0).getClassname()), "classListNode"));
		//classColumn.add(new TreeColumn<EntityClassModel, String>(Model.of(classList.get(1).getClassname())));
		/*classColumn.add(new AbstractColumn<EntityClassModel, String>(Model.of(classList.get(2).getClassname())){

			private static final long serialVersionUID = 1L;

			@Override
			public void populateItem(Item<ICellPopulator<EntityClassModel>> cellItem, String componentId,
					IModel<EntityClassModel> rowModel) {
				// TODO Auto-generated method stub
				
				NodeModel<EntityClassModel> node = (NodeModel<EntityClassModel>) rowModel;
				cellItem.add(new Label(componentId, "" + node.getDepth()));
				
			}
			
		});*/
		
		/*sdasdsdsdsdsdsd
		 * dsdsdsdsdsdsdsd
		 * dsdsdsdsdsdsd
		 * may not accept List<EntityClassModel> but instead just an EntityClassModel
		 */

		/*final TableTree<EntityClassModel, String> tree = 
				new TableTree<EntityClassModel, String>("tableTree", classColumn, provider, Integer.MAX_VALUE){
			
			private static final long serialVersionUID = 1L;

			@Override
			protected Component newContentComponent(String id, IModel<EntityClassModel> classModel) {
				// TODO Auto-generated method stub
				ContentComponent newComp = new ContentComponent(id, classModel);
				
				return newComp;//this.newContentComponent(id, classModel);
			}
			
		};
		
		tree.getTable().addTopToolbar(new HeadersToolbar<>(tree.getTable(), null));
		tree.getTable().addTopToolbar(new NavigationToolbar(tree.getTable()));
		tree.getTable().addBottomToolbar(new NoRecordsToolbar(tree.getTable()));
		add(tree);*/
		
		
		add(new DefaultNestedTree<EntityClassModel>("tableTree", provider){
			
			private static final long serialVersionUID = 1L;
			
			/*@Override
			protected Component newContentComponent(String id, IModel<EntityClassModel> node){
				return super.newContentComponent(id, node);
			}*/
		});
		
		/*Button selectExer = new Button("selectExer");
        selectExer.setDefaultFormProcessing(false);
        selectExer.add(new AjaxEventBehavior("onclick") {
			private static final long serialVersionUID = 1L;

			@Override
			protected void onEvent(AjaxRequestTarget target) {
					System.out.println("got to onClick");
					classColumn.remove(2);
					tree.updateNode(classList.get(2), target);
					//add(tree);
			}
			
		});
        add(selectExer);*/
        
        /*add(new Button("selectExer") {
			
			private static final long serialVersionUID = 1L;
			
			@Override
			public void onSubmit() {
				removeLatin();
				add(tree);
			}
			
        });*/
		
		classColumn.add(new TreeColumn<EntityClassModel, String>(Model.of(classList.get(1).getClassname())));
		classColumn.add(new TreeColumn<EntityClassModel, String>(Model.of(classList.get(2).getClassname())));
		
		//classColumn.remove(1);//Comment out and re-comment adding and removing may simulate dropdown menu - Worst idea
		
		//tree.updateNode(classList.get(2), ajaxHandler);//Comment out and uncomment out
		
		/*
		 * Adds 'create assignment' button to page
		 * Currently the user is directed to the CreateNotificationPage
		 * The user will need to be directed to the CreateAssignmentPage
		 * once implemented.
		 */
		/*add(new Link<Object>("createassignbutton") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				setResponsePage(new CreateAssignmentPage());
			}
		});*/
		
		// Adds each selected class with its corresponding assignments to page
		add(new ListView<EntityClassModel>("classnameList", classSelection) {
			private static final long serialVersionUID = 1L;

			protected void populateItem(ListItem<EntityClassModel> item)
			{
				EntityClassModel classItem = item.getModelObject();
				List<Integer> assignIdList = new ArrayList<Integer>();
				List<EntityAssignmentModel> assignList = new ArrayList<EntityAssignmentModel>();
				
				item.add(new Label("classnameLab",classItem.getClassname()));
						 
				// Retrieves all assignment ids for selected class
				assignIdList = assignmentService.getListOfAssignmentIdsByClassId(item.getModelObject().getId());
				
				// Iterates through all assignment ids and adds the assignment objects to an ArrayList
				if (!assignIdList.isEmpty()) {
					for(int i =0; i< assignIdList.size(); i++){
						assignList.add(assignmentService.findAssignmentById(assignIdList.get(i)));
					}
					
					System.out.println("Number of assignments for class id " + item.getModelObject().getId() + ": " +assignList.size());
					
				}

				// Adds all of the assignments to the page for the selected class
				item.add(new ListView<EntityAssignmentModel>("assignList",assignList) {
					private static final long serialVersionUID = 1L;

					
					Label nodeletelabel;
					
					@Override
					protected void populateItem(final ListItem<EntityAssignmentModel> subitem) {
						nodeletelabel = new Label("nodeleteLab","Already started.");
						nodeletelabel.setVisible(false);
						
						ConfirmButton deletebutton = new ConfirmButton("deletebutton","Delete",
								"Please Confirm Deletion", "The selected assignment will be permanently deleted") {
							private static final long serialVersionUID = 1L;

								@Override
								public void onError() {
									this.error("Validation failed!");
								}

							@Override
							public void onSubmit() {
								System.out.println("delete assignment clicked");
								assignmentService.deleteAssignmentById(subitem.getModelObject().getId());
							}																														
							
																																					
						};	
						
						subitem.add(deletebutton);
						
						Timestamp now = new Timestamp(System.currentTimeMillis());
						if(subitem.getModelObject().getStartDate().before(now)) {
							nodeletelabel.setVisible(true);
							deletebutton.setVisible(false);
							
						}
						subitem.add(new Label("assignLab",subitem.getModelObject().getAssignmentName()));
						subitem.add(nodeletelabel);
						subitem.add(new Label("createddateLab",subitem.getModelObject().getCreatedDate()));
						subitem.add(new Label("startdateLab",subitem.getModelObject().getStartDate()));
						subitem.add(new Label("duedateLab",subitem.getModelObject().getDueDate()));
						subitem.add(new Label("timezoneLab",subitem.getModelObject().getTimeZone()));
						subitem.add(new Label("statusLab",subitem.getModelObject().getStatus()));
						
						/*
						 * Adds 'edit' button to page per assignment
						 * Currently the user is directed to the CreateNotificationPage
						 * The user will need to be directed to the EditAssignmentPage
						 * once implemented.
						 */
						subitem.add(new Link<Object> ("editbutton") {
							private static final long serialVersionUID = 1L;

							@Override
							public void onClick() {
								System.out.println("Edit assignment clicked.");
								PageParameters pageParameters = new PageParameters();
								pageParameters.add("AssignmentId",subitem.getModelObject().getId());
								//setResponsePage(EditAssignmentPage.class, pageParameters);
							}
						});
						/*jw2017-04-10
						subitem.add(new Link ("deletebutton") {
							private static final long serialVersionUID = 1L;

							@Override
							public void onClick() {
								System.out.println("Delete assignment clicked.");
								PageParameters pageParameters = new PageParameters();
								pageParameters.add("AssignmentId",subitem.getModelObject().getId());
								Timestamp creationDate = subitem.getModelObject().getCreatedDate();
								System.out.println("CreationDateDelete: "+creationDate);
								setResponsePage(EditAssignmentPage.class, pageParameters);
							}
						});
						endjw2017-04-10*/
						}
					});
				}
			});
		}
	}
	
	public void removeLatin(){
		classColumn.remove(2);
	}
	
	public List<EntityClassModel> getClasses(){
		
		return publicClassList;
	}
	
}
