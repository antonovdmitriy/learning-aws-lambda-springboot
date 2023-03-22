package org.example.lambda.requestresponse;

import org.springframework.stereotype.Component;

/**
 * Does not work if we are using spring cloud functions but works correct with plain java and aws
 */
@Component
public class IntLambda {

    /**
     * to test use 101 or "101"
     */
    public boolean handlerInt(int input) {
        return input > 100;
    }
}
