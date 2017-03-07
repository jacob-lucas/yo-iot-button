package com.jacoblucas.yoIotButton.model;

import com.google.gson.JsonObject;
import lombok.Data;

@Data
class Contact {
    private String userId;
    private String username;
    private int yoCount;

    Contact(JsonObject obj) {
        userId = obj.get("user_id").getAsString();
        username = obj.get("username").getAsString();
        yoCount = obj.get("yo_count").getAsInt();
    }
}
