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

@FunctionalSpringBootTest(properties = "database.url=my_db")
class EnvVarLambdaWithDbUrlInPropertiesTest {

    @Autowired
    private EnvVarLambda envVarLambda;

    @Autowired
    private FunctionCatalog catalog;

    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    @Test
    void acceptPrintsMessageWhenDatabaseUrlIsNotSpecifiedInEnvVariable() {
        Logger mockLogger = mock(Logger.class);
        ReflectionTestUtils.setField(envVarLambda, "logger", mockLogger);
        ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);
        Consumer<String> consumer = catalog.lookup(Consumer.class, "envVarLambda");
        consumer.accept("");

        verify(mockLogger).info(argumentCaptor.capture());
        assertEquals("DATABASE_URL is set to: my_db", argumentCaptor.getValue());
    }
}