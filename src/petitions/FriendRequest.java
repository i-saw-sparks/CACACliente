/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package petitions;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import controllers.Context;
import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *Procesa la petición para enviar solicitud de amista
 * @author ivan_
 */
public class FriendRequest {
    
    public FriendRequest(Context context, String receiver)
    {
        try {
            OutputStream out = context.getConnection().getOutputStream();
            JsonObject packet = new JsonObject();
            packet.addProperty("type", "friend-request");
            JsonObject args = new JsonObject();
            args.addProperty("user", context.getUsername());
            args.addProperty("request", receiver);
            
            packet.add("args",args);
            Gson gson = new Gson();
            
            String send = gson.toJson(packet);
            
            out.write(send.getBytes());
            
        } catch (IOException ex) 
        {
            Logger.getLogger(FriendRequest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
