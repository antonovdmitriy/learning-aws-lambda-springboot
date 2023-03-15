package org.example.lambda.requestresponse;

import org.springframework.stereotype.Component;

@Component
public class HelloWorldLambda {

    /**
     * to test use some string i.e "ALLA"
     */
    public String handler(String s) {
        return "Hello, " + s;
    }

}
