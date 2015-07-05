package me.faithfull.geuron.controllers;

import java.io.IOException;
import java.util.Optional;

import me.faithfull.geuron.imagesearch.GoogleThumb;
import me.faithfull.geuron.imagesearch.ImageService;

import org.codehaus.jettison.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class AppController {
	
	@Autowired
	private ImageService imageService;

	@RequestMapping(value="/app/images/{terms}", produces="application/json", method=RequestMethod.GET)
	@ResponseBody
	String images(@PathVariable("terms")String terms) throws JsonParseException, JsonMappingException, IOException {
		Optional<JSONArray> possibleJSON = imageService.getImages(terms, 0);
		
		JSONArray images = possibleJSON.get();
		
		ObjectMapper mapper = new ObjectMapper();
		GoogleThumb[] thumbs = mapper.readValue(images.toString(), GoogleThumb[].class);
		return mapper.writeValueAsString(thumbs);
	}
	
	@RequestMapping(value="/app/images/{terms}/{page}", produces="application/json", method=RequestMethod.GET)
	@ResponseBody
	String images(@PathVariable("terms")String terms, @PathVariable("page")int page) throws JsonParseException, JsonMappingException, IOException {
		Optional<JSONArray> possibleJSON = imageService.getImages(terms, page);
		
		JSONArray images = possibleJSON.get();
		
		ObjectMapper mapper = new ObjectMapper();
		GoogleThumb[] thumbs = mapper.readValue(images.toString(), GoogleThumb[].class);
		return mapper.writeValueAsString(thumbs);
	}
}
