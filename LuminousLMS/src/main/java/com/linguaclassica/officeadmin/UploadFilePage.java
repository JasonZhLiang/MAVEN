package com.linguaclassica.officeadmin;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;

import javax.servlet.ServletContext;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.markup.html.form.upload.FileUploadField;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.linguaclassica.access.LMSSession;
import com.linguaclassica.entity.EntityMessageModel;
import com.linguaclassica.entity.EntityOtherResourceModel;
import com.linguaclassica.entity.EntityUserModel;
import com.linguaclassica.model.ModelFactory;
import com.linguaclassica.service.OtherResourceService;
import com.linguaclassica.shared.PrivateBasePage;

public class UploadFilePage extends PrivateBasePage {
	
	EntityOtherResourceModel entityOtherResourceModel;
	
	@SpringBean
	OtherResourceService otherResourceService;
	@SpringBean
	ModelFactory modelFactory;
	
	public UploadFilePage() {
		super();
		UploadFileForm form = new UploadFileForm("Upload a File");

		add(form);

	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	class UploadFileForm extends Form<Object> {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private FileUploadField fileUpload;
		private FileUpload uploadedFile = null;

		public UploadFileForm(String message) {
			super("displayupload");
			add(new Label("uploadl", message));
			Button uploadfilebutton = new Button("uploadfile") {

				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				public void onSubmit() {

					uploadedFile = fileUpload.getFileUpload();
					if (uploadedFile != null)
                    {
						System.out.println("uploadedFile.getClientFileName() in UploadFilePage = " + uploadedFile.getClientFileName());
						ServletContext context = ((WebApplication) getApplication()).getServletContext(); 
						/*File file = new File(context.getRealPath(uploadedFile.getClientFileName())); */
						System.out.println("context.getRealPath(uploadedFile.getClientFileName(): " + context.getRealPath(uploadedFile.getClientFileName()));
					    try
					    {
					    	byte[] barr = uploadedFile.getBytes();
					    	double barlen = barr.length;
							System.out.println("barlen = " + barlen);
							if (barlen > 1000000) {
								info("This file contains " + barlen/1000000 + " Megabytes of data.  Uploaded pdfs may" +
										"not be larger than 1 Megabyte.");
							}
							else {
								Blob blob = new javax.sql.rowset.serial.SerialBlob(barr);
								
								entityOtherResourceModel = (EntityOtherResourceModel) modelFactory.getNewOtherResourceModel();
								entityOtherResourceModel.setResName(uploadedFile.getClientFileName());
								entityOtherResourceModel.setOrgId(LMSSession.get().getCurrentOrganization());
								entityOtherResourceModel.setResBlob(blob);
								
								otherResourceService.createOtherResource(entityOtherResourceModel);
							}
					    }
					    catch(SQLException sqle) {
					    	sqle.printStackTrace();
					    }
                    }
				};
			};
			
			add(uploadfilebutton);

			add(fileUpload = new FileUploadField("fileUpload"));
		}

	}
	/*private byte[] getByteArrayFromFile(final File handledDocument) throws IOException {
		final ByteArrayOutputStream baos = new ByteArrayOutputStream();
		final InputStream in = new FileInputStream(handledDocument);
		final byte[] buffer = new byte[500];
		int read = -1;
		while ((read = in.read(buffer)) > 0) {
		    baos.write(buffer, 0, read);
		}
		in.close();
		return baos.toByteArray();
		}*/
	}