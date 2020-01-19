package handlers;
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
        return input.matches(requestType(SessionEndedRequest.class));
    }

    
    public Optional<Response> handle(HandlerInput input, IntentRequest intentRequest) {
        RequestEnvelope envelope = input.getRequestEnvelope();
                envelope.getSession().getSessionId();
        // any cleanup logic goes here
        return input.getResponseBuilder().build();
    }

}