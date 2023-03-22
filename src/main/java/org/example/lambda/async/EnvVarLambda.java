package org.example.lambda.async;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
public class EnvVarLambda implements Consumer<Object> {

    private final Logger logger = LoggerFactory.getLogger(EnvVarLambda.class);
    @Value("${database.url:#{null}}")
    private String databaseUrl;

    @Override
    public void accept(Object o) {
        if (databaseUrl == null || databaseUrl.isEmpty())
            logger.info("DATABASE_URL is not set");
        else
            logger.info("DATABASE_URL is set to: " + databaseUrl);
    }
}