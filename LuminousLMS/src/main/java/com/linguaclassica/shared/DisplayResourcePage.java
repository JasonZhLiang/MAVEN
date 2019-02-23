package com.linguaclassica.shared;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Date;
import java.util.UUID;

import javax.servlet.Servlet;
import javax.servlet.ServletContext;

import org.apache.wicket.Application;
import org.apache.wicket.core.request.handler.IPageProvider;
import org.apache.wicket.core.util.resource.PackageResourceStream;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.link.InlineFrame;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.http.WebResponse;
import org.apache.wicket.request.resource.ByteArrayResource;
import org.apache.wicket.request.resource.ContextRelativeResource;
import org.apache.wicket.request.resource.IResource;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.linguaclassica.access.LMSSession;
import com.linguaclassica.entity.EntityMessageModel;
import com.linguaclassica.entity.EntityOtherResourceModel;
import com.linguaclassica.entity.EntityUserModel;
import com.linguaclassica.service.OtherResourceService;
import com.linguaclassica.shared.MessagingPage.MessagingForm;
//import org.eclipse.core.resources.*;

public class DisplayResourcePage extends PrivateBasePage {
	
	
	@SpringBean
	OtherResourceService otherResourceService;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public DisplayResourcePage(EntityOtherResourceModel resourceModelItem, int resListSize, String resourceName) {
		super();
		//System.out.println("resourceModel.getResName(): " +resourceModel.getResName());
		
		DisplayResourceForm form = new DisplayResourceForm(resourceModelItem, resListSize, resourceName);
		
        add(form);	
	}
	
	
	class DisplayResourceForm extends Form<Object>
	{
		private static final long serialVersionUID = 1L;
		File pdfFile = null;
		//File pngFile = null;
		File otherFile = null;

		public DisplayResourceForm(EntityOtherResourceModel resourceModelItem, int resListSize, String resourceName)
		{
			super("displayresf");
			
			String resNameExtension = resourceName.split("[.]")[1];
			
			System.out.println("resNameExtension: " + resNameExtension);
			
			Blob blobItem = resourceModelItem.getResBlob();
			byte[] returnBytes = null;
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			FileOutputStream fos = null;
			InputStream inputStream = null;
			
			try {
				inputStream = blobItem.getBinaryStream();
				int inByte;
				while ((inByte = inputStream.read()) != -1) {
					byteArrayOutputStream.write(inByte);
				}
			
				returnBytes = byteArrayOutputStream.toByteArray();
				
				ServletContext context = (ServletContext) WebApplication.get().getServletContext();
				System.out.println("context.getServerInfo(): " + context.getServerInfo());
				//String realPath = context.getRealPath("/src/main/webapp/web");
				//String realPath = context.getRealPath("./src/main/webapp/web");
				String realPath = context.getRealPath(".");
				System.out.println("context.getContextPath(): " + context.getContextPath());
				System.out.println("realPath: " + realPath);
						
				
				
				for(int i = 0; i < resListSize; i++)
				{
					if(resourceModelItem.getId() == (i+1))
					{
						
						if(resNameExtension.equals("pdf"))
						{
							//pdfFile = new File("C:/Users/Robin/workspace/LuminousLMS/src/main/webapp/web/fileFromDB"+resourceModelItem.getId()+".pdf");
							pdfFile = new File(realPath + "/web/fileFromDB"+resourceModelItem.getId()+".pdf");
							//pdfFile = new File(realPath + "/web/fileFromDB"+resourceModelItem.getId());
							fos = new FileOutputStream(pdfFile);
							fos.write(returnBytes);
						}
						//else if(resNameExtension.equals("png"))
						else
						{
							otherFile = new File(realPath + "/web/fileFromDB"+resourceModelItem.getId());
							fos = new FileOutputStream(otherFile);
							fos.write(returnBytes);
						}
					
					}
				}
			
				
				
				/*if(resourceModelItem.getId() == 1)
				{
					pdfFile = new File("C:/Users/Robin/workspace/LuminousLMS/src/main/webapp/web/fileFromDB"+resourceModelItem.getId()+".pdf");
					fos = new FileOutputStream(pdfFile);
					fos.write(returnBytes);
				}
				else if(resourceModelItem.getId() == 2)
				{
					pdfFile = new File("C:/Users/Robin/workspace/LuminousLMS/src/main/webapp/web/fileFromDB"+resourceModelItem.getId()+".pdf");
					fos = new FileOutputStream(pdfFile);
					fos.write(returnBytes);
				}
				else if(resourceModelItem.getId() == 3)
				{
					pdfFile = new File("C:/Users/Robin/workspace/LuminousLMS/src/main/webapp/web/fileFromDB"+resourceModelItem.getId()+".pdf");
					fos = new FileOutputStream(pdfFile);
					fos.write(returnBytes);
				}*/
						
				inputStream.close();
				fos.close();
				byteArrayOutputStream.close();
				blobItem.free();
					
			
			} catch (IOException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			InlineFrame ifrm = new InlineFrame("pdfframe",DisplayResourcePage.class) {
				private static final long serialVersionUID = 1L;

				@Override
				protected void onComponentTag(ComponentTag tag) {
					super.onComponentTag(tag);
					String src = (String) tag.getAttributes().get("src");
					if(resNameExtension.equals("pdf"))
					{
						src = "../web/viewer.html?file=" + pdfFile.getName();
					}
					//else if(resNameExtension.equals("png"))
					else
					{
						src = "../web/" + otherFile.getName();
					}
					tag.getAttributes().put("src", src);
					}
				};
			add(ifrm);
		}
	}
	
	@Override
	protected void setHeaders(WebResponse response) {
		
		Date now = new Date();
		response.setHeader("Pragma", "no-cache"); 
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		response.setHeader("Expires", "0");
		response.setHeader("Cache-Control", "post-check=0, pre-check=0");
	}

}
