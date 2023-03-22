package org.example.pojolambda;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.function.context.FunctionCatalog;
import org.springframework.cloud.function.context.test.FunctionalSpringBootTest;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.test.annotation.DirtiesContext;

import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@FunctionalSpringBootTest
class PojoLambdaTest {

    public static final String FUNCTION_NAME = "pojoLambda";

    @Autowired
    private FunctionCatalog catalog;
    private final ObjectMapper jsonMapper = new ObjectMapper();

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void handlerPojo() throws JsonProcessingException {
        Function<Input, Output> function = catalog.lookup(Function.class, FUNCTION_NAME);
        String payload = "hello";
        Input input = new Input();
        input.setInformation(payload);

        Output output = function.apply(input);
        assertNotNull(output);
        assertEquals("Input was " + payload, output.getResult());
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void handlerPojoJson() throws JsonProcessingException {
        Function<String, GenericMessage<byte[]>> function = catalog.lookup(Function.class, FUNCTION_NAME, "application/json");
        String payload = "hello";
        Input input = new Input();
        input.setInformation(payload);
        String inputJson = jsonMapper.writeValueAsString(input);

        GenericMessage<byte[]> output = function.apply(inputJson);

        String resultJson = new String(output.getPayload());
        assertNotNull(resultJson);
        JsonNode resultNode = jsonMapper.readTree(resultJson).at("/result");
        assertNotNull(resultNode);
        String result = resultNode.asText();
        assertEquals("Input was " + payload, result);
    }
}