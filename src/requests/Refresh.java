/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package requests;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import controllers.Context;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *Se encarga de refrescar la ventana y mostrar los botones actualizados
 * @author ivan_
 */
public class Refresh 
{ 
    public Refresh(Context context)
    {
        try 
        {
            JsonObject request = new JsonObject();
            request.addProperty("type", "refresh");
            JsonObject args = new JsonObject();
            args.addProperty("username", context.getUsername());
            request.add("args", args);
                        
            context.getConnection().getOutputStream().write(new Gson().toJson(request).getBytes());
        } catch (IOException ex) {
            Logger.getLogger(Refresh.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
