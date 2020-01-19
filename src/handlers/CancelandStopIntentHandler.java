package handlers;
import static com.amazon.ask.request.Predicates.intentName;

import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.impl.IntentRequestHandler;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Response;

public class CancelandStopIntentHandler implements IntentRequestHandler {

	@Override
	public boolean canHandle(HandlerInput input, IntentRequest intentRequest) {
        return input.matches(intentName("AMAZON.StopIntent").or(intentName("AMAZON.CancelIntent")).or(intentName("AMAZON.NoIntent")));

	}

	@Override
	public Optional<Response> handle(HandlerInput input, IntentRequest intentRequest) {
		   String speechText = "Goodbye. Come back soon to PDP";
	        return input.getResponseBuilder()
	                .withSpeech(speechText)
	                .withSimpleCard("PDP ", speechText)
	                .build();
	}
}