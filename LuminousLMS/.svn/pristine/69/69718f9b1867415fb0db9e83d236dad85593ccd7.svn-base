package com.linguaclassica.consultant;

import java.sql.Blob;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.markup.html.form.upload.FileUploadField;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.util.lang.Bytes;

import com.linguaclassica.model.ModelFactory;
import com.linguaclassica.model.OrgsResourcesModel;
import com.linguaclassica.model.ResourceModel;
import com.linguaclassica.service.ResourceService;
import com.linguaclassica.shared.PrivateBasePage;

public class UploadResourcePage extends PrivateBasePage {
	private static final long serialVersionUID = 1L;
	
	@SpringBean
	ResourceService resourceService;
	
	@SpringBean
	ModelFactory modelFactory;
	
	private FileUploadField fileUpload;
	private FileUpload uploadedFile = null;
	
	private String resname;

	public UploadResourcePage() {
		super();

		final Form<?> form = new ResourceUploadForm("resourceUploadForm");
		// Initiate an action to import student users from a file
		
				TextField<String> resourcenametf = new TextField<String>("resourcename",
						new PropertyModel<String>(this,"resname"));
				form.add(resourcenametf);
		
				Button uploadfilebutton = new Button("uploadfile"){
					private static final long serialVersionUID = 1L;

					public void onSubmit()
					{
						System.out.println("UploadResourcePage.onSubmit uploadfile");
						
						uploadedFile = fileUpload.getFileUpload();
						
						ResourceModel resourceModel = modelFactory.getNewResourceModel();
						OrgsResourcesModel orgsResourcesModel = modelFactory.getNewOrgsResourcesModel();
						
						if (uploadedFile != null) {
							
							try
							{
								System.out.println("uploadedFile.getClientFileName() = " + uploadedFile.getClientFileName());
								System.out.println("uploadedFile.getSize() = " + uploadedFile.getSize());
								System.out.println("uploadedFile.getContentType() = " + uploadedFile.getContentType());
								
								byte[] filebytes = uploadedFile.getBytes();
								System.out.println("filebytes.length = " + filebytes.length);
								
								Blob blob = new javax.sql.rowset.serial.SerialBlob(filebytes);
								
								resourceModel.setName(resourcenametf.getModelObject());
								resourceModel.setResource(blob);
								resourceModel.setExt(uploadedFile.getContentType());
								
								resourceService.createResource(resourceModel);
								
								orgsResourcesModel.setOrgid(1);
								orgsResourcesModel.setResourceid(resourceModel.getId());
								resourceService.createOrgsResourcesRecord(orgsResourcesModel);

								info("saved record: " + uploadedFile.getClientFileName());
								
							} catch (Exception e) {
								throw new IllegalStateException("Error");

							}
							/*
							catch(SQLException se){
								se.printStackTrace();
							}
								
								/*
								//InputStream is = uploadedFile.getInputStream();
								 BufferedInputStream bis = (BufferedInputStream) uploadedFile.getInputStream();
								
						        resourceModel.setName(resname);	
						        //resourceModel.setResource();
								
								resourceService.createResource(resourceModel);
								
								setResponsePage(new AdminLandingPage());
							
							catch (IOException e)
							{
								e.printStackTrace();
								error("An error occured when reading file: " + uploadedFile.getClientFileName());
							}
							*/
						}
						
					}
				}; 
				
				form.add(uploadfilebutton);

				// Enable multipart mode (need for uploads file)
				form.setMultiPart(true);

				// max upload size, 10k
				form.setMaxSize(Bytes.kilobytes(10000));
		
		form.add(fileUpload = new FileUploadField("fileUpload"));
		add(form);
	}	
	
	class ResourceUploadForm extends Form<Object> 
	{
		private static final long serialVersionUID = 1L;

		public ResourceUploadForm(String id) {
			super(id);

		}
	}
}

