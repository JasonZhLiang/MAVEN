package com.linguaclassica.exercises;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.wicket.Session;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

import com.linguaclassica.entity.EntityAnswerModel;
import com.linguaclassica.model.AnswerModel;
import com.linguaclassica.model.QuestionModel;

public class GreekContainer extends ExercisesContainer implements GreekData {

	//Greek Parser options
	static int indexLbl = indexGreekLabel;// = ;
	static int indexText = indexGreekText;// = defaultAnswerRows;
	static List<String> boxes = Arrays.asList(new String[] {"pos", "var1", "var2", "var3", "var4", "var5"});
	Label lblVar;//6
	TextField<String> Vartf;//7
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public GreekContainer(String id, int questionId) {
		
		super(id, questionId);
		// TODO Auto-generated constructor stub
	}

	public GreekContainer(String id, IModel<?> model, int questionId) {
		super(id, model, questionId);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Boolean updateAnswersfromDatabase(int questionId ){
		
		List<EntityAnswerModel>  listSavedAnswers = null;
		if(questionId > 0){		
			listSavedAnswers = questionService.findAnswersByQuestionId(questionId);
		}
		if(listSavedAnswers == null || listSavedAnswers.isEmpty()){		
			updateAnswers();
			return false;
		}
		int size = listSavedAnswers.size();
		if( size > 0){//if database has answers - create an array for them
			multiAnswerModel = new ArrayList<AnswerModel>(multicount);
		}
		int lblIndex = indexLbl;
		String strVar;
		for(int i = 0; i < multicount; i++){
			if(i < size){// since the arrays of different types - must copy comparable fields
				strVar = listSavedAnswers.get(i).getAnswer();
				for (String var5str : labelList) {//find the lable index
					if(strVar.contains(var5str)){
						lblIndex = i;
						break;
					}
				}
									
				if(i < lblIndex){	//if not empty and not label
					multiAnswerModel.add(CreateAnswerModel(listSavedAnswers.get(i).getAnswer(),
							listSavedAnswers.get(i).getAnswerint(),
							listSavedAnswers.get(i).getCorrect(),
							listSavedAnswers.get(i).getQuestionid()));	
				}
				else {// create the label model and wait until the rest is filled before it's position
					int denominator = strVar.indexOf(":") + 1;
					AnswerModel textAM = CreateAnswerModel(strVar.substring(denominator),//after the delimiter fetch the comment
						listSavedAnswers.get(i).getAnswerint(),
						listSavedAnswers.get(i).getCorrect(),
						listSavedAnswers.get(i).getQuestionid());
						
					
					for( int index = lblIndex; index < indexLbl;index++){// fill the List with empties before lable
						multiAnswerModel.add(CreateAnswerModel("",0, (i == 0), 0));
						i++;
					}
					multiAnswerModel.add(CreateAnswerModel(strVar.substring(0,denominator),0, (i == 0), 0));//fill the label then the text		

					multiAnswerModel.add(textAM);
				}
			}	
		}
		return true;
		
	}
	
	public Boolean SaveExcercise(QuestionModel questionModel){
	
		questionModel.setQuestion(questionModel.getWord());//word is the question here
		questionService.addQuestionData(questionModel);
		questionService.removeAnswersByQuestionId(questionModel.getId());
		String Answer;
		int indexLabel = -1;
		String labelUsed = null;
		
		for ( int i = 0; i < multiAnswerModel.size();i++){
			AnswerModel md = multiAnswerModel.get(i);
			Answer = md.getAnswer();
			if((Answer != null && !Answer.isEmpty())){// add only answers from the filled fields
				md.setQuestionid(questionModel.getId());
				md.setCorrect(true);
				indexLabel = labelList.indexOf(md.getAnswer());
				if(indexLabel < 0){
					if(labelUsed != null && !labelUsed.isEmpty()){
						md.setAnswer(labelUsed+Answer);
					}
					else if( i == indexText){// if not lable and the last answer - skipt it
						break;
					}
					questionService.createAnswer(md);
					questionService.addOrUpdateAnswer(md);
				}
				else{
					labelUsed = Answer;
				}
			}
		}
		
		return true;
	}
	
		
	protected void CreateLabels(int posIndex){
		
		int index = GetListIndex(posIndex, lableStr);	 
		if(index >= 0){
			multiAnswerModel.get(indexLbl).setAnswer(labelList.get(index));
		}
		lblVar = new Label(lableStr,
				new PropertyModel<String>(multiAnswerModel.get(indexLbl),"Answer"));
		lblVar.setOutputMarkupId(true);
		add(lblVar);
		Vartf = new TextField<String>("Vartf",
				new PropertyModel<String>(multiAnswerModel.get(indexText),"Answer"));
		Vartf.setOutputMarkupId(true);
		add(Vartf);
	
		lblVar.setVisible(index >=0);
		Vartf.setVisible(index >=0);
	}
	
	protected void ShowLabels(AjaxRequestTarget target, int posIndex){
		
		String textVar5 = "";
		int index = GetListIndex(posIndex, lblVar.getId());	 
		if(index >= 0)
			textVar5 = labelList.get(index);
		multiAnswerModel.get(indexLbl).setAnswer(textVar5);
		lblVar.setVisible(index >= 0);
		Vartf.setVisible(index >= 0);
		target.add(lblVar);
		target.add(Vartf);
	}
	
	@SuppressWarnings("unchecked")
	public void CreateExcercise(int questionId){
		
		int index = -1;
		int posIndex = postypeList.indexOf(multiAnswerModel.get(0).getAnswer());
		
		for (int i= 0; i < boxes.size(); i++){
			
			AnswerModel am = multiAnswerModel.get(i);
			index = i;
			Boolean show = false;
			if(i > 0 ){//not for pos
				index = GetListIndex(posIndex, boxes.get(i));
				if(index < 1){// if no new index found - use default
					index = i;					
				}
				else{
					show = true;
				}
			}
				
			DropDownChoice<String> dropBox = new DropDownChoice<String>( boxes.get(i), 
					new PropertyModel<String>(am,"Answer"), optionsList.get(index));
			
			dropBox.add(new AjaxFormComponentUpdatingBehavior("onchange") {
				private static final long serialVersionUID = 1L;
			
				@Override
		        protected void onUpdate(AjaxRequestTarget target) {
						//reset if the main type is chanched
					if(dropBox.getId().equals( boxes.get(0))){
						for(int i = 1; i < indexLbl; i++){
							multiAnswerModel.get(i).setAnswer("");
						}
					}
					ShowChoices(target);
			
		        }
		    });
			dropBox.setOutputMarkupId(true);
			
			dropBox.setVisible((i==0) || show); //part of speech must be always be visible
			
			add(dropBox );
		}	
		CreateLabels(posIndex);		
	
		
	}
	
	protected int GetListIndex(int posIndex, String controlID){
		
		if(posIndex > -1){
			 if(controlID.equals(boxes.get(1)))
				 return indexVar1[posIndex];
			 else if(controlID.equals(boxes.get(2)))
				 return indexVar2[posIndex];
			 else if(controlID.equals(boxes.get(3)))
				 return indexVar3[posIndex];
			 else if(controlID.equals(boxes.get(4)))
				 return indexVar4[posIndex];
			 else if(controlID.equals(boxes.get(5)))
				 return indexVar5[posIndex];
			 else if(controlID.equals(lableStr))
				 return indexVar6[posIndex];
		}
			
		return -1;
	}
	
	@SuppressWarnings("unchecked")
	protected void ShowChoices(AjaxRequestTarget target){
				
		String posText = multiAnswerModel.get(0).getAnswer();
		if(posText == null || posText.isEmpty())
			posText = postypeList.get(0);
		int posIndex = postypeList.indexOf(posText);
			
		
		int index = -1;
		
		for(int i = 1; i < boxes.size();i++){//Index O is pos, no need to change it
						
			DropDownChoice<String> dropBox = (DropDownChoice<String>)this.get(boxes.get(i));
		
			index = GetListIndex(posIndex, dropBox.getId());
			if(index > 0){
				dropBox.setChoices(optionsList.get(index));
			}
			
			dropBox.setVisible(index > 0);
	
			target.add(dropBox);	       		
		}
		String textVar5 = "";
		index = GetListIndex(posIndex, lblVar.getId());	 
		if(index >= 0)
			textVar5 = labelList.get(index);
		multiAnswerModel.get(indexLbl).setAnswer(textVar5);
		lblVar.setVisible(index >= 0);
		Vartf.setVisible(index >= 0);
		target.add(lblVar);
		target.add(Vartf);
		target.add(this);
	}
	
	public Boolean Validate(){//override since parsers do not use question field
		String Answer;
		for ( int i = 0; i < boxes.size();i++){
			AnswerModel md = multiAnswerModel.get(i);
			Answer = md.getAnswer();
			@SuppressWarnings("unchecked")
			
			DropDownChoice<String> dropBox = (DropDownChoice<String>)this.get(boxes.get(i));
			if(dropBox.isVisible() && !Answer.equals("Invariable") && Answer.equals(dropBox.getChoices().get(0))){//default choice - no good except Invariable
				Session.get().error("Selection (" + Answer + ") in the choice #" + (i+1) + " is a default value and can not be applied to the selection. Choose a proper option.");
				return false;
			}
		}
		return true;
	}
}
	
