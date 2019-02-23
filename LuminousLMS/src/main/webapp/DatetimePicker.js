jQuery(document).ready(function() {
	
	jQuery('#startdatepicker').datetimepicker({
		timeFormat: 'HH:mm z',
		timezone: 'ET',
		timezoneList: [		               
				{ value: 'ET', label: 'Eastern'}, 
				{ value: 'CT', label: 'Central' }, 
				{ value: 'MT', label: 'Mountain' }, 
				{ value: 'PT', label: 'Pacific' } 
	    ],
		dateFormat: 'yy-mm-dd' 
   	});
	
	jQuery('#enddatepicker').datetimepicker({
		timeFormat: 'HH:mm z',
		timezone: 'ET',
		timezoneList: [		               
				{ value: 'ET', label: 'Eastern'}, 
				{ value: 'CT', label: 'Central' }, 
				{ value: 'MT', label: 'Mountain' }, 
				{ value: 'PT', label: 'Pacific' } 
	    ],
		dateFormat: 'yy-mm-dd'
	});
});


$(function() {
    $( "#startdtpicker" ).datepicker({ dateFormat: 'yy-mm-dd' }).val();
  });

$(function() {
    $( "#enddtpicker" ).datepicker({ dateFormat: 'yy-mm-dd' }).val();	
  });

function validateForm() {
		var startDateTestBox = $('#startdatepicker');
		var endDateTestBox = $('#enddatepicker');
		var testStartDate = new Date(startDateTestBox.val().substring(0,10));
		var testEndDate   = new Date(endDateTestBox.val().substring(0,10));
		var testZoneStartDate = startDateTestBox.val().substring(17);
		var testZoneEndDate   = endDateTestBox.val().substring(17);
		
		if (testStartDate == "Invalid Date") {
			alert("Start Time needs to be selected");
			return false;
		}	
		else if (testEndDate == "Invalid Date") {
			alert("End Time needs to be selected");
			return false;
		}
		else if (testStartDate > testEndDate) {
			alert("Start Time greater than End Time");
			return false;
		}
		else if (testZoneStartDate.localeCompare(testZoneEndDate) != 0)  {
			alert("TimeZone StartTime different to TimeZone EndTime");
			return false;
		}
}