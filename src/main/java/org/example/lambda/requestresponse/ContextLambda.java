package org.example.lambda.requestresponse;

import com.amazonaws.services.lambda.runtime.Context;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.cloud.function.adapter.aws.AWSLambdaUtils.AWS_CONTEXT;

@Component
public class ContextLambda {


    public static final String REMAINING_TIME_IN_MILLIS = "getRemainingTimeInMillis";

    public Map<String, Object> handler(Message<String> input) {

        Context context = input.getHeaders().get(AWS_CONTEXT, Context.class);

        Map<String, Object> toReturn = new HashMap<>();
        toReturn.put("getMemoryLimitInMB", context.getMemoryLimitInMB() + "");
        toReturn.put("getFunctionName", context.getFunctionName());
        toReturn.put("getFunctionVersion", context.getFunctionVersion());
        toReturn.put("getInvokedFunctionArn", context.getInvokedFunctionArn());
        toReturn.put("getAwsRequestId", context.getAwsRequestId());
        toReturn.put("getLogStreamName", context.getLogStreamName());
        toReturn.put("getLogGroupName", context.getLogGroupName());
        toReturn.put("getClientContext", context.getClientContext());
        toReturn.put("getIdentity", context.getIdentity());
        toReturn.put(REMAINING_TIME_IN_MILLIS,
                context.getRemainingTimeInMillis() + "");
        return toReturn;
    }
}