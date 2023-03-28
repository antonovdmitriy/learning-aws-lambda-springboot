package org.example.lambda.async;

import com.amazonaws.services.lambda.runtime.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

import static org.springframework.cloud.function.adapter.aws.AWSLambdaUtils.AWS_CONTEXT;

@Component
public class TimeoutLambda implements Consumer<Message<String>> {


    private final Logger logger = LoggerFactory.getLogger(HelloWorldLambda.class);

    @Override
    public void accept(Message<String> input) {

        Context context = input.getHeaders().get(AWS_CONTEXT, Context.class);
        if (context != null) {
            int count = 0;
            while (count < 10000) {
                try {
                    Thread.sleep(100);
                    logger.info("Context.getRemainingTimeInMillis() : " + context.getRemainingTimeInMillis());
                    count++;
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException(e);
                }
            }
        }
    }
}

