package devopsui;


import com.amazon.ask.Skill;
import com.amazon.ask.SkillStreamHandler;
import com.amazon.ask.Skills;

import handlers.CancelandStopIntentHandler;
import handlers.CreateFutureIntentHandler;
import handlers.FallBackIntentHandler;
import handlers.GenericExceptionHandler;
import handlers.HelpIntentHandler;
import handlers.LaunchHandler;
import handlers.ServiceExceptionHandler;
import handlers.SessionEndedRequestHandler;

public class PdpAlexaStreamHandler extends SkillStreamHandler {

    private static Skill getSkill() {
        return Skills.standard()
                .addRequestHandlers(
                        new LaunchHandler(),
                        new CreateFutureIntentHandler(),
                		 new CancelandStopIntentHandler(),
                         new HelpIntentHandler(),
                         new SessionEndedRequestHandler(),
                         new FallBackIntentHandler()                    
                       //new UnhandledIntentHandler()
                         )
                .addExceptionHandler(new ServiceExceptionHandler())
                .addExceptionHandler(new GenericExceptionHandler())
                // Add your skill id below and uncomment to enable skill ID verification
                .withSkillId("amzn1.ask.skill.5314b8ac-f44f-4714-a6cd-1b45bdc40212")
                .build();
    }

    public PdpAlexaStreamHandler() {
        super(getSkill());
    }

}
