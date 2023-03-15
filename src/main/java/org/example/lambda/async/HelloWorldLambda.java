package org.example.lambda.async;

import org.springframework.stereotype.Component;

@Component("asyncHelloWorld")
public class HelloWorldLambda {

    /**
     * to test use some string i.e "ALLA"
     */
    public void handler(String s) {
        System.out.println("Hello, " + s);
    }
}