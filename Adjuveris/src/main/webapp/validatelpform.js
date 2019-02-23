/* To do client side validation of a html page:
 * 
 * Add ths line inside the <head> tag.
 * <script type="text/javascript" src="validatelpform.js"></script>
 * 
 * Add the single or multiple class attribute to each of the <input> tags inside the <form> tag.
 * <input type="text" class="validate_opt" />
 * <input type="text" class="css_opt validate_opt" />
 * 
 * See the note in the notice function.
 * 
 * That's it. The code on this page overrides the onsubmit event for the <form>s on the html page.
 */

var lastInput = "";

// Keep track of the last input that was clicked
function doclick(elementInput)
{
	lastInput = elementInput;
}


// Check if there is any text in the element
function isValidText(elementInput)
{
	if (elementInput.value == "")
	{
		return false;
	}
	return true;
}

// Check for a valid string length.
// There is no check on the contents.
function isValidLength(group, min, max)
{
	if (group.length < min)
	{
		return false;
	}
	if (group.length > max)
	{
		return false;
	}
	return true;
}

// Check for a single letter or a name that starts and ends with a letter.
// First and last names are no longer mandatory so if they are empty, allow it, 
// otherwise, make sure that they follow the same restrictions as before.
// Subject to length restrictions.
function isValidName(name, min, max)
{
	var singleFilter=/^[A-Za-z]$/;
	var multiFilter=/^[A-Za-z][A-Za-z _'-]*[A-Za-z]$/;

	if (name.length < min)
	{
		return false;
	}
	if (name.length > max)
	{
		return false;
	}
	if (!singleFilter.test(name))
	{
		if (!multiFilter.test(name))
		{
			return false;
		}
	}
	return true;
}

// Simple validation of an email address.
function isValidEmail(emailaddr)
{
	// Use
	// var patt=new RegExp(pattern,modifiers);
	// or more simply:
	// var patt=/pattern/modifiers;
	// EmailAddressValidator.java uses /^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*((\\.[A-Za-z]{2,}){1}$)/i;
	var emailFilter = /^[_A-Za-z0-9-]+(\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\.[A-Za-z0-9-]+)*((\.[A-Za-z]{2,}){1}$)/i;

	if (!emailFilter.test(emailaddr))
	{
		return false;
	}
	return true;
}

// Check for valid password: length and characters.
function isValidPassword(passcode)
{
	var passwordFilter=/^[A-Za-z0-9]+$/;

	if (passcode.length < 6)
	{
		return false;
	}
	if (passcode.length > 20)
	{
		return false;
	}
	if (!passwordFilter.test(passcode))
	{
		return false;
	}
	return true;
}

//Check for valid drill word: length and characters.
function isValidDrillWord(drillword)
{
	var wordFilter=/^[A-Za-z]+$/;

	if (drillword.length < 1)
	{
		return false;
	}
	if (drillword.length > 20)
	{
		return false;
	}
	if (!wordFilter.test(drillword))
	{
		return false;
	}
	return true;
}

// Check for valid institutional code
function isValidInstCode(instcode)
{
   var instCodeFilter=/^[A-Za-z0-9]+$/;
   
   if (instcode.length < 4)
   {
	   return false;
   }
   if (instcode.length > 30)
   {
	   return false;
   }
   if (!instCodeFilter.test(instcode))
   {
	   return false;
   }
   return true;
}

//Check for valid institutional name
function isValidInstApplName(instname)
{
   var instNameFilter=/^[A-Za-z0-9\ ]+$/;
   
   if (instname.length < 4)
   {
	   return false;
   }
   if (instname.length > 30)
   {
	   return false;
   }
   if (!instNameFilter.test(instname))
   {
	   return false;
   }
   return true;
}

//Check for valid institutional password
function isValidInstApplIPass(instpassword)
{
   var instPasswordFilter=/^[A-Za-z0-9]+$/;
   
   if (instname.length < 6)
   {
	   return false;
   }
   if (instname.length > 20)
   {
	   return false;
   }
   if (!instPasswordFilter.test(instname))
   {
	   return false;
   }
   return true;
}

// Check for valid institutional administrator first name
// DAC 20October2013: allow '_' in first name
function isValidInstApplFName(instfname)
{
   var instFirstNameFilter=/^[A-Za-z\_]+$/;
   
   if (instfname.length < 2)
   {
	   return false;
   }
   if (instfname.length > 20)
   {
	   return false;
   }
   if (!instFirstNameFilter.test(instfname))
   {
	   return false;
   }
   return true;
}

//Check for valid institutional last name
function isValidInstApplLName(instlname)
{
   var instLastNameFilter=/^[A-Za-z]+$/;
   
   if (instlname.length < 2)
   {
	   return false;
   }
   if (instlname.length > 20)
   {
	   return false;
   }
   if (!instLastNameFilter.test(instlname))
   {
	   return false;
   }
   return true;
}


// Check for valid question format 
function isValid_stringCharLength_3_70_Characters(name)
{    
	
	if (name.length == 0) 
	{
		return false; 
	}
   else if (name.length < 3)
	{
		return false;
	}
   else	if (name.length > 70)
	{
		return false;
	} 
    else 
	    return true;
}



function isValid_stringCharLength_3_120_Characters(name){
	
	if (name.length == 0) 
	{
		return false; 
	}
   else if (name.length < 3)
	{
		return false;
	}
   else	if (name.length > 120)
	{
		return false;
	} 
    else 
	    return true;	
	
} 


// Verify if question has only Alpha Numeric variables with a ? or . at the end  
function isAlphaNumericCharacterQuestion(name)
{
	 var validCharacter=/[A-Za-z0-9][/?$]/;
	 
	 if (!validCharacter.test(name)){ 	
		    return false;    
	    } else 
	    	
	    	return true;    
} 

function isAlphaNumericCharacterPeriod(name)
{
	 var validCharacter=/[A-Za-z0-9][/.$]/;
	 
	 if (!validCharacter.test(name)){ 	
		    return false;    
	    } else 
	    	
	    	return true;    
} 

// Verify Invalid Special characters 
function isNotAlphaNumericCharacters(name)
{
	 var invalidCharacter=/[*@#%^&${}~|<>\]\[\\\/]/;
	 
	 if (invalidCharacter.test(name)){ 	
		    return false;    
	    } else 
	    	
	    	return true;    
}


//Check for a single letter or number or a class name that starts and ends with a letter or number.
//Subject to length restrictions.
function isValidClassName(name, min, max)
{
	var singleFilter=/^[A-Za-z0-9]$/;
	var multiFilter=/^[A-Za-z0-9][A-Za-z0-9 -]*[A-Za-z0-9]$/;

	if (name.length < min)
	{
		return false;
	}
	if (name.length > max)
	{
		return false;
	}
	if (!singleFilter.test(name))
	{
		if (!multiFilter.test(name))
		{
			return false;
		}
	}
	return true;
}


// Display the validation failure message.
function notice(message)
{
	// One choice is the pop-up message box.
	alert(message);
	
	/* The other choice is to display the error on the page. It needs this control on the page.
	 * <p id="client_feedback" style="color:red"></p>
	 */
	var control = document.getElementById("client_feedback");
	if (control != null)
	{
		control.innerHTML = message;
	}
}

// Validate the passed form.
function validateForm(currentForm)
{
	var bValid = true;
	var bCreateType = false;
	var bSubscriptionLen = false;
	var elementsInputs;
	var sMultiple = "";
	var lastbadInput = null;

	elementsInputs = currentForm.getElementsByTagName("input");
	
	for (var intCounter = 0; intCounter < elementsInputs.length; intCounter++)
	{
		var elementInput = elementsInputs[intCounter];
		var sClassName = elementInput.className;
		var elementValue = elementInput.value;
		
		// Check for a class name and validate the text from that.
		if (sClassName.match("reqfirst"))
		{
			if (!isValidName(elementValue, 1, 35))
			{
				notice('Please enter a valid First Name (alphabetical characters only).');
				bValid = false;
			}
		}
		else if (sClassName.match("reqlast"))
		{
			if (!isValidName(elementValue, 1, 35))
			{
				notice('Please enter a valid Last Name (alphabetical characters only).');
				bValid = false;
			}
		}
		else if (sClassName.match("reqemail"))
		{
			if (!isValidEmail(elementValue))
			{
				notice('Please insert a VALID Email Address');
				bValid = false;
			}
		}
		else if (sClassName.match("reqcemail"))
		{
			if (!isValidEmail(elementValue))
			{
				notice("'Confirm Email Address' must match 'Email Address'");
				bValid = false;
			}
		}
		else if (sClassName.match("reqpassword"))
		{
			if (!isValidPassword(elementValue))
			{
				notice('Please insert a valid Password, between 6 and 20 alphanumeric characters.');
				bValid = false;
			}
		}
		else if (sClassName.match("reqcpassword"))
		{
			if (!isValidPassword(elementValue))
			{
				notice("'Confirm Password' must match 'Password'.");
				bValid = false;
			}
		}
		else if (sClassName.match("maybepassword"))
		{
			if (isValidText(elementInput))
			{
				// There is text, check the password
				if (!isValidPassword(elementValue))
				{
					notice('Please insert a valid Password');
					bValid = false;
				}
			}
		}
		else if (sClassName.match("maybecpassword"))
		{
			if (isValidText(elementInput))
			{
				// There is text, check the password
				if (!isValidPassword(elementValue))
				{
					notice("'Confirm Password' must match 'Password'.");
					bValid = false;
				}
			}
		}
		else if (sClassName.match("reqdrillword"))
		{
			// There may be text, check the drill word
			if (!isValidDrillWord(elementValue))
			{
				if (sMultiple.length > 0)
				{
					sMultiple = sMultiple + "\n";
				}
				sMultiple = sMultiple + "Input '" + elementInput.id + "'does not contain a word.";
				lastbadInput = elementInput;
			}
		}
		else if (sClassName.match("maybedrillword"))
		{
			if (isValidText(elementInput))
			{
				// There is text, check the drill word
				if (!isValidDrillWord(elementValue))
				{
					if (sMultiple.length > 0)
					{
						sMultiple = sMultiple + "\n";
					}
					sMultiple = sMultiple + "Input '" + elementInput.id + "'does not contain a word.";
					lastbadInput = elementInput;
				}
			}
		}
		else if (sClassName.match(/reqinstapplcode/))  // Institutional Admin Page, check institutional code
	    {
			if(!isValidInstCode(elementValue))
			{
				notice("Please insert a valid Institution Code.\n between 4 and 30 chararcters\nvalid characters [A-Za-z0-9]");
				
				bValid = false;
			}
	    }
		else if (sClassName.match(/reqinstapplname/))  // Institutional Admin Page, check institutional name
	    {
			if(!isValidInstApplName(elementValue))
			{
				notice("Please insert a valid Institution Name.\n Please insert between 4 and 30 alphanumeric characters.");
				
				bValid = false;
			}
	    }
		else if (sClassName.match(/reqinstapplipass/))  // Institutional Admin Page, check institutional password
	    {
			if (isValidText(elementInput))
			{
				// There is text, check the password
				if (!isValidPassword(elementValue))
				{
					notice('Please insert a valid Password');
					bValid = false;
				}
			}
			else { 	
				notice('Please insert a valid Password');	// no password entered
				bValid = false;
			}
	    }
		else if (sClassName.match(/reqinstapplfname/))  // Institutional Admin Page, check institutional first name
	    {
			if(!isValidInstApplFName(elementValue))
			{
				notice("Please insert a valid institutional administrator first name.\n Insert between 2 and 20 alphabetic characters and _.");
				
				bValid = false;
			}
	    }
		else if (sClassName.match(/reqinstappllname/))  // Institutional Admin Page, check institutional last name
	    {
			if(!isValidInstApplLName(elementValue))
			{
				notice("Please insert a valid institutional administrator last name.\n Insert between 2 and 20 alphabetic characters.");
				
				bValid = false;
			}
	    }
		else if (sClassName.match(/reqinstapplemail/))  // Institutional Admin Page, check institutional email address
	    {
			if(!isValidEmail(elementValue))
			{
				notice("Please insert a valid institutional administrator email address.");
				
				bValid = false;
			}
	    }
		else if (sClassName.match(/reqinstapplapass/))  //  Institutional Admin Page, check instn administrator password 
	    {
			if (isValidText(elementInput))
			{
				// There is text, check the password
				if (!isValidPassword(elementValue))
				{
					notice('Please insert a valid administrator password');
					bValid = false;
				}
			}
			else {   									// no password entered
				notice('Please insert a valid administrator password');
				bValid = false;				
			}
	    }
		else if (sClassName.match(/reqinstadmpass/))  //  Institutional Admin Page, check general administrator password 
	    {
			if (isValidText(elementInput))
			{
				// There is text, check the password
				if (!isValidPassword(elementValue))
				{
					notice('Please insert a valid administrator password');
					bValid = false;
				}
			}
			else {   									// no password entered
				notice('Please insert a valid administrator password');
				bValid = false;				
			}
	    }
		else if (sClassName.match(/reqinstadmemail/))  //  Institutional Admin Page, check general administrator email 
	    {
			if(!isValidEmail(elementValue))
			{
				notice("Please insert a valid administrator email address.");
				
				bValid = false;
			}
	    }
		else if (sClassName.match("vclassname"))
		{
			if (!isValidClassName(elementValue, 1, 20))
			{
				notice('Please insert a valid Class name or Class code');
				
				bValid = false;
			}
		}
		else if ((lastInput.id == "creategroup") && (elementInput.id == "creategroup"))
		{
			// Check the specified inputs
			var elementGN = document.getElementById("gName");
			var elementGNValue = elementGN.value;
			if (!isValidLength(elementGNValue, 4, 25))
			{
				notice('Please insert a valid Group Name (between 4 and 25 alphanumeric characters).');

				// Highlight the input with invalid data.
				elementGN.focus();
				elementGN.select();
				return false;
			}
			var elementGD = document.getElementById("gDesc");
			var elementGDValue = elementGD.value;
			if (!isValidLength(elementGDValue, 0, 25))
			{
				notice('Please insert a valid Group description: no more than 25 characters');

				// Highlight the input with invalid data.
				elementGD.focus();
				elementGD.select();
				return false;
			}
		}
		else if ((elementInput.name == "gType") && (elementInput.type == "radio") && elementInput.checked)
		{
			// One of the Group Type radio buttons is checked
			bCreateType = true;
		}
		else if ((elementInput.name == "subsblock") && (elementInput.type == "radio") && elementInput.checked)
		{
			// One of the Subscription Length radio buttons is checked
			bSubscriptionLen = true;
		}
		
		

/****************************************************************************
* 
* Function Code for Client side validation True False Questions 
* 
* Return is either true of false
****************************************************************************/
	
		
	    if (sClassName.match("request_question")) {
	    	if (!isNotAlphaNumericCharacters(elementValue)) {
				  notice('Use of special charaters are not allowed.');
				  bValid = false;
			  } /*excessive validation at this point - limits user to the choices that not necessary	
	    	else if (!isValid_stringCharLength_3_70_Characters(elementValue)) {
				notice('Please enter a question with 3 to 70 characters long.');
				bValid = false;
			}	  
			else if ((!isAlphaNumericCharacterQuestion(elementValue)) && (!isAlphaNumericCharacterPeriod(elementValue))) {			 
				notice('Use valid Alphanumeric charaters \n with a "?" or "." the end.');
				bValid = false;
			} */
		
	  }  // End of function code for Client side validation True False Questions  	     

   /****************************************************************************
    * 
    * Function Code for Client side validation Multiple Choice Questions
    * 
    * Return is either true of  false
    ****************************************************************************/
		
	    if (sClassName.match("multipleChoice_question")) {
	 	    	
	    	if (!isNotAlphaNumericCharacters(elementValue)) {
				  notice('Use of special charaters are not allowed in the Question Box.');
				  bValid = false;
			  } 
	    	/*excessive validation at this point - limits user to the choices that not necessary	
	    	else  if (!isValid_stringCharLength_3_120_Characters(elementValue)) {
	 				notice('Questions must be 3 to 120 characters long.');
	 				bValid = false;
	 				
	 		} 		
	 		else if ((!isAlphaNumericCharacterQuestion(elementValue)) && (!isAlphaNumericCharacterPeriod(elementValue))) {			 
	 				notice('Question must have a valid Alphanumeric charaters \n with a "?" or "." the end.');
	 				bValid = false;
	 		} 	*/
	    	
	  
	    
	    } // End of function code for Client side validation Multiple Choice Questions     
	    
	    
	    /****************************************************************************
	     * 
	     * Function Code for Client side validation Multiple Choice Answers
	     * 
	     * Return is either true of  false
	     ****************************************************************************/
		
	 		   if (sClassName.match("multipleChoice_answer")) {
	 	 	    	
	 	 			/* Redundant - validation happens in Java code
	 	 			 if (!isValid_stringCharLength_3_70_Characters(elementValue)) {
	 	 				notice('Answers must be 3 to 70 characters long.');
	 	 				bValid = false;
	 	 				
	 	 			} */

	 	 	  if (!isNotAlphaNumericCharacters(elementValue)) {
	 	 					  notice('Use of special charaters are not allowed in the Answer Box.');
	 	 					  bValid = false;
	 	 				  } 
	 		
	 		 } // End of function code for Client side validation Multiple Choice Answers
	    
	    
		if (!bValid)
		{
			// Highlight the input with invalid data.
			elementInput.focus();
			elementInput.select();
			return false;
		}
	}
	if (sMultiple.length > 0)
	{
		// Show the error(s).
		notice(sMultiple);

		// Highlight the last input with invalid data.
		lastbadInput.focus();
		lastbadInput.select();
		return false;
	}

	if ((lastInput.id == "creategroup") && !bCreateType)
	{
		// No Group Type radio button selected for Create Group
		// Do not set focus
		notice("Please select one of the Group type radio buttons");
		return false;
	}
	if ((lastInput.id == "subscribing") && !bSubscriptionLen)
	{
		// No Subscription Length radio button selected for Subscription
		// Do not set focus
		notice("Please select one of the Subscription length radio buttons");
		return false;
	}
	return true;
}

// End of ---> function validateForm(currentForm) 

/*
// Validate all of the forms on the page.
// Called directly from an onsubmit attribute.
function validateFormsNow()
{
	alert('validateFormsNow');
	if (document.getElementsByTagName)
	{
		var elementsForms = document.getElementsByTagName("form");
		for (var intCounter = 0; intCounter < elementsForms.length; intCounter++)
		{
			if (!validateFormNow(elementsForms[intCounter]))
			{
				return false;
			}
		}
	}
	else
	{
		return false;
	}
	return true;
}
*/

// Initialise the onsubmit attribute for each of the <form>s on the page.
//Initialise the onclick attribute for each of the <input>s on the page.
function initialise()
{
	var elementsForms = document.getElementsByTagName("form");
	for (var intCounter = 0; intCounter < elementsForms.length; intCounter++)
	{
		elementsForms[intCounter].onsubmit = function ()
		{
			return validateForm(this);
		};
	}
	
	var elementsInputs = document.getElementsByTagName("input");
	for (var inputCounter = 0; inputCounter < elementsInputs.length; inputCounter++)
	{
		elementsInputs[inputCounter].onclick = function ()
		{
			return doclick(this);
		};
	}
}

// Called when the page loads.
window.onload = initialise;
