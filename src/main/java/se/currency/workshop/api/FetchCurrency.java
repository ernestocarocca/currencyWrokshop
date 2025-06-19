package se.currency.workshop.api;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class FetchCurrency {
    public static String fetch() throws Exception {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://api.exchangerate.host/live?access_key=25cc1b613714a9063b2722e61259e194")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful() && response.body() != null) {
                return response.body().string(); // ✅ returnerar bara här
            } else {
                throw new RuntimeException("Request failed: " + response.code());
            }
        }
    }
}
