/*
 * page.js
 * Page helper functions
 */

/*
 * Shows the appropriate section requested by the hash
 * segment of the URL. e.g. apps.faithfull.me/geuron/app#step2
 */
$(function() {
	  if(window.location.hash) {
	      var hash = window.location.hash.substring(1);
	      $("#" + hash).show();
	  } else {
		  $("#step1").show();
	  }
})

/*
 * Helper function for step transitions with sexy
 * JQuery animations.
 */
function changeStep(stepName) {
	$('.transient').slideUp(400, function() {
		$('#' + stepName).slideDown(400);
	});
}