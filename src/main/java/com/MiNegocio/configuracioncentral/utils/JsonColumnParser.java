package com.MiNegocio.configuracioncentral.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

public class JsonColumnParser {

    public static List<String> extraerNombresColumnas(String columnasJson) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode columnasArray = mapper.readTree(columnasJson);
        List<String> nombres = new ArrayList<>();

        for (JsonNode col : columnasArray) {
            nombres.add(col.get("nombre").asText());
        }

        return nombres;
    }
}
