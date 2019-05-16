/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package requests;

import Views.FriendChatFrame;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import controllers.Context;
import java.util.Hashtable;

/**
 *
 * @author ivan_
 */
public class ChargeConversation 
{ 
    private String with;
    public ChargeConversation(String sender, JsonArray args, Context context)
    {
        with = sender;
        Hashtable<String,FriendChatFrame> chats = context.getChats();
        chats.forEach((user,frame)->
        {
            if(with.equals(user))
            {
                args.forEach((u)->
                {
                    JsonObject msg = u.getAsJsonObject();
                    String usr = msg.get("sender").getAsString();
                    String ms = msg.get("message").getAsString();
                    frame.AgregarMensaje(usr, ms);
                });
            }
        });
    }
}
