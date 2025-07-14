package com.MiNegocio.configuracioncentral.service;

import okhttp3.*;
import org.json.JSONObject;

public class IAService {

    private static final String API_KEY = "OBD9F5L8RMdqsxg61W6MDKQs970CPYCH1cU44VeF";
    private static final String API_URL = "https://api.cohere.ai/v1/chat";

    public static String generarSQLDesdePregunta(String prompt) throws Exception {
        OkHttpClient client = new OkHttpClient();

        JSONObject body = new JSONObject()
            .put("message", prompt)
            .put("model", "command-r") // modelo gratuito actual de Cohere
            .put("temperature", 0.3)
            .put("chat_history", new org.json.JSONArray());  // vacía por ahora

        RequestBody requestBody = RequestBody.create(
            body.toString(),
            MediaType.parse("application/json")
        );

        Request request = new Request.Builder()
            .url(API_URL)
            .post(requestBody)
            .addHeader("Authorization", "Bearer " + API_KEY)
            .addHeader("Content-Type", "application/json")
            .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                System.out.println("?? Error al llamar a la API Cohere: " + response.code());
                System.out.println("Respuesta: " + response.body().string());
                throw new RuntimeException("Error en la API de Cohere");
            }

            String result = response.body().string();
            JSONObject json = new JSONObject(result);
            return json.getString("text").trim();
        }
    }
}
