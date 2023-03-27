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
class IntegerLambdaWithBooleanTest {

    public static final String FUNCTION_NAME = "integerLambdaWithBoolean";
    @Autowired
    private FunctionCatalog catalog;

    @Test
    void functionReturnsTrueWhenArgumentMoreThenOneHundred() {
        Function<Integer, Boolean> function = catalog.lookup(Function.class, FUNCTION_NAME);
        assertTrue(function.apply(101));
    }

    @Test
    void functionReturnsFalseWhenArgumentLessThenOneHundred() {
        Function<Integer, Boolean> function = catalog.lookup(Function.class, FUNCTION_NAME);
        assertFalse(function.apply(25));
    }
}