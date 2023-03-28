package org.example.lambda.requestresponse;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.function.Function;

@Component
public class StreamLambda implements Function<InputStream, String> {


    @Override
    public String apply(InputStream inputStream) {
        try {
            return new String(inputStream.readAllBytes()).toUpperCase();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}