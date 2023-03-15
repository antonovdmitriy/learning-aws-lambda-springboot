package org.example.lambda.requestresponse;


import org.springframework.stereotype.Component;

@Component
public class HelloWorldSpringComponent {

    private final HelloWorldLambda inner;

    public HelloWorldSpringComponent(HelloWorldLambda inner) {
        this.inner = inner;
    }

    public String handler(String s) {
        return inner.handler(s);
    }

}
