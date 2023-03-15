package org.example.lambda.requestresponse;

import org.springframework.stereotype.Component;

@Component
public class BooleanLambda {

    /**
     * to test use true or "true"
     */
    public boolean handlerBoolean(boolean input) {
        return !input;
    }
}
