package com.linguaclassica.student;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.image.resource.RenderedDynamicImageResource;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.linguaclassica.access.AlphPlusSession;
import com.linguaclassica.entity.EntityAssignmentModel;
import com.linguaclassica.entity.EntityStudentAssignmentResultsModel;
import com.linguaclassica.model.UserModel;
import com.linguaclassica.service.AssignmentService;
import com.linguaclassica.service.AssignmentFactService;
import com.linguaclassica.service.ResultsService;
import com.linguaclassica.service.StudentAnswerService;
import com.linguaclassica.service.TermService;
import com.linguaclassica.service.UserService;
import com.linguaclassica.service.ExerciseService;
import com.linguaclassica.service.QuestionService;

public class ExercisesPolarChartPage extends StudentBasePage
{
	private static final long serialVersionUID = 1L;

	@SpringBean
	private UserService userService;
	@SpringBean
	private TermService termService;
	@SpringBean
	private ExerciseService exerciseService;
	@SpringBean
	private QuestionService questionService;
	@SpringBean
	private StudentAnswerService studentanswerService;
	@SpringBean
	private AssignmentService assignmentService;
	@SpringBean
	ResultsService resultsService;
	@SpringBean
	private AssignmentFactService assignmentfactService;
	
	List<EntityStudentAssignmentResultsModel> assignResultsList = 
			new ArrayList<EntityStudentAssignmentResultsModel>();
	
	int rosechatHight = 350;
	int rosechatWidth = 900;
	String maxDate ="";
	String minDate= "";
	double[] proficiencyPct = new double[4];
	
	
	int assignfactid;
	int assignid;
	EntityAssignmentModel assignmentModel;
	 
	// Active user
	private final UserModel userModel = AlphPlusSession.get().getUser(getRequest());
	Integer studentId ;
	Integer teacherId;	
	
	public final class CircleDynamicImageResource extends RenderedDynamicImageResource
    {
		private static final long serialVersionUID = 1L;

		private CircleDynamicImageResource(int width, int height)
        {
            super(width, height);
        }


        @Override
        protected boolean render(Graphics2D graphics, Attributes attributes)
        {
            drawRoseChart(graphics);
            return true;
        }
    }

	public ExercisesPolarChartPage()
	{
		super();
		this.studentId = userModel.getId();
		this.teacherId = 0;
	    setOutputMarkupId(true);		
	
		buildForm();
	}

	public ExercisesPolarChartPage(final String idIn, String category, final Integer studentId, Integer teacherId)
	{
		super();
		this.studentId = studentId;
		this.teacherId = teacherId;
	    setOutputMarkupId(true);		
	
		buildForm();
	}



	private void buildForm() {
	
		// Get assignment results only from unrestricted terms
		Integer instId = AlphPlusSession.get().getCurrentInstitution();
		List<Integer> termIdList = termService.getListOfRecentTermIDs(instId, AlphPlusSession.get().getDateRange());

		assignResultsList = resultsService.getListOfAssignmentResultsByStudentId(studentId,"COMPLETED", termIdList);
		
		int listsize = assignResultsList.size();
		
		Image windrose = new Image("windrose", new CircleDynamicImageResource(rosechatWidth, rosechatHight));
		add(windrose);
		windrose.setVisible(true);
		
		add(new Label("proficiencylabel","Proficiencies mapping for:  "+userModel.getFirstName() + 
				" " + userModel.getLastName()));
		
		if(listsize < 1) {
			windrose.setVisible(false);
		}
	}	

	void drawCircle(Graphics2D graphics, int x,int y, int radius,String type)
	{
		if (type=="fill")
			graphics.fillOval(x-radius, y-radius, radius*2, radius*2);
		else
			graphics.drawOval(x-radius, y-radius, radius*2, radius*2);
	}

	void drawSubArc(Graphics2D graphics, String proficiencyStr, int radius,int startAngle, int arcAngle)
	{
		int xradius;
		double proficiencyRate = Double.valueOf(proficiencyStr)/100;
		xradius=  (int) (radius * proficiencyRate) ;
		System.out.println("startAngle, radius, proficiencyStr = " +Integer.toString(startAngle)+", "+Integer.toString(radius)+", " +proficiencyStr);
		graphics.fillArc(-xradius, -xradius, xradius+xradius, xradius+xradius, startAngle, arcAngle);
	}

	void drawRay(Graphics2D graphics, double degree, int length,int width)
	{
		graphics.setStroke(new BasicStroke(width));	
		graphics.rotate(Math.toRadians(degree));
		graphics.drawLine(0, 0, 0, length);
		graphics.setFont(new Font("Arial", Font.PLAIN, 8));
		graphics.rotate(-Math.toRadians(degree)); 
	}

	void drawRoseChart(Graphics2D graphics)
	{
		graphics.setColor(Color.white);
		int x=0;
		int y=0;
		int dx=rosechatWidth;
		int dy=rosechatHight;
		graphics.fillRect(x, y, dx, dy);
		
		graphics.setFont(new Font("Arial", Font.PLAIN, 20));
		graphics.setColor(Color.black);
		graphics.drawString("Syntax",        220,                90);
		graphics.drawString("Vocabulary", rosechatWidth-280, 90);
		graphics.drawString("Inflection",    220,                rosechatHight-90);
		graphics.drawString("Comprehension",    rosechatWidth-280, rosechatHight-90);
			    
		// set circle center
		float xCenter = rosechatWidth / 2;
		int radius = rosechatHight/ 2;
		graphics.translate(xCenter, radius);
		
		// set circle radius
		radius = (int) (radius * 0.85);
		
		// fill the circle
		graphics.setStroke(new BasicStroke(1));	
		graphics.setColor(Color.lightGray);
		drawCircle(graphics, 0,0,radius,"fill");
		
		graphics.setColor(Color.gray);
		drawCircle(graphics,0, 0, (int)radius/4,"draw");	     
		drawCircle(graphics,0, 0, (int)radius*2/4,"draw");	     
		drawCircle(graphics,0, 0, (int)radius*3/4,"draw");	     
		drawCircle(graphics,0, 0, radius,"draw");
        
		// draw the circle spokes
		graphics.setColor(Color.gray);
		for (int i = 0; i < 16; i++) {
			double degree=i * 22.5;
			int linewidth = 1;
			if (i==0 || i==4 || i==8 || i==12)
				linewidth = 3 ;
			drawRay(graphics, degree, radius, linewidth);
		}

        int startAngle =0;
        int arcAngle=(int) 90/assignResultsList.size()-2;
        String proficiencyStr ;
        int listln = assignResultsList.size();
        for (int i = 0; i < listln; i++)
        {
           	//based on student answers	        	
        	//Vocabulary
        	startAngle = 90-(int) 90 * i/listln -arcAngle;	        	
        	proficiencyStr = assignResultsList.get(i).getVocabpct().toString();
        	graphics.setColor(Color.yellow);
        	drawSubArc(graphics, proficiencyStr, radius, startAngle, arcAngle);
        	
        	//Syntax
        	startAngle = 180-(int) 90 * i/listln -arcAngle;
        	
        	proficiencyStr= assignResultsList.get(i).getSyntaxpct().toString();
        	graphics.setColor(Color.red);
        	drawSubArc(graphics, proficiencyStr, radius, startAngle, arcAngle);

        	//Inflection
        	startAngle = 270-(int) 90 * i/listln -arcAngle ;
   	
        	proficiencyStr = assignResultsList.get(i).getInflectpct().toString();
        	graphics.setColor(Color.blue);
        	drawSubArc(graphics, proficiencyStr, radius, startAngle, arcAngle);

        	//Comprehension
        	startAngle = 360-(int) 90 * i/listln -arcAngle;
        	
        	proficiencyStr = assignResultsList.get(i).getComprehenpct().toString();
        	graphics.setColor(Color.green);
        	drawSubArc(graphics, proficiencyStr, radius, startAngle, arcAngle);
        }
	}
	    
}
