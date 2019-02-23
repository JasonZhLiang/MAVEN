package com.linguaclassica.consultant;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;

import com.linguaclassica.access.LMSSession;
import com.linguaclassica.model.UserModel;
import com.linguaclassica.shared.PrivateBasePage;
import com.linguaclassica.shared.TranscriptPage;

public class ConsultantVideoPage extends PrivateBasePage
{
	private static final long serialVersionUID = 1L;
	
	private Logger logger = LogManager.getLogger(ConsultantVideoPage.class);
	
	// Active user
	private final UserModel userModel = LMSSession.get().getUser(getRequest());
	
	public ConsultantVideoPage()
	{
		super();
		
		// This default constructor may only be called by pasting a URL for an invalid user.
		// The security systems should trap it and deflect to the session expired page
		// instead of the white Wicket exception screen.
		add(new Label("videoview", "should never see this page").setEscapeModelStrings(false));
	}

	public ConsultantVideoPage(String name, String videoid, String transcript)
	{
		super();	

		logger.info("videoid = " + videoid);
		
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
                    setResponsePage(new TranscriptPage(name, transcript));
            }
        });
        
		String videocode = "<script src='https://fast.wistia.com/embed/medias/"+videoid+".jsonp' async>" +
		"</script>" +
		"<script src='https://fast.wistia.com/assets/external/E-v1.js' async></script>" +
		"<div class='wistia_responsive_padding' style='padding:56.25% 0 0 0;position:relative;'>" +
		"<div class='wistia_responsive_wrapper' style='height:100%;left:0;position:absolute;top:0;width:100%;'>" +
		"<div class='wistia_embed wistia_async_"+videoid+" videoFoam=true' style='height:100%;width:100%'>&nbsp;" +
		"</div></div></div>";
		
		add(new Label("videoview",videocode).setEscapeModelStrings(false));
	}
}
