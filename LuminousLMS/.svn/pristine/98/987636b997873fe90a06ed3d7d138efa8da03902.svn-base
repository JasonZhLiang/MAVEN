package com.linguaclassica.consultant;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import javax.imageio.ImageIO;

import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.request.resource.DynamicImageResource;
import org.apache.wicket.request.resource.IResource;
import org.apache.wicket.spring.injection.annot.SpringBean;
import com.linguaclassica.model.ModelFactory;
import com.linguaclassica.model.ResourceModel;
import com.linguaclassica.service.ResourceService;
import com.linguaclassica.shared.PrivateBasePage;

public class DisplayImagePage extends PrivateBasePage {
	private static final long serialVersionUID = 1L;
	
	@SpringBean
	ResourceService resourceService;
	
	@SpringBean
	ModelFactory modelFactory;
	
	byte[] blobAsBytes;
	
	IResource imageResource;

	public DisplayImagePage() {
		super();
	}
	
	public DisplayImagePage(int id) {		

		ResourceModel resourceModel = resourceService.findResourceById(id);
		Blob blob = resourceModel.getResource();
		try {
			int blobLength = (int) blob.length();  
			blobAsBytes = blob.getBytes(1, blobLength);
			Boolean isImage = ImageIO.read(new ByteArrayInputStream(blobAsBytes)) != null;
			if(isImage){
				imageResource = new DynamicImageResource() {
					private static final long serialVersionUID = 1L;
					
	                @Override
					protected byte[] getImageData(IResource.Attributes attributes) {
	                    return blobAsBytes;
	                }
	            };
			}
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(IOException ie) {
			ie.printStackTrace();
		}
		
		Image image = new Image("imageview", imageResource);
		add(image);
	}
}

