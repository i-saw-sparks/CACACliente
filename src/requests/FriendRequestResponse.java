/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package requests;

import controllers.Context;
import javax.swing.JOptionPane;

/**
 *Procesa la petición para enviar una petición de amistad
 * @author ivan_
 */
public class FriendRequestResponse {
    
    public FriendRequestResponse(boolean status, Context context)
    {
        JOptionPane alert = new JOptionPane();
        if(status)
        {
            alert.showMessageDialog(null, "Solicitud enviada");
        }
        else 
        {
            alert.showMessageDialog(null, "Error, no se ha podido enviar la solicitud"
                    + "\nrevise que no haya una solicitud del sujeto en notificaciones "
                    + "\no que no haya enviado una solicitud antes");
        }
    }   
}
