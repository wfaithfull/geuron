$(function() {
	//google.load("search", "1");
	//imageSearch = new google.search.ImageSearch();
	//imageSearch.setSearchCompleteCallback(this, getThumbs, null);
	
	$('#searchTerms').submit(function(args){
		$('#grid').empty();
		getThumbs($('#searchTermsInput').val(), 0, 8);
		return false;
	})
})

function getThumbs(searchTerms, start, batchSize) {
	
	console.log("api request... " + searchTerms);
	var max = 50;
	$.ajax({
		type: 'GET',
		url: window.location.href + "/images/" + searchTerms.toString() + "/" + start.toString(),
		success: function(result) {
			console.log(result);
			
			for(i=0;i<result.length;i++) {
				thumb = result[i];
				$("#grid").append($("<img>", { src : thumb.tbUrl }));
			}
			if(start < max) {
				start = start + batchSize;
				getThumbs(searchTerms, start, batchSize);
			}
		}
	})
	
}