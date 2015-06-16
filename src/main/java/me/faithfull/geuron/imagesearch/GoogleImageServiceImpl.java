package me.faithfull.geuron.imagesearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class GoogleImageServiceImpl implements ImageService {
	
	private static Log log = LogFactory.getLog(GoogleImageServiceImpl.class);
	
	@Value("${geuron.googleImages}") private String googleApi;

	public Optional<JSONArray> getImages(String terms, int start) {
		String api = googleApi + "&q=";
		
		URLConnection connection = null;
		try {
			URL url = new URL(api + URLEncoder.encode(terms, "UTF-8") + "&rsz=8" + "&start=" + start);
			log.info("Fetching from " + url.toString());
			connection = url.openConnection();
			connection.addRequestProperty("Referer", "apps.faithfull.me/geuron");
			
			String line;
			StringBuilder builder = new StringBuilder();
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			while((line = reader.readLine()) != null) {
				builder.append(line);
			}

			JSONObject json = new JSONObject(builder.toString());
			return Optional.of(json.getJSONObject("responseData").getJSONArray("results"));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Optional.empty();
		
	}
}
