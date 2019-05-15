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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ivan_
 */
public class FriendAccept 
{ 
    
    public FriendAccept(Context context, String name, boolean status)
    {
        try 
        {
            JsonObject response = new JsonObject();
            response.addProperty("type", "accept-request");
            JsonObject args = new JsonObject();
            args.addProperty("username", context.getUsername() );
            args.addProperty("friend", name);
            args.addProperty("status", status);
            response.add("args", args);
            Gson gson = new Gson();
            context.getConnection().getOutputStream().write(gson.toJson(response).getBytes());
            
        } catch (IOException ex) {
            Logger.getLogger(FriendAccept.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
