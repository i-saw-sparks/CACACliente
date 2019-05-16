/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import requests.FriendRequestResponse;
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

        Sign sign;
        Login login;
        switch (response.get("type").getAsString()) {
            case "sign":
                 sign = new Sign(response.get("status").getAsBoolean());
                break;
            case "login":
                login = new Login(response.get("args").getAsJsonObject(), context);
                break;
            case "friend-request":
                FriendRequestResponse resp = new FriendRequestResponse(response.get("status").getAsBoolean(),context);
                login = new Login(context,response.get("args").getAsJsonObject());
                break;
            case "refresh":
                login = new Login(context,response.get("args").getAsJsonObject());
                break; 
            case "exit":
            case "newGroup":
            case "modifyGroup":
            case "deleteGroup":
            case "modifyAlias":
            case "add-to-group":
                sign = new Sign(response.get("status").getAsBoolean());
                login = new Login(context,response.get("args").getAsJsonObject());
                break;
            default:
        }
    }
}
