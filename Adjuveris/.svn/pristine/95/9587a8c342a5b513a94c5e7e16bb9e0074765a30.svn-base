package com.linguaclassica.exercises;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.wicket.Session;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.basic.MultiLineLabel;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.googlecode.wicket.jquery.ui.form.button.ConfirmButton;
import com.linguaclassica.access.AlphPlusSession;
import com.linguaclassica.entity.EntityAnswerModel;
import com.linguaclassica.entity.EntityQuestionModel;
import com.linguaclassica.entity.EntityTextPassageModel;
import com.linguaclassica.model.AnswerModel;
import com.linguaclassica.model.ExerCategoryModel;
import com.linguaclassica.model.ExerciseModel;
import com.linguaclassica.model.ModelFactory;
import com.linguaclassica.model.QuestionModel;
import com.linguaclassica.model.TextPassageModel;
import com.linguaclassica.model.UserModel;
import com.linguaclassica.service.ClassService;
import com.linguaclassica.service.ExerciseService;
import com.linguaclassica.service.MessageService;
import com.linguaclassica.service.QuestionService;
import com.linguaclassica.service.UserService;
import com.linguaclassica.teacher.ExerciseListPage;
import com.linguaclassica.teacher.TeacherBasePage;


/**
 * The initial welcome page with the main site navigation elements
 * 
 * @author Eugene Price
 * @Copyright("2012 Lingua Classica")
 */
//@RequireHttps
public class EditExercisePage extends TeacherBasePage {
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unused")
	private Logger logger = LoggerFactory.getLogger("EditExercisePage");

	@SpringBean
	private UserService userService;

	@SpringBean
	private MessageService messageService;
 
	@SpringBean
	private ClassService classService;

	@SpringBean
	private QuestionService questionService;

	@SpringBean
	private ExerciseService exerciseService;
	
	@SpringBean
	private ModelFactory modelFactory;
	
	UserModel userModel;
	
	ExerciseModel exerciseModel;
	
	ExerCategoryModel exerCategoryModel;
	
	QuestionModel questionModel;
	public QuestionModel getQuestionModel() {
		return questionModel;
	}
	public void setQuestionModel(QuestionModel questionModel) {
		this.questionModel = questionModel;
	}

	AnswerModel answerModel;
	TextPassageModel textPassageModel;
	
	int exerciseId;
	int exerCategoryId;
	int passageid = 0;
	int questionGroupId = 0;
		
	String passagecontent = "";
	String passageword = "";

	/////////////////////////////
	//setup for test choices
	String selected;  

	List<String> questiontypeList = Arrays.asList(new String[] {
			"True-false", "Multiple choice", "Multiple select", "Fill in the blank", 
			"List matching", "Latin Parsing", "Greek Parsing" });
	
	List<String> proficienciesList = Arrays.asList(new String[] {
			"Vocabulary","Inflections", "Syntax", "Comprehension"});

	List<String> exerciseNameList = Arrays.asList(new String[] {
			"truefalse", "multichoice", "multiselect", "fillblank", "listmatch", "latinparsing", "greekparsing" });
		//////////////////
	static final public Integer  optionTrueFalse = 0;
	static final public Integer  optionMultiChoice = 1;
	static final public Integer  optionMultiSelect = 2;
	static final public Integer  optionFillBlank = 3;
	static final public Integer  optionListMatch = 4;
	static final public Integer  optionLatinParsing = 5;
	static final public Integer  optionGreekParsing = 6;
	////////////////////////////////////////
	Label questionType;	
	DropDownChoice<String> questiontypeChoice;
	QuestionData qt = new QuestionData();
	////////////////////////
	Integer count = 1;
	int questioncountCheck;
	int questioncount;
	int exercisecount;
	int questionId;
	String passagestr;
	boolean editoption = false;
	boolean addoption = false;
	/////////////
	TextField<String> textinfotf;	
	TextField<String> wordshowtf;
	TextField<Integer> lncntshowtf;
	TextField<Integer> wrdcntshowtf;
	TextField<Integer> qcounttf;
	TextField<Integer> strtshowtf;
	TextArea<String> passageTextTa;
	List<String> passageButtons = Arrays.asList(new String[] {
			"formatbutton","numbrbutton","replbutton","reversebutton"});
		///////////////
	//setup for the answer number choices
		//////////////////
	TrueFalseContainer truefalse;
	MultiChoiceContainer multichoice;
	MultiSelectContainer multiselect;
	FillInBlankContainer fillblank;		
	ListMatchContainer listmatch;
	LatinContainer latinparsing;
	GreekContainer greekparsing;
	WebMarkupContainer passagetxt;
	WebMarkupContainer createtxtdiv;
	WebMarkupContainer proficspan;
	
	Button finishbutton;
	Button savebutton;
	Button cancelbutton;
	Button closepassagelink;
	Label insertAfter;
	DropDownChoice<String> insertPosition;
	List<String> availableInserts = new ArrayList<String> (); 
	String insertPos;
	
	DropDownChoice<String> proficiencySelect;
	String proficSelected;
	///////////////////////
	List<QuestionModel> questionList = new ArrayList<QuestionModel>();
	
	int passageIdPrv = 0;
	int listMatchQuestionsTotal = 0;
	int prevListId = 0;// to check ListMatch grouping
	int maxquestion = 0;
	int countList = 0;;

	public EditExercisePage() {
		super();
		
		System.out.println("EditExercisePage loaded");
		//System.out.println("onload AlphPlusSession.get().getCount() = " + 
				//AlphPlusSession.get().getCount());
		
		RetrieveSessionData();
		SetupEditOptions();
		
		// 3.  The following sets up the feedback panel, instantiates the form and otherwise sets up the page.
		
		final Form<?> form = new EditExerciseForm("EditExerciseForm");		
	//	session.setSelectedClasses(null);  //clears classes selected for TeacherTALandingPage
		
		form.add(new Button("gotoListButton") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onSubmit() {
				AlphPlusSession.get().setUnsavedQuestion("");
				setSessionVariables(0, "None", 0, 0, "", "", false, false);
				ResetToDefaults();					
				setResponsePage(ExerciseListPage.class);
			}			
		});
		
		exerCategoryModel = exerciseService.findExerCategoryById(exerCategoryId);
			
		form.add(new Label("exernamelab",
				new PropertyModel<String>(exerCategoryModel,"exercisename")));	
	
	//got to stop putting text animation  here - it affects the exercise name display
		
		questionType = new Label("questionType", "Question type: ");

		questiontypeChoice = new DropDownChoice<String>("questionchoice", 
				new PropertyModel<String>(qt,"qtype"), questiontypeList);/* {
			private static final long serialVersionUID = 1L;
			
			protected boolean wantOnSelectionChangedNotifications() {
                return true;
            }
			@Override
		    protected String getNullKeyDisplayValue() {
		        return "Choose one";
		    }*/
			// 9.  The following changes the value of selected when a new value is selected in the 
			//question type dropdown select. 
		 
		questiontypeChoice.add(new AjaxFormComponentUpdatingBehavior("onchange") {
			private static final long serialVersionUID = 1L;
				
			@Override
		     protected void onUpdate(AjaxRequestTarget target) {
				//First retrieve the old container to render and check if it's a valid option
				int indexOption = questiontypeList.indexOf(selected);
				if(indexOption > -1){
					ExercisesContainer webContainer1 = (ExercisesContainer)form.get(exerciseNameList.get(indexOption));
					target.add(webContainer1);
				}
					//reset selection
				selected = questiontypeChoice.getConvertedInput();
				if(!selected.contains("Multi")){
								
					if(selected.contains("Parsing")){
						questionModel.setQuestion("");//as Parser options have no question
					}
					answerModel.setAnswer("");
					answerModel.setAnswerint(0);
					answerModel.setCorrect(false);
					answerModel.setQuestionid(0);
									
					truefalse.ResetToDefaults();
					multichoice.ResetToDefaults();
					multiselect.ResetToDefaults();
					fillblank.ResetToDefaults();
					listmatch.ResetToDefaults();
					latinparsing.ResetToDefaults();
					greekparsing.ResetToDefaults();
				}
				//get a new selection index
				indexOption = questiontypeList.indexOf(selected);
				passagecontent = textPassageModel.getTextcontent();
				if (passagecontent != null && !passagecontent.isEmpty() && passagetxt.isVisible()) {
					truefalse.setVisible(indexOption == optionTrueFalse);    	
				    multichoice.setVisible(indexOption == optionMultiChoice);	    
				 	multiselect.setVisible(indexOption == optionMultiSelect);
				 	fillblank.setVisible(indexOption == optionFillBlank);
				 	listmatch.setVisible(indexOption == optionListMatch);
				 	latinparsing.setVisible(indexOption == optionLatinParsing);
				 	greekparsing.setVisible(indexOption == optionGreekParsing); 
					//Last retrieve the new container to render
					ExercisesContainer webContainer2 = (ExercisesContainer)form.get(exerciseNameList.get(indexOption));
					target.add(webContainer2);
				}
				else{
					setSelectedVisibility(indexOption);
					target.add(form);//if a new exercise - update the entire form
				}
			}
		});	
		questiontypeChoice.setOutputMarkupId(true);
		
		form.add(questionType);
		form.add(questiontypeChoice);
		
	
		form.setOutputMarkupId(true);
		/*****************************************************************************************************
		 * 4.  The following sets up WebMarkupContainers which correspond with html divs for each question type 
		 * which are set as visible=false unless one is made visible to create or edit question content for a 
		 * particular type of question.	
		 *****************************************************************************************************/
		CreatePassageContainer();
		
		int optionChoice = questiontypeList.indexOf(selected); //check for the active selection and create exercise containers accordingly
		for(int i = 0; i < questiontypeList.size(); i++){
			CreateExercise(optionChoice,i);
		}
	
		finishbutton = new Button("finishbutton") {
			private static final long serialVersionUID = 1L;

			public void onSubmit() {
				setSessionVariables(0, "None", 0, 0, "", "", false, false);	
				setResponsePage(EditExercisePage.class);
        	}
		};	
    	
		form.add(finishbutton);
				
		// 7.a.  The following creates a 'Save' button used to save all question types.  This 
		//differs from and is addition to the 'add question' feature of fill-in-the-blank 
		//questions, which permits the user to add more questions related to a given passage. 
	
		savebutton = new Button("savebutton") {
			private static final long serialVersionUID = 1L;

			public void onSubmit() {
					 if(SaveToServer()){
						 setResponsePage(EditExercisePage.class);
					 }
			
			}
		};
		insertAfter = new Label("insertAfter", "Insert new question after:");
		
		insertPosition = new DropDownChoice<String>("insertPosition", 
				new PropertyModel<String>(this,"insertPos"),	availableInserts) {
			private static final long serialVersionUID = 1L;
			
			protected boolean wantOnSelectionChangedNotifications() {
                return true;
            }
			@Override
		    protected String getNullKeyDisplayValue() {
		        return "Last one";
		    }
			// 9.  The following changes the value of selected when a new value is selected in the 
			//question type dropdown select. 
			 public void onSelectionChanged(String newSelection) {
				 if(newSelection.equals("Last one")){
					 exercisecount = questionService.getSizeOfQuestionListByExerID(questionList);
				 }
				 else{
					 exercisecount = Integer.parseInt(newSelection) - 1;//one is added when saved for the case of the last exercise
				 }
			}
		};
		insertAfter.setOutputMarkupId(true);	 
		insertPosition.setOutputMarkupId(true);			
		
		// 7.b.  The following creates a 'Cancel' button for the edit mode.  
		cancelbutton = new Button("cancelbutton") {
					private static final long serialVersionUID = 1L;

					public void onSubmit() {
						setSessionVariables(0, "None", 0, 0, "", "", false, false);
						ResetToDefaults();
						setResponsePage(EditExercisePage.class);
					}	
		};
		
		//following creates dropdown selet for question proficiencies
		Integer proficId = questionModel.getProficiencyindex();
		if(proficId != null && proficId >= 0){
			proficSelected = proficienciesList.get(proficId);
		}
		
		proficiencySelect = new DropDownChoice<String>("proficsel", new PropertyModel<String>(this,"proficSelected"),
				proficienciesList);
		proficspan = new WebMarkupContainer("proficspan");
		proficspan.add(proficiencySelect);
		proficspan.setVisible(false);
		form.add(proficspan);
		
		
		form.add(insertAfter);
		form.add(insertPosition);
		form.add(savebutton);
		form.add(cancelbutton);
		
		/////////////////////////////////
		form.setEnabled(true);
		//Hide Question type selection if editing an exiting test
		questionType.setVisible(!editoption);
		questiontypeChoice.setVisible(!editoption);
			
		//add containers to the form				
		form.add(truefalse);
		form.add(multichoice);
		form.add(multiselect);
		form.add(fillblank);
		form.add(listmatch);
		form.add(latinparsing);	
		form.add(greekparsing);	
		form.add(passagetxt);
		form.add(createtxtdiv);
		form.setOutputMarkupId(true);	

		add(form);
		
		CreateDisplayExerciseForm();
		
		setSelectedVisibility(questiontypeList.indexOf(selected));
		//for logout
		ExternalLink logoutLink = new ExternalLink("logout_link", "/j_spring_security_logout");
		logoutLink.setContextRelative(true);
		add(logoutLink);

	} 	
	/****************************************************************************************************
	 * 8.  The following sets visibility for question type webmarkup containers where 'selected' is passed in
	 * as a PageParameter when the page is reloaded.
	 ****************************************************************************************************/
	@SuppressWarnings("unused")
	protected void setSelectedVisibility(int indexOption) {	
		
		savebutton.setVisible(indexOption > -1);// save is true for all but one option
		proficspan.setVisible(indexOption > -1);
		
		cancelbutton.setVisible(editoption || addoption);
		passagecontent = textPassageModel.getTextcontent();
    	//when passage text is in use or for the cases it must be used - show it
    	if (passagecontent == null || passagecontent.isEmpty()) {
    			if(indexOption == optionFillBlank || indexOption == optionLatinParsing || indexOption == optionGreekParsing) {
    				passagetxt.setVisible(true);// must have passage text cases
    				createtxtdiv.setVisible(false);
    				finishbutton.setVisible(true);
    				closepassagelink.setVisible(true);//prevent closing passage text in this option that works only with the passage text	
    				insertPosition.setVisible(false );//only  if passage isn't there
        			insertAfter.setVisible(false);
    				 
    			}
    			else{
    				passagetxt.setVisible(false);// 
    				createtxtdiv.setVisible(true);
    				createtxtdiv.get("createtxtbutton").setVisible(!editoption && !addoption);//if editing the saved exercise without a passage - disable creation passage option
    				finishbutton.setVisible(false);
    				closepassagelink.setVisible(false); 
    				insertPosition.setVisible(indexOption > -1 && !editoption && !addoption );//only  if passage isn't there
        			insertAfter.setVisible(insertPosition.isVisible());
    			}	
    			
    	}
    	else{//default settings
    		passagetxt.setVisible(true || editoption || addoption);
    		createtxtdiv.setVisible(false && !editoption && !addoption);
    		finishbutton.setVisible(!editoption && !addoption);
    		closepassagelink.setVisible(!editoption  && !addoption);//prevent closing passage text in this option that works only with the passage text	  
    		insertPosition.setVisible(false );//only  if passage isn't there
			insertAfter.setVisible(false);
    	}
    		
     	truefalse.setVisible(indexOption == optionTrueFalse);    	
	    multichoice.setVisible(indexOption == optionMultiChoice);	    
	 	multiselect.setVisible(indexOption == optionMultiSelect);
	 	fillblank.setVisible(indexOption == optionFillBlank);
	 	listmatch.setVisible(indexOption == optionListMatch);
	 	latinparsing.setVisible(indexOption == optionLatinParsing);
	 	greekparsing.setVisible(indexOption == optionGreekParsing);      	
		
		return;
	}
	
	protected void setSessionVariables(int questionId, String selectedVal, int questionGroupId, int passageId, 
			String passageTxt, String passageWrd, Boolean editOption, Boolean addOption) {
		AlphPlusSession session = AlphPlusSession.get();

		session.setCount(count);
		session.setQuestionId(questionId);
		session.setSelectedVal(selectedVal);
		session.setQuestionGroupId(questionGroupId);		
		session.setPassageId(passageId);		
		session.setPassageTxt(passageTxt);
		session.setPassageWrd(passageWrd);
		session.setEditOption(editOption);
		session.setAddOption(addOption);
		
		return;
	}
	
	// 13.  Creates the EditExercise form.  A generalized 'submit' button had previously been 
	// part  of this form but should NOT be used in the interests of more precise control 

	class EditExerciseForm extends Form<Object> {
		private static final long serialVersionUID = 1L;

		public EditExerciseForm(String id) {
			super(id);		
		}
	}	
	
// 15.  Getters and setters for variables in this class
	
	public String getSelected() {
		return selected;
	}

	public void setSelected(String selected) {
		this.selected = selected;
	}
	

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}
	
	
	//////////////
	protected void ResetMultiAnswers() {
		
		AlphPlusSession thisSession = AlphPlusSession.get();
		
		thisSession.setExerciseCategory(exerCategoryId);
		thisSession.setExerciseId(exerciseId);
		if (questionModel.getId() != null){
			thisSession.setQuestionId(questionModel.getId());
		} else{
			// Set Questiontext and answers text
			thisSession.setUnsavedQuestion(questionModel.getQuestion());
		}
		setSessionVariables(questionId, qt.getQtype(), questionGroupId, passageid, 
				textPassageModel.getTextcontent(), questionModel.getWord(), editoption, addoption);
		
		setResponsePage(EditExercisePage.class);	
	}
	
	//////////////
	//Reset the form to its default settings and view
	protected void ResetToDefaults(){
		
		questionModel.setQuestion("");
		truefalse.ResetToDefaults();
		multichoice.ResetToDefaults();
		multiselect.ResetToDefaults();
		latinparsing.ResetToDefaults();
		greekparsing.ResetToDefaults();
		fillblank.ResetToDefaults();
		listmatch.ResetToDefaults();
		questionModel.setWord("");
		questionModel.setLinenumber(null);
		questionModel.setWordnumber(null);		
	}
	
	protected void RetrieveSessionData(){
		 // 1.  The following retrieves variables from the session object.
		
		//logger.info("EditExercisePage, line 202:  exerciseIdStr = " + exerciseId);
	
		AlphPlusSession session = AlphPlusSession.get();
		session.setSelectedClasses(null);  //clears classes selected for TeacherTALandingPage
		
		exerciseId = session.getExerciseId();
		exerCategoryId = session.getExerciseCategory();
		questionId = session.getQuestionId();		
	//	questioncount = session.getCount();
		passageid = session.getPassageId();		
		passagecontent = session.getPassageTxt();
		passageword = session.getPassageWrd();
	
		editoption = session.getEditOption();
		addoption = session.getAddOption();
		questionGroupId = session.getQuestionGroupId();	
		
		selected = session.getSelectedVal();
		if (!selected.equals("None")){
			qt.setQtype(selected);
		}
		/////////////////////
		questionList = questionService.getListOfQuestionByExerID(exerciseId);
		exercisecount = questionService.getSizeOfQuestionListByExerID(questionList);		
		if(questioncount < 1){
			questioncount = exercisecount;
		}
		Integer prevNum = 0;
		for(int i = 0; i < questionList.size();i++){
			QuestionModel qm = questionList.get(i);
			if((qm.getPassageid() == null || qm.getPassageid() == 0) && prevNum != qm.getQuestionGroupid()){
				availableInserts.add(Integer.toString(qm.getQnumber()));
				prevNum = qm.getQuestionGroupid();// to accound for the same group questions like ListMatch
			}
			
		}
		availableInserts.add("Last one");
				
	}
	private void SetupEditOptions(){
		 //2. The following detects whether question is a new question or an existing question to be edited
		 // and either generates a new questionModel or gets the questionModel from the database.
		AlphPlusSession session = AlphPlusSession.get();
		if(editoption){// retrieve from DB 
			questionModel = questionService.findQuestionById(questionId);
			answerModel  = questionService.findCorrectAnswerByQuestionId(questionId);			
			selected = questionModel.getQuestiontype();
		}else if(addoption){
			questionModel = modelFactory.getNewQuestionModel();
			answerModel = modelFactory.getNewAnswerModel();
		}
		else{
			questionModel = modelFactory.getNewQuestionModel();
			answerModel = (EntityAnswerModel) modelFactory.getNewAnswerModel();
			
			questionModel.setQuestion(session.getUnsavedQuestion());
			questionModel.setWord(session.getPassageWrd());
			questionModel.setLinenumber(session.getLinenumber());
			questionModel.setWordnumber(session.getWordnumber());
	
		}
		
	}

	protected void CreatePassageContainer(){
		
		passagetxt = new WebMarkupContainer("textdiv");
		createtxtdiv = new WebMarkupContainer("createtxtdiv");
		//Create a variable that informs passages2.js about the state we're in
		if (passageid > 0) {
			textPassageModel = questionService.findPassageById(passageid);
			passagecontent = textPassageModel.getTextcontent();
		}
		else {
			textPassageModel = modelFactory.getNewTextPassageModel();
			textPassageModel.setTextcontent(passagecontent);
		}
		//find the last (and highest) number in the passage
		if(passagecontent != null && !passagecontent.isEmpty()){
			int index = passagecontent.lastIndexOf("}");
		
			if( index > 0){
				String highNum = passagecontent.substring(passagecontent.lastIndexOf("{")+1,index);
				questioncount = Integer.parseInt(highNum);
			}
			
		}
		questioncountCheck = questioncount;
		// 5.  Following sets up the fill-in-the-blank question type
		passageTextTa = new TextArea<String>("passagetext",
				new PropertyModel<String>(textPassageModel,"textcontent"));	
			passageTextTa.add( new AjaxFormComponentUpdatingBehavior("onkeyup") {
			private static final long serialVersionUID = 1L;
			@Override
			protected void onUpdate(AjaxRequestTarget target){
				
			}					
		});
		passageTextTa.add( new AjaxFormComponentUpdatingBehavior("onchange") {
			private static final long serialVersionUID = 1L;
			@Override
			protected void onUpdate(AjaxRequestTarget target){
				textPassageModel.setTextcontent(passageTextTa.getModelObject());
							
			}					
		});
		
		passageTextTa.setEnabled(!editoption || addoption);
	
		textinfotf = new TextField<String>("textinfotf", 
				new PropertyModel<String>(textPassageModel,"textinfo"));
		textinfotf.add( new AjaxFormComponentUpdatingBehavior("onkeyup") {
			private static final long serialVersionUID = 1L;
			@Override
			protected void onUpdate(AjaxRequestTarget t){
						
			}					
		});
				
		wordshowtf = new TextField<String>("wordshow", 
				new PropertyModel<String>(questionModel,"word"));	
		wordshowtf.add( new AjaxFormComponentUpdatingBehavior("onchange") {
			private static final long serialVersionUID = 1L;
			@Override
			protected void onUpdate(AjaxRequestTarget t){
				
			}					
		});
		lncntshowtf = new TextField<Integer>("lncntshow", 
				new PropertyModel<Integer>(questionModel,"linenumber"));
		lncntshowtf.add( new AjaxFormComponentUpdatingBehavior("onchange") {
			private static final long serialVersionUID = 1L;
			@Override
			protected void onUpdate(AjaxRequestTarget t){
				
			}					
		});
		wrdcntshowtf = new TextField<Integer>("wrdcntshow", 
				new PropertyModel<Integer>(questionModel,"wordnumber"));
		wrdcntshowtf.add( new AjaxFormComponentUpdatingBehavior("onchange") {
			private static final long serialVersionUID = 1L;
			@Override
			protected void onUpdate(AjaxRequestTarget t){
				
			}					
		});
		
		strtshowtf = new TextField<Integer>("strtshow", 
				new PropertyModel<Integer>(questionModel,"strword"));
		strtshowtf.add( new AjaxFormComponentUpdatingBehavior("onchange") {
			private static final long serialVersionUID = 1L;
			@Override
			protected void onUpdate(AjaxRequestTarget t){
				questionModel.setStrword(strtshowtf.getModelObject());
				
			}					
		});
		qcounttf = new TextField<Integer>("qcounttf", 
				new PropertyModel<Integer>(this,"questioncount"));
		qcounttf.add( new AjaxFormComponentUpdatingBehavior("onchange") {
			private static final long serialVersionUID = 1L;
			@Override
			protected void onUpdate(AjaxRequestTarget t){
				
				System.out.println(" questioncount - " + questioncount);
				
			}					
		});
						
		Button format = new Button("formatbutton");
		format.add( new AjaxFormComponentUpdatingBehavior("onclick") {
			private static final long serialVersionUID = 1L;
			@Override
			protected void onUpdate(AjaxRequestTarget t){
				
				
			}					
		});
		format.setOutputMarkupId(true);
		
		passagetxt.add(new Button("numbrbutton").add( new AjaxFormComponentUpdatingBehavior("onclick") {
			private static final long serialVersionUID = 1L;
			@Override
			protected void onUpdate(AjaxRequestTarget t){	
				t.add(qcounttf);
				
			}					
		}));
	
		passagetxt.add(new Button("replbutton").add( new AjaxFormComponentUpdatingBehavior("onclick") {
			private static final long serialVersionUID = 1L;
			@Override
			protected void onUpdate(AjaxRequestTarget t){
				System.out.println(" replbutton onchange");
				}					
		}));
		textPassageModel.setTextcontent(passageTextTa.getModelObject());
		passagetxt.add(new Button("reversebutton").add( new AjaxFormComponentUpdatingBehavior("onclick") {
			private static final long serialVersionUID = 1L;
			@Override
			protected void onUpdate(AjaxRequestTarget t){
				System.out.println(" reversebutton onchange");
				//on reverse simply restore the previous settings
				//t.add(textinfotf);
				setSessionVariables(questionId, qt.getQtype(), questionGroupId, passageid, 
						textPassageModel.getTextcontent(), questionModel.getWord(), editoption, addoption);				
				setResponsePage(EditExercisePage.class);	
			
				}					
		}));
		
		passagetxt.add(format);
		passagetxt.add(passageTextTa);
		passagetxt.add(textinfotf); 
		passagetxt.add(wordshowtf);
		passagetxt.add(lncntshowtf);
		passagetxt.add(wrdcntshowtf);
		passagetxt.add(strtshowtf);
		passagetxt.add(qcounttf);
			
		Button createtextlink = new Button("createtxtbutton") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onSubmit() {
				passagetxt.setVisible(true);
				createtxtdiv.setVisible(false);
				closepassagelink.setVisible(true);
				insertPosition.setVisible(false );//only  if passage isn't there
				insertAfter.setVisible(false);
			}			
		};
		createtxtdiv.add(createtextlink);
		
		closepassagelink = new Button("closepassagelink") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onSubmit() {
				
				questionModel.setQuestion("");
				questionModel.setWord("");
				questionModel.setLinenumber(null);
				questionModel.setWordnumber(null);
				textPassageModel.setTextcontent("");
				textPassageModel.setTextinfo("");
				createtxtdiv.setVisible(true);
				passagetxt.setVisible(false);
				finishbutton.setVisible(false);
				insertAfter.setVisible(false);
				
			}			
		};
		
		passagetxt.add(closepassagelink);
		
		// The following populates the passage text area and word text box if these are not 
		//empty in the session object

		if (passagecontent != null && !passagecontent.isEmpty()) {
			passageTextTa.setModelObject(passagecontent);
			passagetxt.setVisible(true);
		}
		if (passageword != null && !passageword.isEmpty()) {
			wordshowtf.setModelObject(passageword);
		}	
		passagetxt.setOutputMarkupId(true);
	}
	
	
	
	protected Boolean SaveToServer(){
		//Validations before saving 
		Integer optionSelect = questiontypeList.indexOf(selected);
		//before starting saving - make sure proficiency is selected
		int proficId = proficienciesList.indexOf(proficSelected);
		if(proficId < 0){
			Session.get().error("Proficiency must be selected");//no point in moving forward if the conditions for the test are  not met
			return false;
		}
		
		ExercisesContainer exerciseContainer  = (ExercisesContainer)this.get("EditExerciseForm").get(exerciseNameList.get(optionSelect));
		if(!exerciseContainer.Validate())//validate input
			return false;
		//we save the corresponding index, not the id
		questionModel.setProficiencyindex(proficId);
		
		questionModel.setQuestion(exerciseContainer.getQuestion());//set the question into the model
		
		if(!SavePassage()){
			return false;
		}
		// 7.b.  The following defines actions to be taken when saving each question 
		//type, on submit.  For now not all the question types are handled.	
		
		if(!exerciseContainer.SaveExcercise(questionModel))//SaveParser(optionMultiChoice))
			return false;
		AlphPlusSession.get().setUnsavedQuestion("");
		Integer passageid = questionModel.getPassageid();
		if(!editoption && !addoption && passageid != null && passageid > 0){// reset all only when editing existing exercise
			setSessionVariables(questionId, "None", 0, passageid, "", 
				"", false, false);
		}
		else{
			setSessionVariables(0, "None", 0, 0, "", "", false, false);
		}
		ResetToDefaults();
		questionService.RenumberPassage(exerciseId,"add");
	//	questionService.renumberQuestions(exerciseId, questionModel.getStrword(), "add");	
		return true;
	
	}

	protected Boolean SavePassage(){
		//creates a text passage record where a text passage exists (is not an empty string)
		Integer optionSelect = questiontypeList.indexOf(selected);
	
		passagecontent = textPassageModel.getTextcontent();
		
		if (passagecontent != null && !passagecontent.isEmpty() && passagetxt.isVisible()) {
			
			if((questionModel.getWord()== null || questionModel.getWord().isEmpty())){
				
				//test can not be completed without the word
				if(optionSelect == optionFillBlank || optionSelect == optionLatinParsing || optionSelect == optionGreekParsing){
			
						Session.get().error("This test reguires a selected word from the passage text. Select a word for the exercise.");//no point in moving forward if the conditions for the test are  not met
						return false;
				}
				else{//force user to  create the word or close it
					Session.get().error("The word field is empty. If you do not wish to use it in your selection - please close the passage option.");//no point in moving forward if the conditions for the test are  not met
					return false;
				}	
				
			}
			else if (!editoption) {//check if the new word is numbered
															
				if(questioncountCheck == questioncount){//if no new number was introduced - the last number is the highest
					int firstBracketIndex = passagecontent.lastIndexOf("{");// find index before extracting a number to avoid out of bounds exception
					int laseBracketIndex = passagecontent.lastIndexOf("}");//  in case of no number was selected in a new unnumbered passage
					if(firstBracketIndex < 0 || laseBracketIndex < 0 || 
							questioncount == Integer.parseInt(passagecontent.substring(firstBracketIndex+1,laseBracketIndex))){
					 	Session.get().error("You must assign a number to the word by clicking the Number button");//no point in moving forward if the conditions for the test are  not met
						return false;
					}
				}
			}
			
			textPassageModel.setExerciseid(exerciseId);
			textPassageModel.setTextcontent(passagecontent);// Redundant?
			if (passageid == 0) {
				passageid = exerciseService.createTextPassage(textPassageModel);
			}
			else {
				exerciseService.updateTextPassage(textPassageModel);
			}
		}
		else if(passagetxt.isVisible()){// if passage option is available without the text
				//test can not be completed without the passage
			if(optionSelect == optionFillBlank || optionSelect == optionLatinParsing || optionSelect == optionGreekParsing){
			
					Session.get().error("This test reguires a passage text. You must create a passage text for this exercise.");//no point in moving forward if the conditions for the test are  not met
					return false;			
			}
			else{//force user to either create the passage or close it
				Session.get().error("The passage of text field is empty. If you do not wish to use it in your selection - please close the passage option.");//no point in moving forward if the conditions for the test are  not met
				return false;
			}
		}
		else{
			questioncount = 0;// not passage - no words for questions to count
		}
		if (!editoption) {
			questionModel.setQnumber((questioncount > 0) ? questioncount : (exercisecount+1));// in case no text - use general number
			
		}	
		questionModel.setExerciseid(exerciseId);				
		
		
		questionModel.setQuestiontype(selected);
		if (passageid == 0) {
			textPassageModel = modelFactory.getNewTextPassageModel();
		}
		else {
			textPassageModel = questionService.findPassageById(passageid);
		}
	
		if (passageid > 0) {
			questionModel.setPassageid(passageid);
		}

		if (questionModel.getPassageid() != null && questionModel.getPassageid() > 0) {
			String word = questionModel.getWord();//wordshowtf.getModelObject();
			int wordlen = 0;
			if (word != null && !word.isEmpty()) {
				wordlen = word.length();
			}
			if (word == null || word.isEmpty()) {
				questionModel.setStrword(passagecontent.length() + questionModel.getQnumber());
				questionModel.setEndword(passagecontent.length() + wordlen + questionModel.getQnumber());
				//set strword for passage questions with no word selected that places these questions
				//after those that refer to words
			}
		}
		// ensures that strword and endword are not null
		if (questionModel.getStrword() == null || questionModel.getStrword() == 0) {
			questionModel.setStrword(0);
		}
		if (questionModel.getEndword() == null || questionModel.getEndword() == 0) {
			questionModel.setEndword(0);
		}
		return true;
	}

		
	private  void CreateExercise(int activeChoice, int containerIndex){
	
		int dbID = 0;
		String question = "";
		if (activeChoice == containerIndex && editoption && questionId > 0){
			if (containerIndex == optionListMatch){// List MaAtching uses group iD for its questions
				dbID = questionModel.getQuestionGroupid();
			}
			else{
				dbID = questionId;
			}
			question = questionModel.getQuestion();
		}
		if (containerIndex == optionTrueFalse){
			truefalse = new TrueFalseContainer(exerciseNameList.get(optionTrueFalse), dbID);
			truefalse.setQuestion(question);
			truefalse.setOutputMarkupPlaceholderTag(true);
			
		}
		else if (containerIndex == optionMultiChoice){// parser answer options only
			multichoice = new MultiChoiceContainer(exerciseNameList.get(optionMultiChoice), dbID);
			multichoice.setQuestion(question);
			multichoice.setOutputMarkupPlaceholderTag(true);
		}
		else if (containerIndex == optionMultiSelect){
			multiselect = new MultiSelectContainer(exerciseNameList.get(optionMultiSelect), dbID);
			multiselect.setQuestion(question);
			multiselect.setOutputMarkupPlaceholderTag(true);
		}
		else if (containerIndex == optionLatinParsing){// parser has no question
			latinparsing = new LatinContainer(exerciseNameList.get(optionLatinParsing),dbID);
			latinparsing.setOutputMarkupPlaceholderTag(true);
		}
		else if (containerIndex == optionGreekParsing){// parser has no question
			greekparsing = new GreekContainer(exerciseNameList.get(optionGreekParsing),dbID);
			greekparsing.setOutputMarkupPlaceholderTag(true);
		}
		else if (containerIndex == optionFillBlank){// parser answer options only
			fillblank = new FillInBlankContainer(exerciseNameList.get(optionFillBlank), dbID);
			fillblank.setQuestion(question);
			fillblank.setOutputMarkupPlaceholderTag(true);
		}
		else if (containerIndex == optionListMatch){// parser answer options only
			listmatch = new ListMatchContainer(exerciseNameList.get(optionListMatch), dbID);
			listmatch.setQuestion(question);
			listmatch.setOutputMarkupPlaceholderTag(true);
		}
	
	}
	
	void CreateDisplayExerciseForm(){
		// 12.  Below displays the exercise questions already created in the right-hand panel 
		//on the page
		
		final Form<?> displayExerciseForm = new EditExerciseForm("DisplayExerciseForm");		
	
		listMatchQuestionsTotal = 0;
		
		
		displayExerciseForm.add(new ListView<QuestionModel>("questionListDisplay",questionList){
	
			private static final long serialVersionUID = 1L;
			
			protected void populateItem(final ListItem<QuestionModel> qitem) 
			{				
				TextArea<String> passageta = null;
				Label passagenote = null;
				Label endpassagenote = null;
				Boolean showComment = false;		
				Boolean showEditDelete = true;
			
				
				final EntityQuestionModel questions = 
						(EntityQuestionModel) qitem.getModelObject();
				endpassagenote = new Label("endpassagenote","-- End of this passage --");
				endpassagenote.setVisible(false);
				EntityTextPassageModel passageModel = new EntityTextPassageModel();
				
				int profindex = -1;//to avoid false positives from old SQL files
				if(questions.getProficiencyindex() != null){
					profindex = questions.getProficiencyindex();
				}
				String profstr = "none";
				if(profindex == 0) {
					profstr = "voc.";
				}
				else if(profindex == 1) {
					profstr = "infl.";
				}
				else if(profindex == 2) {
					profstr = "synt.";
				}
				else if(profindex == 3) {
					profstr = "comp.";
				}
				
				final Button addmorequestionsbutton = new Button("addmorequestionsbutton") {
					private static final long serialVersionUID = 1L;
	
					public void onSubmit() {
						System.out.println("addmorequestions link clicked");
						
						int passageId = 0;
						if (questions.getPassageid() != null) {
							passageId = questions.getPassageid();
						}
	
						setSessionVariables(questionId, "Fill in the blank", 0, passageId, "", 
								"", false, true);
						
						setResponsePage(EditExercisePage.class);	
					}
				};
	
				//checks to see if this is a passage question and the first passage question.
				//if so, adds passage note
				//System.out.println("EditExercisePage, line 984:  questions.getPassageid(), " + 
						//"passageIdPrv = " + questions.getPassageid() + ", " + passageIdPrv);
				if (questions.getPassageid() != null && passageIdPrv != questions.getPassageid()) {
					
					passageModel = questionService.findPassageById(questions.getPassageid());
					passagenote = new Label("passagenote",
							"The following questions refer to this text passage:");
					passageta = new TextArea<String>("passage",
							new PropertyModel<String>(passageModel,"textcontent"));
					passagenote.setVisible(true);
					passageta.setVisible(true);
					passageIdPrv = questions.getPassageid();
					//cannot use this since now numbers are s
					maxquestion = questionService.getPassageQuestionListSize(questions.getPassageid());
								
					addmorequestionsbutton.setVisible(true);
				}	
				else {
					passagenote = new Label("passagenote","No passage:");
					passagestr = "No passage";
					passageta = new TextArea<String>("passage", 
							new PropertyModel<String>(passageModel,"textcontent"));
					passageta.setVisible(false);
					passagenote.setVisible(false);
					addmorequestionsbutton.setVisible(false);
				}
				
				//checks to see if question is last passage question.  If so, adds end note
				//must have a passage, be not the first in the group and the count down of the group members should be down to 0
				endpassagenote.setVisible(questions.getPassageid()!= null && passageIdPrv == questions.getPassageid() &&  (--maxquestion) == 0);// hide the end of passage if );
					
				
				showComment = passageModel.getTextinfo()!= null && !passageModel.getTextinfo().isEmpty();
				qitem.add(passagenote);
				qitem.add(passageta);
				// comments to passage if exist
				qitem.add(new Label("comments","Comments: ").setVisible(showComment));
						
				if(!questions.getQuestiontype().equals(questiontypeList.get(optionListMatch))){//not list match case ;
					qitem.add(new Label("qnumberlab",questions.getQnumber()));
					qitem.add(new Label("qtypelab",questions.getQuestiontype()));
					qitem.add(new Label("questlab",questions.getQuestion()));
					qitem.add(new Label("proflab",profstr));
					qitem.add(new Label("infotext",passageModel.getTextinfo()).setVisible(showComment));
				}
				
				if(questions.getQuestiontype().equals(questiontypeList.get(optionListMatch))){//list match case ;
					
					int guestingroupID = questions.getQuestionGroupid();//questionService.findQuestionGroupId(questions.getId());
					
					showEditDelete = (prevListId != guestingroupID);
					
					
					String multipleChoiceAnswerList = "";
					String multipleChoiceQuestionList = "";
					
					if( showEditDelete && guestingroupID > 0)
					{
						prevListId = guestingroupID;		
						
						List<QuestionModel>  listMatchQuestions = questionService.findQuestionsByQuestionGroupId(guestingroupID);
						
						
						for (QuestionModel em : listMatchQuestions){				 
							
							multipleChoiceQuestionList += em.getQuestion()+ "\n";		
							EntityAnswerModel eam = questionService.findCorrectAnswerByQuestionId(em.getId());
							multipleChoiceAnswerList += eam.getAnswer() + "\n";								
						}
						listMatchQuestionsTotal += listMatchQuestions.size();					
					}	
					qitem.add(new Label("infotext",	"").setVisible(false));			
					qitem.add(new MultiLineLabel("answerlab", multipleChoiceAnswerList).setEscapeModelStrings(false).setVisible(!multipleChoiceAnswerList.isEmpty()));
					qitem.add(new MultiLineLabel("questlab", multipleChoiceQuestionList).setEscapeModelStrings(false).setVisible(!multipleChoiceQuestionList.isEmpty()));
					qitem.add(new MultiLineLabel("proflab",profstr).setEscapeModelStrings(false).setVisible(!multipleChoiceQuestionList.isEmpty()));
					qitem.add(new Label("qnumberlab",questions.getQnumber()).setVisible(showEditDelete));
					qitem.add(new Label("qtypelab",questions.getQuestiontype()).setVisible(showEditDelete));
				}
				else if (questions.getQuestiontype().contains("Multiple ")|| questions.getQuestiontype().contains("Parsing"))  {//Process both Multiple answers options
					 
					String multipleChoiceAnswerList = "";
					
					List<EntityAnswerModel> multipleChoiceAnswerModels = questionService.findAnswersByQuestionId(questions.getId());
					
					for (EntityAnswerModel answerModel : multipleChoiceAnswerModels) {
						String answerItem = "";
						
						if (answerModel.getCorrect()) {	
							answerItem += "<strong>" + answerModel.getAnswer() + "</strong>";
						} else {
							answerItem += answerModel.getAnswer();
						}			
						
						multipleChoiceAnswerList += answerItem + "\n";
					}
					
					qitem.add(new MultiLineLabel("answerlab", multipleChoiceAnswerList).setEscapeModelStrings(false));
					
				}
				else {
					qitem.add(new Label("answerlab",questionService.findCorrectAnswerByQuestionId(questions.getId()).getAnswer()));
				}
				
				qitem.add(endpassagenote);
				qitem.add(addmorequestionsbutton);
				
				countList = countList + 1;
	
				if (countList == questionList.size()) {
					passageIdPrv = 0;
					maxquestion = 0;
					countList = 0;
					prevListId = 0;
				}
				
				// ConfirmButton set up outside if clauses because it must be added to all 
				// question items but is visible only if there is only one question for a 
				// passage-based question.
				
				ConfirmButton confdeletebutton = new ConfirmButton("confdeletebutton","Delete",
						"Please Confirm", "The question and the passage will be deleted. " +
						"If you want to change the question click Edit instead of Delete"){
					private static final long serialVersionUID = 1L;
	
					@Override
					public void onError() {
						this.error("Validation failed!");
					}
	
					@Override
					public void onSubmit() {
						System.out.println("delete link clicked");
					
						//below if clause added to handle back button problem
						if (questionService.getListOfQuestionsById(questions.getId()).size() > 0) {
							if(questions.getQuestiontype().equals(questiontypeList.get(optionListMatch))){//list match case ;
								questionService.removeQuestionsByQuestionGroupId(questions.getQuestionGroupid());
							}
							else{
								questionService.deleteQuestion(questions.getId());
							}
						
							questionService.deleteTextPassage(questions.getPassageid());
								
							questionService.RenumberPassage(exerciseId, "subtract");
						}
						
						setSessionVariables(questionId, "None",	0, 0, "", "", false, false); 
	
						setResponsePage(EditExercisePage.class);
					}
					
				};
				
				// Delete button set up for passage-type questions
				
				if (questions.getPassageid() != null) { 
					List<QuestionModel> questionModelList = 
							questionService.findQuestionByPassageId(questions.getPassageid());
						
						Button deletebutton = new Button("deletebutton") {
							private static final long serialVersionUID = 1L;
	
							public void onSubmit() {
								System.out.println("delete a link within a Passage group clicked, questions.getWord() = " + 
										questions.getWord());
								int qidOut = questions.getId();
								String word = questions.getWord();
								int wordlen = word.length();
								textPassageModel = 
										questionService.findPassageById(questions.getPassageid());
								String passageCont = textPassageModel.getTextcontent();
	
								int strWord = questions.getStrword() - 5;
								int strt = passageCont.indexOf("{",strWord - 6);
								if (strt > 0) {
									int clospar = passageCont.indexOf("}",strt+2);
									int endWord = (clospar+1) + wordlen;  
									String firstStr = passageCont.substring(0,strt); 
									String secondStr = passageCont.substring(endWord,passageCont.length());
									passageCont =  firstStr + questions.getWord() + secondStr;
									
								}
								textPassageModel.setTextcontent(passageCont);
								exerciseService.updateTextPassage(textPassageModel);
															
								if(questions.getQuestiontype().equals(questiontypeList.get(optionListMatch))){//list match case ;
									questionService.removeQuestionsByQuestionGroupId(questions.getQuestionGroupid());
								}
								else{
									questionService.deleteQuestion(qidOut);
								}
							
								questionService.RenumberPassage(exerciseId, "subtract");
								// Update question number in the passage content as a question 
								//was deleted
	
								setSessionVariables(questionId,"None",0,0,"","",false,false);
								setResponsePage(EditExercisePage.class);
				        	}
						};
	
						// Make confirmation button visible only if there is only one question 
						//for the passage
	
						if (questionModelList.size() == 1) {
						
						confdeletebutton.setVisible(true);
						qitem.add(confdeletebutton);
						deletebutton.setVisible(false);
						qitem.add(deletebutton);
					}
					else {
						// Add delete button if there is more than one question for the passage 
						// Remove the question number from the passage
						// Delete the question number 
						// Update all the question numbers for the passage 
					
						confdeletebutton.setVisible(false);
						qitem.add(confdeletebutton);
						deletebutton.setVisible(showEditDelete);
						qitem.add(deletebutton);
					}
					
				}
				else {
					// Add delete button for other questions where passageId is null 
					// Delete the question number 
					// Update all the question numbers for the passage 
					
					Button deletebutton = new Button("deletebutton") {
						private static final long serialVersionUID = 1L;
	
						public void onSubmit() {
							System.out.println("delete link clicked");
							
							if(questions.getQuestiontype().equals(questiontypeList.get(optionListMatch))){//list match case ;
								questionService.removeQuestionsByQuestionGroupId(questions.getQuestionGroupid());
							}
							else{
								questionService.deleteQuestion(questions.getId());
							}
						
							questionService.RenumberPassage(exerciseId, "subtract");
							
							setSessionVariables(questionId, "None",	0, 0, "", "", false, false);
						
							setResponsePage(EditExercisePage.class);
			        	}
					};
				
					
					confdeletebutton.setVisible(false);
					qitem.add(confdeletebutton);
					deletebutton.setVisible(showEditDelete);
					qitem.add(deletebutton);
				}
				
				Button editlink  = new Button("editlink") {
					private static final long serialVersionUID = 1L;
	
					@Override
					public void onSubmit() {
						System.out.println("edit link clicked");
						
						int passageId = 0;
						if (questions.getPassageid() != null) {
							passageId = questions.getPassageid();
						}
						setSessionVariables(questions.getId(), questions.getQuestiontype(),0, passageId,"","",true, false);
						AlphPlusSession.get().setUnsavedAnswers(null);
						AlphPlusSession.get().setUseUnsavedAnswers(false);
						AlphPlusSession.get().setUnsavedQuestion(null);
						AlphPlusSession.get().setMultipleAnswersCount(0);
						selected = questions.getQuestiontype();
						setResponsePage(EditExercisePage.class);	
					}
				};
				qitem.add(editlink.setVisible(showEditDelete));		
			}	
		});
			
		add(displayExerciseForm);
		
	}
}
