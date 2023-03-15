package org.example.lambda.requestresponse;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

@Component
public class MapLambda {


    /**
     * to test { "a" : "x", "b" : "y"}
     */
    public Map<String, String> handlerMap(Map<String, String> input) {
        Map<String, String> newMap = new HashMap<>();
        input.forEach((k, v) -> newMap.put("New Map -> " + k, v));
        return newMap;
    }
}
