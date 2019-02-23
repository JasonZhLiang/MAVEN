package com.linguaclassica.consultant;

import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.request.resource.IResource;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.linguaclassica.entity.EntityResourceModel;
import com.linguaclassica.service.ResourceService;
import com.linguaclassica.shared.MessagePage;
import com.linguaclassica.shared.PrivateBasePage;

public class ViewResourcesPage extends PrivateBasePage {
	private static final long serialVersionUID = 1L;
	
	@SpringBean
	ResourceService resourceService;

	public ViewResourcesPage() {
		super();
		
		System.out.println("got to ViewResourcesPage");
		
		IResource imageResource = null;

		List<EntityResourceModel> resourceList = resourceService.getListOfResourceidsByOrgid(1);
		System.out.println("resourceList.size() = " + resourceList.size());
		ListView<EntityResourceModel> resourcesList = new ListView<EntityResourceModel>("resourcelist",resourceList){
			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(ListItem<EntityResourceModel> item) {
				EntityResourceModel resources = (EntityResourceModel) item.getModelObject();
				System.out.println("resources.getName() = " + resources.getName());
				
				item.add(new Label("resourcetitle",resources.getName()));
				item.add(new Link<Object>("viewlink"){
					private static final long serialVersionUID = 1L;
					@Override
					public void onClick() {
						String ext = resources.getExt();
						if(!ext.equals("application/pdf")){
							setResponsePage(new DisplayImagePage(resources.getId()));
						}
						else {
							setResponsePage(MessagePage.class);
						}
					}
				});
			};
		};
		add(resourcesList);
	}
}

