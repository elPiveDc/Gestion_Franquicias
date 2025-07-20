package com.MiNegocio.configuracioncentral.service;

import okhttp3.*;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class IAService {

    private static String API_KEY;
    private static String API_URL;

    static {
        try (InputStream input = new FileInputStream("config.properties")) {
            Properties prop = new Properties();
            prop.load(input);
            API_KEY = prop.getProperty("ia.api_key");
            API_URL = prop.getProperty("ia.api_url");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("No se pudo cargar la configuración de IA");
        }
    }

    public static String generarSQLDesdePregunta(String prompt) throws Exception {
        OkHttpClient client = new OkHttpClient();

        JSONObject body = new JSONObject()
                .put("message", prompt)
                .put("model", "command-r")
                .put("temperature", 0.3)
                .put("chat_history", new org.json.JSONArray());

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
                System.out.println("Error al llamar a la API Cohere: " + response.code());
                System.out.println("Respuesta: " + response.body().string());
                throw new RuntimeException("Error en la API de Cohere");
            }

            String result = response.body().string();
            JSONObject json = new JSONObject(result);
            return json.getString("text").trim();
        }
    }
}
