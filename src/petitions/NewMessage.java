/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package petitions;

import Views.FriendChatFrame;
import com.google.gson.JsonObject;
import controllers.Context;
import java.util.Hashtable;

/**
 *Procesa la petición para enviar un nuevo mensaje
 * @author ivan_
 */
public class NewMessage 
{ 
    private String destinatario;
    private String remitente;
    public NewMessage(JsonObject args, Context context)
    {
        destinatario = args.get("destinatario").getAsString();
        remitente = args.get("remitente").getAsString();
        System.out.println(destinatario+remitente+context.getUsername());
        
        if(destinatario.equals(context.getUsername()))
        {
            System.out.println("Soy destinatario");
            Hashtable<String, FriendChatFrame> chats = context.getChats();
            chats.forEach( (usr, frm) -> 
            {
                if(usr.equals(remitente))
                {
                    frm.AgregarMensaje(remitente, args.get("mssg").getAsString());
                }
            });
        }
        else if(remitente.equals(context.getUsername()))
        {
            System.out.println("Soy remitente");
            Hashtable<String, FriendChatFrame> chats = context.getChats();
            chats.forEach( (usr, frm) -> 
            {
                if(usr.equals(destinatario))
                {
                    frm.AgregarMensaje(args.get("remitente").getAsString(), args.get("mssg").getAsString());
                }
            });
        }
        else 
        {
            System.out.println("WTF");
        }
    }    
}
