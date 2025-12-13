package org.sabre.apiclient;


import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

public class APIClientTest {

    public void testGetRequest() throws Exception {
        String baseUrl = "https://jsonplaceholder.typicode.com"; // Example public API
        APIClient apiClient = new APIClient(baseUrl);
        Map<String, String> headers = new HashMap<>();
        headers.put("Accept", "application/json");
        HttpResponse<String> response = apiClient.get("/posts/1", headers);
        Assert.assertEquals(200, response.statusCode());
        Assert.assertTrue(response.body().contains("userId"));
    }
}

