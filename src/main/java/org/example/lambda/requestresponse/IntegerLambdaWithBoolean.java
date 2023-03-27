package org.example.lambda.requestresponse;

import org.springframework.stereotype.Component;

@Component
public class IntegerLambdaWithBoolean {

    /**
     *  to test use 101 or "101"
     */
    public Boolean handlerInteger(Integer input) {
        return input > 100;
    }
}
