/**
 * Lingua Classica Table Sizer
 * author: Joe A. Brown
 * 2017-02-10
 * 
 * Description:
 * Adjust the size of the page contents (on the right) or the menu (on the left)
 * so that the left and right sides are the same height.
 * Use when the right-hand contents are of fixed size.
 * The script will run automatically when the page loads.
 * 
 * Instructions:
 * Add to the <head> section of a page that inherits a base page:
 * <wicket:head>
 * <script src="LCFixedSizer.js"></script>
 * </wicket:head>
 * 
 * To validate the form 
 * Add 'onsubmit="return validateInputs(this)"' to the <form> tag
 * Add 'class="symbol"' to an <input> tag where "symbol" is one of the strings below
 * eg. <input type="text" wicket:id="firstname" class="reqfirst" />
 * 
 */


// Check if there is any text in the element
function isValidText(elementInput)
{
	if (elementInput.value == "")
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

//Check for valid password: length and characters.
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
function validateInputs(currentForm)
{
	var bValid = true;
	var elementsInputs;

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
		else if (sClassName.match("vclassname"))
		{
			if (!isValidClassName(elementValue, 1, 20))
			{
				notice('Please insert a valid Class name or Class code');
				
				bValid = false;
			}
		}
		if (!bValid)
		{
			// Highlight the input with invalid data.
			elementInput.focus();
			elementInput.select();
			return false;
		}
	}
	return true;
}

/*
 * Call this function when the page has been loaded
 */
function pageLoaded()
{
	var leftnav = document.getElementById("leftnav");
	var rightbox = document.getElementById("page");
	if ((leftnav != null) && (rightbox != null))
	{
		if (leftnav.scrollHeight < rightbox.scrollHeight)
		{
			if (navmenu.scrollHeight < leftnav.scrollHeight)
			{
				// Increase menu size
				// padding 10 + 10 = 20
				delta = rightbox.scrollHeight - leftnav.scrollHeight;
				navmenu.style.height = navmenu.scrollHeight + delta + "px";
			}
		}
		else if (leftnav.scrollHeight > rightbox.scrollHeight)
		{
			// Increase page size
			// padding 20 + 20 = 40
			//border 1 + 1 = 2
			rightbox.style.height = leftnav.scrollHeight - 40 - 2 + "px";
		}
	}
}

//Called when the page loads.
window.onload = pageLoaded;
