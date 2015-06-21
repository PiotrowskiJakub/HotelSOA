$( document ).ready(function() {

	$('#responseButton').click( function(evt){
		if($('#complaint_response').is(":hidden")){
			$('#complaint_response').slideDown("slow");
		} else {
			$('#complaint_response').hide();
		}
	});
});