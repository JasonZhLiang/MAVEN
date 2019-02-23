package com.linguaclassica.shared;
import java.util.List;

import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.core.util.resource.PackageResourceStream;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.linguaclassica.access.LMSSession;
import com.linguaclassica.entity.EntityMessageModel;
import com.linguaclassica.entity.EntityOtherResourceModel;
import com.linguaclassica.service.MessageService;
import com.linguaclassica.service.OtherResourceService;

public class OtherResourcePage extends PrivateBasePage {
	private static final long serialVersionUID = 1L;
	
	@SpringBean
	OtherResourceService otherResourceService;

	public OtherResourcePage(final PageParameters parameters) {
		super(parameters);
		
		Integer currentOrg = LMSSession.get().getCurrentOrganization();
		
		List<EntityOtherResourceModel> otherResList = otherResourceService.findOtherResourceByOrgId(currentOrg);
		ListView<EntityOtherResourceModel> otherResView = new ListView<EntityOtherResourceModel>("otherResView", otherResList)
		{
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(ListItem<EntityOtherResourceModel> item) {
				item.add(new Label("resname", item.getModelObject().getResName()));
				
				item.add(new AjaxEventBehavior("onclick")
				{

					private static final long serialVersionUID = 1L;

					@Override
				    protected void onEvent(AjaxRequestTarget target)
				    {
						EntityOtherResourceModel resourceModelItem = otherResourceService.findOtherResourceById(item.getModelObject().getId());
						setResponsePage(new DisplayResourcePage(resourceModelItem, otherResList.size(), item.getModelObject().getResName()));
				    }
				});
						
			}
			
		};
		add(otherResView);
    }
}