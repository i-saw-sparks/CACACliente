package caca;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ppc
 */
public class CACA {

    public static void main(String[] args) {
        Chat_ventana objeto = null;
        try {
            objeto = new Chat_ventana();
        } catch (InterruptedException ex) {
            Logger.getLogger(CACA.class.getName()).log(Level.SEVERE, null, ex);
        }
        objeto.setVisible(true);
        Login_ventana objeto2=new Login_ventana();
        objeto2.setVisible(true);
    }
    
}
