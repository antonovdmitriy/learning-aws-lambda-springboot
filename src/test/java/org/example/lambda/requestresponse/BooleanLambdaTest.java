package org.example.lambda.requestresponse;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.function.context.FunctionCatalog;
import org.springframework.cloud.function.context.test.FunctionalSpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

@FunctionalSpringBootTest
@DirtiesContext
class BooleanLambdaTest {


    @Autowired
    private FunctionCatalog catalog;
    @Test
    void handlerBoolean() {
        assertThrows(IllegalStateException.class, () -> {
            catalog.lookup(Function.class, "booleanLambda");
        });
    }
}