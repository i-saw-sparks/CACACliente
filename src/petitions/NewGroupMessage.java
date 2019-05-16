/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package petitions;

import Views.GroupChatFrame;
import com.google.gson.JsonObject;
import controllers.Context;
import java.util.Hashtable;

/**
 *
 * @author ivan_
 */
public class NewGroupMessage 
{
    private String id;
    private String remitente;
    private String message;
    
    public NewGroupMessage(JsonObject args, Context context)
    { 
        id = args.get("id").getAsString();
        remitente = args.get("remitente").getAsString();
        message = args.get("mssg").getAsString();
        
        Hashtable<String, GroupChatFrame> chats = context.getgChats();
        
        chats.forEach((_id, _fr) ->
        {
            if(_id.equals(id))
            {
                _fr.AgregarMensaje(remitente, message);
            }
        });
    }
}
