/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import petitions.AcceptPrivate;
import petitions.NewGroupMessage;
import petitions.NewMessage;
import petitions.NewPrivateRequest;
import petitions.PrivateMessage;
import requests.ChargeConversation;
import requests.ChargeGroupConversation;
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
        System.out.println(request);
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
            case "ok":
                sign = new Sign(response.get("status").getAsBoolean());
                break;
            case "exit":
            case "newGroup":
            case "modifyGroup":
            case "deleteGroup":
            case "modifyAlias":
            case "add-to-group":
            case "accept-group-request":
                sign = new Sign(response.get("status").getAsBoolean());
                login = new Login(context,response.get("args").getAsJsonObject());
                break;
            case "responseChat":
                new ChargeConversation(response.get("with").getAsString(),response.get("args").getAsJsonArray(),context);
                break;
            case "responseGroup":
                new ChargeGroupConversation(response.get("id").getAsString(),response.get("args").getAsJsonArray(),context);
                break;
            case "newPersonalMssg":
                System.out.println("Recibí mensaje");
                new NewMessage(response.get("args").getAsJsonObject(),context);
                break;
            case "newGroupMssg":
                new NewGroupMessage(response.get("args").getAsJsonObject(),context);
                break;
            case "new-private":
                new NewPrivateRequest(response.get("args").getAsJsonObject(),context);
                break;
            case  "accept-private":
                new AcceptPrivate(response.get("args").getAsJsonObject(),context);
                break;
            case "privateMessage":
                new PrivateMessage(response.get("args").getAsJsonObject(),context);
                break;
            default:
                System.out.println("No coincide");
                System.out.println(request);
        }
    }
}
