/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package requests;

import Views.GroupChatFrame;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import controllers.Context;
import java.util.Hashtable;

/**
 *Procesa la petición para recibir el historial de un chat de grupo
 * @author ivan_
 */
public class ChargeGroupConversation {
    
    public ChargeGroupConversation(String id,JsonArray args, Context context)
    {
        Hashtable<String, GroupChatFrame> chats = context.getgChats();
        
        chats.forEach((_id, _fr)->
        {
            if(_id.equals(id))
            {
                args.forEach(u->
                {
                    JsonObject mu = u.getAsJsonObject();
                    String sender = mu.get("sender").getAsString();
                    String message = mu.get("message").getAsString();
                    _fr.AgregarMensaje(sender, message);
                });
            }
        });
    }
    
}
