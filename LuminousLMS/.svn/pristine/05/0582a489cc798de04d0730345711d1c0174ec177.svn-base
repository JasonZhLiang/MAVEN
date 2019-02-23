package com.linguaclassica.shared;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormChoiceComponentUpdatingBehavior;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.model.PropertyModel;
import com.linguaclassica.shared.PrivateBasePage;
import com.linguaclassica.shared.SelectionMultiCheck;
/**
 * 
 * @author Dmitry Berger
 * SharedSelectionRangePage is a shared common page that displays and allows to change check box choices and time ranges
 *
 */
public class SharedSelectionRangePage extends PrivateBasePage
{
	private static final long serialVersionUID = 1L;
	
	protected String containerName;
	SelectionMultiCheck<String> checkboxes;
	static List<String> dropboxes = Arrays.asList(new String[] {"fromYear", "fromMonth", "toYear", "toMonth"});
	static List<String> timeHolders = Arrays.asList(new String[] {"indexYearFrom", "indexMonthFrom", "indexYearTo", "indexMonthTo"});
	static int startYearCount = 2017;//year the database was created
	static int yearsAhead = 30;//current date
	
	static List<Integer> listMonths = Arrays.asList(new Integer[] {1, 2, 3, 4, 5, 6,7,8,9,10,11,12});

	Integer indexYearFrom = startYearCount;
	Integer indexMonthFrom = 1;
	Integer indexYearTo = startYearCount;
	Integer indexMonthTo = 1;
	
	
	private Logger logger = LogManager.getLogger(SharedSelectionRangePage.class);
			
	private final static List<String> userStatusList = Arrays.asList(new String[] {
			"Active", "Temporary Inactive", "Archived"});
	protected List<String> checkselectionList = null;

	public SharedSelectionRangePage(String containerName, List<String> checkselectionList)
	{
		super();
		if(checkselectionList != null){
			this.checkselectionList = checkselectionList;
		}
		else{
			this.checkselectionList = userStatusList;
		}
		
		
		this.containerName = containerName;
		logger.debug("SharedSelectionRangePage");
		////////////////////
		SetCheckBoxes();
		
		SetContainer();
		
		
		SetDropBoxes();
		
		
	}
	
	/**SetContainer function must be used for initializing wicket containers in derived classes
	  **/
	
	protected void SetContainer() {
	}
	
	protected void SetDropBoxes(){
		
		ArrayList<Integer> yearsArrey = new ArrayList<Integer>();
		int length = indexYearFrom - indexYearFrom;
		if(length < 2)
			length = 2;
		for( int j = 0; j < length; j++){
			yearsArrey.add(j, indexYearFrom + j);
		}
		List<Integer> listYears = yearsArrey;
		for (int i= 0; i < dropboxes.size(); i++){	
			DropDownChoice<Integer> dropBox;
			String name = timeHolders.get(i);
			if((i%2)==0){//even
				dropBox = new DropDownChoice<Integer>( dropboxes.get(i), 
						new PropertyModel<Integer>(this,name), listYears);
			}else{//odd
				dropBox = new DropDownChoice<Integer>( dropboxes.get(i), 
						new PropertyModel<Integer>(this,name), listMonths);
			
			}
			dropBox.add(new AjaxFormComponentUpdatingBehavior("change") {
				private static final long serialVersionUID = 1L;
						
				@Override
		        protected void onUpdate(AjaxRequestTarget target) {
				
					for (int i= 0; i < dropboxes.size(); i++){
						if(dropboxes.get(i).equals(dropBox.getId())){
								
							int yearDifference = indexYearFrom.compareTo(indexYearTo);

							if(yearDifference > 0){
								indexYearTo = indexYearFrom;
								target.add(target.getPage().get(dropboxes.get(2)));	
							}
							else if(yearDifference == 0 && indexMonthFrom.compareTo(indexMonthTo) > 0){	
									indexMonthTo = indexMonthFrom;
									target.add(target.getPage().get(dropboxes.get(3)));	
							}
	
							break;
						}
						
					}					
					
					target.add(	target.getPage().get("classcheckboxes"));
					target.add(	target.getPage().get(containerName));
				
		        }
		    });
			dropBox.setOutputMarkupId(true);			
			add(dropBox );
		}	
			
	}

	
	public Boolean compareSelection(Timestamp timeFrom){
		
		int checkyear = timeFrom.getYear()+1900;
				
		Boolean show = (checkyear >= indexYearFrom) && (checkyear <= indexYearTo);	
		
		if(show){
			show &= (timeFrom.getMonth() + 1 >= indexMonthFrom)  ||  (timeFrom.getMonth()+ 1 <= indexMonthTo);//reflects the way Timestamp retrieves months						System.out.println("AdminClientsPage - client = " + client.getFirstName()+ " " + client.getLastName() + " year " + timeFrom.getYear()+ " month " + timeFrom.getMonth() );
		}
		return show;
	}
	
	protected Boolean getSelection(int toCompare){
		Boolean match = false;
		List <String> selection = checkboxes.getSelection();
		for(String status:selection){
			match = checkselectionList.get(toCompare).equals(status);
			if(match)
				break;
		}
		return match;
	}
	
	protected void setTimeRange(Timestamp timeStart,Timestamp timeEnd){		
		startYearCount = timeStart.getYear() + 1900;
		indexMonthFrom = timeStart.getMonth() + 1;
		indexYearFrom = startYearCount;
		indexYearTo = timeEnd.getYear()+ 1900;
		indexMonthTo = timeEnd.getMonth() + 1;
	}
	
	private void SetCheckBoxes(){
		checkboxes = new SelectionMultiCheck<String>(checkselectionList); 
		
		//do not put this add AjaxFormChoiceComponentUpdatingBehavior call into Initialize function - it will create a long update loop
		checkboxes.add( new AjaxFormChoiceComponentUpdatingBehavior() {
					private static final long serialVersionUID = 1L;
					
					@Override
					protected void onUpdate(AjaxRequestTarget target) {					
						checkboxes.ResetChoices();
						
						target.add(	target.getPage().get("classcheckboxes"));
						target.add(	target.getPage().get(containerName));
					
					}	
					//to use with setRequired(true)
					@Override
					protected void onError(AjaxRequestTarget target,
					           RuntimeException e){		
						if(e == null){//in case of validation mishap when no boxes are checked
							checkboxes.clearSelection();
							onUpdate(target);	
						}
						
					}	
				
				});

		add(checkboxes.setOutputMarkupId(true));
	}
	
	protected List <String> getSelection(){
		return checkboxes.getSelection();
	}
}

