package me.faithfull.geuron.google;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Optional;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ImageService {
	
	@Value("${geuron.googleImages}") private String googleApi;

	public Optional<JSONObject> getImages(String terms) {
		String api = googleApi + "&q=";
		
		URLConnection connection = null;
		try {
			URL url = new URL(api + URLEncoder.encode(terms, "UTF-8"));
			connection = url.openConnection();
			connection.addRequestProperty("Referer", "apps.faithfull.me/geuron");
			
			String line;
			StringBuilder builder = new StringBuilder();
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			while((line = reader.readLine()) != null) {
				builder.append(line);
			}

			JSONObject json = new JSONObject(builder.toString());
			return Optional.of(json);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Optional.empty();
		
	}
}
