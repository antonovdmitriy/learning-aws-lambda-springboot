package org.example.lambda.requestresponse;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.function.context.FunctionCatalog;
import org.springframework.cloud.function.context.test.FunctionalSpringBootTest;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.function.Function;


@FunctionalSpringBootTest
class ListLambdaTest {

    public static final String FUNCTION_NAME = "listLambda";
    @Autowired
    private FunctionCatalog catalog;

    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    @Test
    void handlerList() {
        Function<List<Integer>, List<Integer>> function = catalog.lookup(Function.class, FUNCTION_NAME);
        List<Integer> result = function.apply(List.of(10, 20));
        assertEquals(List.of(110, 120), result);
    }

    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    @Test
    void handlerListJson() {
        Function<String, GenericMessage<byte[]>> function = catalog.lookup(Function.class, FUNCTION_NAME, "application/json");
        GenericMessage<byte[]> result = function.apply("[ 1, 2, 3 ]");
        assertEquals("[101,102,103]", new String(result.getPayload()));
    }
}