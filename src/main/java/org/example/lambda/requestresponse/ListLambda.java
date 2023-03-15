package org.example.lambda.requestresponse;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ListLambda {

    /**
     * to test  [ 1, 2, 3 ]
     */
    public List<Integer> handlerList(List<Integer> input) {
        List<Integer> newList = new ArrayList<>();
        input.forEach(x -> newList.add(100 + x));
        return newList;
    }
}
