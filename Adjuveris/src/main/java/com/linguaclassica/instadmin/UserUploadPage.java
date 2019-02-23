package com.linguaclassica.instadmin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.wicket.Session;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.util.lang.Bytes;
import org.apache.wicket.util.string.StringValue;
import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.markup.html.form.upload.FileUploadField;

import com.linguaclassica.access.AlphPlusSession;
import com.linguaclassica.entity.EntityTermModel;
import com.linguaclassica.model.ModelFactory;
import com.linguaclassica.model.PermissionModel.Permission;
import com.linguaclassica.service.ClassService;
import com.linguaclassica.service.TermService;
import com.linguaclassica.service.UserService;

public class UserUploadPage extends InstAdminBasePage
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
	    
	private Permission permission;

	public UserUploadPage(PageParameters parameters)
	{
		super(parameters);

		StringValue sv = parameters.get("usertype");
		if (sv != null)
		{
			String usertype = sv.toString();
			if (usertype !=null)
			{
				permission = Permission.valueOf(usertype);
			}
		}
		
		Form<Object> form = new UserUploadForm();
		add(form);
	}

	class UserUploadForm extends Form<Object> 
	{
		private static final long serialVersionUID = 1L;

		private EntityTermModel destinationTerm;
	    private AlphPlusSession session = AlphPlusSession.get();

        private FileUploadField fileUpload;
        private FileUpload uploadedFile = null;

		public UserUploadForm()
		{
			super("useruploadform");
			
			Integer institutionId = AlphPlusSession.get().getCurrentInstitution();
			
			java.sql.Date now = new java.sql.Date(Calendar.getInstance().getTime().getTime());

			List<EntityTermModel> termsList = termService.getListOfNewAndExistingTerms(institutionId, now);
			
			// Create a drop-down control for the term to add students to
			destinationTerm = (EntityTermModel) modelFactory.getNewTermModel();
			if (termsList.size() > 0)
			{
				// Initialize to the newest item unless term retrieved from alph-session
				if (session.getUserPageTermId() == -1)
				{	
					destinationTerm = termsList.get(termsList.size()-1);
				}
				else
				{
					destinationTerm = termService.findTermById(session.getUserPageTermId());
					//destinationTerm = termService.findTermById(Integer.parseInt(session.getTeacherListTermId()));
				}
			}
			final DropDownChoice<EntityTermModel> termselect = new DropDownChoice<EntityTermModel>("selectterm",
					new PropertyModel<EntityTermModel>(this, "destinationTerm"), termsList, new ChoiceRenderer<EntityTermModel>("termname", "id"));
			termselect.setNullValid(false);
			termselect.setRequired(true);
			
			add(termselect);
			
			if (permission == Permission.STUDENT)
			{
				add (new Label("userlabel", "Student"));
			}
			else if (permission == Permission.TEACHER)
			{
				add (new Label("userlabel", "Teacher"));
			}
			else if (permission == Permission.TA)
			{
				add (new Label("userlabel", "TA"));
			}


            // Initiate an action to import teacher users from a file
            Button uploadfilebutton = new Button("uploadfile"){
                    private static final long serialVersionUID = 1L;

                    public void onSubmit()
                    {
                            System.out.println("UserUploadPage.onSubmit uploadfile");

                            if ((destinationTerm == null) || (destinationTerm.getId() <= 0))
                            {
                                    Session.get().error("New term must be selected to import users.");
                                    return;
                            }

                            // Input teachers from a file
                            uploadedFile = fileUpload.getFileUpload();
                            if (uploadedFile != null)
                            {
                                System.out.println("uploadedFile.getClientFileName() = " + uploadedFile.getClientFileName());
                                    try
                                    {
                                            InputStream is = uploadedFile.getInputStream();
                                            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                                            String line;
                                            List<String> strings = new ArrayList<String>();
                                            while ((line = reader.readLine()) != null)
                                            {
                                                    strings.add(line);
                                            }
                                            System.out.println("Read " + strings.size() + " lines");
                                            setResponsePage(new UserFileAdjustPage(strings, destinationTerm, permission));
                                    }
                                    catch (IOException e)
                                    {
                                            e.printStackTrace();
                                            error("An error occured when reading file: " + uploadedFile.getClientFileName());
                                    }
                            }
                            else
                            {
                                    error("No CSV file has been selected.");
                                    System.out.println("WARNING: No CSV file has been selected.");
                            }
                    }
             };
			
			add(uploadfilebutton);
			
            // Enable multipart mode (need for uploads file)
            setMultiPart(true);

            // max upload size, 10k
            setMaxSize(Bytes.kilobytes(10));

            add(fileUpload = new FileUploadField("fileUpload"));
			
 		}

		public void onSubmit()
		{
			System.out.println("UserUploadForm.onSubmit()    termname= " + destinationTerm.getTermname());
			            
			//session.setUserPageTermId(destinationTerm.getId());
			//setResponsePage(UserUploadPage.class);
		}
	}

}
