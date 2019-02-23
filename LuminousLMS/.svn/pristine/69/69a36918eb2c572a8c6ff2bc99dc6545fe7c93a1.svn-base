package com.linguaclassica.shared;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.markup.html.form.CheckBoxMultipleChoice;
import org.apache.wicket.model.Model;

@SuppressWarnings("rawtypes")
public class SelectionMultiCheck<T> extends CheckBoxMultipleChoice {

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	List<String> selection;
	ArrayList<String> selectedChoices;

	static List<String> availableChoices;

	public SelectionMultiCheck(List<String> userStatusList) {
		
		super("classcheckboxes");

		setSuffix(" "); // sets checkbox separator and ensures inline display
		SetChoices (userStatusList);
		Initialize();
		setSelection(null);
	}

	@SuppressWarnings("unchecked")
	protected void Initialize()
	{	 
		// Stores these selected Choices' names in 'selectedChoices'
		
		selectedChoices = new ArrayList<String>();
		if (selection != null && selection.size() != 0) {
			for (String item : selection) {
				selectedChoices.add(item);
			}
			// If the user has selected all of his/her available Choices, then
			// 'All' option is automatically selected.
			if (selection.size() == availableChoices.size() && availableChoices.size() > 1) {
				selectedChoices.add("All");
			}
		}
		else{
			selection = new ArrayList<String>();
		}
		
		setModel(new Model<ArrayList<String>>(selectedChoices));
		setOutputMarkupId(true);
	}
	
	@SuppressWarnings("unchecked")
	public void setSelectionMultiChecks(){
		
		// Adds 'All' option to select all available Choices for user if user
		// has more than one class			
		if (availableChoices.size() > 1) {
			availableChoices.add("All");
		}
		setChoices(availableChoices);
		
	}
	
	public List<String> getSelection(){
		return selection;
	}
	
	public ArrayList<String> getSelectedChoices(){
		return selectedChoices;
	}

	public Boolean noSelection(){
		return (selectedChoices == null || selectedChoices.isEmpty());
	}
	
	public void clearSelection(){
		if(selectedChoices != null)
			selectedChoices.clear();
		if(selection != null)
			selection.clear();
	}
	
	public void ResetChoices(){
		System.out.println("Go button clicked, ChoicesSelected = " + selectedChoices.toString());

		// Iterates through user's checkbox selections and adds each
		// selection to 'selection'
		// Works if no Choices are selected
					
		if (selectedChoices.contains("All")) {
			if(selectedChoices.size() < getChoices().size() && selectedChoices.size()> 1 
					&& selection.contains("All")){//was previously Select All
				selectedChoices.remove("All");//toggle
				selection.clear();
				for (String str : selectedChoices) {
						selection.add(str);
					
				}
			}
			else{
				selection.addAll(availableChoices);				
				selectedChoices.addAll(availableChoices);		
			}
		}
		else if (!selectedChoices.isEmpty()) {
			
			if(selectedChoices.size() == getChoices().size()- 1){//was Select All
				if(selection.contains("All")){
					selection.clear();
					selectedChoices.clear();//toggle
				}
				else{
					selection.clear();
					selection.addAll(availableChoices);
					
					selectedChoices.addAll(availableChoices);	
				
				}
			}
			else{
				selection.clear();
			}
			if(selection.isEmpty()){
				selection.clear();
				for (String str : selectedChoices) {
					if(!str.equals("All")){
						selection.add(str);
					}
				}
			}
		}
		else if (selectedChoices.isEmpty()) {
			selection.clear();
		}
				
	}
	
	public void SetChoices(List<String> userStatusList){
//		String userType = userService.getUserPermissionString(userService.findUserById(userId).getId()).toUpperCase();
		// List of all Choices associated with user
		availableChoices = new ArrayList<String>();
		for (String str : userStatusList) {
			availableChoices.add(str);
		
		}
		
		setSelectionMultiChecks();
	}
	
	public  void setSelection(List<String> listSelection){
		
		if(listSelection == null || listSelection.isEmpty()){
			
			selection.add((String)getChoices().get(0));//default if no selection
			selectedChoices.add((String)getChoices().get(0));//default if no selection	
		}
		else{
			for (String str : listSelection) {
				selection.add(str);		
				selectedChoices.add(str);		
			}
		}

	}
	
	public void setSelectionIndex(List<Integer> selectionIndex){
		if(availableChoices != null && !availableChoices.isEmpty()){	
			for (Integer index : selectionIndex) {
				selection.add(availableChoices.get(index));	
				selectedChoices.add(availableChoices.get(index));	
			}
		}
	}
}

