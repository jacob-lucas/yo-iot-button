package com.jacoblucas.yo;

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
