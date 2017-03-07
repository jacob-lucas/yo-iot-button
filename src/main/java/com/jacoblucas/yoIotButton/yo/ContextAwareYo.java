package com.jacoblucas.yoIotButton.yo;

import com.jacoblucas.yoIotButton.model.YoRequest;
import com.jacoblucas.yoIotButton.model.YoResponse;

public class ContextAwareYo extends Yo {
    public YoResponse yo(YoRequestSender yoRequestSender) {
        String apiKey = System.getenv("apiKey");
        String username = System.getenv("username");

        YoRequest req = YoRequest
                .builder()
                .apiKey(apiKey)
                .username(username)
                .build();

        return send(req, yoRequestSender);
    }
}
