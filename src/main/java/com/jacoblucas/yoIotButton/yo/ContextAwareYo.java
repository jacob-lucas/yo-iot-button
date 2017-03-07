package com.jacoblucas.yoIotButton.yo;

import com.jacoblucas.yoIotButton.model.YoRequest;
import com.jacoblucas.yoIotButton.model.YoResponse;
import lombok.Builder;

import java.io.IOException;

@Builder
public class ContextAwareYo extends Yo {
    private YoRequest yoRequest;

    @Override
    public YoResponse yo() throws IOException {
        return sendYo(yoRequest, new YoRequestSender());
    }
}
