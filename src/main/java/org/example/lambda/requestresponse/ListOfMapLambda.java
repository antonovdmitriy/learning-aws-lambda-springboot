package org.example.lambda.requestresponse;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

@Component
public class ListOfMapLambda {

    /**
     * to test
     * [
     * { "m" : 1, "n" : 2 },
     * { "x" : 8, "y" : 9 }
     * ]
     *
     */
    public Map<String, Map<String, Integer>> handlerNestedCollection(List<Map<String, Integer>> input) {
        Map<String, Map<String, Integer>> newMap = new HashMap<>();
        IntStream.range(0, input.size())
                .forEach(i -> newMap.put("Nested at position " + i, input.get(i)));
        return newMap;
    }
}
