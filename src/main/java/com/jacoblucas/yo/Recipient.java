package com.jacoblucas.yo;

import com.google.gson.JsonObject;
import lombok.Data;

@Data
class Recipient {
    private String userId;
    private String username;
    private int yoCount;

    Recipient(JsonObject obj) {
        userId = obj.get("user_id").getAsString();
        username = obj.get("username").getAsString();
        yoCount = obj.get("yo_count").getAsInt();
    }
}
