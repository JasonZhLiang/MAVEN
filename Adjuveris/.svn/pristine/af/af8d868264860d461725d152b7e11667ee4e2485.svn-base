
/* The Save function:
 * - formats and saves a new private text
 * - copies a class group text to the user's private library with the name of the group
 * - saves title and author of an already formatted text or of a collaborative text
*/

var regex = [ "(", "[", ":", ";", ",", ".", "?", "!", "\n"];//first go the tokens normally used before punctuation marks
//on the assumption that braces and brackets used in proper pairs

function Save() {
	
	var textsav = document.EditExerciseForm.passagetext.value;
	var endtxt = textsav.lastIndexOf("+");
	var beforend = endtxt - 2;
	var chcktxt = textsav.substring(beforend, endtxt);
	
	if(!textsav || textsav.length == 0){
		return;// checking if a string is blank
	}
	
	// If the text has not been formatted yet.
	if (chcktxt != "++") {
		// A text that hasn't been saved yet does not have a textid.
		// Use 0 to indicate this.
		textid = "0";
		// Check for invalid characters
		var ix = 0;
		if ((ix = textsav.indexOf('%')) >= 0) {
			alert("Text contains invalid character '%'");
			$("<p>Text contains invalid character '%'.</p>").alert();
			document.getElementById('passagetext').focus();
			return;
		}
		if ((ix = textsav.indexOf('&')) >= 0) {
			alert("Text contains invalid character '&'");
			$("<p>Text contains invalid character '&'.</p>").alert();
			document.getElementById('passagetext').focus();
			return;
		}
		if ((ix = textsav.indexOf('+')) >= 0) {
			alert("Text contains invalid character '+'");
			$("<p>Text contains invalid character '+'.</p>").alert();
			document.getElementById('passagetext').focus();
			return;
		}
		
		var finaltxt = doFormat(textsav);
		document.EditExerciseForm.passagetext.value = finaltxt;
			
		document.getElementById("passagetext").readOnly = true;
		document.getElementById("formatbutton").disabled = true;
		
	    return;
	}
		
}
$(function() {
	$('#formatbutton').bind('click',function(event) {
		Save();
	});
});

$(function() {
	$('#passagetext').bind('paste',function(event) {
		document.getElementById("passagetext").readOnly = false;
		document.getElementById("formatbutton").disabled = false;
		document.getElementById("numbrbutton").disabled = true;
		document.getElementById("replbutton").disabled = true;
		document.getElementById("reversebutton").disabled = true;
		
	});
});

function doFormat(txt) {
		
		var newtxt = txt;
        newtxt = newtxt.replace(/ /g,"  "); //inserts double spaces
        newtxt = newtxt.replace(/\n/g,'\n '); //makes sure there is at least one space after every newline
        newtxt = newtxt + '\n';
        var finaltxt = '1 -- ';             //inserts first line no and tab
		var biglen = newtxt.length;
		var cumulen = biglen;
		var lncnt = 2;
		var wrkstr = newtxt;
		var begidx = 0;
		var substr = "";
		var linidx = 0;
		var wrkstrlen = wrkstr.length;
		var nosp = /\S/;
		var nospidx = 0;
		
		while (cumulen > 0 & wrkstrlen > 0) {
			wrkstr = newtxt.substring(begidx,biglen);
			linidx = wrkstr.indexOf('\n');
			wrkstrlen = wrkstr.length;
			if (linidx > 1 & linidx < 65 ) {
				substr = newtxt.substring(begidx,(begidx + linidx));
				nospidx = substr.search(nosp);
				if (nospidx > 0) {
					substr = substr.substring(nospidx,linidx);
				}
				
				finaltxt = finaltxt + substr + '\n' + lncnt + ' -- ';
				lncnt++;
				begidx = begidx + linidx + 1;
				cumulen = cumulen - linidx;
				}
			else if (linidx >= 65) {		
				var fstsubstr = wrkstr.substring(65,biglen);    
				var endidx = fstsubstr.indexOf("  ");
				if (endidx < 0) {
					endidx=biglen;
				}
				
				substr = newtxt.substring(begidx,(begidx + 65 + endidx));
			
				nospidx = substr.search(nosp);
														
				if (nospidx > 0) {
					substr = substr.substring(nospidx,linidx);
				}
				
				finaltxt = finaltxt + substr + '\n' + lncnt + ' -- ';
				lncnt++;
				begidx = begidx + 64 + endidx + 1;
				cumulen = cumulen - (64 + endidx);
				}
			//handles lines with no characters
			else {
				finaltxt = finaltxt + '\n' + lncnt + ' -- ';
				lncnt++;
				begidx = begidx + linidx + 1;
				cumulen = cumulen - (linidx + 1);
				}
			}
		finaltxt = finaltxt + '\n' + "+++";
		return(finaltxt);
}

$(function() {
	$('#passagetext').bind('dblclick',function(event) {
		OnTheDouble();
			
	});
});

/*$(function() {
	$('#passagetext').bind('doubletap',function(event) {
		OnTheDouble();
		
	})
		  
});*/

function OnTheDouble(){

	if(!document.getElementById("numbrbutton").disabled ||
			!document.getElementById("replbutton").disabled ||
			!document.getElementById("reversebutton").disabled ||
			!document.getElementById("formatbutton").disabled){
		return;
	}
	displayResult();
	
	// if the word is selected
	if($('#strtshow').val() > 0 &&  $('#wrdcntshow').val()> 0){
		
		document.getElementById("numbrbutton").disabled = false;
 		document.getElementById("replbutton").disabled = true;
		document.getElementById("reversebutton").disabled = true;
		document.getElementById("passagetext").readOnly = true;
	}
}

function displayResult() {
	
    var textarea = document.getElementById("passagetext");
    if (window.getSelection) {  //all browsers except IE before 9
    	
    	if (document.activeElement && 
    			(document.activeElement.tagName.toLowerCase() == "textarea" ||
		    			 document.activeElement.tagName.toLowerCase() == "input")) {
			start = textarea.selectionStart;
			end = textarea.selectionEnd;
		
		text = textarea.value;	
		word = text.substring (start,end);
    		
		if(!word){
    			return;
    		}
		
    		var endcheck = word.indexOf(" ");// check for space nside the word
		var i = 0;
    		while(endcheck == -1 && i < 9) {
    			endcheck = word.indexOf(regex[i]); 
			i++	
        		
        	}
		if(endcheck > 0){
			word = word.substring(0,endcheck);
			end = start + endcheck;  
			textarea.setSelectionRange(start, end);// 
		}
    		if(text.charAt(start -1) != " " && text.charAt(end +1) != " " && text.charAt(end +1) != "." && text.charAt(end +1) != ","
    			&& text.charAt(end +1) != "-")
        	{
			//make sure it is a whole word and not a charater inside a word		
        		return;
        	}
	   	
    		
        	var subtxt = textarea.value.substring(0,end);
        	var retidx = subtxt.lastIndexOf("\n");  //finds end of previous line
        	var idxaug = retidx+1;  //finds start of next line
        	
        	var linstr = text.substring(idxaug,start);
        	var patmch = linstr.match(/\s\S/g);
        
        	if (patmch != null)
        	{
        		var letters = /[a-zA-Z0-9?-??-?\s]+/;//added Greek letters recognision
        		//[\p{Greek}\s\d a-zA-Z]+/;// /[A-Za-z]+/; [a-zA-Z0-9?-??-?\s]
        		//-- /^[^?&\^"]*$/ (that means the string is composed only of characters outside the five you listed)...
        		if (letters.test(word))
        		{
                	wrdcnt = patmch.length;  //count space/non-space pattern 
                	
                	//following necessary to avoid effects of quotes in causing regex pattern to be skipped
                	var frst1 = text.substring(start-1,start);        	
                	if (frst1 == '\'' || frst1 == '\"' || frst1 == '\[') {
                		wrdcnt = wrdcnt - 1;
                	}
                	
                	charac = start - idxaug;  //finds index of first character
        			//$('#lnchartf').val(charac);
                	
                	var subb = textarea.value.substr(idxaug,10);  //substr of first ten characters 
                	var temp = new Array();
                	temp = subb.split(' -- '); //splits lncnt[0] from text[1]
            		if (idxaug>1) {
                		lncnt = temp[0];  //finds lncnt for line
            		}
            		else {
                 		lncnt = 1; //if first line 
            		}
        			
        			var wrdshw = document.getElementById("wordshow");
        			var lncntshw = document.getElementById("lncntshow");
        			var wrdcntshw = document.getElementById("wrdcntshow");
        			var strtshw = document.getElementById("strtshow");

        			var endshw = document.getElementById("endshow");
        			var countshw = document.getElementById("countshow");
        			wrdshw.value = word;
        			lncntshw.value = lncnt;
        			wrdcntshw. value = wrdcnt;
        			strtshw.value = start;
        			endshw.value = end;
        		}
        	}
    	}
    }
}


$(function() {
	$('#numbrbutton').bind('click',function(event) {
		
		NumberWord();
	    document.getElementById("numbrbutton").disabled = true;
		document.getElementById("replbutton").disabled = false;
		document.getElementById("reversebutton").disabled = true;
		document.getElementById("passagetext").readOnly = true;
    	
});



$(function() {
	$('#replbutton').bind('click',function(event) {
    	var str = $("#passagetext").val();
    	var strt = $('#strtshow').val();
    	var startprim = str.indexOf("}",+strt - +2);
    	var start = +startprim + +1;
    	
    	var end = str.indexOf(" ",start);
    	var endcheck = 0;
    	for (i = 0; i < 9; i++) {
    		endcheck = str.indexOf(regex[i],start);
    	
        	if (endcheck > 0 && endcheck < end) {//if regex is before the space
        		end = endcheck -1;
        		break;
        	}
        }
      	
    	var wordlen = end - start;
    	
    	var strtstr = str.substring(0,start);
    	var endstr = str.substring(+start + +1,str.length);

        for (i = 0; i < wordlen; i++) {
        		endstr = setCharAt(endstr,i,'_');
        }
        
        str = strtstr + endstr;
        $("#passagetext").val(str);
        start = 0;
        document.getElementById("numbrbutton").disabled = true;
    	document.getElementById("replbutton").disabled = true;
    	document.getElementById("reversebutton").disabled = false;
    	document.getElementById("passagetext").readOnly = true;
	});
	
	
	
});

function setCharAt(str,index,chr) {
    if(index > str.length-1) return str;
    
    var bfr = str.substr(0,index);
    var lngth = str.length;
    var strt = +index + +1;
    var aftr = str.substr(strt,lngth);
    return bfr + chr + aftr;
}

function renumberPassageQuestions(str) {
	var numbarr = [];
	
	var openparen = 0;  
	var closparen = 0;  
	var start = 1;  
	
	while (start > 0) {
		openparen = str.indexOf("{",start);
		closparen = str.indexOf("}",start);

		if (closparen > 0) {
			var numb = str.substring(+openparen + +1,closparen);
			numbarr.push(numb);	
		}
		if (openparen > 0) {
			start = +openparen + +4;
		}
		else {
			start = -1;
		}
	}
		
	numbarr = numbarr.sort(function(a,b) { return a - b; }); //function avoids alphabetic sort
	var qnumprim = numbarr[0];   //gets smallest qnumber
	var arrlen = numbarr.length + 1;
	for (var i = 0; i < arrlen; i++) {
		openparen = str.indexOf("{",start);
		closparen = str.indexOf("}",+start + +4);
		if (closparen > 0) {
			var startstr = str.substring(0,+openparen + +1);
			var endstr = str.substring(closparen,str.length);
			str = startstr + qnumprim + endstr;
		
		}
		if (openparen > 0) {
			start = +openparen + +1;
			qnumprim++;
		}
		else {
			start = -1;
		}		
	}
	return(str);
}

/*
$(function() {
	$('#reversebutton').bind('click',function(event) {
    	var str = $("#passagetext").val();
    	var strlen = str.length; 
    	var strt = $('#strtshow').val();
    	var start = str.indexOf("{",+strt - +2);
    	var end = str.indexOf(" ",start);
    	
    	
    	var end = str.indexOf(" ",start);
    	var endcheck = 0;
    	for (i = 0; i < 9; i++) {
    		endcheck = str.indexOf(regex[i],start)  	
        	if (endcheck > 0 && endcheck < end) {//if regex is before the space
        		end = endcheck;
        		break;
        	}
        }
    	
    	var wrd = $('#wordshow').val();
    	wrd = wrd.trim();
    	var startstr = str.substring(0,start);
    	var endstr = str.substring(end,strlen);
    	str = startstr + wrd + endstr;
    	
    	var count = $("#qcounttf").val();
    	count = +count - +1;
    	if(count < 0){
    		count = 0;
    	}
    	renumberPassageQuestions(str);
    
    	$("#qcounttf").val(count);
    	
    	$("#wordshow").val(" ");
    	$("#strtshow").val(" ");
    	$("#endshow").val(" ");
    	$("#lncntshow").val(" ");
    	$("#wrdcntshow").val(" ");
        $("#passagetext").val(str);
       
        document.getElementById("numbrbutton").disabled = true;
    	document.getElementById("replbutton").disabled = true;
    	document.getElementById("reversebutton").disabled = true;
        return;
	});
});*/


function NumberWord(){
		var str = $("#passagetext").val();
		var start = $('#strtshow').val();
		var count = $('#qcounttf').val();
	
		var strtstr = str.substring(0,start);
		var endstr = str.substring(start,str.length);
		var countstr = "{" + (+count + +1) + "}";// get a new number
		
	    str = strtstr + countstr + endstr;// insert it into the string
		
		str = renumberPassageQuestions(str); // reculculate the number sequevence
		
		countstr = str.substring((+start + +1),str.indexOf("}", start));//get the new value of the selected number
		count = parseInt(countstr);
		$('#qcounttf').val(count);// store the updated number into the variable
		
	    $("#passagetext").val(str);	
	  
	}

//-----------------------------------------------------


$( document ).ready(function() {
	
 
	var textsav =  $("#passagetext").val();
	var  fin = false;
	if(textsav != null){
		
	  fin = textsav.indexOf("+++") > 0;
	}
	//prevent the JQery exception when text and buttons are hidden
	if(document.getElementById("passagetext") != null){
		document.getElementById("passagetext").readOnly = fin;//disable text formatting if it is already formatted
	}
	if(document.getElementById("formatbutton") != null){
		document.getElementById("formatbutton").disabled = fin;
	}
	if(document.getElementById("numbrbutton") != null){
		document.getElementById("numbrbutton").disabled = true;
	}
	if(document.getElementById("replbutton") != null){
		document.getElementById("replbutton").disabled = true;// (fin != true);
	}
	if(document.getElementById("reversebutton") != null){
		document.getElementById("reversebutton").disabled = true;// (fin != true);
	}
	
});


var myElement = document.getElementById("passagetext");
if(myElement != null){
	
	//"selectionchange" - the event is processed on IOS devices, where the double tap disables the selection
	document.addEventListener("selectionchange", function() {
		event.preventDefault();
		OnTheDouble();
	}, false);
	
	myElement.style.userSelect = "text";
	var timeout;
	var lastTap = new Date().getTime() - 4000;
/*	myElement.addEventListener("select", function(e) { 
		 
			
		// alert("select");
		OnTheDouble();
		
	});*/
	
	

	var longpress = false;
	var longpressTimer = null;
	var loop = null;
	var latestSelection = null;
	//for Android devices where the "selectionchange" event is not processed as on IOS.
	//the 'touchend' EventListener checks for the double tap after the word selection is made and calls the function to deal with theselected word
	myElement.addEventListener('touchend', function(event) {
	   
		myElement.focus();
		var currentTime = new Date().getTime();
	    var tapLength = currentTime - lastTap;
	    clearTimeout(timeout);
	    if (tapLength < 500 && tapLength > 0) {
			    	 
	    	event.preventDefault();
		
	    	OnTheDouble();		   
	    } else {
	    	
	        timeout = setTimeout(function() {
	            clearTimeout(timeout);
	        }, 500);
		if(longpressTimer){
        		clearTimeout(longpressTimer);
			
 			longpress = false;
    	   	}
	       
	    }
	    
    	  
	    lastTap = currentTime;
	   
	});
	/*
	myElement.addEventListener('touchstart', function(event){
    		longpressTimer = setTimeout(function(){
        		longpress = true;
        		loop = setInterval(getSelection, 200);
    		}, 500)
	});

	
	var getSelection = function (){
    	var s = window.getSelection();
	
    	if(s){
		latestSelection = s.getRangeAt(0);
		event.preventDefault();
		alert("Selection" + s + " " + latestSelection);
		OnTheDouble();
		
    	}else{
        	clearInterval(loop);
        	var selEndEvent = new CustomEvent("selectionEnd", {"detail": latestSelection});
        	window.dispatchEvent(selEndEvent);
    	}
     }*/
		
}


});

