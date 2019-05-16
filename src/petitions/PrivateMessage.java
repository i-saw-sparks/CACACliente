/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package petitions;

import Views.UnfriendChatFrame;
import com.google.gson.JsonObject;
import controllers.Context;
import java.util.Hashtable;

/**
 *Procesa la petición para enviar mensaje privado
 * @author ivan_
 */
public class PrivateMessage 
{
    private String destinatario;
    private String remitente;
    private String mensaje;
    
    public PrivateMessage(JsonObject args, Context context)
    { 
        destinatario = args.get("destinatario").getAsString();
        remitente = args.get("remitente").getAsString();
        mensaje = args.get("mensaje").getAsString();
        
        Hashtable<String, UnfriendChatFrame> frames = context.getuChats();
        
        if(destinatario.equals(context.getUsername()))
        {
            frames.forEach((_u,_f)-> 
            {
                if(_u.equals(remitente))
                {
                    _f.AgregarMensaje(remitente, mensaje);
                }
            });
        }
        else if(remitente.equals(context.getUsername()))
        {
            frames.forEach((_u,_f)->
            {
                if(_u.equals(destinatario))
                {
                    _f.AgregarMensaje(remitente, mensaje);
                }
            });
        }
        else 
        {
            System.out.println("Ah cabrón");
        }
        
    }
}