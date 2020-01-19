package handlers;

import static com.amazon.ask.request.Predicates.intentName;

import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.impl.IntentRequestHandler;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.Slot;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import models.Features;

public class CreateFutureIntentHandler implements IntentRequestHandler{
	 @Override
	public boolean canHandle(HandlerInput input, IntentRequest intentRequest) {
        return input.matches(intentName("createFeatureIntent").or(intentName("AMAZON.YesIntent")));
	}
	 @Override
public Optional<Response> handle(HandlerInput input, IntentRequest intentRequest) {
		Map<String, Slot> slots = intentRequest.getIntent().getSlots();
		String featureName=slots.get("applicationName").getValue();
		String branchName=slots.get("branchName").getValue();
		Features feature=new Features();
		feature.setFeatureName(featureName);
		feature.setBranchName(branchName);
		HttpHeaders headers = new HttpHeaders();
		ObjectMapper objectMapper = new ObjectMapper();

		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = null;
		try {
			request = new HttpEntity<String>(objectMapper.writeValueAsString(feature),headers);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		final String url = "https://6k7oq6kw7c.execute-api.us-east-1.amazonaws.com/Prod/createFeature";
		
	    RestTemplate restTemplate = new RestTemplate();
	    
	    ResponseEntity<String> rs=restTemplate.exchange(url, HttpMethod.POST,request, String.class);
			
	    return input.getResponseBuilder()
                .withSpeech(rs.getBody())
                .build();
	}

}
