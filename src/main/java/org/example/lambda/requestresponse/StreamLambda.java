package org.example.lambda.requestresponse;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

@Component
public class StreamLambda {

    /**
     * to test use "my simple text"
     */
    public String handlerStream(InputStream inputStream) throws IOException {
        return new String(inputStream.readAllBytes()).toUpperCase();
    }
}