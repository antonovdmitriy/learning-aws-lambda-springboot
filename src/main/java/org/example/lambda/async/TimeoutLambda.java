package org.example.lambda.async;

import com.amazonaws.services.lambda.runtime.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class TimeoutLambda {


    private final Logger logger = LoggerFactory.getLogger(HelloWorldLambda.class);

    /**
     * to test use some string i.e "ALLA"
     *
     * @param input
     * @param context
     * @throws InterruptedException
     */
    public void handler(Object input, Context context) throws InterruptedException {
        while (true) {
            Thread.sleep(100);
            logger.info("Context.getRemainingTimeInMillis() : " + context.getRemainingTimeInMillis());
        }
    }
}

