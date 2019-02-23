package com.linguaclassica.instadmin;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.linguaclassica.access.AlphPlusSession;
import com.linguaclassica.entity.EntityTermModel;
import com.linguaclassica.model.TeacherAssignmentResultsModel;
import com.linguaclassica.model.UserModel;
import com.linguaclassica.service.ClassService;
import com.linguaclassica.service.ResultsService;
import com.linguaclassica.service.TermService;
import com.linguaclassica.service.UserService;

public class TeachersSummaryResultsPage extends InstAdminBasePage
{
	private static final long serialVersionUID = 1L;
	
	@SpringBean
	TermService termService;
	
	@SpringBean
	UserService userService;
	
	@SpringBean
	ClassService classService;
	
	@SpringBean
	private ResultsService resultsService;		
	
	private Integer institutionId;

	public TeachersSummaryResultsPage()
	{
		super();
		
		institutionId = AlphPlusSession.get().getCurrentInstitution();
		
		permitPreferenceSelection();

		// Create the form
		TeachersSummaryResultsForm form = new TeachersSummaryResultsForm(institutionId);
		add(form);
	}

	class TeachersSummaryResultsForm extends Form<Object>
	{
		private static final long serialVersionUID = 1L;
		
		public TeachersSummaryResultsForm(Integer institId)
		{
			super("teachersSummary");
			
			System.out.format("TeachersSummaryResultsForm(inst %d)%n", institId);
			
			java.sql.Date now = new java.sql.Date(Calendar.getInstance().getTime().getTime());
			
			List<EntityTermModel> currentTerms = termService.getListOfExistingTerms(institId, now);
			
			List<EntityTermModel> pastTerms = termService.getListOfPastTerms(institId, now,
					AlphPlusSession.get().getDateRange()); 
			
			addRows("currentList", "current", currentTerms);
			
			addRows("pastList", "past", pastTerms);
		}
		
		private void addRows(String rowid, String prefix, List<EntityTermModel> termsList)
		{
			List<Integer> teacheridlist =  termService.getListOfTeacherIdsByTermIds(termsList);
			//List<EntityUserModel> teacherUsers = userService.getListOfStudentsByIdList(teacheridlist);
			
			ListView<Integer> listView = new ListView<Integer>(rowid, teacheridlist)
			{
				private static final long serialVersionUID = 1L;
				
				@Override
				protected void populateItem(final ListItem<Integer> useritem)
				{
					String teachernameId = prefix + "teachernameLab";
					String vocabularyId = prefix + "vocabularyLab";
					String syntaxId = prefix + "syntaxLab";
					String inflectionId = prefix + "inflectionLab";
					String comprehensionId = prefix + "comprehensionLab";
					String averageId = prefix + "averageLab";
					
					Integer teacherId = useritem.getModelObject();
					TeacherAssignmentResultsModel teacherAssignmentResults = 
							resultsService.getTeacherAssignmentResultsByTeacherId(teacherId);
					UserModel teacherinst = userService.findUserById(teacherId);
					
					String teachername = teacherinst.getFirstName() + " " + teacherinst.getLastName();
					float vocabpct = 0;
					float syntaxpct = 0;
					float inflectpct = 0;
					float comprehenpct = 0;
					float average = 0;
					if (teacherAssignmentResults != null)
					{
						int vocabtotal = teacherAssignmentResults.getVocabtotal();
						int vocabright = teacherAssignmentResults.getVocabright();
						if (vocabtotal > 0)
						{
							float vocabtotalflt = (float) vocabtotal;
							float vocabrightflt = (float) vocabright;
							vocabpct = (float) (vocabrightflt/vocabtotalflt)*100;
						}
						int syntaxtotal = teacherAssignmentResults.getSyntaxtotal();
						int syntaxright = teacherAssignmentResults.getSyntaxright();
						if (syntaxtotal > 0)
						{
							float syntaxtotalflt = (float) syntaxtotal;
							float syntaxrightflt = (float) syntaxright;
							syntaxpct = (float) (syntaxrightflt/syntaxtotalflt)*100;
						}
						int inflecttotal = teacherAssignmentResults.getInflecttotal();
						int inflectright = teacherAssignmentResults.getInflectright();
						if (inflecttotal > 0)
						{
							float inflecttotalflt = (float) inflecttotal;
							float inflectrightflt = (float) inflectright;
							inflectpct = (float) (inflectrightflt/inflecttotalflt)*100;
						}
						int comprehentotal = teacherAssignmentResults.getComprehentotal();
						int comprehenright = teacherAssignmentResults.getComprehenright();
						if (comprehentotal > 0)
						{
							float comprehentotalflt = (float) comprehentotal;
							float comprehenrightflt = (float) comprehenright;
							comprehenpct = (float) (comprehenrightflt/comprehentotalflt)*100;
						}
						average = teacherAssignmentResults.getAverage();
					}
					
	        		DecimalFormat df = new DecimalFormat("##.##");
	        		String percentstr = df.format(average);
	        		String vocabpctstr = df.format(vocabpct);
	        		String syntaxpctstr = df.format(syntaxpct);
	        		String inflectpctstr = df.format(inflectpct);
	        		String comprehenpctstr = df.format(comprehenpct);
					
	        		useritem.add(new Label(teachernameId,teachername));
	        		useritem.add(new Label(vocabularyId, vocabpctstr + "%"));
	        		useritem.add(new Label(syntaxId, syntaxpctstr + "%"));
	        		useritem.add(new Label(inflectionId, inflectpctstr + "%"));
	        		useritem.add(new Label(comprehensionId, comprehenpctstr + "%"));
	        		useritem.add(new Label(averageId, percentstr + "%"));
				}
			};
			add(listView);
		}
	}
}
