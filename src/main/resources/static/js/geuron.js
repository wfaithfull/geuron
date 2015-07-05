$(function() {
	
	$('#searchTerms').submit(function(args) {
		$('#searchGrid').empty();
		getThumbs($('#searchTermsInput').val(), 0, 8);
		return false;
	})
})

var chosenThumbs = [];

/*
 * Calls the geuron API which retrieves thumbnails from the
 * concrete image search service.
 */
function getThumbs(searchTerms, start, batchSize) {
	var max = 50;
	$.ajax({
		type : 'GET',
		url : window.location.href.replace(/\?.*/,'') + "/images/" + searchTerms.toString() + "/"
				+ start.toString(),
		success : function(result) {

			for (i = 0; i < result.length; i++) {
				thumb = result[i];
				$("#searchGrid").append($("<img>", {
					src : thumb.tbUrl,
					id : thumb.imageId,
					class : "thumb"
				}));
				
				$('#' + thumb.imageId).click(function() {
					selectThumb(this)
				})
			}

			if (start < max) {
				start = start + batchSize;
				getThumbs(searchTerms, start, batchSize);
			}
		}
	})

}

/*
 * When passed an <img> tag, visually selects it for the user.
 * TODO: Add / remove chosen thumb to / from collection.
 */
function selectThumb(element) {
	if ($('#trainingGrid').find(element).length) {
		$('#trainingGrid > #' + element.id).remove();
		
		var index = chosenThumbs.indexOf(element.src);
		if(index != -1) {
			chosenThumbs.splice(index, 1);
		}
	} else {
		$('#trainingGrid').append(element);
		chosenThumbs.push(element.src)
	}
}