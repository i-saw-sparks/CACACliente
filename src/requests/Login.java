/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package requests;

import Views.ChatFrame;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import controllers.Context;
import javax.swing.JOptionPane;

/**
 *
 * @author ivan_
 */
public class Login 
{
    public Login(JsonObject args, Context context) 
    {
        JOptionPane alert = new JOptionPane();
        if (args.get("status").getAsBoolean()) 
        {
            context.getLogVent().setVisible(false);
            ChatFrame chat = new ChatFrame(context);
            chat.setVisible(true);
            
            context.chat = chat;
            
            JsonArray connected = args.get("connected").getAsJsonArray();
            connected.forEach(u -> 
            {
                JsonObject user = u.getAsJsonObject();
                String username = user.get("username").getAsString();
                System.out.println(username);
                chat.addConnected(username);
            });
            JsonArray disconnected = args.get("disconnected").getAsJsonArray();
            disconnected.forEach(u->
            {
                JsonObject user = u.getAsJsonObject();
                String username = user.get("username").getAsString();
                System.out.println(username);
                chat.addDisconnected(username);
            });
            JsonArray notifications = args.get("notifications").getAsJsonArray();
            notifications.forEach(u ->
            {
                JsonObject element = u.getAsJsonObject();
                String name  = element.get("origin").getAsString();
                String type = element.get("type").getAsString();
                chat.addNotification(name, type);
            });
        }
        else 
        {
            alert.showMessageDialog(null, "Credenciales erroneas");
            if (context.getCounter() > 2) {
                alert.showMessageDialog(null, "Por favor, haga el registro");
            }
        }
    }
}