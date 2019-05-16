/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package petitions;

import com.google.gson.JsonObject;
import controllers.Context;

/**
 *Procesa la petición para iniciar una conversación privada
 * @author ivan_
 */
public class NewPrivateRequest 
{    
    public NewPrivateRequest(JsonObject args, Context context)
    {
        String request = args.get("request").getAsString();
        String requester = args.get("requester").getAsString();
        context.getChat().addNotification(requester, "chat");
    }
}