package org.example.lambda.requestresponse;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.function.context.FunctionCatalog;
import org.springframework.cloud.function.context.test.FunctionalSpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;

@FunctionalSpringBootTest
@DirtiesContext
class HelloWorldLambdaTest {

    public static final String HELLO = "Hello";
    @Autowired
    private FunctionCatalog catalog;

    @Test
    public void handler() throws Exception {
        Function<String, String> function = catalog.lookup(Function.class, "helloWorldLambda");
        String result = function.apply(HELLO);
        assertEquals("Hello, " + HELLO, result);
    }
}