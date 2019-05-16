/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package requests;

import javax.swing.JOptionPane;

/**
 *Procesa la petición para enviar una petición de registro
 * @author ivan_
 */
public class Sign 
{
    JOptionPane alert = new JOptionPane();
    
    public Sign(boolean status, String extra)
    {
        
    }
    
    public Sign(boolean status) 
    {
        if (status) 
        {
            alert.showMessageDialog(null, "Operación completada con éxito");
        }
        else 
        {
            alert.showMessageDialog(null, "La operación no ha podido ser completada"
                    + "\n con éxito");
        }
    }
}
