package com.jacoblucas.yoIotButton.yo;

import com.jacoblucas.yoIotButton.model.YoRequest;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class YoRequestSender {

    /**
     * Post a Yo request.
     * Reproduced from http://docs.justyo.co/docs/getting-started.
     * @param url The Yo API URL to post the request to.
     * @param req The Yo request.
     * @return A String representation of the raw response.
     * @throws IOException in case of error posting the Yo request.
     */
    String postYoRequest(String url, YoRequest req) throws IOException {
        URL obj = new URL(url);
        HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

        con.setRequestMethod("POST");

        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes("api_token=" + req.getApiKey() + "&username=" + req.getUsername());
        wr.flush();
        wr.close();

        return parseResponse(con.getInputStream());
    }

    /**
     * Issues a GET request for all the user's contacts. The user is identified by the provided access token.
     * @param accessToken The access token representing the user.
     * @return A List of all the user's contacts, or an empty list in case of error.
     */
    String getContacts(String url, String accessToken) throws IOException {
        URL obj = new URL(url + "?access_token=" + accessToken);
        HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
        con.setRequestMethod("GET");

        return parseResponse(con.getInputStream());
    }

    private String parseResponse(InputStream is) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(is));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        return response.toString();
    }

}