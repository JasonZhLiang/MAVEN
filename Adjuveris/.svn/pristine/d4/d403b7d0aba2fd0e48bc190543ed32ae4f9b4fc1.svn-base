package com.linguaclassica.access;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.RuntimeConfigurationType;
import org.apache.wicket.Session;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;
import org.apache.wicket.request.mapper.parameter.UrlPathPageParametersEncoder;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;

import com.linguaclassica.entity.EntityClassModel;
import com.linguaclassica.instadmin.ClassPage;
import com.linguaclassica.instadmin.ClassesListPage;
import com.linguaclassica.instadmin.InstAdminOverviewPage;
import com.linguaclassica.instadmin.TeachersSummaryResultsPage;
import com.linguaclassica.instadmin.TermPage;
import com.linguaclassica.instadmin.TermsListPage;
import com.linguaclassica.instadmin.UserCopyPage;
import com.linguaclassica.instadmin.UserUploadPage;
import com.linguaclassica.student.ExercisesMetricsPage;
import com.linguaclassica.student.ExercisesResultsPage;
import com.linguaclassica.student.NotificationsPage;
import com.linguaclassica.student.PracticeExercisesPage;
import com.linguaclassica.teacher.CreateAssignmentPage;
import com.linguaclassica.teacher.TeacherAssignmentsPage;
import com.linguaclassica.teacher.TeacherExercisesPage;
import com.linguaclassica.teacher.TeacherNotificationPage;
import com.linguaclassica.teacher.TeacherNotificationsPage;
//import com.linguaclassica.teacher.TeacherPreferences;
import com.linguaclassica.teacher.TeacherResultsPage;
import com.linguaclassica.instadmin.UserCreatePage;
import com.linguaclassica.instadmin.UserEditPage;
import com.linguaclassica.instadmin.UserFileAdjustPage;


/**
 * Application object for your web application.
 * If you want to run this application without deploying, run the Start class.
 * 
 * @see com.linguaclassica.Start#main(String[])
 */
public class WicketApplication extends WebApplication
{
	
    //private TeacherAssignmentsPage tAssignPage = new TeacherAssignmentsPage();
    public List<EntityClassModel> classes = new ArrayList<>();

	@Override
    public RuntimeConfigurationType getConfigurationType() {
		
        return RuntimeConfigurationType.DEPLOYMENT;
    }
	/**
	 * @see org.apache.wicket.Application#getHomePage()
	 */
	@Override
	public Class<? extends WebPage> getHomePage()
	{
		return HomePage.class;
	}
	
	@Override
	public Session newSession(Request request, Response response) {
		  return new AlphPlusSession(request);
	  }
	

	/**
	 * @see org.apache.wicket.Application#init()
	 */
	@Override
	public void init()
	{
		super.init();

		System.out.println("WicketApplication.init starts");

		// add your configuration here
		mount(new org.apache.wicket.core.request.mapper.MountedMapper("homepage", HomePage.class,
				new UrlPathPageParametersEncoder()));
		
		mount(new org.apache.wicket.core.request.mapper.MountedMapper("selectpermission", SelectPermissionPage.class,
				new UrlPathPageParametersEncoder()));
		
		// Institutional Administrator pages
		mount(new org.apache.wicket.core.request.mapper.MountedMapper("institutionadminoverviewpage", InstAdminOverviewPage.class,
				new UrlPathPageParametersEncoder()));
		
		mount(new org.apache.wicket.core.request.mapper.MountedMapper("termpage", TermPage.class,
				new UrlPathPageParametersEncoder()));
		
		mount(new org.apache.wicket.core.request.mapper.MountedMapper("termspage", TermsListPage.class,
				new UrlPathPageParametersEncoder()));
		
		mount(new org.apache.wicket.core.request.mapper.MountedMapper("classpage", ClassPage.class,
				new UrlPathPageParametersEncoder()));
		
		mount(new org.apache.wicket.core.request.mapper.MountedMapper("classespage", ClassesListPage.class,
				new UrlPathPageParametersEncoder()));

		mount(new org.apache.wicket.core.request.mapper.MountedMapper("usercreatepage", UserCreatePage.class,
				new UrlPathPageParametersEncoder()));

		mount(new org.apache.wicket.core.request.mapper.MountedMapper("usereditepage", UserEditPage.class,
				new UrlPathPageParametersEncoder()));

		mount(new org.apache.wicket.core.request.mapper.MountedMapper("useruploadpage", UserUploadPage.class,
				new UrlPathPageParametersEncoder()));

		mount(new org.apache.wicket.core.request.mapper.MountedMapper("usercopypage", UserCopyPage.class,
				new UrlPathPageParametersEncoder()));

		mount(new org.apache.wicket.core.request.mapper.MountedMapper("userfileadjustpage", UserFileAdjustPage.class,
				new UrlPathPageParametersEncoder()));

		mount(new org.apache.wicket.core.request.mapper.MountedMapper("teacherssummary", TeachersSummaryResultsPage.class,
				new UrlPathPageParametersEncoder()));

		// Student Pages
		mount(new org.apache.wicket.core.request.mapper.MountedMapper("studentnotifications", NotificationsPage.class,
				new UrlPathPageParametersEncoder()));

		mount(new org.apache.wicket.core.request.mapper.MountedMapper("studentexercises", PracticeExercisesPage.class,
				new UrlPathPageParametersEncoder()));

		mount(new org.apache.wicket.core.request.mapper.MountedMapper("studentresults", ExercisesResultsPage.class,
				new UrlPathPageParametersEncoder()));

		mount(new org.apache.wicket.core.request.mapper.MountedMapper("studentmetrics", ExercisesMetricsPage.class,
				new UrlPathPageParametersEncoder()));

		// Teacher Pages
		mount(new org.apache.wicket.core.request.mapper.MountedMapper("teachernotification", TeacherNotificationPage.class,
				new UrlPathPageParametersEncoder()));

		mount(new org.apache.wicket.core.request.mapper.MountedMapper("teachernotifications", TeacherNotificationsPage.class,
				new UrlPathPageParametersEncoder()));

		mount(new org.apache.wicket.core.request.mapper.MountedMapper("teacherexercises", TeacherExercisesPage.class,
				new UrlPathPageParametersEncoder()));

		mount(new org.apache.wicket.core.request.mapper.MountedMapper("teacherassignment", CreateAssignmentPage.class,
				new UrlPathPageParametersEncoder()));

		mount(new org.apache.wicket.core.request.mapper.MountedMapper("teacherassignments", TeacherAssignmentsPage.class,
				new UrlPathPageParametersEncoder()));

		mount(new org.apache.wicket.core.request.mapper.MountedMapper("teacherresults", TeacherResultsPage.class,
				new UrlPathPageParametersEncoder()));
/*
		mount(new org.apache.wicket.core.request.mapper.MountedMapper("teacherpreferences", TeacherPreferences.class,
				new UrlPathPageParametersEncoder()));
*/
		getComponentInstantiationListeners().add(new SpringComponentInjector(this));

		//Initialize Lists
		//classes = tAssignPage.getClasses();
		classes.add(new EntityClassModel(1, "Latin 101", null, /*Date startdate, Date enddate, */1));
		classes.add(new EntityClassModel(2, "Latin 102", null, /*Date startdate, Date enddate, */1));
		classes.add(new EntityClassModel(3, "Latin 103", null, /*Date startdate, Date enddate, */1));
		
		System.out.println("WicketApplication.init ends");
     }
	
	public EntityClassModel getEntityClassModel(int id){
		return findEntityClassModel(classes, id);
	}

	private static EntityClassModel findEntityClassModel(List<EntityClassModel> classes, int id){
		for (EntityClassModel c : classes) {
			if (c.getId() == id) {
				return c;
			}

			/*EntityClassModel temp = findEntityClassModel(c.getEntityClassModelList(), id);
			if(temp != null){
				return temp;
			}*/
		}
		
		return null;
	}


	public static WicketApplication getTreeApplication(){
		return (WicketApplication)WebApplication.get();
	}

}
