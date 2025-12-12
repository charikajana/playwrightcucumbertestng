package org.sabre.apiclient;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Map;

public class APIClient {
    private final HttpClient httpClient;
    private final String baseUrl;
    private String authToken;
    private String authHeaderName = "Authorization";

    public APIClient(String baseUrl) {
        this.baseUrl = baseUrl;
        this.httpClient = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(10))
                .build();
    }

    /**
     * Set the authentication token to be used in all requests.
     * @param token The token value (e.g., "Bearer ..." or just the token string)
     */
    public void setAuthToken(String token) {
        this.authToken = token;
    }

    /**
     * Optionally set the header name for the token (default is "Authorization").
     */
    public void setAuthHeaderName(String headerName) {
        this.authHeaderName = headerName;
    }

    private void addAuthHeader(Map<String, String> headers) {
        if (authToken != null && !authToken.isEmpty()) {
            if (headers != null) {
                headers.put(authHeaderName, authToken);
            }
        }
    }

    public HttpResponse<String> get(String endpoint, Map<String, String> headers) throws IOException, InterruptedException {
        addAuthHeader(headers);
        HttpRequest.Builder builder = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + endpoint))
                .GET();
        if (headers != null) {
            headers.forEach(builder::header);
        }
        HttpRequest request = builder.build();
        return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public HttpResponse<String> post(String endpoint, String body, Map<String, String> headers) throws IOException, InterruptedException {
        addAuthHeader(headers);
        HttpRequest.Builder builder = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + endpoint))
                .POST(HttpRequest.BodyPublishers.ofString(body));
        if (headers != null) {
            headers.forEach(builder::header);
        }
        HttpRequest request = builder.build();
        return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public HttpResponse<String> put(String endpoint, String body, Map<String, String> headers) throws IOException, InterruptedException {
        addAuthHeader(headers);
        HttpRequest.Builder builder = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + endpoint))
                .PUT(HttpRequest.BodyPublishers.ofString(body));
        if (headers != null) {
            headers.forEach(builder::header);
        }
        HttpRequest request = builder.build();
        return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public HttpResponse<String> delete(String endpoint, Map<String, String> headers) throws IOException, InterruptedException {
        addAuthHeader(headers);
        HttpRequest.Builder builder = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + endpoint))
                .DELETE();
        if (headers != null) {
            headers.forEach(builder::header);
        }
        HttpRequest request = builder.build();
        return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public HttpResponse<String> patch(String endpoint, String body, Map<String, String> headers) throws IOException, InterruptedException {
        addAuthHeader(headers);
        HttpRequest.Builder builder = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + endpoint))
                .method("PATCH", HttpRequest.BodyPublishers.ofString(body));
        if (headers != null) {
            headers.forEach(builder::header);
        }
        HttpRequest request = builder.build();
        return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public HttpResponse<String> head(String endpoint, Map<String, String> headers) throws IOException, InterruptedException {
        addAuthHeader(headers);
        HttpRequest.Builder builder = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + endpoint))
                .method("HEAD", HttpRequest.BodyPublishers.noBody());
        if (headers != null) {
            headers.forEach(builder::header);
        }
        HttpRequest request = builder.build();
        return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public HttpResponse<String> options(String endpoint, Map<String, String> headers) throws IOException, InterruptedException {
        addAuthHeader(headers);
        HttpRequest.Builder builder = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + endpoint))
                .method("OPTIONS", HttpRequest.BodyPublishers.noBody());
        if (headers != null) {
            headers.forEach(builder::header);
        }
        HttpRequest request = builder.build();
        return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }
}
