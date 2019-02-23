// Get the parameters from the uri
function getParams() {
	var assign = null;
	var var1 = null;
	var urlquery = location.href.split("?");
	var paramIn = urlquery[1];
	var assignmenttype = "";
	var assignmentid = 0;
	var assignmentstatus = "";
	
	var paramArr = new Array;	
	paramArr = paramIn.split("&");
	if (paramArr != null) {
		var exerArr = new Array;
		exerArr =	paramArr[0].split("=");
		exerid = exerArr[1];
		var var1Arr = new Array;
		var1Arr =	paramArr[1].split("=");
		var1 = "";
		var1 = decodeURI(var1Arr[1]);
		var1Arr =	paramArr[2].split("=");
		var2 = "";
		var2 = var1Arr[1];
		var assignmentidArr = paramArr[3].split("=");
		assignmentid = assignmentidArr[1];
		var classidArr = paramArr[4].split("=");
		classid = classidArr[1];
		var assignmentstatusArr = paramArr[5].split("=");
		assignmentstatus = assignmentstatusArr[1];
		console.log("assignmentstatus = " + assignmentstatus);
		document.checkform.exerciseid.value = exerid;
		document.checkform.variable1.value = var1;
		document.checkform.variable2.value = var2;
		document.checkform.assignmentid.value = assignmentid;
		document.checkform.classid.value = classid;
		document.checkform.assignmentstatus.value = assignmentstatus;
		
		console.log('VAR 2');
		console.log(var2);

		var declsel = document.getElementById("declension");
		var wordsel = document.getElementById("word");
		
		document.checkform.variable1.value = var1;
		document.checkform.variable1.value = var1;
		document.checkform.variable2.value = var2;
		
		for(var i = 0, j = declsel.options.length; i < j; ++i) {
        if(declsel.options[i].innerHTML === var1) {
	           declsel.selectedIndex = i;
	           break;
	        }
	    }
		
		var declval = document.checkform.declension.value;
		loadSel(declval);
		
		for(var i = 0, j = wordsel.options.length; i < j; ++i) {
			var wordprim = wordsel.options[i].innerHTML;
			var wordprimarr = wordprim.split(" (");
			var wordbis = wordprimarr[0];
			wordbis = wordbis.trim();
			if(wordbis === var2) {
				wordsel.selectedIndex = i;
				break;
			}
		}
		console.log("declsel.selectedIndex, wordsel.selectedIndex = " + declsel.selectedIndex + ", " + 
				wordsel.selectedIndex);
		
		var var1str = declsel.options[0].innerHTML; 
		document.checkform.var1string.value = var1str;
		var chck = document.checkform.variable1.value;
		
		var actionTarget = "../JsonServlet";
		
		if(assignmentid == 0){
			actionTarget =  "../ShowAlertServlet";
		}
		
		$('#checkform').attr('action',actionTarget);
		
		setInputs();
	}
}

function showcheck() {

//start masculine
	var anomS = document.checkform.nomaS.value;
	var nomS = document.getElementById("nomaS");
	var agenS = document.checkform.genaS.value;
	var genS = document.getElementById("genaS");
	var aaccS = document.checkform.accaS.value;
	var baccS = document.checkform.accbS.value;
	var adatS = document.checkform.dat_aS.value;
	var datS = document.getElementById("dat_aS");
	var aablS = document.checkform.ablaS.value;
	var ablS = document.getElementById("ablaS");

	var anomP = document.checkform.nomaP.value;
	var nomP = document.getElementById("nomaP");
	var agenP = document.checkform.genaP.value;
	var genP = document.getElementById("genaP");
	var aaccP = document.checkform.accaP.value;
	var accP = document.getElementById("accaP");
	var adatP = document.checkform.dat_aP.value;
	var datP = document.getElementById("dat_aP");
	var aablP = document.checkform.ablaP.value;
	var ablP = document.getElementById("ablaP");
}


// Clear the contents of the input controls
function clearEdits() {
	document.checkform.nomaS.value = "";
	document.checkform.genaS.value = "";
	document.checkform.accaS.value = "";
	document.checkform.dat_aS.value = "";
	document.checkform.ablaS.value = "";
	document.checkform.nomaP.value = "";
	document.checkform.genaP.value = "";
	document.checkform.accaP.value = "";
	document.checkform.dat_aP.value = "";
	document.checkform.ablaP.value = "";
	return;
}


//var declArr = ["First","Second","Third","Third i-stem","Fourth","Fifth","Irregular","Greek first","Greek second"];

var porta = ["porta","portae","portam","portae","porta","portae","portarum","portas","portis","portis"];

var amica = ["amica","amicae","amicam","amicae","amica","amicae","amicarum","amicas","amicis","amicis"];

var servus = ["servus","servi","servum","servo","servo","servi","servorum","servos","servis","servis"];

var donum = ["donum","doni","donum","dono","dono","dona","donorum","dona","donis","donis"];

var puer = ["puer","pueri","puerum","puero","puero","pueri","puerorum","pueros","pueris","pueris"];

var ager = ["ager","agri","agrum","agro","agro","agri","agrorum","agros","agris","agris"];

var lex = ["lex","legis","legem","legi","lege","leges","legum","leges","legibus","legibus"];

var rex = ["rex","regis","regem","regi","rege","reges","regum","reges","regibus","regibus"];

var corpus = ["corpus","corporis","corpus","corpori","corpore","corpora","corporum","corpora","corporibus","corporibus"];

var mos = ["mos","moris","morem","mori","more","mores","morum","mores","moribus","moribus"];

var genus = ["genus","generis","genus","generi","genere","genera","generum","genera","generibus","generibus"];

var civis = ["civis","civis","civem","civi","cive","cives","civium","cives","civibus","civibus"];

var urbs = ["urbs","urbis","urbem","urbi","urbe","urbes","urbium","urbes","urbibus","urbibus"];

var mare = ["mare","maris","mare","mari","mari","maria","marium","maria","maribus","maribus"];

var fructus = ["fructus","fructus","fructum","fructui","fructu","fructus","fructuum","fructus","fructibus","fructibus"];

var cornu = ["cornu","cornus","cornu","cornu","cornu","cornua","cornuum","cornua","cornibus","cornibus"];

var dies = ["dies","diei","diem","diei","die","dies","dierum","dies","diebus","diebus"];

var res = ["res","rei","rem","rei","re","res","rerum","res","rebus","rebus"];

var senex = ["senex","senis","senem","seni","sene","senes","senum","senes","senibus","senibus"];

var caro = ["caro","carnis","carnem","carni","carne","carnes","carnium","carnes","carnibus","carnibus"];

var os = ["os","ossis","os","ossi","osse","ossa","ossium","ossa","ossibus","ossibus"];

var Archias = ["Archias","Archiae","Archiam","Archiae","Archia","--","--","--","--","--"];

var epitome = ["epitome","epitomes","epitomen","epitomae","epitome","epitomae","epitomarum","epitomas","epitomis","epitomis"];

var cometes = ["cometes","cometae","cometen","cometae","comete","cometae","cometarum","cometas","cometis","cometis"];

var barbitos = ["barbitos","barbiti","barbiton","barbito","barbito","barbiti","barbitorum","barbitos","barbitis","barbitis"];

var Androgeos = ["Androgeos","Androgeo","Androgeon","Androgeo","Androgeo","--","--","--","--","--"];

var Ilion = ["Ilion","Ilii","Ilion","Ilio","Ilio","--","--","--","--","--"];


var wrdsArr = new Array();

// Initialize the inputs states
function setInputs()
{
	noplurals = false;
	document.checkform.nomaS.value = "";
	if(document.checkform.word.value == "110") {
		wrdsArr = porta;
	} else if(document.checkform.word.value == "111") {
		wrdsArr = amica;
	} else if(document.checkform.word.value == "210") {
		wrdsArr = servus;
	} else if(document.checkform.word.value == "211") {
		wrdsArr = donum;
	} else if(document.checkform.word.value == "212") {
		wrdsArr = puer;
	} else if(document.checkform.word.value == "213") {
		wrdsArr = ager;
	} else if(document.checkform.word.value == "310") {
		wrdsArr = lex;
	} else if(document.checkform.word.value == "311") {
		wrdsArr = rex;
	} else if(document.checkform.word.value == "312") {
		wrdsArr = corpus;
	} else if(document.checkform.word.value == "313") {
		wrdsArr = mos;
	} else if(document.checkform.word.value == "314") {
		wrdsArr = genus;
	} else if(document.checkform.word.value == "410") {
		wrdsArr = civis;
	} else if(document.checkform.word.value == "411") {
		wrdsArr = urbs;
	} else if(document.checkform.word.value == "412") {
		wrdsArr = mare;
	} else if(document.checkform.word.value == "510") {
		wrdsArr = fructus;
	} else if(document.checkform.word.value == "511") {
		wrdsArr = cornu;
	} else if(document.checkform.word.value == "610") {
		wrdsArr = dies;
	} else if(document.checkform.word.value == "611") {
		wrdsArr = res;
	} else if(document.checkform.word.value == "710") {
		wrdsArr = senex;
	} else if(document.checkform.word.value == "711") {
		wrdsArr = caro;
	} else if(document.checkform.word.value == "712") {
		wrdsArr = os;
	} else if(document.checkform.word.value == "810") {
		wrdsArr = Archias;
		noplurals = true;
	} else if(document.checkform.word.value == "811") {
		wrdsArr = epitome;
	} else if(document.checkform.word.value == "812") {
		wrdsArr = cometes;
	} else if(document.checkform.word.value == "910") {
		wrdsArr = barbitos;
	} else if(document.checkform.word.value == "911") {
		wrdsArr = Androgeos;
		noplurals = true;
	} else if(document.checkform.word.value == "912") {
		wrdsArr = Ilion;
		noplurals = true;
	}
	
	// initialise one input
	var wrdopt = new Array();
	wrdopt = wrdsArr;
	document.checkform.nomaS.value = wrdopt[0];
	
	// Set usability of plural controls
	plth = document.getElementById("pluralt");
	thcolor = "black";
	if (noplurals == true)
	{
		thcolor = "gray"
	}
	plth.style.color = thcolor;
	
	setInputState("nomaP", noplurals);
	setInputState("genaP", noplurals);
	setInputState("accaP", noplurals);
	setInputState("dat_aP", noplurals);
	setInputState("ablaP", noplurals);
}


// Respond to a change in the noun selection 
function goSel() {
	clearEdits();
	setInputs();
	return;

}

// Set the disabled state and the validation class for the input element
// replace is used twice but only one should result in a change
function setInputState(inId, notstate)
{
	elem = document.getElementById(inId);
	elem.disabled = noplurals;
	curstr = elem.getAttribute("class");
	if (noplurals == false)
	{
		newstr = curstr.replace("drillholder", "reqdrillword");
		newstr = newstr.replace("nodrillword", "reqdrillword");
	}
	else
	{
		newstr = curstr.replace("drillholder", "nodrillword");
		newstr = newstr.replace("reqdrillword", "nodrillword");
	}
	elem.setAttribute("class", newstr);
}

// Clear the first input
function clearOpt(){
	document.checkform.nomaS.value = "";
}

// Load the options into the selection control
function loadSel(declval) {
	var wordsel = document.getElementById("word");
	$.each(selectoptions, function(kd, vd)
	{
		if (declval == vd.key)
		{
			index = 0;
			$.each(vd.values, function(kn, vn)
			{
				wordsel.options[index] = new Option(kn, vn);
				index++;
			});
		}
	});
}


