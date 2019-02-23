package com.linguaclassica.instadmin;

import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.linguaclassica.access.AlphPlusSession;
import com.linguaclassica.access.CommonUtilities;
import com.linguaclassica.access.LoginHelper;
import com.linguaclassica.access.SendEmail;
import com.linguaclassica.entity.EntityTermModel;
import com.linguaclassica.entity.EntityUserModel;
import com.linguaclassica.model.ModelFactory;
import com.linguaclassica.model.UserModel;
import com.linguaclassica.model.PermissionModel.Permission;
import com.linguaclassica.service.PasswordGenerateService;
import com.linguaclassica.service.TermService;
import com.linguaclassica.service.UserService;

public class UserFileAdjustPage extends InstAdminBasePage {
	private static final long serialVersionUID = 1L;
	
	@SpringBean
	ModelFactory modelFactory;
	
	@SpringBean
	UserService userService;	
	
	@SpringBean
	PasswordGenerateService passwordGenerateService;
	
	@SpringBean
	TermService termService;
	
	DropDownChoice<String> firstnameddc;
	DropDownChoice<String> lastnameddc;
	DropDownChoice<String> emailaddressddc;
	DropDownChoice<String> usercodeddc;
	
	String firstname;
	String lastname;
	String emailaddress;
	String usercode;	
	
	int recordlistlen;
	int termid;
	
	Permission permission;
	
	List<String> filelist;

	public UserFileAdjustPage(List<String> fileread, EntityTermModel termModel, Permission permission)
	{
		System.out.format("UserFileAdjustPage(%d strings, %d)%n", fileread.size(), termModel.getId());
		
		permitPreferenceSelection();
		
		Form<?> fileform = new FileForm("fileform");
		
		String termname = termModel.getTermname();
		termid = termModel.getId();
		this.permission = permission;
		
		fileform.add(new Label("termnamelab",termname));
		
		filelist = fileread;
		
		recordlistlen = filelist.size();
		
		fileform.add(new ListView<String>("lineslist",filelist) {
			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(ListItem<String> item) {
				String linetxt = item.getModelObject();
				String[] colarr = linetxt.split(",");
				List<String> columnlist = Arrays.asList(colarr); 
				int listlen = columnlist.size();
				if (listlen < 2) {
					info("File is not a comma-separated-value (csv) file or has only a single column.");
				}
				else {
					item.add(new Label("col1lab",columnlist.get(0)));
					item.add(new Label("col2lab",columnlist.get(1)));
					if (listlen > 2) {
						item.add(new Label ("col3lab",columnlist.get(2)));
					}
					else {
						item.add(new Label("col3lab","--"));
					}
					if (listlen > 3) {
						item.add(new Label ("col4lab",columnlist.get(3)));
					}
					else {
						item.add(new Label("col4lab","--"));
					}
					if (listlen > 4) {
						item.add(new Label ("col5lab",columnlist.get(4)));
					}
					else {
						item.add(new Label("col5lab","--"));
					}
					if (listlen > 5) {
						item.add(new Label ("col6lab",columnlist.get(5)));
					}
					else {
						item.add(new Label("col6lab","--"));
					}
					if (listlen > 6) {
						item.add(new Label ("col7lab",columnlist.get(6)));
					}
					else {
						item.add(new Label("col7lab","--"));
					}
					if (listlen > 7) {
						item.add(new Label ("col8lab",columnlist.get(7)));
					}
					else {
						item.add(new Label("col8lab","--"));
					}
					if (listlen > 8) {
						item.add(new Label ("col9lab",columnlist.get(8)));
					}
					else {
						item.add(new Label("col9lab","--"));
					}
					if (listlen > 9) {
						item.add(new Label ("col10lab",columnlist.get(9)));
					}
					else {
						item.add(new Label("col10lab","--"));
					}
					if (listlen > 10) {
						info("File contains more than ten columns");
					}
				}
			}
			
		});
		
		List<String> selintlist = Arrays.asList(new String[] {"1","2","3","4","5","6","7","8","9","10"});
		
		firstnameddc = new DropDownChoice<String>("firstnameddc", 
				new PropertyModel<String>(this,"firstname"),selintlist);
		firstnameddc.setRequired(true);
		lastnameddc = new DropDownChoice<String>("lastnameddc", 
				new PropertyModel<String>(this,"lastname"),selintlist);
		lastnameddc.setRequired(true);
		emailaddressddc = new DropDownChoice<String>("emailaddressddc", 
				new PropertyModel<String>(this,"emailaddress"),selintlist);
		emailaddressddc.setRequired(true);
		usercodeddc = new DropDownChoice<String>("usercodeddc", 
				new PropertyModel<String>(this,"usercode"),selintlist);
				
		fileform.add(firstnameddc);
		fileform.add(lastnameddc);
		fileform.add(emailaddressddc);
		fileform.add(usercodeddc);
		
		// Add the selection check validator
		SelectValidator sv = new SelectValidator(firstnameddc);
		sv.add(lastnameddc);
		sv.add(emailaddressddc);
		sv.add(usercodeddc);
		fileform.add(sv);
		
		if (permission == Permission.STUDENT)
		{
			fileform.add (new Label("userlabel", "Student"));
			fileform.add (new Label("codelabel", "Student"));
		}
		else if (permission == Permission.TEACHER)
		{
			fileform.add (new Label("userlabel", "Teacher"));
			fileform.add (new Label("codelabel", "Employee"));
		}
		else if (permission == Permission.TA)
		{
			fileform.add (new Label("userlabel", "TA"));
			fileform.add (new Label("codelabel", "Student/Employee"));
		}
		
		add(fileform);		
	}
	
	class FileForm extends Form<Object> {
		private static final long serialVersionUID = 1L;

		FileForm(String id) {
			super(id);
		}

		public void onSubmit() {
			System.out.println("UserFileAdjustPage.FileForm.onSubmit");
			
			Integer institutionId = AlphPlusSession.get().getCurrentInstitution();

			int firstnameidx = Integer.parseInt(firstnameddc.getModelObject()) - 1;
			int lastnameidx = Integer.parseInt(lastnameddc.getModelObject()) - 1;
			int emailaddressidx = Integer.parseInt(emailaddressddc.getModelObject()) - 1;
			int usercodeidx = -1;
			if (usercodeddc.getModelObject() != null)
			{
				usercodeidx = Integer.parseInt(usercodeddc.getModelObject()) - 1;
			}
			UserModel theUserModel = null;
			String passwd = "";
			int shortcount = 0;
			int newtrycount = 0;
			int newgoodcount = 0;
			int newbadcount = 0;
			int inusecount = 0;
			
			System.out.println("FileForm.onSubmit " + recordlistlen + " records");
			
			for (int j = 0; j < recordlistlen; j++)
			{
				// Check one input line
				String lineread = filelist.get(j);
				List<?> linelist = Arrays.asList(lineread.split(","));
				if ((linelist.size() >= 3) && (emailaddressidx < linelist.size()))
				{
					String useremailaddress = ((String) linelist.get(emailaddressidx)).trim();
					boolean bvalid = true;
					
					// Check whether the email address is in use.
					List<EntityUserModel> userEmailList = userService.checkDuplicates(useremailaddress);				
					if (userEmailList.size() < 1)
					{
						// no duplicate, add user record to database
						String userfirstname = ((String) linelist.get(firstnameidx)).trim();
						String userlastname = ((String) linelist.get(lastnameidx)).trim();
						String usernumber = "";
						if ((usercodeidx >= 0) && (usercodeidx < linelist.size()))
						{
							usernumber = ((String) linelist.get(usercodeidx)).trim();
						}
	
						// Determine whether the data is valid
						bvalid = CommonUtilities.checkValidUserNameInput(userfirstname);
						if (bvalid)
						{
							bvalid = CommonUtilities.checkValidUserNameInput(userlastname);
						}
						if (bvalid)
						{
							bvalid = CommonUtilities.checkEmailAddress(useremailaddress);
						}
						if (bvalid && (usercodeidx >= 0) && (usercodeidx < linelist.size()))
						{
							bvalid = CommonUtilities.checkAlphanumeric(usernumber, 1) && usernumber.length() <= 30;
						}
	
						if (bvalid)
						{
							// The data is valid, create the user.
							newtrycount++;
							theUserModel = modelFactory.getNewUserModel();
							theUserModel.setFirstName(userfirstname);
							theUserModel.setLastName(userlastname);
							theUserModel.setEmailAddress(useremailaddress);
							theUserModel.setStudentnumber(usernumber);
							passwd = passwordGenerateService.generatePassword();
							String encrypdpasswd = null;
							try
							{
								encrypdpasswd = LoginHelper.encodePassword(passwd);
								theUserModel.setPassword(encrypdpasswd);
							}
							catch(NoSuchAlgorithmException nsae)
							{
								bvalid = false;
								nsae.printStackTrace();					
							}
							catch(NullPointerException npe)
							{
								bvalid = false;
								npe.printStackTrace();
							}
						}
						if (bvalid)
						{
							System.out.format("Creating user %s with plain password %s%n", theUserModel.getEmailAddress(), passwd);
							if (permission == Permission.TEACHER)
							{
								userService.createUser(theUserModel, Permission.TEACHER, institutionId);
							}
							else if (permission == Permission.STUDENT)
							{
								userService.createUser(theUserModel, Permission.STUDENT, institutionId);
							}
							else if (permission == Permission.TA)
							{
								userService.createUser(theUserModel, Permission.TA, institutionId);
							}
								
							SendEmail sendEmail = new SendEmail();
//							sendEmail.sendNewUserNotice(theUserModel, passwd);
							newgoodcount++;
						}
						else
						{
							// The data is invalid
							newbadcount++;
						}
					}
					else
					{
						// user exists
						inusecount++;
						theUserModel = userService.findUserByEmailAddress(useremailaddress);
						userService.addAuthorization(institutionId, theUserModel, 
								userService.getPermissionModel(permission).getId());
					}
					if (bvalid)
					{
						int success = 0;
						// add the user to the term

						if (permission == Permission.TEACHER)
						{
							success = termService.addTeacherToList(theUserModel,termid);
						}
						else if (permission == Permission.STUDENT)
						{
							success = termService.addStudentToList(theUserModel,termid);
						}
						else if (permission == Permission.TA)
						{
							success = termService.addTaToList(theUserModel,termid);
						}

						System.out.println("FileForm.onSubmit added user-term " + success);
					}
				}
				else
				{
					shortcount++;
				}
			}
			System.out.format("FileForm.onSubmit %d of %d good, %d short, %d invalid, %d in use%n", 
					newgoodcount, newtrycount, shortcount, newbadcount, inusecount);
			
			setResponsePage(new UserEditPage(permission));
		}
	}
}
