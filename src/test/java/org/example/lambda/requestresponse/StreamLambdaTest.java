package org.example.lambda.requestresponse;

import org.example.LambdaApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.function.adapter.aws.FunctionInvoker;
import org.springframework.cloud.function.context.FunctionCatalog;
import org.springframework.cloud.function.context.test.FunctionalSpringBootTest;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.test.annotation.DirtiesContext;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

class StreamLambdaTest {

    @Test
    void handlerStream() throws IOException {

        System.setProperty("MAIN_CLASS", LambdaApplication.class.getName());
        System.setProperty("spring.cloud.function.definition", "streamLambda");
        FunctionInvoker invoker = new FunctionInvoker();

        InputStream targetStream = new ByteArrayInputStream("\"tadam\"".getBytes());
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        invoker.handleRequest(targetStream, output, null);
        String result = new String(output.toByteArray(), StandardCharsets.UTF_8);
        System.out.println(result);
    }
}