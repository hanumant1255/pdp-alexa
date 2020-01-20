package handlers;
import java.util.Arrays;
import java.util.Optional;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.LaunchRequest;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.services.ups.UpsServiceClient;
import com.amazon.ask.request.Predicates;

import models.Constants;

public class LaunchHandler implements RequestHandler {
    private static final Logger LOG = LogManager.getLogger(LaunchHandler.class);

    @Override
    public boolean canHandle(HandlerInput handlerInput) {
    	LOG.info("Skill started"); 	
        return handlerInput.matches(Predicates.requestType(LaunchRequest.class));
    }
 
	@Override
	public Optional<Response> handle(HandlerInput input) {
		String consentToken = input.getRequestEnvelope().getContext().getSystem().getApiAccessToken();
        if (consentToken != null) {
            UpsServiceClient customerProfileServiceClient = input.getServiceClientFactory().getUpsService();
            String email = customerProfileServiceClient.getProfileEmail();
            LOG.info("user email id"+email);

            if (email == null) {
                return input.getResponseBuilder()
                        .withSpeech(Constants.EMAIL_MISSING)
                        .withShouldEndSession(true)
                        .build();
            } else {
                String speechText = "Welcome to PDP. How may I help you.\n";

                return input.getResponseBuilder()
                        .withSpeech(speechText + Constants.EMAIL_AVAILABLE + email)
                        .withShouldEndSession(false)
                        .build();
            }
        } else {
            return input.getResponseBuilder()
                    .withSpeech(Constants.NOTIFY_MISSING_PERMISSIONS)
                    .withShouldEndSession(true)
                    .withAskForPermissionsConsentCard(Arrays.asList(Constants.NAME_PERMISSION, Constants.EMAIL_PERMISSION, Constants.MOBILE_NUMBER_PERMISSION))
                    .build();
        }
	}
   
}