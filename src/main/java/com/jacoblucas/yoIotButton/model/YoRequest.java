package com.jacoblucas.yoIotButton.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class YoRequest {
    private String apiKey;
    private String username;
}
