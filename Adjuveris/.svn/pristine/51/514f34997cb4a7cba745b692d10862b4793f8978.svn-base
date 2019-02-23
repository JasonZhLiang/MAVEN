function getParams() {
	var assign = null;
	var var1 = null;
	var atype = "";
	var urlquery = location.href.split("?");
	var paramIn = urlquery[1];

	var paramArr = new Array;	
	paramArr = paramIn.split("&");
	if (paramArr != null) {
		var exerArr = new Array;
		exerArr =	paramArr[0].split("=");
		exerid = exerArr[1];
		var var1Arr = new Array;
		var1Arr =	paramArr[1].split("=");
		var1 = "";
		var1 = var1Arr[1];
		var assignmentidArr = paramArr[3].split("=");
		var assignmentid = assignmentidArr[1];
		var classidArr = paramArr[4].split("=");
		classid = classidArr[1];
		var assignmentstatArr = paramArr[5].split("=");
		var assignmentstatus = assignmentstatArr[1];
		console.log("paramArr[4], assignmentstatus = " + paramArr[4] + ", " + assignmentstatus);

		document.checkform.exerciseid.value = exerid;
		document.checkform.variable1.value = var1;
		document.checkform.assignmentid.value = assignmentid;
		document.checkform.classid.value = classid;
		document.checkform.assignmentstatus.value = assignmentstatus;
		console.log("document.checkform.assignmentids.value = " + document.checkform.assignmentid.value);
		console.log("document.checkform.assignmentstatus.value = " + document.checkform.assignmentstatus.value);

		var var1sel = 0;
		if (var1 == "hic,haec,hoc") {
			var1sel = 0;
		}
		else if (var1 == "idem,eadem,idem") {
			var1sel = 1;
		}
		else if (var1 == "ille,illa,illud") {
			var1sel = 2;
		}
		else if (var1 == "ipse,ipsa,ipsum") {
			var1sel = 3;
		}
		else if (var1 == "is,ea,id") {
			var1sel = 4;
		}
		else if (var1 == "iste,ista,istud") {
			var1sel = 5;
		}
		else if (var1 == "qui,quae,quod") {
			var1sel = 6;
		}
		else if (var1 == "quidam,quaedam,quiddam") {
			var1sel = 7;
		}
		else if (var1 == "quis,quis,quid") {
			var1sel = 8;
		} 
		console.log("var1, var1sel = " + var1 + ", " + var1sel);
		var sel = document.getElementById("selOpts");
		document.getElementById("selOpts").selectedIndex = var1sel;  //var1;
		document.checkform.variable1 = var1;
		var var1str = document.getElementById("selOpts").value;
		document.checkform.var1string.value = var1str;
		var chck = document.checkform.variable1.value;
		
		var actionTarget = "../JsonServlet";
		
		if(assignmentid == 0){
			actionTarget =  "../ShowAlertServlet";
		}
		
		document.getElementById("checkform").setAttribute("action",actionTarget);
	}
}


function Clear() {
	
	document.checkform.comment.value = "";

	document.checkform.nomaM.value = "";
	document.checkform.genaM.value = "";
	document.checkform.accaM.value = "";
	document.checkform.dat_aM.value = "";
	document.checkform.ablaM.value = "";
	document.checkform.nomaMP.value = "";
	document.checkform.genaMP.value = "";
	document.checkform.accaMP.value = "";
	document.checkform.dat_aMP.value = "";
	document.checkform.ablaMP.value = "";

	document.checkform.nomaF.value = "";
	document.checkform.genaF.value = "";
	document.checkform.accaF.value = "";
	document.checkform.dat_aF.value = "";
	document.checkform.ablaF.value = "";
	document.checkform.nomaFP.value = "";
	document.checkform.genaFP.value = "";
	document.checkform.accaFP.value = "";
	document.checkform.dat_aFP.value = "";
	document.checkform.ablaFP.value = "";

	document.checkform.nomaN.value = "";
	document.checkform.genaN.value = "";
	document.checkform.accaN.value = "";
	document.checkform.dat_aN.value = "";
	document.checkform.ablaN.value = "";
	document.checkform.nomaNP.value = "";
	document.checkform.genaNP.value = "";
	document.checkform.accaNP.value = "";
	document.checkform.dat_aNP.value = "";
	document.checkform.ablaNP.value = "";

	return;
}

function validateForm() {
	if(document.checkform.selOpts.selectedIndex < 0) {
		alert("Please select a pronoun from the list provided.");
		return false;
	}
	var sel = document.getElementById("selOpts");
	var selectidx = document.checkform.selOpts.selectedIndex;
	if(selectidx == 0 && document.checkform.nomaM.value != 'hic') {
		alert("The pronoun selected does not match your first entry.");
		return false;
	}
	else if(selectidx == 1 && document.checkform.nomaM.value != 'idem') {
		alert("The pronoun selected does not match your first entry.");
		return false;
	}
	else if(selectidx == 2 && document.checkform.nomaM.value != 'ille') {
		alert("The pronoun selected does not match your first entry.");
		return false;
	}
	else if(selectidx == 3 && document.checkform.nomaM.value != 'ipse') {
		alert("The pronoun selected does not match your first entry.");
		return false;
	}
	else if(selectidx == 4 && document.checkform.nomaM.value != 'is') {
		alert("The pronoun selected does not match your first entry.");
		return false;
	}
	else if(selectidx == 5 && document.checkform.nomaM.value != 'iste') {
		alert("The pronoun selected does not match your first entry.");
		return false;
	}
	else if(selectidx == 6 && document.checkform.nomaM.value != 'qui') {
		alert("The pronoun selected does not match your first entry.");
		return false;
	}
	else if(selectidx == 7 && document.checkform.nomaM.value != 'quidam') {
		alert("The pronoun selected does not match your first entry.");
		return false;
	}
	else if(selectidx == 8 && document.checkform.nomaM.value != 'quis') {
		alert("The pronoun selected does not match your first entry.");
		return false;
	}
}

var wrdsArr = ["hic,haec,hoc","idem,eadem,idem","ille,illa,illud","ipse,ipsa,ipsum","is,ea,id","iste,ista,istud","qui,quae,quod","quidam,quaedam,quiddam","quis,quis,quid"];

var hic = ["hic","huius","hunc","huic","hoc","haec","huius","hanc","huic","hac","hoc","huius","hoc","huic","hoc","hi","horum","hos","his","his","hae","harum","has","his","his","haec","horum","haec","his","his","--"];

var idem = ["idem","eiusdem","eundem","eidem","eodem","eadem","eiusdem","eandem","eidem","eadem","idem","eiusdem","idem","eidem","eodem","eidem,iidem","eorundem","eosdem","eisdem","eisdem","eaedem","earundem","easdem","eisdem","eisdem","eadem","eorundem","eadem","eisdem","eisdem","--"];

var ille = ["ille","illius","illum","illi","illo","illa","illius","illam","illi","illa","illud","illius","illud","illi","illo","illi","illorum","istos","illis","illis","illae","illarum","illas","illis","illis","illa","illorum","illa","illis","illis","--"];

var ipse = ["ipse","ipsius","ipsum","ipsi","ipso","ipsa","ipsius","ipsam","ipsi","ipsa","ipsum","ipsius","ipsum","ipsi","ipso","ipsi","ipsorum","istos","ipsis","ipsis","ipsae","ipsarum","ipsas","ipsis","ipsis","ipsa","ipsorum","ipsa","ipsis","ipsis","--"];

var is = ["is","eius","eum","ei","eo","ea","eius","eam","ei","ea","id","eius","id","ei","eo","ei,ii","eorum","eos","eis,iis","eis,iis","eae","earum","eas","eis,iis","eis,iis","ea","eorum","ea","eis,iis","eis,iis","--"];

var iste = ["iste","istius","istum","isti","isto","ista","istius","istam","isti","ista","istud","istius","istud","isti","isto","isti","istorum","istos","istis","istis","istae","istarum","istas","istis","istis","ista","istorum","ista","istis","istis","--"]; 

var qui = ["qui","cuius","quem","cui","quo","quae","cuius","quam","cui","qua","quod","cuius","quod","cui","quo","qui","quorum","quos","quibus","quibus","quae","quarum","quas","quibus","quibus","quae","quorum","quae","quibus","quibus","--"];

var quidam = ["quidam","cuiusdam","quendam","cuidam","quodam","quaedam","cuiusdam","quandam","cuidam","quadam","quiddam","cuiusdam","quiddam","cuidam","quodam","quidam","quorundam","quosdam","quibusdam","quibusdam","quaedam","quarundam","quasdam","quibusdam","quibusdam","quaedam","quorundam","quaedam","quibusdam","quibusdam","Adjectival form differs: 'quoddam' in neuter sing. nom. and pl."];

var quis = ["quis","cuius","quem","cui","quo","quis","cuius","quem","cui","quo","quid","cuius","quid","cui","quo","qui","quorum","quos","quibus","quibus","quae","quarum","quas","quibus","quibus","quae","quorum","quae","quibus","quibus","Plural forms are rare."];




var selbrg = [hic,idem,ille,ipse,is,iste,qui,quidam,quis] ;


function goSel(form) {
	Clear();
	var i = form.selOpts.selectedIndex;
	var selopt = selbrg[i];

		document.checkform.nombM.value = selopt[0];
		document.checkform.genbM.value = selopt[1];
		document.checkform.accbM.value = selopt[2];
		document.checkform.dat_bM.value = selopt[3];
		document.checkform.ablbM.value = selopt[4];
		document.checkform.nombF.value = selopt[5];
		document.checkform.genbF.value = selopt[6];
		document.checkform.accbF.value = selopt[7];
		document.checkform.dat_bF.value = selopt[8];
		document.checkform.ablbF.value = selopt[9];
		document.checkform.nombN.value = selopt[10];
		document.checkform.genbN.value = selopt[11];
		document.checkform.accbN.value = selopt[12];
		document.checkform.dat_bN.value = selopt[13];
		document.checkform.ablbN.value = selopt[14];
		document.checkform.nombMP.value = selopt[15];
		document.checkform.genbMP.value = selopt[16];
		document.checkform.accbMP.value = selopt[17];
		document.checkform.dat_bMP.value = selopt[18];
		document.checkform.ablbMP.value = selopt[19];
		document.checkform.nombFP.value = selopt[20];
		document.checkform.genbFP.value = selopt[21];
		document.checkform.accbFP.value = selopt[22];
		document.checkform.dat_bFP.value = selopt[23];
		document.checkform.ablbFP.value = selopt[24];
		document.checkform.nombNP.value = selopt[25];
		document.checkform.genbNP.value = selopt[26];
		document.checkform.accbNP.value = selopt[27];
		document.checkform.dat_bNP.value = selopt[28];
		document.checkform.ablbNP.value = selopt[29];
		document.checkform.comment.value = selopt[30];
	
}; 

function setVariable() {
	var sel = document.getElementById("selOpts");
	variable1 = sel.options[sel.selectedIndex].value;
	document.checkform.variable1.value = variable1;
}

