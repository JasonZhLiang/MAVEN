

function showcheck() {

//start active indicative
	var afrstSingAI = document.checkform.frstSingAIa.value;
	var bfrstSingAI = document.checkform.frstSingAIb.value;
	var frstSingAI = document.getElementById("frstSingAIa");
	var ascndSingAI = document.checkform.scndSingAIa.value;
	var bscndSingAI = document.checkform.scndSingAIb.value;
	var scndSingAI = document.getElementById("scndSingAIa");
	var athrdSingAI = document.checkform.thrdSingAIa.value;
	var bthrdSingAI = document.checkform.thrdSingAIb.value;
	var thrdSingAI = document.getElementById("thrdSingAIa");
	var afrstPlAI = document.checkform.frstPlAIa.value;
	var bfrstPlAI = document.checkform.frstPlAIb.value;
	var frstPlAI = document.getElementById("frstPlAIa");
	var ascndPlAI = document.checkform.scndPlAIa.value;
	var bscndPlAI = document.checkform.scndPlAIb.value;
	var scndPlAI = document.getElementById("scndPlAIa");
	var athrdPlAI = document.checkform.thrdPlAIa.value;
	var bthrdPlAI = document.checkform.thrdPlAIb.value;
	var thrdPlAI = document.getElementById("thrdPlAIa");		


//start active subjunctive
	var afrstSingAS = document.checkform.frstSingASa.value;
	var bfrstSingAS = document.checkform.frstSingASb.value;
	var frstSingAS = document.getElementById("frstSingASa");
	var ascndSingAS = document.checkform.scndSingASa.value;
	var bscndSingAS = document.checkform.scndSingASb.value;
	var scndSingAS = document.getElementById("scndSingASa");
	var athrdSingAS = document.checkform.thrdSingASa.value;
	var bthrdSingAS = document.checkform.thrdSingASb.value;
	var thrdSingAS = document.getElementById("thrdSingASa");
	var afrstPlAS = document.checkform.frstPlASa.value;
	var bfrstPlAS = document.checkform.frstPlASb.value;
	var frstPlAS = document.getElementById("frstPlASa");
	var ascndPlAS = document.checkform.scndPlASa.value;
	var bscndPlAS = document.checkform.scndPlASb.value;
	var scndPlAS = document.getElementById("scndPlASa");
	var athrdPlAS = document.checkform.thrdPlASa.value;
	var bthrdPlAS = document.checkform.thrdPlASb.value;
	var thrdPlAS = document.getElementById("thrdPlASa");

//start passive indicative
	var afrstSingPI = document.checkform.frstSingPIa.value;
	var bfrstSingPI = document.checkform.frstSingPIb.value;
	var frstSingPI = document.getElementById("frstSingPIa");
	var ascndSingPI = document.checkform.scndSingPIa.value;
	var bscndSingPI = document.checkform.scndSingPIb.value;
	var scndSingPI = document.getElementById("scndSingPIa");
	var athrdSingPI = document.checkform.thrdSingPIa.value;
	var bthrdSingPI = document.checkform.thrdSingPIb.value;
	var thrdSingPI = document.getElementById("thrdSingPIa");
	var afrstPlPI = document.checkform.frstPlPIa.value;
	var bfrstPlPI = document.checkform.frstPlPIb.value;
	var frstPlPI = document.getElementById("frstPlPIa");
	var ascndPlPI = document.checkform.scndPlPIa.value;
	var bscndPlPI = document.checkform.scndPlPIb.value;
	var scndPlPI = document.getElementById("scndPlPIa");
	var athrdPlPI = document.checkform.thrdPlPIa.value;
	var bthrdPlPI = document.checkform.thrdPlPIb.value;
	var thrdPlPI = document.getElementById("thrdPlPIa");

//start passive subjunctive
	var afrstSingPS = document.checkform.frstSingPSa.value;
	var bfrstSingPS = document.checkform.frstSingPSb.value;
	var frstSingPS = document.getElementById("frstSingPSa");
	var PScndSingPS = document.checkform.scndSingPSa.value;
	var bscndSingPS = document.checkform.scndSingPSb.value;
	var scndSingPS = document.getElementById("scndSingPSa");
	var athrdSingPS = document.checkform.thrdSingPSa.value;
	var bthrdSingPS = document.checkform.thrdSingPSb.value;
	var thrdSingPS = document.getElementById("thrdSingPSa");
	var afrstPlPS = document.checkform.frstPlPSa.value;
	var bfrstPlPS = document.checkform.frstPlPSb.value;
	var frstPlPS = document.getElementById("frstPlPSa");
	var ascndPlPS = document.checkform.scndPlPSa.value;
	var bscndPlPS = document.checkform.scndPlPSb.value;
	var scndPlPS = document.getElementById("scndPlPSa");
	var athrdPlPS = document.checkform.thrdPlPSa.value;
	var bthrdPlPS = document.checkform.thrdPlPSb.value;
	var thrdPlPS = document.getElementById("thrdPlPSa");




//active indicative

	if (afrstSingAI == null) {
		frstSingAI.style.color = "black";
		}

	else if (afrstSingAI == bfrstSingAI) {
		frstSingAI.style.color = "black";
	}
	else {
		frstSingAI.style.color = "black";
	}

	if (ascndSingAI == "") {
		scndSingAI.style.color = "black";
		}

	else if (ascndSingAI == bscndSingAI) {
		scndSingAI.style.color = "black";
	}
	else {
		scndSingAI.style.color = "black";
	}
	if (athrdSingAI == "") {
		thrdSingAI.style.color = "black";
		}

	else if (athrdSingAI == bthrdSingAI) {
		thrdSingAI.style.color = "black";
	}
	else {
		thrdSingAI.style.color = "black";
	}
	if (afrstPlAI == "") {
		frstPlAI.style.color = "black";
		}

	else if (afrstPlAI == bfrstPlAI) {
		frstPlAI.style.color = "black";
	}
	else {
		frstPlAI.style.color = "black";
	}
	if (ascndPlAI == "") {
		scndPlAI.style.color = "black";
		}

	else if (ascndPlAI == bscndPlAI) {
		scndPlAI.style.color = "black";
	}
	else {
		scndPlAI.style.color = "black";
	}
	if (athrdPlAI == "") {
		thrdPlAI.style.color = "black";
		}

	else if (athrdPlAI == bthrdPlAI) {
		thrdPlAI.style.color = "black";
	}
	else {
		thrdPlAI.style.color = "black";
	}

//Active subjunctive
	if (afrstSingAS == null) {
		frstSingAS.style.color = "black";
		}

	else if (afrstSingAS == bfrstSingAS) {
		frstSingAS.style.color = "black";
	}
	else {
		frstSingAS.style.color = "black";
	}
	if (ascndSingAS == "") {
		scndSingAS.style.color = "black";
		}

	else if (ascndSingAS == bscndSingAS) {
		scndSingAS.style.color = "black";
	}
	else {
		scndSingAS.style.color = "black";
	}
	if (athrdSingAS == "") {
		thrdSingAS.style.color = "black";
		}

	else if (athrdSingAS == bthrdSingAS) {
		thrdSingAS.style.color = "black";
	}
	else {
		thrdSingAS.style.color = "black";
	}
	if (afrstPlAS == "") {
		frstPlAS.style.color = "black";
		}

	else if (afrstPlAS == bfrstPlAS) {
		frstPlAS.style.color = "black";
	}
	else {
		frstPlAS.style.color = "black";
	}
	if (ascndPlAS == "") {
		scndPlAS.style.color = "black";
		}

	else if (ascndPlAS == bscndPlAS) {
		scndPlAS.style.color = "black";
	}
	else {
		scndPlAS.style.color = "black";
	}
	if (athrdPlAS == "") {
		thrdPlAS.style.color = "black";
		}

	else if (athrdPlAS == bthrdPlAS) {
		thrdPlAS.style.color = "black";
	}
	else {
		thrdPlAS.style.color = "black";
	}

//passive indicative
	if (afrstSingPI == null) {
		frstSingPI.style.color = "black";
		}

	else if (afrstSingPI == bfrstSingPI) {
		frstSingPI.style.color = "black";
	}
	else {
		frstSingPI.style.color = "black";
	}

	if (ascndSingPI == "") {
		scndSingPI.style.color = "black";
		}

	else if (ascndSingPI == bscndSingPI) {
		scndSingPI.style.color = "black";
	}
	else {
		scndSingPI.style.color = "black";
	}
	if (athrdSingPI == "") {
		thrdSingPI.style.color = "black";
		}

	else if (athrdSingPI == bthrdSingPI) {
		thrdSingPI.style.color = "black";
	}
	else {
		thrdSingPI.style.color = "black";
	}
	if (afrstPlPI == "") {
		frstPlPI.style.color = "black";
		}

	else if (afrstPlPI == bfrstPlPI) {
		frstPlPI.style.color = "black";
	}
	else {
		frstPlPI.style.color = "black";
	}
	if (ascndPlPI == "") {
		scndPlPI.style.color = "black";
		}

	else if (ascndPlPI == bscndPlPI) {
		scndPlPI.style.color = "black";
	}
	else {
		scndPlPI.style.color = "black";
	}
	if (athrdPlPI == "") {
		thrdPlPI.style.color = "black";
		}

	else if (athrdPlPI == bthrdPlPI) {
		thrdPlPI.style.color = "black";
	}
	else {
		thrdPlPI.style.color = "black";
	}

//passive subjunctive
	if (afrstSingPS == null) {
		frstSingPS.style.color = "black";
		}

	else if (afrstSingPS == bfrstSingPS) {
		frstSingPS.style.color = "black";
	}
	else {
		frstSingPS.style.color = "black";
	}
	if (PScndSingPS == "") {
		scndSingPS.style.color = "black";
		}

	else if (PScndSingPS == bscndSingPS) {
		scndSingPS.style.color = "black";
	}
	else {
		scndSingPS.style.color = "black";
	}
	if (athrdSingPS == "") {
		thrdSingPS.style.color = "black";
		}

	else if (athrdSingPS == bthrdSingPS) {
		thrdSingPS.style.color = "black";
	}
	else {
		thrdSingPS.style.color = "black";
	}
	if (afrstPlPS == "") {
		frstPlPS.style.color = "black";
		}

	else if (afrstPlPS == bfrstPlPS) {
		frstPlPS.style.color = "black";
	}
	else {
		frstPlPS.style.color = "black";
	}
	if (ascndPlPS == "") {
		scndPlPS.style.color = "black";
		}

	else if (ascndPlPS == bscndPlPS) {
		scndPlPS.style.color = "black";
	}
	else {
		scndPlPS.style.color = "black";
	}
	if (athrdPlPS == "") {
		thrdPlPS.style.color = "black";
		}

	else if (athrdPlPS == bthrdPlPS) {
		thrdPlPS.style.color = "black";
	}
	else {
		thrdPlPS.style.color = "black";
	}

}



function Clear() {
	var showlayer = document.getElementById("show");		
	showlayer.style.visibility = "hidden";
	document.checkform.frstSingAIa.value = "";
	document.checkform.scndSingAIa.value = "";
	document.checkform.thrdSingAIa.value = "";
	document.checkform.frstPlAIa.value = "";
	document.checkform.scndPlAIa.value = "";
	document.checkform.thrdPlAIa.value = "";
	
	var showlayer2 = document.getElementById("show2");		
	showlayer2.style.visibility = "hidden";
	document.checkform.frstSingASa.value = "";
	document.checkform.scndSingASa.value = "";
	document.checkform.thrdSingASa.value = "";
	document.checkform.frstPlASa.value = "";
	document.checkform.scndPlASa.value = "";
	document.checkform.thrdPlASa.value = "";
	
	var showlayer3 = document.getElementById("show3");		
	showlayer3.style.visibility = "hidden";
	document.checkform.frstSingPIa.value = "";
	document.checkform.scndSingPIa.value = "";
	document.checkform.thrdSingPIa.value = "";
	document.checkform.frstPlPIa.value = "";
	document.checkform.scndPlPIa.value = "";
	document.checkform.thrdPlPIa.value = "";
	
	var showlayer4 = document.getElementById("show4");		
	showlayer4.style.visibility = "hidden";
	document.checkform.frstSingPSa.value = "";
	document.checkform.scndSingPSa.value = "";
	document.checkform.thrdSingPSa.value = "";
	document.checkform.frstPlPSa.value = "";
	document.checkform.scndPlPSa.value = "";
	document.checkform.thrdPlPSa.value = "";
	
	preGetOpts();
}

function showCorrectAI() {	
	var showlayer = document.getElementById("show");
	showlayer.style.visibility = "visible";
}

function showCorrectAS() {	
	var showlayer2 = document.getElementById("show2");
	showlayer2.style.visibility = "visible";
}

function showCorrectPI() {	
	var showlayer3 = document.getElementById("show3");
	showlayer3.style.visibility = "visible";
}

function showCorrectPS() {	
	var showlayer4 = document.getElementById("show4");
	showlayer4.style.visibility = "visible";
}

function hideCorrectAI() {	
	var showlayer = document.getElementById("show");
	showlayer.style.visibility = "hidden";
}

function hideCorrectAS() {	
	var showlayer2 = document.getElementById("show2");
	showlayer2.style.visibility = "hidden";
}

function hideCorrectPI() {	
	var showlayer3 = document.getElementById("show3");
	showlayer3.style.visibility = "hidden";
}

function hideCorrectPS() {	
	var showlayer4 = document.getElementById("show4");
	showlayer4.style.visibility = "hidden";
}

var verbArr = ["servare","monere","cedere","facere","audire","esse","posse","velle","nolle","malle","fieri","ire","ferre"];

var tenseArr = ["present","perfect","imperfect","future","pluperfect","future perfect"];

var selopt = new Array();

function preGetOpts() {
	document.checkform.selOpts.selectedIndex = 0;
	document.checkform.tenseOpts.selectedIndex = 0;
	getOpts();
}

function getOpts(form) {
	var i = document.checkform.selOpts.selectedIndex;
	var verb = verbArr[i];
	var j = document.checkform.tenseOpts.selectedIndex;
	var tense = tenseArr[j];

	var golabtf = document.getElementById("golab");
	golabtf.value = verb + ", " + tense + " tense";
	
	selopt = selArr[i][j];
		
  	document.checkform.frstSingAIb.value = selopt[0];
	document.checkform.scndSingAIb.value = selopt[1];
	document.checkform.thrdSingAIb.value = selopt[2];
	document.checkform.frstPlAIb.value = selopt[3];
	document.checkform.scndPlAIb.value = selopt[4];
	document.checkform.thrdPlAIb.value = selopt[5];

  	document.checkform.frstSingASb.value = selopt[6];
	document.checkform.scndSingASb.value = selopt[7];
	document.checkform.thrdSingASb.value = selopt[8];
	document.checkform.frstPlASb.value = selopt[9];
	document.checkform.scndPlASb.value = selopt[10];
	document.checkform.thrdPlASb.value = selopt[11];

  	document.checkform.frstSingPIb.value = selopt[12];
	document.checkform.scndSingPIb.value = selopt[13];
	document.checkform.thrdSingPIb.value = selopt[14];
	document.checkform.frstPlPIb.value = selopt[15];
	document.checkform.scndPlPIb.value = selopt[16];
	document.checkform.thrdPlPIb.value = selopt[17];

  	document.checkform.frstSingPSb.value = selopt[18];
	document.checkform.scndSingPSb.value = selopt[19];
	document.checkform.thrdSingPSb.value = selopt[20];
	document.checkform.frstPlPSb.value = selopt[21];
	document.checkform.scndPlPSb.value = selopt[22];
	document.checkform.thrdPlPSb.value = selopt[23];
	
}
