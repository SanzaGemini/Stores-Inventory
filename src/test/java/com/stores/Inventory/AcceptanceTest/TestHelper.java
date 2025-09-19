package com.stores.Inventory.AcceptanceTest;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.gson.JsonObject;
import kong.unirest.Unirest;

public class TestHelper {
    private static final String rootUrl = "http://localhost:8080/api/v1";

    public static JsonNode getHandler(String location){
        return (JsonNode) Unirest.get(rootUrl + location).asJson();
    }

    public static JsonNode postHandler(String location,JsonObject body){
        return (JsonNode) Unirest.post(rootUrl+location).body(body);
    }

    public static JsonObject buildProduct(String name, String description, Double price, int quantity){
        JsonObject jsonObject = new JsonObject();

        jsonObject.addProperty("name",name);
        jsonObject.addProperty("description",description);
        jsonObject.addProperty("price",price);
        jsonObject.addProperty("quantity",quantity);

        return jsonObject;
    }
}
