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
class ListOfMapLambdaTest {


    public static final String FUNCTION_NAME = "listOfMapLambda";
    @Autowired
    private FunctionCatalog catalog;

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void handlerNestedCollectionJson() {
        Function<String, GenericMessage> function = catalog.lookup(Function.class, FUNCTION_NAME, "application/json");
        String input = "[\n" +
                "      { \"m\" : 1, \"n\" : 2 },\n" +
                "      { \"x\" : 8, \"y\" : 9 }\n" +
                "      ]";
        GenericMessage result = function.apply(input);

        String expectedOutput = "{\"Nested at position 0\":{\"m\":1,\"n\":2},\"Nested at position 1\":{\"x\":8,\"y\":9}}";
        assertEquals(expectedOutput, new String((byte[]) result.getPayload()));
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void handlerNestedCollection() {
        Function<List<Map<String, Integer>>, Map<String, Map<String, Integer>>> function = catalog.lookup(Function.class, FUNCTION_NAME);
        List<Map<String, Integer>> input = List.of(
                Map.ofEntries(
                        Map.entry("m", 1),
                        Map.entry("n", 2)),
                Map.ofEntries(
                        Map.entry("x", 8),
                        Map.entry("y", 9))
        );
        Map<String, Map<String, Integer>> result = function.apply(input);

        Map<String, Map<String, Integer>> expectedOutput = Map.ofEntries(
                Map.entry("Nested at position 0", Map.ofEntries(
                        Map.entry("m", 1),
                        Map.entry("n", 2))),
                Map.entry("Nested at position 1", Map.ofEntries(
                        Map.entry("x", 8),
                        Map.entry("y", 9)
                )));

        assertEquals(expectedOutput, result);
    }
}