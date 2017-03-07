package com.jacoblucas.yoIotButton.yo;

import com.jacoblucas.yoIotButton.model.YoRequest;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

class YoRequestSender {

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

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        return response.toString();
    }

}