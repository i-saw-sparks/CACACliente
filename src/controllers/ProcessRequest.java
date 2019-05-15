/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import requests.Login;
import requests.Sign;

/**
 *
 * @author manie
 */
public class ProcessRequest {

    private static JsonParser parser;

    static {
        parser = new JsonParser();
    }

    public static void processRequest(String request, Context context) {
        JsonObject response;
        try {
            response = parser.parse(request).getAsJsonObject();
        } catch (IllegalStateException ex) {
            return;
        }

        switch (response.get("type").getAsString()) {
            case "sign":
                Sign sign = new Sign(response.get("status").getAsBoolean());
                break;
            case "login":
                Login login = new Login(response.get("args").getAsJsonObject(), context);
                break;
            default:
                break;
        }
    }
}
