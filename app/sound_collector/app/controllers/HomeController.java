package controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.QueryBuilder;
import com.mongodb.ServerAddress;

import models.NodeBean;
import models.Sound;
import models.ContinuousSound;
import models.SporadicSound;
import models.SoundFactory;
import play.libs.Json;
import play.mvc.*;
import java.util.Date;
import java.util.List;




/**
 * This controller contains an action to handle HTTP requests to the
 * application's home page.
 */
public class HomeController extends Controller {
	
	/**
	 * An action that renders an HTML page with a welcome message. The configuration
	 * in the <code>routes</code> file means that this method will be called when
	 * the application receives a <code>GET</code> request with a path of
	 * <code>/</code>.
	 */
	
	MongoClient mMongoClient;
	DB mDB;
	DBCollection mContinuousCollection;
	
	

	public Result api_post() {
		ObjectMapper mapper = new ObjectMapper();
		JsonNode jsonNode = request().body().asJson();
		try {
			NodeBean incomingSound = mapper.treeToValue(jsonNode, NodeBean.class);
			SoundFactory soundFactory = new SoundFactory();
			soundFactory.makeSound(incomingSound);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return ok("Got json: " + jsonNode);
	}



	public Result api_query(String queryType, Long startTime, Long endTime) {
		ContinuousSound contSound = null;
		contSound = new ContinuousSound();
		
		SporadicSound spoSound = null;
		spoSound = new SporadicSound();
		
		ObjectNode result = Json.newObject();
		
		BasicDBObject query = null;
		

		if(queryType.equals("contCount")) {
			query = new BasicDBObject("endTime", new BasicDBObject("$gt", startTime).append("$lte", endTime));
			Long count = contSound.getCountOfContinuousSounds(query);
			result.put("count of continuous", count);
			return ok(result);
		}else if(queryType.equals("spoCount")){
			query = new BasicDBObject("startTime", new BasicDBObject("$gt", startTime).append("$lte", endTime));
			Long count = spoSound.getCountOfSporadicSounds(query);
			result.put("count of sporadic", count);
			return ok(result);
		}else if(queryType.equals("contSounds")){
			try {
				query = new BasicDBObject("endTime", new BasicDBObject("$gt", startTime).append("$lte", endTime));
				List<DBObject> sounds = contSound.getContinuousSounds(query);
				ObjectMapper objectMapper = new ObjectMapper();
				String arrayToJson;
				arrayToJson = objectMapper.writeValueAsString(sounds);
				result.put("continuous sounds", arrayToJson);
				return ok(result);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
				return ok("Something went terribly wrong --- please check your query type");
			}
			
		}else if(queryType.equals("spoSounds")){
			try {
				query = new BasicDBObject("startTime", new BasicDBObject("$gt", startTime).append("$lte", endTime));
				List<DBObject> sounds = spoSound.getSporadicSounds(query);
				ObjectMapper objectMapper = new ObjectMapper();
				String arrayToJson;
				arrayToJson = objectMapper.writeValueAsString(sounds);
				result.put("sporadic sounds", arrayToJson);
				return ok(result);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
				return ok("Something went terribly wrong --- please check your query type");
			}
		}else {
			return ok("Something went terribly wrong --- please check your query type");
		}	
	}


}
