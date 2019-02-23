/**
 * Lingua Classica Table Sizer
 * author: Joe A. Brown
 * 2017-05-16
 * 
 * Description:
 * Adjust the size of the page contents (on the right) and/or the menu (on the left)
 * so that the left and right sides are the same height.
 * Use when there is a table on the right-hand side.
 * The script will run automatically when the page loads.
 * Sizes will be adjusted when the page opens and when the window size changes.
 * 
 * Instructions:
 * 
 * [1]
 * Add to the <head> section of a page that inherits a base page:
 * <wicket:head>
 * <script src="LCFTableSizer.js"></script>
 * </wicket:head>
 * 
 * [2]
 * Surround the table with a div
 * <div class="maxxet">
 * <table>...</table>
 * </div>
 * 
 * [3]
 * In the css file
 * div.maxxet
 * {
 *  min-height: 210px;
 * }
 * Typical table 8 lines of 23px plus header
 * 
 * [4]
 * A different solution is required when there are more than one table 
 * and when a form requires validation.
 */

var sizeable = false;

/*
 * Internal function to set the size
 */
function trySizing()
{
	if (sizeable == true)
	{
		var leftnav = document.getElementById("leftnav");
		var rightbox = document.getElementById("page");
		var maxdivs = document.getElementsByClassName("maxxet");
		if (leftnav.scrollHeight < rightbox.scrollHeight)
		{
			if (navmenu.scrollHeight < leftnav.scrollHeight)
			{
				// wrapper margin = 20
				// padding 10 + 10 = 20
				if ((rightbox.scrollHeight + 20) < window.innerHeight)
				{
					// Fits on the window, increase menu size
					mexpand = rightbox.scrollHeight - leftnav.scrollHeight;
					navmenu.style.height = navmenu.scrollHeight + mexpand + "px";
				}
				else
				{
					// Increase menu size to fit window
					mexpand = window.innerHeight - leftnav.scrollHeight - 20;
					if (mexpand > 0)
					{
						navmenu.style.height = navmenu.scrollHeight + mexpand + "px";
					}
					
					// Reduce page size to fit
					// min-height overrides height
					shrink = rightbox.scrollHeight + 20 - window.innerHeight;
					if (mexpand < 0)
					{
						shrink += mexpand;
					}
					var flexdiv = maxdivs[0];
					flexdiv.style.height = flexdiv.scrollHeight - shrink + "px";
				}
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

function initialSizing(leftnav, rightbox, navmenu, flexdiv)
{
	if (leftnav.scrollHeight < rightbox.scrollHeight)
	{
		if (navmenu.scrollHeight < leftnav.scrollHeight)
		{
			// wrapper margin = 20
			// padding 10 + 10 = 20
			if ((rightbox.scrollHeight + 20) < window.innerHeight)
			{
				// Fits on the window, increase menu size
				mexpand = rightbox.scrollHeight - leftnav.scrollHeight;
				navmenu.style.height = navmenu.scrollHeight + mexpand + "px";
			}
			else
			{
				// Increase menu size to fit window
				mexpand = window.innerHeight - leftnav.scrollHeight - 20;
				if (mexpand > 0)
				{
					navmenu.style.height = navmenu.scrollHeight + mexpand + "px";
				}
				
				// Reduce page size to fit
				// min-height overrides height
				shrink = rightbox.scrollHeight + 20 - window.innerHeight;
				if (mexpand < 0)
				{
					shrink += mexpand;
				}
				flexdiv.style.height = flexdiv.scrollHeight - shrink + "px";
			}
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

/*
 * Call this function when the page has been loaded
 */
function pageLoaded()
{
	getVideoId();
	var leftnav = document.getElementById("leftnav");
	var rightbox = document.getElementById("page");
	if ((leftnav != null) && (rightbox != null))
	{
		var navmenu = document.getElementById("navmenu");
		var maxdivs = document.getElementsByClassName("maxxet");
		if (maxdivs.length > 1)
		{
			console.log("multiple maxxet divs");
		}
		else if (maxdivs.length == 1)
		{
			sizeable = true;
			initialSizing(leftnav, rightbox, navmenu, maxdivs[0]);
		}
		else
		{
			console.log("no maxxet divs");
		}
		/*
		if (leftnav.scrollHeight < rightbox.scrollHeight)
		{
			if (navmenu.scrollHeight < leftnav.scrollHeight)
			{
				// wrapper margin = 20
				// padding 10 + 10 = 20
				if ((rightbox.scrollHeight + 20) < window.innerHeight)
				{
					// Fits on the window, increase menu size
					mexpand = rightbox.scrollHeight - leftnav.scrollHeight;
					navmenu.style.height = navmenu.scrollHeight + mexpand + "px";
				}
				else
				{
					// Increase menu size to fit window
					mexpand = window.innerHeight - leftnav.scrollHeight - 20;
					if (mexpand > 0)
					{
						navmenu.style.height = navmenu.scrollHeight + mexpand + "px";
					}
					
					// Reduce page size to fit
					// min-height overrides height
					shrink = rightbox.scrollHeight + 20 - window.innerHeight;
					if (mexpand < 0)
					{
						shrink += mexpand;
					}
					var flexdiv = maxdivs[0];
					flexdiv.style.height = flexdiv.scrollHeight - shrink + "px";
				}
			}
		}
		else if (leftnav.scrollHeight > rightbox.scrollHeight)
		{
			// Increase page size
			// padding 20 + 20 = 40
			//border 1 + 1 = 2
			rightbox.style.height = leftnav.scrollHeight - 40 - 2 + "px";
		}
		*/
	}
}

/*
 * Call this function when the page size changes
 */
function tableSizer()
{
	console.log("inner height is now " + window.innerHeight);
	trySizing();
}
/*
//Initialise the onsubmit attribute for each of the <form>s on the page.
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
}
*/
//Called when the page loads.
window.onload = pageLoaded;
//window.onresize = tableSizer;

function getVideoId() {
	console.log("got to VideoGet");
	var videoid = null;
	console.log("location = " + location);
	var urlquery = location.href.split("?");
	console.log("urlquery = " + urlquery);
	var videoidIn = urlquery[1];
	console.log("videoidIn = " + videoidIn);
	if (videoidIn != null) {
		var paramArr = new Array;
		paramArr =	videoidIn.split("=");
		videoid = paramArr[1];
		console.log("videoid = " + videoid);
	}
	return;
}