package com.jacoblucas.yoIotButton.yo;

import com.jacoblucas.yoIotButton.model.Contact;
import com.jacoblucas.yoIotButton.model.YoRequest;
import com.jacoblucas.yoIotButton.model.YoResponse;

import java.io.IOException;
import java.util.List;
import java.util.Random;

public class RandomUserYo extends Yo {
    public YoResponse yo(YoRequestSender yoRequestSender) {
        String username;
        try {
            String accessToken = System.getenv("accessToken");
            Contact random = findRandomContactForUser(accessToken, yoRequestSender);
            if (random == null) {
                System.out.println("Unable to send random Yo - no contacts found for access token [" + accessToken + "].");
                return errorResponse();
            }
            username = random.getUsername();
            System.out.println("Sending random yo to: " + random);
        } catch (Exception e) {
            System.out.println("Error finding contacts: " + e.toString());
            return errorResponse();
        }

        String apiKey = System.getenv("apiKey");
        YoRequest req = YoRequest
                .builder()
                .apiKey(apiKey)
                .username(username)
                .build();

        return send(req, yoRequestSender);
    }

    private Contact findRandomContactForUser(String accessToken, YoRequestSender yoRequestSender) throws IOException {
        List<Contact> contactList = YoApi.getContacts(accessToken, yoRequestSender);
        if (contactList.isEmpty()) {
            return null;
        }

        int n = new Random().nextInt(1024) % contactList.size();
        return contactList.get(n);
    }
}
