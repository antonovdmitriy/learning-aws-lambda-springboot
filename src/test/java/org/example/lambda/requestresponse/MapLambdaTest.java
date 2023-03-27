package org.example.lambda.requestresponse;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.function.context.FunctionCatalog;
import org.springframework.cloud.function.context.test.FunctionalSpringBootTest;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;


@FunctionalSpringBootTest
class MapLambdaTest {


    public static final String FUNCTION_NAME = "mapLambda";
    @Autowired
    private FunctionCatalog catalog;

    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    @Test
    void handlerMap() {
        Function<Map<String, String>, Map<String, String>> function = catalog.lookup(Function.class, FUNCTION_NAME);
        Map<String, String> result = function.apply(Map.ofEntries(
                Map.entry("a", "x"),
                Map.entry("b", "y")
        ));
        assertEquals(Map.ofEntries(
                Map.entry("New Map -> a", "x"),
                Map.entry("New Map -> b", "y")), result);
    }

    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    @Test
    void handlerMapJson() {
        Function<String, GenericMessage<byte[]>> function = catalog.lookup(Function.class, FUNCTION_NAME, "application/json");
        GenericMessage<byte[]> result = function.apply("{ \"a\" : \"x\", \"b\" : \"y\"}");
        assertEquals("{\"New Map -> a\":\"x\",\"New Map -> b\":\"y\"}", new String(result.getPayload()));
    }
}