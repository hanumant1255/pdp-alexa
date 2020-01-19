package handlers;

import static com.amazon.ask.request.Predicates.intentName;

import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.impl.IntentRequestHandler;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Response;

public class HelpIntentHandler implements IntentRequestHandler {

	 @Override
    public boolean canHandle(HandlerInput input, IntentRequest intentRequest) {
        return input.matches(intentName("AMAZON.HelpIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input, IntentRequest intentRequest) {
        String speechText = "I can tell you about PDP. Try saying tell about PDP";
        return input.getResponseBuilder()
                .withSpeech(speechText)
                .withSimpleCard("Airplane Facts", speechText)
                .withReprompt(speechText)
                .build();
    }

}
