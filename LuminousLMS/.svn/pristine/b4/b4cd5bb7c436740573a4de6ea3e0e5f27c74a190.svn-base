//Wistia Player Customization

//Increase the filename number by 1 and update references to it whenever you update the code within.
//The purpose here is to defeat browser caching in the event of code changing.

function ctaFinishVideo() {
	document.getElementById('videocompleted').value = "true";
	document.getElementById('ClientVideoForm').submit();
}

window._wq = window._wq || [];
_wq
		.push({
			id : "_all",
			options : {
				playbar: "true",
				plugin : {
					"postRoll-v1" : {
						text : "This clickable message\n will appear after your\n video ends!",
						raw : "<div id=\"wistia_163_postRoll\" class=\"wistia-postroll\" \n"
								+ "style=\"background-color: rgba(255, 255, 0, 0.5); color: rgb(255, 255, 255); font-family: WistiaOpenSans, Helvetica, Arial, sans-serif; font-size: 48px; line-height: 1.2em; position: relative; text-align: center; z-index: 12; height: 288px; opacity: 1;\">\n"
								+ "<a href=\"#\" onClick=\"ctaFinishVideo(); return false;\" \n"
								+ "style=\"padding-bottom: 2px; padding-top: 2px;\"><span>Click Here<br> to finish this video!\" \n"
								+ "</span></a>\n" + "</div>\n"

					}
				}
			}
		});