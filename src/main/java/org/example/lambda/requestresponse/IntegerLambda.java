package org.example.lambda.requestresponse;

import org.springframework.stereotype.Component;


/**
 * Does not work if we are using spring cloud functions but works correct with plain java and aws
 */
@Component
public class IntegerLambda {

    /**
     *  to test use 101 or "101"
     */
    public boolean handlerInteger(Integer input) {
        return input > 100;
    }
}
