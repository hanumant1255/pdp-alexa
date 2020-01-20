package handlers;
import static com.amazon.ask.request.Predicates.intentName;
import static com.amazon.ask.request.Predicates.requestType;

import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.impl.IntentRequestHandler;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.RequestEnvelope;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.SessionEndedRequest;

public class SessionEndedRequestHandler implements IntentRequestHandler {


    
    public boolean canHandle(HandlerInput input, IntentRequest intentRequest) {
        return input.matches(requestType(SessionEndedRequest.class).or(intentName("exitIntent")));
    }

    
    public Optional<Response> handle(HandlerInput input, IntentRequest intentRequest) {
        RequestEnvelope envelope = input.getRequestEnvelope();
                envelope.getSession().getSessionId();
                String speechText="Thank you for using PDP. You are successfully logged out from PDP";
        // any cleanup logic goes here
        return input.getResponseBuilder().withSpeech(speechText).withShouldEndSession(true).build();
    }

}