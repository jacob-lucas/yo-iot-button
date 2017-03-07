package com.jacoblucas.yoIotButton.model;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.Data;
import lombok.NonNull;

@Data
public class YoResponse {
    private String yoId;
    private boolean success;
    private Contact recipient;

    public YoResponse(@NonNull String json) {
        JsonObject obj = new JsonParser()
                .parse(json)
                .getAsJsonObject();
        yoId = obj.get("yo_id").getAsString();
        success = obj.get("success").getAsBoolean();
        recipient = new Contact(obj.get("recipient").getAsJsonObject());
    }
}
