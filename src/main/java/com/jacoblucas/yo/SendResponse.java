package com.jacoblucas.yo;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.Data;
import lombok.NonNull;

@Data
class SendResponse {
    private String yoId;
    private boolean success;
    private Recipient recipient;

    SendResponse(@NonNull String json) {
        JsonObject obj = new JsonParser()
                .parse(json)
                .getAsJsonObject();
        yoId = obj.get("yo_id").getAsString();
        success = obj.get("success").getAsBoolean();
        recipient = new Recipient(obj.get("recipient").getAsJsonObject());
    }
}
