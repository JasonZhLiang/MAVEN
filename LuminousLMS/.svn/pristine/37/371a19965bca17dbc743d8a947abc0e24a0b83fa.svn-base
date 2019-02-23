$(document).ready(function() {
	
	console.log("in Bilingual.js");
	
	var language = $('#langtf').val();
	console.log("language = " + language);
	
	if(language == 'Français'){
		var elementsInputs = document.getElementsByTagName("input");
		console.log("elementsInputs.length = " + elementsInputs.length);
		for (var inputCounter = 0; inputCounter < elementsInputs.length; inputCounter++) {
			var element = elementsInputs[inputCounter];
				console.log("$(element).attr('value') = " + $(element).attr('value'));
				
				if ($(element).attr("value") == 'Upload file') {
					$(element).attr("value", "Soumettre");
				};
				
				$('#resourcenamespan').text("Entrer un nomme pour la ressource:");
				$('#uploadresourcetitle').text("Télécharger un Ressource");
				$('#adminlandingtitle').text("Page d'Aterrissage du Admin");
				$('#introP').text("C'est seulement une place réservé pour le present.");
				$('#notimplementedtitle').text("Pas Encore Implementé");
				$('#notimplementedP').text("Pas encore implementé.");
				$('#viewvideostitle').text("Voir les videos");
				$('#titleth').text("Titre");
				$('#viewth').text("Voir");
				$('#transcriptth').text("Transcription");
				$('#viewresourcestitle').text("Voir les ressources");
		}
	}
});