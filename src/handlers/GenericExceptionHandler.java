package handlers;
import java.util.Optional;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.amazon.ask.dispatcher.exception.ExceptionHandler;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Response;

import models.Constants;


public class GenericExceptionHandler implements ExceptionHandler {
    private static final Logger LOG = LogManager.getLogger(GenericExceptionHandler.class);

    @Override
    public boolean canHandle(HandlerInput handlerInput, Throwable throwable) {
        return true;
    }

    @Override
    public Optional<Response> handle(HandlerInput handlerInput, Throwable throwable) {
        throwable.printStackTrace();
        LOG.info("Error occured "+throwable.getMessage());
        return handlerInput.getResponseBuilder()
                .withSpeech(Constants.ERROR)
                .build();
    }
}