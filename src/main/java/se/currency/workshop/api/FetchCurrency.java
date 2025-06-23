package se.currency.workshop.api;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class FetchCurrency {
    private static final String API_KEY = "25cc1b613714a9063b2722e61259e194";

    public static String fetch() throws Exception {
        OkHttpClient client = new OkHttpClient();

        String url = String.format(
                "https://api.exchangerate.host/live?base=USD&symbols=SEK,EUR&access_key=%s",
                API_KEY
        );

        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful() && response.body() != null) {
                return response.body().string(); // raw JSON
            } else {
                throw new RuntimeException("Request failed: " + response.code());
            }
        }
    }
}
