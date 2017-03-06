package com.jacoblucas.yo;

public class LambdaRequestHandler {
    /**
     * Handler for AWS Lambda code.
     * To set up who the Yo is sent from, change the 'apiKey' environment variable in the Lambda configuration.
     * To set up who the Yo is sent to, change the 'username' environment variable in the Lambda configuration.
     */
    public YoResponse handleRequest() {
        String apiKey = System.getenv("apiKey");
        String username = System.getenv("username");

        YoRequest req = YoRequest
                .builder()
                .apiKey(apiKey)
                .username(username)
                .build();

        ContextAwareYo contextAwareYo = ContextAwareYo
                .builder()
                .yoRequest(req)
                .build();

        try {
            return contextAwareYo.yo();
        } catch (Exception e) {
            System.out.println("Error sending yo request [" + req + "] : " + e.toString());
            return null;
        }
    }
}
