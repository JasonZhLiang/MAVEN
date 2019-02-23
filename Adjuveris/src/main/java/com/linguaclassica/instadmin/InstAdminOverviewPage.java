package com.linguaclassica.instadmin;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.linguaclassica.access.AlphPlusSession;
import com.linguaclassica.entity.EntityAssignmentModel;
import com.linguaclassica.entity.EntityClassModel;
import com.linguaclassica.entity.EntityTermModel;
import com.linguaclassica.service.AssignmentService;
import com.linguaclassica.service.ClassService;
import com.linguaclassica.service.TermService;
import com.linguaclassica.service.UserService;

public class InstAdminOverviewPage extends InstAdminBasePage
{
	private static final long serialVersionUID = 1L;
	
	@SpringBean
	TermService termService;
	
	@SpringBean
	ClassService classService;
	
	@SpringBean
	UserService userService;
	
	@SpringBean
	AssignmentService assignmentService;
	
	int institutionId;
	
	List<EntityClassModel> selclasslistIn;
	
	public InstAdminOverviewPage()
	{
		super();

		institutionId = AlphPlusSession.get().getCurrentInstitution();
		
		permitPreferenceSelection();

		// Create the form
		InstAdminOverviewForm form = new InstAdminOverviewForm("overviewForm");
		add(form);
	}

	class InstAdminOverviewForm extends Form<Object>
	{
		private static final long serialVersionUID = 1L;
		
		InstAdminOverviewForm(String id)
		{
			super(id);

			System.out.format("InstAdminOverviewForm(%s)%n", id);

			java.sql.Date now = new java.sql.Date(Calendar.getInstance().getTime().getTime());
			
			List<EntityTermModel> nowList = termService.getListOfExistingTerms(institutionId, now);
			
			List<EntityTermModel> newList = termService.getListOfNewTerms(institutionId, now);
			
			List<EntityTermModel> pastList = termService.getListOfPastTerms(institutionId, now,
					AlphPlusSession.get().getDateRange());
			
			ListView<EntityTermModel> currentList = new ListView<EntityTermModel>("currentList", nowList)
			{
				private static final long serialVersionUID = 1L;

				protected void populateItem(ListItem<EntityTermModel> item)
				{
					EntityTermModel terms = (EntityTermModel) item.getModelObject();
					item.add(new Label("termnameLab",terms.getTermname()));
					item.add(new Label("startLab",terms.getStartDate()));
					item.add(new Label("endLab",terms.getEndDate()));
					List<EntityClassModel> classes = classService.getListOfClassesByTermId(terms.getId());
					int classlistlen = classes.size();
					item.add(new Label("classesLab",classlistlen));
					List<Integer> studentIds = termService.getListOfStudentIdsByTermId(terms.getId());
					item.add(new Label("studentsLab",studentIds.size()));
					List<Integer> teacherIds = termService.getListOfTeacherIdsByTermId(terms.getId());
					item.add(new Label("teachersLab",teacherIds.size()));
					List<Integer> taIds = termService.getListOfTaIdsByTermId(terms.getId());
					item.add(new Label("tasLab",taIds.size()));
					List<Integer> classIdList = new ArrayList<Integer>();
					for (int i = 0; i < classlistlen; i++)
					{
						classIdList.add(classes.get(i).getId());
					}
					List<EntityAssignmentModel> assigns = 
							assignmentService.getListOfAssignmentsByClassIdList(classIdList);
					int assignslen = 0;
					if (assigns != null)
					{
						assignslen = assigns.size();
					}
					item.add(new Label("assignsLab", assignslen));
				}
				
			};
			
			add(currentList);
			
			ListView<EntityTermModel> newtermList = new ListView<EntityTermModel>("newList", newList)
			{
				private static final long serialVersionUID = 1L;

				protected void populateItem(ListItem<EntityTermModel> newitem)
				{
					EntityTermModel terms = (EntityTermModel) newitem.getModelObject();
					newitem.add(new Label("newtermnameLab",terms.getTermname()));
					newitem.add(new Label("newstartLab",terms.getStartDate()));
					newitem.add(new Label("newendLab",terms.getEndDate()));
					List<EntityClassModel> classes = classService.getListOfClassesByTermId(terms.getId());
					int classlistlen = classes.size();
					newitem.add(new Label("newclassesLab",classlistlen));
					List<Integer> studentIds = termService.getListOfStudentIdsByTermId(terms.getId());
					newitem.add(new Label("newstudentsLab",studentIds.size()));
					List<Integer> teacherIds = termService.getListOfTeacherIdsByTermId(terms.getId());
					newitem.add(new Label("newteachersLab",teacherIds.size()));
					List<Integer> taIds = termService.getListOfTaIdsByTermId(terms.getId());
					newitem.add(new Label("newtasLab",taIds.size()));
					List<Integer> classIdList = new ArrayList<Integer>();
					for (int i = 0; i < classlistlen; i++)
					{
						classIdList.add(classes.get(i).getId());
					}
					List<EntityAssignmentModel> assigns = 
							assignmentService.getListOfAssignmentsByClassIdList(classIdList);
					int assignslen = 0;
					if (assigns != null)
					{
						assignslen = assigns.size();
					}
					newitem.add(new Label("newassignsLab",assignslen));
				}
				
			};
			
			add(newtermList);
			
			ListView<EntityTermModel> pasttermList = new ListView<EntityTermModel>("pastList", pastList)
			{
				private static final long serialVersionUID = 1L;

				protected void populateItem(ListItem<EntityTermModel> pastitem)
				{
					EntityTermModel terms = (EntityTermModel) pastitem.getModelObject();
					pastitem.add(new Label("pasttermnameLab",terms.getTermname()));
					pastitem.add(new Label("paststartLab",terms.getStartDate()));
					pastitem.add(new Label("pastendLab",terms.getEndDate()));
					List<EntityClassModel> classes = classService.getListOfClassesByTermId(terms.getId());
					int classlistlen = classes.size();
					pastitem.add(new Label("pastclassesLab",classlistlen));
					List<Integer> studentIds = termService.getListOfStudentIdsByTermId(terms.getId());
					pastitem.add(new Label("paststudentsLab",studentIds.size()));
					List<Integer> teacherIds = termService.getListOfTeacherIdsByTermId(terms.getId());
					pastitem.add(new Label("pastteachersLab",teacherIds.size()));
					List<Integer> taIds = termService.getListOfTaIdsByTermId(terms.getId());
					pastitem.add(new Label("pasttasLab",taIds.size()));
					List<Integer> classIdList = new ArrayList<Integer>();
					for (int i = 0; i < classlistlen; i++)
					{
						classIdList.add(classes.get(i).getId());
					}
					List<EntityAssignmentModel> assigns = 
							assignmentService.getListOfAssignmentsByClassIdList(classIdList);
					int assignslen = 0;
					if (assigns != null)
					{
						assignslen = assigns.size();
					}
					pastitem.add(new Label("pastassignsLab",assignslen));
				}
				
			};
			
			add(pasttermList);
		}
	}
}
