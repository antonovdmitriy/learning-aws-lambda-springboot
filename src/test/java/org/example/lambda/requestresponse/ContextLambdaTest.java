package org.example.lambda.requestresponse;

import com.amazonaws.services.lambda.runtime.Context;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.function.context.FunctionCatalog;
import org.springframework.cloud.function.context.test.FunctionalSpringBootTest;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Map;
import java.util.function.Function;

import static org.example.lambda.requestresponse.ContextLambda.REMAINING_TIME_IN_MILLIS;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.cloud.function.adapter.aws.AWSLambdaUtils.AWS_CONTEXT;

@FunctionalSpringBootTest
@DirtiesContext
class ContextLambdaTest {


    @Autowired
    private FunctionCatalog catalog;

    @Test
    void handlerReturnsExpectedRemainingTIme() {
        Function<Message<String>, Map<String, Object>> function = catalog.lookup(Function.class, "contextLambda");
        MessageBuilder<String> messageBuilder = MessageBuilder.withPayload("tadam");
        Context mockContext = mock(Context.class);
        Integer expectedRemainingTime = 50;
        when(mockContext.getRemainingTimeInMillis()).thenReturn(expectedRemainingTime);
        messageBuilder.setHeader(AWS_CONTEXT, mockContext);

        Map<String, Object> result = function.apply(messageBuilder.build());

        assertNotNull(result);
        assertEquals(expectedRemainingTime.toString(), result.get(REMAINING_TIME_IN_MILLIS));
    }

    @Test
    void handlerReturnsEmptyMapIfContextIsNull() {
        Function<Message<String>, Map<String, Object>> function = catalog.lookup(Function.class, "contextLambda");
        MessageBuilder<String> messageBuilder = MessageBuilder.withPayload("tadam");

        Map<String, Object> result = function.apply(messageBuilder.build());

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void handlerReturnsEmptyMapIfMessageIsNull() {
        ContextLambda function = new ContextLambda();

        Map<String, Object> result = function.handler(null);

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
}