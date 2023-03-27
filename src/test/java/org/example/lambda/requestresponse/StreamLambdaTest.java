package org.example.lambda.requestresponse;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.function.context.FunctionCatalog;
import org.springframework.cloud.function.context.test.FunctionalSpringBootTest;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.test.annotation.DirtiesContext;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

@FunctionalSpringBootTest
class StreamLambdaTest {

    public static final String FUNCTION_NAME = "streamLambda";
    @Autowired
    private FunctionCatalog catalog;

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void handlerStream() {
        Function<InputStream, String> function = catalog.lookup(Function.class, FUNCTION_NAME);
        String input = "my text";
        String result = function.apply(new ByteArrayInputStream(input.getBytes()));
        assertEquals(input.toUpperCase(), result);
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void handlerStreamJson() {
        Function<InputStream, GenericMessage<byte[]>> function = catalog.lookup(Function.class, FUNCTION_NAME, "application/json");
        String input = "\"my text\"";
        GenericMessage<byte[]> result = function.apply(new ByteArrayInputStream(input.getBytes()));
        assertEquals(input.toUpperCase(), new String(result.getPayload()));
    }
}