package org.example.lambda.async;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.function.context.FunctionCatalog;
import org.springframework.cloud.function.context.test.FunctionalSpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;


@FunctionalSpringBootTest
class AsyncHelloWorldLambdaTest {

    @Autowired
    private FunctionCatalog catalog;
    @Autowired
    private HelloWorldLambda asyncHelloWorld;

    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    @Test
    void handler() {
        Logger mockLogger = mock(Logger.class);
        ReflectionTestUtils.setField(asyncHelloWorld, "logger", mockLogger);
        ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);
        Consumer<String> consumer = catalog.lookup(Consumer.class, "asyncHelloWorld");
        String input = "my text";

        consumer.accept(input);

        verify(mockLogger).info(argumentCaptor.capture());
        assertEquals("Hello, " + input, argumentCaptor.getValue());
    }
}