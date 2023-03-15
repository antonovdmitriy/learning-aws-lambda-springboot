package org.example.lambda.requestresponse;

import org.springframework.stereotype.Component;

@Component
public class IntegerLambda {

    /**
     *  to test use 101 or "101"
     */
    public boolean handlerInteger(Integer input) {
        return input > 100;
    }
}
