package controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

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
	public Result index() {
		
		return ok(views.html.index.render());
	}

	public Result api_post() {
		ObjectMapper mapper = new ObjectMapper();

		JsonNode jsonNode = request().body().asJson();
		
		
		System.out.println(jsonNode);
		try {

			NodeBean incomingSound = mapper.treeToValue(jsonNode, NodeBean.class);
			Boolean isContinuous = checkIfContinuous(incomingSound);

			// SoundFactory soundFactory = new SoundFactory();
			// Sound processedSound = null;
			// processedSound = soundFactory.makeSound(incomingSound);

			if (isContinuous) {
				Boolean isConsequtiveEvent = checkIfConsequtiveEvent(incomingSound);
				if (isConsequtiveEvent) {
					System.out.println(
							"This really shouldnt have printed. something went wrong   - " + isConsequtiveEvent);
				} else {
					ContinuousSound continuosSound = null;
					continuosSound = makeContinuousSound(incomingSound);
					persistToContinuousCollection(continuosSound);
				}
			} else {
				SporadicSound sporadicSound = null;
				sporadicSound = makeSporadicSound(incomingSound);
				persistToSporadicCollection(sporadicSound);
			}

			// System.out.println(incomingSound.getSound());
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return ok("Got json: " + jsonNode);

	}

	private ContinuousSound makeContinuousSound(NodeBean incomingSound) {
		// TODO Auto-generated method stub
		String type = incomingSound.getSound();
		String senderIP = incomingSound.getSenderIP();

		Date date = new Date();
		Long startTime = date.getTime();

		ContinuousSound processedSound = null;
		processedSound = new ContinuousSound();
		processedSound.setType(type);
		processedSound.setStartTime(startTime);
		processedSound.setEndTime(startTime);
		processedSound.setSenderIP(senderIP);
		processedSound.connectToDataBase();
		return processedSound;

	}

	private SporadicSound makeSporadicSound(NodeBean incomingSound) {
		String type = incomingSound.getSound();
		String senderIP = incomingSound.getSenderIP();

		Date date = new Date();
		Long startTime = date.getTime();

		SporadicSound processedSound = null;
		processedSound = new SporadicSound();
		processedSound.setType(type);
		processedSound.setStartTime(startTime);
		processedSound.setSenderIP(senderIP);
		return processedSound;

	}

	private void persistToSporadicCollection(SporadicSound processedSound) {
		// TODO Auto-generated method stub
		System.out.println("This is a Sporadic Sound which has type   - " + processedSound.getType()
				+ " and start time - " + processedSound.getStartTime());

	}

	private void persistToContinuousCollection(ContinuousSound processedSound) {
		// TODO Auto-generated method stub

		System.out.println("This is a continuous sound - MONGODB  - should have  " + processedSound.getEndTime());

	}

	private Boolean checkIfContinuous(NodeBean incomingSound) {
		// TODO Auto-generated method stub
		String isContinuousString = incomingSound.getIs_continuous();
		Boolean isContinuous = Boolean.valueOf(isContinuousString);
		return isContinuous;

	}

	private Boolean checkIfConsequtiveEvent(NodeBean incomingSound) {
		// must edit once MongoDb is set up
		Boolean isConsequtive = false;
		return isConsequtive;
	}

	public Result api_query(String queryType, Integer startTime, Integer endTime) {
		return ok("api get: " + queryType + ",   start " + startTime + ",   end " + endTime);
	}


}
