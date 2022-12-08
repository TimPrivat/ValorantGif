package ValorantGif.ValorantGifProject;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ApiController {

	RestTemplateBuilder rtb = new RestTemplateBuilder();
	RestTemplate restTemplate = rtb.build();
	static final String puuidOfClient = "469dd5cc-d2e7-5a91-bdba-b0fad91fbef3";
	
	public ApiController() {
		
	}
	
	public String getMatchID() {
		
		String apiPath = "https://api.henrikdev.xyz/valorant/v3/by-puuid/matches/eu/" + puuidOfClient;
		
		String result = restTemplate.getForObject(apiPath, String.class);
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		JsonObject temp = (JsonObject) gson.fromJson(result, JsonObject.class);
		JsonArray allData = (JsonArray) temp.get("data");
		JsonObject firstGameData = (JsonObject) allData.get(0);
		JsonObject test = (JsonObject) firstGameData.get("metadata");
		result = test.get("matchid").toString();
		
		
		return result.substring(1, result.length() - 1);
		
	}
	
	public String getAgent(String matchid) {
		
		String apiPath = "https://api.henrikdev.xyz/valorant/v2/match/" + matchid;
		
		String result = restTemplate.getForObject(apiPath, String.class);
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		JsonObject temp = (JsonObject) gson.fromJson(result, JsonObject.class);
		JsonObject allData = (JsonObject) temp.get("data");
		JsonObject player = (JsonObject) allData.get("players");
		JsonArray allPlayers = (JsonArray) player.get("all_players");
		
		for(int i = 0; i < allPlayers.size(); i++) {
			
			JsonObject tempPlayer = (JsonObject) (allPlayers.get(i));
			if(tempPlayer.get("puuid").toString().substring(1, tempPlayer.get("puuid").toString().length() - 1).equals(puuidOfClient)) {
				
				return tempPlayer.get("character").toString().substring(1, tempPlayer.get("character").toString().length() - 1);
				
			}
			
			
			
		}
		
		
		return null;
	}
	
}
