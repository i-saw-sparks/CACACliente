package Views;

import controllers.Context;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author usuario
 */
public class UnfriendChatFrame extends JFrame{
    
    private GroupLayout orden;
    private String userName;
    private JLabel nombreUsuario;
    private JTextArea mensajesArea;
    private JScrollPane scroll; 
    private JTextField escribirMensaje;
    private JButton enviar;
    Context context;

    
    public UnfriendChatFrame(String name,Context con){
        this.userName=name;
        this.context=con;
        Configuracion();
    }
    
    public void Configuracion(){
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setTitle("Chat con "+userName);
        this.getContentPane().setBackground(Color.black);

        Font LucidNom = new Font("Lucida Sans Typewriter", 1, 15);    
        Font fuente1 = new Font("Calibri", 1, 15);
        Font fuente2 = new Font("Calibri", 1, 20);
        
        enviar=new JButton("Enviar"); 
        enviar.setFont(fuente1);
        enviar.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent a){  
                    EnviarMensaje();
                }             
            }
        );

        nombreUsuario=new JLabel(userName); 
        nombreUsuario.setFont(LucidNom);
        nombreUsuario.setForeground(Color.white);

        mensajesArea=new JTextArea();
        mensajesArea.setFont(fuente1);
        mensajesArea.setEditable(false);
        
        scroll=new JScrollPane(mensajesArea);
        
        escribirMensaje=new JTextField();
        escribirMensaje.setFont(fuente1);
        
        orden=new GroupLayout(this.getContentPane());
        orden.setAutoCreateContainerGaps(true);
        orden.setAutoCreateGaps(true);
        orden.setHorizontalGroup(
        orden.createParallelGroup()
                .addComponent(nombreUsuario)
                .addComponent(scroll,200,500,2000)
                .addGroup(
                        orden.createSequentialGroup()
                        .addComponent(escribirMensaje)
                        .addComponent(enviar) 
                )
                
        );
        orden.setVerticalGroup(
                orden.createSequentialGroup()
                    .addComponent(nombreUsuario)
                .addComponent(scroll,200,400,1000)
                .addGroup(
                        orden.createParallelGroup()
                        .addComponent(escribirMensaje)
                        .addComponent(enviar,20,40,1000) 
                )
        );

        this.setLayout(orden);
        this.pack();
    }
 
    public void EnviarMensaje(){
        ////Aqui se envia el mensaje          escribirMensaje.getText()      es el mensaje a enviar
        mensajesArea.setText("Yo mero:\n" +escribirMensaje.getText()+ "\n\n"+ mensajesArea.getText());
        escribirMensaje.setText("");
    }
    
    public void AgregarMensaje(String origen,String mensaje){
        mensajesArea.setText(origen+ ": \n" +mensaje+ "\n\n"+ mensajesArea.getText());
    }
}
