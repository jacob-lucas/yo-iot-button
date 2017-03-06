package com.jacoblucas.yo;

import java.io.IOException;

/**
 * Abstract class for sending Yo requests. To send a Yo to a specific user, extend and implement the yo() function.
 */
abstract class Yo {

    // Current API for sending Yo requests
    static String YO_URL = "https://api.justyo.co/yo/";

    /**
     * Yo! Sends through a given YoRequest through a YoRequestSender.
     * @param req The Yo request.
     * @param yrs The Yo request sender.
     * @return a YoResponse for the request.
     * @throws IOException in case of error sending the Yo request.
     */
    YoResponse sendYo(YoRequest req, YoRequestSender yrs) throws IOException {
        String response = yrs.post(YO_URL, req);
        YoResponse yoResponse = new YoResponse(response);

        System.out.println("Request  = " + req);
        System.out.println("Response = " + yoResponse);

        return yoResponse;
    }

    /**
     * Abstract function for derived classes to implement to send a Yo to a given user.
     * Implementations of the function should contain the API Key and username as part of the Yo request.
     * For example:
     *     public YoResponse yo() throws IOException {
     *         YoRequest sendRequest = YoRequest
     *             .builder()
     *             .apiKey("my-api-key")
     *             .username("JOE")
     *             .build();
     *         return sendYo(sendRequest, new YoRequestSender());
     *     }
     * @return a YoResponse for the Yo!
     * @throws IOException in case of error sending the Yo.
     */
    abstract YoResponse yo() throws IOException;

}
