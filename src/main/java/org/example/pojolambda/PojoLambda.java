package org.example.pojolambda;

import org.springframework.stereotype.Component;

@Component
public class PojoLambda {

    /**
     * to test  { "information" : "Hello Lambda" }  output { "result" : "Input was Hello Lambda" }
     */
    public Output handlerPojo(Input input) {
        return new Output("Input was " + input.getInformation());
    }
}
