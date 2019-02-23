/**
 * 
 */
package com.linguaclassica.instadmin;

import java.io.Serializable;
import java.util.Arrays;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.linguaclassica.access.AlphPlusSession;
import com.linguaclassica.access.HomePage;
import com.linguaclassica.access.MenuItemEnum;
import com.linguaclassica.access.NavBarPanel;
import com.linguaclassica.model.PermissionModel.Permission;
import com.linguaclassica.model.UserModel;

/**
 * @author Joe A. Brown 2017
 *
 */
public class InstAdminBasePage extends WebPage
{
	private static final long serialVersionUID = 1L;
	
	private static Logger logger = null;
	
	public interface TermSettingModel extends Serializable
	{
		void setSetting(Integer s);
		
		Integer getSetting();
		
		void setDescription(String desc);
		
		String getDescription();
	}
	
	public class TermSettingEntity implements TermSettingModel
	{
		private static final long serialVersionUID = 3924097675882539518L;
		
		private Integer setting;
		private String description;

		private TermSettingEntity(Integer initsetting, String initdesc)
		{
			setting = initsetting;
			description = initdesc;
		}
		
		public void setSetting(Integer s)
		{
			setting = s;
		}
		
		public Integer getSetting()
		{
			return setting;
		}
		
		public void setDescription(String desc)
		{
			description = desc;
		}
		
		public String getDescription()
		{
			return description;
		}
	}
	
	// Active user
	private final UserModel userModel = AlphPlusSession.get().getUser(getRequest());
	
	private boolean allowPreference = false;
	
	@SuppressWarnings("unused")
	private TermSettingEntity tsetting;
	
	public InstAdminBasePage()
	{
		super();
		
		buildCommon();
		buildMenu();
	}

	/**
	 * @param model
	 */
	public InstAdminBasePage(IModel<?> model)
	{
		super(model);

		buildCommon();
		buildMenu();
	}

	/**
	 * @param parameters
	 */
	public InstAdminBasePage(PageParameters parameters)
	{
		super(parameters);

		buildCommon();
		buildMenu();
	}
	
	// Allow the setting the be set by a super-class
	protected void permitPreferenceSelection()
	{
		allowPreference = true;
	}
	
	private void buildCommon()
	{
		FeedbackPanel feedbackpanel = new FeedbackPanel("feedbackmessages");
		add(feedbackpanel);
		
		Label userlabel = new Label("ident", new PropertyModel<String>(userModel, "firstName"));
		add(userlabel);
		
		// Number of prior terms to show
		tsetting = new TermSettingEntity(AlphPlusSession.get().getDateRange(), "dummy");
		TermSettingEntity[] toptions = new TermSettingEntity[] 
		{
				new TermSettingEntity(0, "Current Term"), 
				new TermSettingEntity(1, "One Prior Term"), 
				new TermSettingEntity(2, "Two Prior Terms"), 
				new TermSettingEntity(-1, "All Terms")
		};
		ChoiceRenderer<TermSettingModel> choiceRenderer = new ChoiceRenderer<TermSettingModel>("description", "setting");
		DropDownChoice<TermSettingModel> prefselect = new DropDownChoice<TermSettingModel>("termPref", 
				new PropertyModel<TermSettingModel>(this, "tsetting"), Arrays.asList(toptions), choiceRenderer)
		{
			private static final long serialVersionUID = 1L;
			
			public boolean isVisible()
			{
				return allowPreference;
			}
			
			protected boolean wantOnSelectionChangedNotifications()
			{
				return true;
			}
			
			protected void onSelectionChanged(final TermSettingModel newSelection)
			{
				System.out.format("onSelectionChanged(%s)%n", newSelection.getSetting().toString());
				AlphPlusSession.get().setDateRange(newSelection.getSetting());
				PageParameters pp = this.getWebPage().getPageParameters();
				if (!pp.isEmpty())
				{
					setResponsePage(getPage().getClass(), pp);
				}
				else
				{
					setResponsePage(getPage().getClass());
				}
			}
		};
		prefselect.setNullValid(false);
		prefselect.setRequired(true);
		add(prefselect);

		add(new Link<Object>("permswap")
		{
			private static final long serialVersionUID = 1L;
			
			public boolean isVisible()
			{
				return AlphPlusSession.get().areMultiplePermissions();
			}
			
			@Override
			public void onClick()
			{
				System.out.println("InstAdminBasePage.onClick");
				setResponsePage(IASwitchPermissionPage.class);
			}
		});
	}

	private void buildMenu()
	{
		String usertype = new String("usertype");
		PageParameters paramS = new PageParameters();
		paramS.add(usertype, Permission.STUDENT);
		PageParameters paramTA = new PageParameters();
		paramTA.add(usertype, Permission.TA);
		PageParameters paramTE = new PageParameters();
		paramTE.add(usertype, Permission.TEACHER);
		
		add(new NavBarPanel.Builder("navBar", HomePage.class)
			.withMenuItem(MenuItemEnum.OVERVIEW, InstAdminOverviewPage.class) 
			.withMenuItemAsDropdown(MenuItemEnum.TERMS, TermPage.class, "\u25BB New Term")   //TermsEditPage
			.withMenuItemAsDropdown(MenuItemEnum.TERMS, TermsListPage.class, "\u25BB View Terms")
			.withMenuItemAsDropdown(MenuItemEnum.CLASSES, ClassPage.class, "\u25BB New Class")
			.withMenuItemAsDropdown(MenuItemEnum.CLASSES, ClassesListPage.class, "\u25BB View Classes")
			.withMenuItemAsDropdown(MenuItemEnum.TEACHER, UserCreatePage.class, "\u25BB New Teacher", paramTE)
			.withMenuItemAsDropdown(MenuItemEnum.TEACHER, UserEditPage.class, "\u25BB View/Edit Teachers", paramTE)
			.withMenuItemAsDropdown(MenuItemEnum.TEACHER, UserUploadPage.class, "\u25BB Import Teachers", paramTE)
			.withMenuItemAsDropdown(MenuItemEnum.TEACHER, UserCopyPage.class, "\u25BB Copy prior term", paramTE)
			.withMenuItemAsDropdown(MenuItemEnum.TA,      UserCreatePage.class, "\u25BB New TA", paramTA)
			.withMenuItemAsDropdown(MenuItemEnum.TA,      UserEditPage.class, "\u25BB View/Edit TAs", paramTA)
			.withMenuItemAsDropdown(MenuItemEnum.TA,      UserUploadPage.class, "\u25BB Import TAs", paramTA)
			.withMenuItemAsDropdown(MenuItemEnum.TA,      UserCopyPage.class, "\u25BB Copy prior term", paramTA)
			.withMenuItemAsDropdown(MenuItemEnum.STUDENT, UserCreatePage.class, "\u25BB New Student", paramS)
			.withMenuItemAsDropdown(MenuItemEnum.STUDENT, UserEditPage.class, "\u25BB View/Edit Students", paramS)
			.withMenuItemAsDropdown(MenuItemEnum.STUDENT, UserUploadPage.class, "\u25BB Import Students", paramS)
			.withMenuItemAsDropdown(MenuItemEnum.STUDENT, UserCopyPage.class, "\u25BB Copy prior term", paramS)
			.withMenuItemAsDropdown(MenuItemEnum.REPORTS, TeachersSummaryResultsPage.class, "\u25BB Teachers Summary")
			.withMenuItemAsDropdown(MenuItemEnum.REPORTS, TeachersSummaryResultsPage.class, "\u25BB Teachers Summary")
			.withMenuItem(MenuItemEnum.INSTPREFS, InstitutionPreferences.class) 
			.build()
		);
	}

	// Log and display the message page when exception occur on subclassed pages
	public void processException(String pageName, Exception ex)
	{
		logger = LoggerFactory.getLogger(pageName);
		logger.error(ex.toString());
		PageParameters params = new PageParameters();
		params.add("message", ex.toString());
		setResponsePage(IAMenuErrorPage.class, params);
	}
	
	public /*abstract*/ MenuItemEnum getActiveMenu() {
		return null;
	}
}
