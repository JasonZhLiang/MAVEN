package com.linguaclassica.client;

import java.sql.Timestamp;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.HiddenField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

//import com.linguaclassica.access.LMSSession;
import com.linguaclassica.entity.EntityAssignmentSequencesModel;
import com.linguaclassica.entity.EntityClientSequenceProgressModel;
import com.linguaclassica.entity.EntityClientSequenceUsageModel;
import com.linguaclassica.entity.EntityContentItemsModel;
import com.linguaclassica.entity.EntityModelFactory;
import com.linguaclassica.entity.EntityVideoModel;
import com.linguaclassica.model.AssignmentSequencesModel;
//import com.linguaclassica.model.UserModel;
import com.linguaclassica.service.AssignmentSequencesService;
import com.linguaclassica.service.ClientSequenceProgressService;
import com.linguaclassica.service.ClientSequenceUsageService;
import com.linguaclassica.service.ContentItemsService;
import com.linguaclassica.service.VideoService;
import com.linguaclassica.shared.PrivateBasePage;
import com.linguaclassica.shared.TranscriptPage;

public class ClientVideoPage extends PrivateBasePage
{
	private static final long serialVersionUID = 1L;
	
	@SpringBean
	ClientSequenceProgressService clientSequenceProgressService;
	
	@SpringBean
	ClientSequenceUsageService clientSequenceUsageService;
	
	@SpringBean
	AssignmentSequencesService assignmentSequencesService;
	
	@SpringBean
	ContentItemsService contentItemsService;
	
	@SpringBean
	VideoService videoService;
	
	// Active user
	//private final UserModel userModel = LMSSession.get().getUser(getRequest());
	private final AssignmentSequencesModel videoSequence;
	private final Integer clientAssignmentId;
	private final Date sessionStartTime = new Date();
	private EntityClientSequenceUsageModel clientSequenceUsage;
	private Logger logger = LogManager.getLogger(ClientVideoPage.class);
	
	public ClientVideoPage()
	{
		super();
		
		// This default constructor may only be called by pasting a URL for an invalid user.
		// The security systems should trap it and deflect to the session expired page
		// instead of the white Wicket exception screen.
		this.videoSequence = null;
		this.clientAssignmentId = 0;
	}

	public ClientVideoPage(EntityAssignmentSequencesModel videoSequence, Integer clientAssignmentId)
	{
		super();	
		
		this.videoSequence = videoSequence;
		
		this.clientAssignmentId = clientAssignmentId;
		
		EntityContentItemsModel contentItem = contentItemsService.getOne(videoSequence.getContentId());
		
		EntityVideoModel video = videoService.getFromContentId(contentItem.getId());
		
		logger.info("(" + video.getVideoid() + ")");

		String transcript = video.getTranscript();
        add(new Link<Object>("tscrbutton") {
            private static final long serialVersionUID = 1L;

            @Override
            public boolean isVisible()
            {
            	return transcript != null;
            }
            
            @Override
            public void onClick() {
                    System.out.println("Transcript button clicked.");
                    setResponsePage(new TranscriptPage(contentItem.getName(), transcript));
            }
        });
		
		String videocode = "<script src='https://fast.wistia.com/embed/medias/"+ video.getVideoid() +".jsonp' async>" +
		"</script>" +
		"<script src='https://fast.wistia.com/assets/external/E-v1.js' async></script>" +
		//"<div class='wistia_responsive_padding' style='padding:56.25% 0 0 0;position:relative;'>" +
		"<div class='wistia_responsive_padding' style='padding:0;position:relative;'>" +
		//"<div class='wistia_responsive_wrapper' style='height:100%;left:0;position:absolute;top:0;width:100%;'>" +
		"<div class='wistia_responsive_wrapper' style='height:100%;width:100%;'>" +
		"<div class='wistia_embed wistia_async_"+ video.getVideoid() +" videoFoam=true' style='height:100%;width:100%'>&nbsp;" +
		"</div></div></div>";
		
		add(new Label("videoview",videocode).setEscapeModelStrings(false));
		
		ClientVideoForm videoCompletionForm = new ClientVideoForm();
		add(videoCompletionForm);
	}
	
	class ClientVideoForm extends Form<Object>
	{
		private static final long serialVersionUID = 1L;
		
		private String videoCompleted;

		public ClientVideoForm() {
			super("ClientVideoForm");
			
			HiddenField<String> videoCompletedField = new HiddenField<String>("videocompleted", new PropertyModel<String>(this, "videoCompleted"));
			videoCompletedField.setModelObject("false");
			add(videoCompletedField);
			
			setEnabled(true);
		}
		
		@Override
		public void onSubmit() {
			logger.debug("onSubmit");
			
			if(videoCompleted != null && !videoCompleted.isEmpty()) {
				if(videoCompleted.equals("true")) {
					logger.info("user finished watching video");
					logger.info(videoSequence.getId());
					EntityClientSequenceProgressModel segmentProgress = clientSequenceProgressService.getClientSequenceProgressForClientAssignmentSequence(videoSequence.getId(), clientAssignmentId);
					
					if(segmentProgress != null) {
						segmentProgress.setCompleted(true);
						clientSequenceProgressService.updateClientSequenceProgress(segmentProgress);
					} else
					{
						segmentProgress = (EntityClientSequenceProgressModel) clientSequenceProgressService.addClientSequenceProgressForClientAssignmentSequence(videoSequence.getId(), clientAssignmentId, true, 0);
					}

					EntityModelFactory entityFactory = new EntityModelFactory();
					
					clientSequenceUsage = entityFactory.getNewClientSequenceUsageModel();
					
					Timestamp timestamp = new Timestamp(sessionStartTime.getTime());
					Date endTime = new Date();
					Timestamp endTimestamp = new Timestamp(endTime.getTime());
					int elapsedTimeInSeconds = (int) ((endTimestamp.getTime() - timestamp.getTime()) / 1000);
					
					clientSequenceUsage.setClientSequenceUsageId(segmentProgress.getId());
					clientSequenceUsage.setStartTime(timestamp);
					clientSequenceUsage.setStopTime(endTimestamp);
					clientSequenceUsage.setDuration(elapsedTimeInSeconds);
					
					clientSequenceUsageService.addClientSequenceUsageForSequenceProgress(clientSequenceUsage);
					
					setResponsePage(new AssignmentsPage());
				}
				else if(videoCompleted.equals("false"))
				{
					logger.info("user did not finish watching video");
				}
			}
		}
	}
}
