package com.linguaclassica.shared;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.model.PropertyModel;
import com.linguaclassica.model.ModelFactory;

public class TranscriptPage extends PrivateBasePage
{
	private static final long serialVersionUID = 1L;
	
	@SpringBean
	ModelFactory modelFactory;
	
	String transcriptText;
	
	//private Logger logger = LogManager.getLogger(CreateMessagePage.class);
	
	public TranscriptPage(String name, String trText)
	{
		super();

		String head = getString("titlel").replace("[name]", name);
		add(new Label("header", head));
		
		transcriptText = trText;
		TextArea<String> transcriptTa = new TextArea<String>("trscriptta",
				new PropertyModel<String>(this, "transcriptText"));
		add(transcriptTa);
	}
}
