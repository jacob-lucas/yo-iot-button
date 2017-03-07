package com.jacoblucas.yoIotButton.yo;

import com.jacoblucas.yoIotButton.model.YoRequest;
import com.jacoblucas.yoIotButton.model.YoResponse;

abstract class Yo {
    YoResponse send(YoRequest yoRequest, YoRequestSender yoRequestSender) {
        try {
            return YoApi.sendYo(yoRequest, yoRequestSender);
        } catch (Exception e) {
            System.out.println("Error sending yo request [" + yoRequest + "] : " + e.toString());
            return errorResponse();
        }
    }

    YoResponse errorResponse() {
        return new YoResponse("{\"success\":false}");
    }
}
