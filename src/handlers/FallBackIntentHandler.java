package handlers;

import static com.amazon.ask.request.Predicates.intentName;

import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.impl.IntentRequestHandler;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Response;

public class FallBackIntentHandler implements IntentRequestHandler{

	 @Override
    public boolean canHandle(HandlerInput input, IntentRequest intentRequest) {
        return input.matches(intentName("AMAZON.FallbackIntent"));
    }

	 @Override
    public Optional<Response> handle(HandlerInput input, IntentRequest intentRequest) {
        String speechText = "Sorry, I don't know that. Would you like an PDP?";
        return input.getResponseBuilder()
                .withSpeech(speechText)
                .withSimpleCard("Airplane Facts", speechText)
                .withReprompt(speechText)
                .build();
    }

}
