package controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;

import models.NodeBean;
import models.Sound;
import models.ContinuousSound;
import models.SporadicSound;
import models.SoundFactory;
import play.mvc.*;
import java.util.Date;


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



	public Result api_query(String queryType, Integer startTime, Integer endTime) {
		return ok("api get: " + queryType + ",   start " + startTime + ",   end " + endTime);
	}


}
