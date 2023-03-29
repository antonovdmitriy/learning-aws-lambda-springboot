package org.example.lambda.async;

import com.amazonaws.services.lambda.runtime.Context;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.function.context.FunctionCatalog;
import org.springframework.cloud.function.context.test.FunctionalSpringBootTest;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Map;
import java.util.concurrent.*;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.cloud.function.adapter.aws.AWSLambdaUtils.AWS_CONTEXT;


@FunctionalSpringBootTest
@DirtiesContext
class TimeoutLambdaTest {


    @Autowired
    private FunctionCatalog catalog;

    @Test
    void accept() {
        Function<Message<String>, Map<String, Object>> function = catalog.lookup(Function.class, "timeoutLambda");
        MessageBuilder<String> messageBuilder = MessageBuilder.withPayload("tadam");
        Context mockContext = mock(Context.class);
        Integer expectedRemainingTime = 50;
        when(mockContext.getRemainingTimeInMillis()).thenReturn(expectedRemainingTime);
        messageBuilder.setHeader(AWS_CONTEXT, mockContext);

        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future future = executor.submit(() -> function.apply(messageBuilder.build()));
        boolean interrupted = false;
        try {
            future.get(1, TimeUnit.SECONDS);
        } catch (TimeoutException e) {
            future.cancel(true);
            interrupted = true;
        } catch (Exception e) {
            // NOPE
        } finally {
            executor.shutdownNow();
        }
        assertTrue(interrupted);
    }
}