/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package requests;

import javax.swing.JOptionPane;

/**
 *
 * @author ivan_
 */
public class Sign 
{
    public Sign(boolean status) 
    {
        JOptionPane alert = new JOptionPane();
        if (status) 
        {
            alert.showMessageDialog(null, "El usuario ha sido registrado correctamente");
        }
        else 
        {
            alert.showMessageDialog(null, "Ha ocurrido un error y no se ha podido registrar el usuario."
                    + "Pruebe con otro nombre de usuario.");
        }
    }
}
