$(function() {
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

function selectThumb(element) {
	if ($(element).hasClass("selected-thumb")) {
		$(element).removeClass("selected-thumb")
	} else {
		$(element).addClass("selected-thumb");
	}
}