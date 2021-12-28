package org.ye.yeoldegrep.handler;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class YeHandler {
    public static void ye() {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.kanye.rest"))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());

            System.out.println("\""+ response.body().split("\"")[3] +"\" ~ Ye West");
            System.exit(1);
        } catch (Exception e) {
        }
    }
}
