package com.linguaclassica.instadmin;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.validation.validator.StringValidator;
import org.springframework.dao.DataIntegrityViolationException;

import com.linguaclassica.access.AlphPlusSession;
import com.linguaclassica.entity.EntityClassModel;
import com.linguaclassica.entity.EntityTermModel;
import com.linguaclassica.model.ModelFactory;
import com.linguaclassica.service.ClassService;
import com.linguaclassica.service.TermService;
import com.linguaclassica.service.UserService;
import com.linguaclassica.service.exception.EntityAlreadyExistsException;

public class ClassPage extends InstAdminBasePage
{
	private static final long serialVersionUID = 1L;
	
	@SpringBean
	ModelFactory modelFactory;
	
	@SpringBean
	TermService termService;

	@SpringBean
	private ClassService classService;
	
	@SpringBean
	UserService userService;
	
	public ClassPage()
	{
		super();
		
		EntityClassModel newclass = (EntityClassModel) modelFactory.getNewClassModel();
		newclass.setTermid(AlphPlusSession.get().getCurrentTerm());
		
		add(new Label("classaction", "Create Class"));
		
		Form<Object> form = new CreateEditClassForm(newclass);
		add(form);
	}

	public ClassPage(EntityClassModel existingclass)
	{
		super();
		
		add(new Label("classaction", "Edit Class"));
		
		Form<Object> form = new CreateEditClassForm(existingclass);
		add(form);
	}

	class CreateEditClassForm extends Form<Object> 
	{
		private static final long serialVersionUID = 1L;

		TextField<String> classnameIn;
		TextField<String> classcodeIn;
		
		EntityClassModel classModel;
		EntityTermModel destinationTerm;
		List<EntityTermModel> termsList;
		
		public CreateEditClassForm(EntityClassModel oneClass)
		{
			super("creditclassform");
			
			classModel = oneClass;
			
			// Add controls to the form
			classnameIn = new TextField<String>("classnametf", new PropertyModel<String>(classModel, "classname"));
			classcodeIn = new TextField<String>("classcodetf", new PropertyModel<String>(classModel, "classcode"));
			classnameIn.setRequired(true);
			classnameIn.add(StringValidator.maximumLength(20));
			classcodeIn.add(StringValidator.maximumLength(20));

			Integer institutionId = AlphPlusSession.get().getCurrentInstitution();
			
			if ((classModel.getId() != null) && (classModel.getId() > 0))
			{
				// Existing class, use existing term only
				termsList = new ArrayList<EntityTermModel>();
				EntityTermModel currentTerm = termService.findTermById(classModel.getTermid());
				termsList.add(currentTerm);
			}
			else
			{
				// New class, allow all current and future terms
				java.sql.Date now = new java.sql.Date(Calendar.getInstance().getTime().getTime());
				termsList = termService.getListOfNewAndExistingTerms(institutionId, now);
			}
			
			// Create a drop-down control for the term to add students to
			destinationTerm = (EntityTermModel) modelFactory.getNewTermModel();
			destinationTerm.setId(classModel.getTermid());
			final DropDownChoice<EntityTermModel> termselect = new DropDownChoice<EntityTermModel>("selectterm",
					new PropertyModel<EntityTermModel>(this, "destinationTerm"), termsList, new ChoiceRenderer<EntityTermModel>("termname", "id"))
			{
				private static final long serialVersionUID = 1L;
				
				public boolean isEnabled()
				{
					return termsList.size() > 1;
				}
			};
			termselect.setNullValid(false);
			termselect.setRequired(true);
			
			add(classnameIn);
			add(classcodeIn);
			add(termselect);
		}

		public void onSubmit()
		{
			System.out.println("CreateEditClassForm.onSubmit");

			int classId = 0;
			int termId = destinationTerm.getId();
			if (classModel.getId() != null)
			{
				classId = classModel.getId();
			}
			try
			{
				if (classId > 0)
				{
					// Existing class
					classService.updateClass(classModel);
				}
				else
				{
					// New class
					// Copy relevant data from term to class and create the class
					classModel.setTermid(destinationTerm.getId());
					classId = classService.createClass(classModel);
				}
				
				// Update the current term and class for use by other pages
				AlphPlusSession.get().setCurrentTerm(termId);
				AlphPlusSession.get().setCurrentClass(classId);
				setResponsePage(ClassesListPage.class);
			}
			catch (EntityAlreadyExistsException eaee)
			{
                error("Error: create class failed.  Class name '" + classModel.getClassname() + "' already exists in term '" + destinationTerm.getTermname() + "'.  Please ensure class name is unique within the given term.");
                System.out.println("CreateEditClassForm.onSubmit failed with exception. " + eaee.toString());
			}
			catch (DataIntegrityViolationException dive)
			{
				error("Error: edit class failed.  Class name '" + classModel.getClassname() + "' already exists in term '" + destinationTerm.getTermname() + "'.  Please ensure class name is unique within the given term.");
				System.out.println("CreateEditClassForm.onSubmit failed with exception. " + dive.toString());
			}
			catch (Exception e)
			{
				error("Error: edit class failed. Please ensure class name is unique within the given term.");
				System.out.println("CreateEditClassForm.onSubmit failed with exception. " + e.toString());
			}
		}
	}
}
