package com.jacoblucas.yoIotButton.yo;

import com.jacoblucas.yoIotButton.model.YoRequest;
import com.jacoblucas.yoIotButton.model.YoResponse;

public class ContextAwareYo {
    public YoResponse yo(YoRequestSender yoRequestSender) {
        String apiKey = System.getenv("apiKey");
        String username = System.getenv("username");

        YoRequest req = YoRequest
                .builder()
                .apiKey(apiKey)
                .username(username)
                .build();

        try {
            return Yo.sendYo(req, yoRequestSender);
        } catch (Exception e) {
            System.out.println("Error sending yo request [" + req + "] : " + e.toString());
            return new YoResponse("{\"success\":false}");
        }
    }
}
