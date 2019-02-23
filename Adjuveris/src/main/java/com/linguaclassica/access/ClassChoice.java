package com.linguaclassica.access;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormChoiceComponentUpdatingBehavior;
import org.apache.wicket.markup.html.form.CheckBoxMultipleChoice;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.linguaclassica.entity.EntityClassModel;
import com.linguaclassica.model.ModelFactory;
import com.linguaclassica.service.ClassService;
import com.linguaclassica.service.PermissionService;
import com.linguaclassica.service.UserService;

@SuppressWarnings("rawtypes")
public class ClassChoice<T> extends CheckBoxMultipleChoice
{
	private static final long serialVersionUID = 1L;

	@SpringBean
	ModelFactory modelFactory;

	@SpringBean
	private ClassService classService;

	@SpringBean
	private UserService userService;

	@SpringBean
	private PermissionService permissionService;
	
	public final static int DISPLAY = 0;
	public final static int CREATE = 1;
	public final static int EDIT = 3;

	private int mode = DISPLAY;
	private int userId;
	private String formid;
	private int currentTermId;
	
	private List<EntityClassModel> selectedClasses;
	private EntityClassModel ecmAll;
	private List<EntityClassModel> availableClasses;

	public ClassChoice(String formIdIn, int userIdIn, int modeIn, int currentTermIdIn)
	{
		super("classcheckboxes");

		formid = formIdIn;
		userId = userIdIn;
		mode = modeIn;
		currentTermId = currentTermIdIn;
		setSuffix(" "); // sets checkbox separator and ensures inline display

		ecmAll = (EntityClassModel) modelFactory.getNewClassModel();
		ecmAll.setClassname("All");

		// Configure the choices: all classes associated with the user
		Integer institutionId = AlphPlusSession.get().getCurrentInstitution();
		Date now = new Date(Calendar.getInstance().getTime().getTime());

		//Old Mine
		
		System.out.println("currentTermId in ClassChoice: " + currentTermId);
		Integer permissionId = AlphPlusSession.get().getCurrentPermission();
		String permit = permissionService.getModel(permissionId).getPermissionString();
		if(currentTermId == 0)
		{	
			availableClasses = classService.getCurrentClassesForUser(institutionId, userId, permit, now);
		}
		else
		{
			availableClasses = classService.getClassesForTerm(currentTermId, userId,permit, now);
		}
		
		//Old Mine
		
		
		
		setClassChoices();

		// Do not put this add AjaxFormChoiceComponentUpdatingBehavior call into Initialize function - it will create a long update loop
		add( new AjaxFormChoiceComponentUpdatingBehavior()
		{
			private static final long serialVersionUID = 1L;
			
			@Override
			protected void onUpdate(AjaxRequestTarget target)
			{
				System.out.println("ClassChoice.onUpdate");

				// Process a checkbox state change and make appropriate adjustments
				boolean balter = false;
				List<Integer> previousIDs = AlphPlusSession.get().getSelectedClassIDs();
				if ((previousIDs.size() > 0) && ((previousIDs.size() + 1) >= availableClasses.size()))
				{
					// Was previously Select All
					if (selectedClasses.get(selectedClasses.size() - 1) == ecmAll)
					{
						// Select All still selected, remove it
						selectedClasses.remove(selectedClasses.size() - 1);
					}
					else
					{
						// Remove all selections
						selectedClasses.clear();
					}
					balter = true;
				}
				else if (selectedClasses.size() > 0)
				{
					// Was none or some selected
					if (selectedClasses.get(selectedClasses.size() - 1) == ecmAll)
					{
						// Select All, select all available
						selectedClasses.clear();
						selectedClasses.addAll(availableClasses);
						balter = true;
					}
					else if ((selectedClasses.size() + 1) >= availableClasses.size())
					{
						// Is now full, add Select All
						selectedClasses.add(ecmAll);
						balter = true;
					}
					// else change but no special handling required
				}
				// else none are currently selected
				
				AlphPlusSession.get().setSelectedClasses(selectedClasses);
				
				// Generate a list of selected class IDs, excluding All
				// Save in in Session object to persist over multiple pages
				List<Integer> selectedIDs = new ArrayList<Integer>();
				int copysize = selectedClasses.size();
				if ((copysize > 0) && (selectedClasses.get(copysize - 1) == ecmAll))
				{
					copysize--;
				}
				for (int index = 0; index < copysize; index++)
				{
					selectedIDs.add(selectedClasses.get(index).getId());
				}
				AlphPlusSession.get().setSelectedClassIDs(selectedIDs);
				
				if(mode == DISPLAY)
				{
					// Refresh the page
					setResponsePage(target.getPage().getClass());
				}
				else if (balter)
				{
					// Update the checkboxes
					target.add(target.getPage().get(formid).get("classcheckboxes"));
				}
			}					
		});

		Initialize();
	}

	@SuppressWarnings("unchecked")
	protected void Initialize()
	{	
		// Grabs already selected classes from AlphPlusSession
		List<Integer> selectedIDs = AlphPlusSession.get().getSelectedClassIDs();
		selectedClasses = classService.getClassesByClassIDs(selectedIDs);
		if (selectedIDs.size() > 1)
		{
			if ((selectedIDs.size() + 1) >= availableClasses.size())
			{
				selectedClasses.add(ecmAll);
			}
		}
		setModel(Model.ofList(selectedClasses));
		
		// Configure the data and display
		setChoiceRenderer(new ChoiceRenderer<EntityClassModel>("classname", "id"));
		setOutputMarkupId(true);
	}
	
	@SuppressWarnings("unchecked")
	public void setClassChoices()
	{
		// Adds 'All' option when there is more than one class
		if (availableClasses.size() > 1)
		{
			availableClasses.add(ecmAll);
		}
		setChoices(availableClasses);
	}

	public List<EntityClassModel> getSelection()
	{
		return selectedClasses;
	}
}

