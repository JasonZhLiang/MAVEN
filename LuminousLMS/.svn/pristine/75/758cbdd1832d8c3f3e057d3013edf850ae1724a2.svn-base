 package com.linguaclassica.consultant;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.wicket.Session;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.linguaclassica.entity.EntityContentItemsModel;
import com.linguaclassica.service.ContentItemsService;
import com.linguaclassica.entity.EntityVideoModel;
import com.linguaclassica.service.VideoService;
import com.linguaclassica.shared.PrivateBasePage;

public class ViewVideosPage extends PrivateBasePage {
	private static final long serialVersionUID = 1L;
	
	@SpringBean
	ContentItemsService contentItemsService;
	
	@SpringBean
	VideoService videoService;
	
	private Logger logger = LogManager.getLogger(ViewVideosPage.class);

	public ViewVideosPage() {
		super();
		

		List<EntityVideoModel> videoList = videoService.getAllVideosTEMP();
		ListView<EntityVideoModel> videosList = new ListView<EntityVideoModel>("videolist",videoList){
			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(ListItem<EntityVideoModel> item) {
				EntityVideoModel videos = (EntityVideoModel) item.getModelObject();
				EntityContentItemsModel content = contentItemsService.getOne(videos.getContentId());
				System.out.println("videos.getVideoid() = " + videos.getVideoid());
				item.add(new Label("videotitle", content.getName()));
				item.add(new Link<Object>("viewlink"){
					private static final long serialVersionUID = 1L;
					@Override
					public void onClick() {
						if(!videos.getVideoid().equals("videoid")){
							setResponsePage(new ConsultantVideoPage(content.getName(),
									videos.getVideoid(), videos.getTranscript()));
						}
						else {
							// TODO Handle this error
							Session.get().info("no id for this video");
						}
					}			
				});
				item.add(new Link<Object>("transcriptlink"){
					private static final long serialVersionUID = 1L;
					@Override
					public void onClick() {
						// TODO Handle this action
						logger.warn("ViewVideosPage onClick transcript not yet available");
							Session.get().info("transcript not yet available");
					}			
				});
				
			}
		};
		add(videosList);
	}
}

