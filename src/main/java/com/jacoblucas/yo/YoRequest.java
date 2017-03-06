package com.jacoblucas.yo;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
class YoRequest {
    private String apiKey;
    private String username;
}
