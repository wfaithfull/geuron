$(function() {
	
	$('.photoset-grid-custom').photosetGrid({
		  // Set the gutter between columns and rows
		  gutter: '5px',
		  // Manually set the grid layout
		  layout: '21',
		  // Wrap the images in links
		  highresLinks: true,
		  // Asign a common rel attribute
		  rel: 'print-gallery',

		  onInit: function(){},
		  onComplete: function(){
		    // Show the grid after it renders
		    $('.photoset-grid-custom').attr('style', '');
		  }
		});
	
	$('#searchTerms').submit(function(args) {
		$('#grid').empty();
		getThumbs($('#searchTermsInput').val(), 0, 8);
		$("#grid").on('click', 'img.thumb', function() {
			selectThumb(this);
		});
		return false;
	})
})

var chosenThumbs = [];

/*
 * Calls the geuron API which retrieves thumbnails from the
 * concrete image search service.
 */
function getThumbs(searchTerms, start, batchSize) {

	console.log("api request... " + searchTerms);
	var max = 50;
	$.ajax({
		type : 'GET',
		url : window.location.href + "/images/" + searchTerms.toString() + "/"
				+ start.toString(),
		success : function(result) {
			console.log(result);

			for (i = 0; i < result.length; i++) {
				thumb = result[i];
				$("#grid").append($("<img>", {
					src : thumb.tbUrl,
					id : thumb.imageId,
					class : "thumb"
				}));

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
	if ($(element).hasClass("selected-thumb")) {
		$(element).removeClass("selected-thumb")
	} else {
		$(element).addClass("selected-thumb");
	}
}