package org.example.lambda.requestresponse;

import org.springframework.stereotype.Component;

@Component
public class IntLambda {

    /**
     * to test use 101 or "101"
     */
    public boolean handlerInt(int input) {
        return input > 100;
    }
}
