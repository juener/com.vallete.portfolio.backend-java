package transaction.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class TransactionControllerTest {

    @Test()
    public void saveTransactionTest() throws JSONException, IOException, InterruptedException {
    /*
    payload = {
        "name": "name transaction test postman 15",
        "value": 5.60,
        "type": "REVENUE",
        "status": "PENDING",
        "dueDate": "2022-12-20",
        "user": "93979c93-2aa4-406d-b220-c2a10df4226c"
    }
    */

//        JSONObject json = new JSONObject();
//        json.put("someKey", 5.60);

        var values = new HashMap<String, String>() {{
            put("name", "John Doe");
            put ("value", "5.60");
        }};

        var objectMapper = new ObjectMapper();
        String requestBody = objectMapper
                .writeValueAsString(values);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://localhost:8080/portfolio/transaction"))
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body());
    }
}
