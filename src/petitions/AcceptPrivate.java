/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package petitions;

import Views.UnfriendChatFrame;
import com.google.gson.JsonObject;
import controllers.Context;

/**
 *Procesa la petición para aceptar invitación a un chat privado
 * @author ivan_
 */
public class AcceptPrivate {
    
    public AcceptPrivate(JsonObject args, Context context)
    { 
        String request = args.get("request").getAsString();
        
        UnfriendChatFrame fr = new UnfriendChatFrame(request,context);
        
        context.getuChats().put(request, fr);
    }
}
