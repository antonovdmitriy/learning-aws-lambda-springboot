package org.example.lambda.async;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component("asyncHelloWorld")
public class HelloWorldLambda implements Consumer<String> {


    private final Logger logger = LoggerFactory.getLogger(HelloWorldLambda.class);

    /**
     * to test use some string i.e "ALLA"
     */
    @Override
    public void accept(String s) {
        logger.info("Hello, " + s);
    }
}