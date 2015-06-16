package me.faithfull.geuron.imagesearch;

import java.util.Optional;

import org.codehaus.jettison.json.JSONArray;

public interface ImageService {
	
	Optional<JSONArray> getImages(String query, int page);
}
