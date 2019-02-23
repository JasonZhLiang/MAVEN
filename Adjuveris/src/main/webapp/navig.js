/* Student Landing Page */

function displayAssign() {
	var assign = document.getElementById('assignments');
	var practice = document.getElementById('practice');
	var results = document.getElementById('results');
	var profic = document.getElementById('profic');
	var help = document.getElementById('help');
	assign.style.visibility = 'visible';
	practice.style.visibility = 'hidden';
	results.style.visibility = 'hidden';
	profic.style.visibility = 'hidden';
	help.style.visibility = 'hidden';	 
}

function displayPractice() {
	var assign = document.getElementById('assignments');
	var practice = document.getElementById('practice');
	var results = document.getElementById('results');
	var profic = document.getElementById('profic');
	var help = document.getElementById('help');
	assign.style.visibility = 'hidden';
	practice.style.visibility = 'visible'; 
	results.style.visibility = 'hidden';
	profic.style.visibility = 'hidden';
	help.style.visibility = 'hidden';
}

function displayResults() {
	var assign = document.getElementById('assignments');
	var practice = document.getElementById('practice');
	var results = document.getElementById('results');
	var profic = document.getElementById('profic');
	var help = document.getElementById('help');
	assign.style.visibility = 'hidden';
	practice.style.visibility = 'hidden'; 
	results.style.visibility = 'visible';
	profic.style.visibility = 'hidden';
	help.style.visibility = 'hidden';
	help.style.visibility = 'hidden';
}

function displayProfic() {
	var assign = document.getElementById('assignments');
	var practice = document.getElementById('practice');
	var results = document.getElementById('results');
	var profic = document.getElementById('profic');
	var help = document.getElementById('help');
	assign.style.visibility = 'hidden';
	practice.style.visibility = 'hidden'; 
	results.style.visibility = 'hidden';
	profic.style.visibility = 'visible';
	help.style.visibility = 'hidden';
}

function displayHelp() {
	var assign = document.getElementById('assignments');
	var practice = document.getElementById('practice');
	var results = document.getElementById('results');
	var profic = document.getElementById('profic');
	var help = document.getElementById('help');
	assign.style.visibility = 'hidden';
	practice.style.visibility = 'hidden'; 
	results.style.visibility = 'hidden';
	profic.style.visibility = 'hidden';
	help.style.visibility = 'visible';
}

/* Teacher Landing Page */

function clear() {
	var notif = document.getElementById('notifications');
	var exercise = document.getElementById('exercises');
	var tassigns = document.getElementById('tassignments');
	var tgroups = document.getElementById('tgroups');
	var thelp = document.getElementById('thelp');
	notif.style.visibility = 'hidden';
	exercise.style.visibility = 'hidden';
	tassigns.style.visibility = 'hidden';
	tgroups.style.visibility = 'hidden';
	//thelp.style.visibility = 'visible';	 
}

function displayNotif() {
	var notif = document.getElementById('notifications');
	var exercise = document.getElementById('exercises');
	var tassigns = document.getElementById('tassignments');
	var tgroups = document.getElementById('tgroups');
	var thelp = document.getElementById('thelp');
	var thome = document.getElementById('thome');
	notif.style.visibility = 'visible';
	exercise.style.visibility = 'hidden';
	tassigns.style.visibility = 'hidden';
	tgroups.style.visibility = 'hidden';
	thelp.style.visibility = 'hidden';
	thome.style.visibility = 'hidden';	 
}

function displayExer() {
	var notif = document.getElementById('notifications');
	var exercise = document.getElementById('exercises');
	var tassigns = document.getElementById('tassignments');
	var tgroups = document.getElementById('tgroups');
	var thelp = document.getElementById('thelp');
	notif.style.visibility = 'hidden';
	exercise.style.visibility = 'visible';
	tassigns.style.visibility = 'hidden';
	tgroups.style.visibility = 'hidden';
	thelp.style.visibility = 'hidden';	 
}

function displayTAssign() {
	var notif = document.getElementById('notifications');
	var exercise = document.getElementById('exercises');
	var tassigns = document.getElementById('tassignments');
	var tgroups = document.getElementById('tgroups');
	var thelp = document.getElementById('thelp');
	notif.style.visibility = 'hidden';
	exercise.style.visibility = 'hidden';
	tassigns.style.visibility = 'visible';
	tgroups.style.visibility = 'hidden';
	thelp.style.visibility = 'hidden';	 
}

function displayGroup() {
	var notif = document.getElementById('notifications');
	var exercise = document.getElementById('exercises');
	var tassigns = document.getElementById('tassignments');
	var tgroups = document.getElementById('tgroups');
	var thelp = document.getElementById('thelp');
	notif.style.visibility = 'hidden';
	exercise.style.visibility = 'hidden';
	tassigns.style.visibility = 'hidden';
	tgroups.style.visibility = 'visible';
	thelp.style.visibility = 'hidden';	 
}

/* Inst admin Landing Page */


var overview = null;
var acadterms = null;
var people = null;
var iaresults = null;
var iaprofic = null;
var adminacct = null;

function displayOverview() {
	overview = document.getElementById('overview');
	acadterms = document.getElementById('acadterms');
	people = document.getElementById('people');
	iaresults = document.getElementById('iaresults');
	iaprofic = document.getElementById('iaprofic');
	adminacct = document.getElementById('adminacct');
	overview.style.visibility = 'visible';
	acadterms.style.visibility = 'hidden';
	people.style.visibility = 'hidden';
	iaresults.style.visibility = 'hidden';
	iaprofic.style.visibility = 'hidden';
	adminacct.style.visibility = 'hidden';
}

function displayAcadterms() {
	overview = document.getElementById('overview');
	acadterms = document.getElementById('acadterms');
	people = document.getElementById('people');
	iaresults = document.getElementById('iaresults');
	iaprofic = document.getElementById('iaprofic');
	adminacct = document.getElementById('adminacct');
	//overview.style.visibility = 'hidden';
	acadterms.style.visibility = 'visible';
	people.style.visibility = 'hidden';
	iaresults.style.visibility = 'hidden';
	iaprofic.style.visibility = 'hidden';
	adminacct.style.visibility = 'hidden';	 
}

function displayPeople() {
	overview = document.getElementById('overview');
	acadterms = document.getElementById('acadterms');
	people = document.getElementById('people');
	iaresults = document.getElementById('iaresults');
	iaprofic = document.getElementById('iaprofic');
	adminacct = document.getElementById('adminacct');
	//overview.style.visibility = 'hidden';
	acadterms.style.visibility = 'hidden';
	people.style.visibility = 'visible';
	iaresults.style.visibility = 'hidden';
	iaprofic.style.visibility = 'hidden';
	adminacct.style.visibility = 'hidden';	 
}

function displayIaresults() {
	overview = document.getElementById('overview');
	acadterms = document.getElementById('acadterms');
	people = document.getElementById('people');
	iaresults = document.getElementById('iaresults');
	iaprofic = document.getElementById('iaprofic');
	adminacct = document.getElementById('adminacct');
	//overview.style.visibility = 'hidden';
	acadterms.style.visibility = 'hidden';
	people.style.visibility = 'hidden';
	iaresults.style.visibility = 'visible';
	iaprofic.style.visibility = 'hidden';
	adminacct.style.visibility = 'hidden';	 
}

function displayIaprofic() {
	overview = document.getElementById('overview');
	acadterms = document.getElementById('acadterms');
	people = document.getElementById('people');
	iaresults = document.getElementById('iaresults');
	iaprofic = document.getElementById('iaprofic');
	adminacct = document.getElementById('adminacct');
	//overview.style.visibility = 'hidden';
	acadterms.style.visibility = 'hidden';
	people.style.visibility = 'hidden';
	iaresults.style.visibility = 'hidden';
	iaprofic.style.visibility = 'visible';
	adminacct.style.visibility = 'hidden';	 
}

function displayAdminacct() {
	overview = document.getElementById('overview');
	acadterms = document.getElementById('acadterms');
	people = document.getElementById('people');
	iaresults = document.getElementById('iaresults');
	iaprofic = document.getElementById('iaprofic');
	adminacct = document.getElementById('adminacct');
	//overview.style.visibility = 'hidden';
	acadterms.style.visibility = 'hidden';
	people.style.visibility = 'hidden';
	iaresults.style.visibility = 'hidden';
	iaprofic.style.visibility = 'hidden';
	adminacct.style.visibility = 'visible';	 
}









function clearScreen() {
	//alert("got here");
	clear();
}
