package controllers;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

import models.ContinuousSound;
import models.NodeBean;
import models.SoundFactory;
import models.SporadicSound;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

public class HomeController extends Controller {

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
		ObjectNode result = Json.newObject();
		
		switch(queryType) {
		  case "contCount":
			  	result = handleContinuousSoundsCount(startTime, endTime);
				return ok(result);
		  case "spoCount":
			  	result = handleSporadicSoundsCount(startTime, endTime);
				return ok(result);
		  case "contSounds":
			    result = handleContinuousSounds(startTime, endTime);
				return ok(result);
		  case "spoSounds":
			    result = handleSporadicSounds(startTime, endTime);
				return ok(result);   
		  case "contSoundsIntersect":
			  	result = handleContinuousSoundsWithIntersectingSporadics(startTime, endTime);
				return ok(result);
		  default:
			  return ok("Something went terribly wrong --- please check your query type");
		}
	}

	private Object getFieldFromCursor(DBObject o, String fieldName) {

		final String[] fieldParts = StringUtils.split(fieldName, '.');

		int i = 1;
		Object val = o.get(fieldParts[0]);

		while (i < fieldParts.length && val instanceof DBObject) {
			val = ((DBObject) val).get(fieldParts[i]);
			i++;
		}

		return val;
	}
	
	private ObjectNode handleContinuousSoundsCount(Long startTime, Long endTime) {
		ContinuousSound contSound = new ContinuousSound();
		ObjectNode result = Json.newObject();
		BasicDBObject query = new BasicDBObject("endTime", new BasicDBObject("$gt", startTime).append("$lte", endTime));
		Long count = contSound.getCountOfContinuousSounds(query);
		result.put("count of continuous", count);
		return result;
	}
	
	private ObjectNode handleSporadicSoundsCount(Long startTime, Long endTime) {
		SporadicSound spoSound = new SporadicSound();
		ObjectNode result = Json.newObject();
		BasicDBObject query = new BasicDBObject("startTime", new BasicDBObject("$gt", startTime).append("$lte", endTime));
		Long count = spoSound.getCountOfSporadicSounds(query);
		result.put("count of sporadic", count);
		return result;
	}
	
	private ObjectNode handleContinuousSounds(Long startTime, Long endTime) {
		ContinuousSound contSound = new ContinuousSound();
		ObjectNode result = Json.newObject();
		BasicDBObject query = new BasicDBObject("endTime", new BasicDBObject("$gt", startTime).append("$lte", endTime));
		List<DBObject> sounds = contSound.getContinuousSounds(query);
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			String arrayToJson = objectMapper.writeValueAsString(sounds);
			result.put("continuous sounds", arrayToJson);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	private ObjectNode handleSporadicSounds(Long startTime, Long endTime) {
		SporadicSound spoSound = new SporadicSound();
		ObjectNode result = Json.newObject();
		BasicDBObject query = new BasicDBObject("startTime", new BasicDBObject("$gt", startTime).append("$lte", endTime));
		List<DBObject> sounds = spoSound.getSporadicSounds(query);
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			String arrayToJson = objectMapper.writeValueAsString(sounds);
			result.put("sporadic sounds", arrayToJson);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	private ObjectNode handleContinuousSoundsWithIntersectingSporadics(Long startTime, Long endTime) {
		ContinuousSound contSound = new ContinuousSound();
		SporadicSound spoSound = new SporadicSound();
		ObjectNode result = Json.newObject();
		BasicDBObject query = new BasicDBObject("endTime", new BasicDBObject("$gt", startTime).append("$lte", endTime));
		List<DBObject> sounds = contSound.getContinuousSounds(query);
		List<DBObject> listOfsoundsWithIntersects = new ArrayList<DBObject>();
		for (DBObject sound : sounds) {
			try {
				Object start = getFieldFromCursor(sound, "startTime");
				Object end = getFieldFromCursor(sound, "endTime");
				System.out.println("---start:  " + start + "---end:  " + end);
				DBObject entry = new BasicDBObject();
				entry.put("sound", sound);
				BasicDBObject subquery = new BasicDBObject("startTime", new BasicDBObject("$gt", start).append("$lte", end));
				List<DBObject> sporadicSounds = spoSound.getSporadicSounds(subquery);
				ObjectMapper objectMapper = new ObjectMapper();
				String arrayToJson = objectMapper.writeValueAsString(sporadicSounds);
				entry.put("interestingSporadic", arrayToJson);
				listOfsoundsWithIntersects.add(entry);
				System.out.println(entry);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}

		try {
			ObjectMapper objectMapper = new ObjectMapper();
			String arrayToJson = objectMapper.writeValueAsString(listOfsoundsWithIntersects);
			result.put("continuous sounds with intersects", arrayToJson);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return result;
	}

}
