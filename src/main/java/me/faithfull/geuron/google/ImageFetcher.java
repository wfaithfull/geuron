package me.faithfull.geuron.google;

import org.apache.commons.httpclient.HttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ImageFetcher {
	
	@SuppressWarnings("unused")
	private HttpClient client;
	@Value("${geuron.googleImages}") private String googleApi;

}
