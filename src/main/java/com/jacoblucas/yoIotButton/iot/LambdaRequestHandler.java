package com.jacoblucas.yoIotButton.iot;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.jacoblucas.yoIotButton.model.IotButtonEvent;
import com.jacoblucas.yoIotButton.model.YoResponse;
import com.jacoblucas.yoIotButton.yo.ContextAwareYo;
import com.jacoblucas.yoIotButton.yo.RandomUserYo;
import com.jacoblucas.yoIotButton.yo.YoRequestSender;

public class LambdaRequestHandler implements RequestHandler<IotButtonEvent, YoResponse> {
    /**
     * Handler for AWS Lambda code.
     * To set up who the Yo is sent from, change the 'apiKey' environment variable in the Lambda configuration.
     * To set up who the Yo is sent to, change the 'username' environment variable in the Lambda configuration.
     */
    public YoResponse handleRequest(IotButtonEvent iotButtonEvent, Context ctx) {
        System.out.println("Initiating Yo from: " + iotButtonEvent);

        YoRequestSender yoRequestSender = new YoRequestSender();

        if (iotButtonEvent.isSingleClick() || iotButtonEvent.isDoubleClick()) {
            return new ContextAwareYo().yo(yoRequestSender);
        } else {
            // long click
            return new RandomUserYo().yo(yoRequestSender);
        }
    }
}
