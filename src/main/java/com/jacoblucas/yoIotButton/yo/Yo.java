package com.jacoblucas.yoIotButton.yo;

import com.jacoblucas.yoIotButton.model.Contact;
import com.jacoblucas.yoIotButton.model.ContactsResponse;
import com.jacoblucas.yoIotButton.model.YoRequest;
import com.jacoblucas.yoIotButton.model.YoResponse;

import java.io.IOException;
import java.util.List;

/**
 * Class for interfacing with the Yo API.
 */
class Yo {

    // Current API for sending Yo requests
    static String YO_URL = "https://api.justyo.co/";

    /**
     * Yo! Sends through a given YoRequest through a YoRequestSender.
     * @param req The Yo request.
     * @param yrs The Yo request sender.
     * @return a YoResponse for the request.
     * @throws IOException in case of error sending the Yo request.
     */
    static YoResponse sendYo(YoRequest req, YoRequestSender yrs) throws IOException {
        String response = yrs.postYoRequest(YO_URL + "yo/", req);
        YoResponse yoResponse = new YoResponse(response);

        System.out.println("Request  = " + req);
        System.out.println("Response = " + yoResponse);

        return yoResponse;
    }

    /**
     * Gets a list of contacts for a given Yo user, identified by the provided access token.
     * @param accessToken an access token used to identify the user who owns the list of contacts.
     * @param yrs The Yo request sender.
     * @return a list of Contacts for the user identified by the provided access token.
     * @throws IOException in case of error sending the Yo request.
     */
    static List<Contact> getContacts(String accessToken, YoRequestSender yrs) throws IOException {
        String response = yrs.getContacts(YO_URL + "contacts/", accessToken);
        ContactsResponse contactsResponse = new ContactsResponse(response);
        List<Contact> contacts = contactsResponse.getContacts();
        System.out.println("Found " + contacts.size() + " contacts: " + contacts);
        return contacts;
    }

}
