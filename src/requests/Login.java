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
 *Clase que se encarga de procesar las peticiones para el login
 * @author ivan_
 */
public class Login 
{
    Context context;
    ChatFrame chat;
    
    /**
     * Constructor
     * @param contextcontexto
     * @param args argumentos de la petición
     */
    public Login(Context context, JsonObject args)
    {
        this.chat = context.chat;
        chat.removeAll();
        updatePanel(args);
    }
    
    /**
     * Constructor en caso de llegar a limite de pruebas
     * @param args
     * @param context 
     */
    public Login(JsonObject args, Context context) 
    {
        this.context = context;
        JOptionPane alert = new JOptionPane();
        if (args.get("status").getAsBoolean()) 
        {
            context.getLogVent().setVisible(false);
            ChatFrame chat = new ChatFrame(context);
            chat.setVisible(true);
            this.chat = chat;
            context.chat = chat;
            updatePanel(args);
        }
        else 
        {
            alert.showMessageDialog(null, "Credenciales erroneas");
            if (context.getCounter() > 2) {
                alert.showMessageDialog(null, "Por favor, haga el registro");
            }
        }
    }
    /**
     * Se encarga de recibir la lista de usuarios cuando se conecta
     * @param args argumentos de la petición
     */
    public void updatePanel(JsonObject args)
    {
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
                chat.addDisconnected(username);
            });
            JsonArray notifications = args.get("notifications").getAsJsonArray();
            notifications.forEach(u ->
            {
                JsonObject element = u.getAsJsonObject();
                String name  = element.get("origin").getAsString();
                String type = element.get("type").getAsString();
                if(type.equals("group"))
                {
                    chat.addNotification(name, type, element.get("id").getAsString());
                }
                else 
                {
                    chat.addNotification(name, type);
                }
            });
            
            JsonArray amigos = args.get("friends").getAsJsonArray();
            amigos.forEach(u -> 
            {
                JsonObject element = u.getAsJsonObject();
                chat.addFriend(element.get("username").getAsString(), element.get("alias").getAsString());
            });
            
            JsonArray grupos = args.get("groups").getAsJsonArray();
            grupos.forEach( u ->
            {
                JsonObject g = u.getAsJsonObject();
                chat.addGroup(g.get("id").getAsString(),g.get("asunto").getAsString());
            });
    }
}