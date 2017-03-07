package com.jacoblucas.yoIotButton.model;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.Data;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

@Data
public class ContactsResponse {
    private List<Contact> contacts;

    public ContactsResponse(@NonNull String json) {
        contacts = new ArrayList<>();

        JsonObject obj = new JsonParser()
                .parse(json)
                .getAsJsonObject();

        JsonArray arr = obj.get("contacts").getAsJsonArray();
        for (int i=0; i<arr.size(); i++) {
            Contact c = new Contact(arr.get(i).getAsJsonObject());
            contacts.add(c);
        }
    }
}
