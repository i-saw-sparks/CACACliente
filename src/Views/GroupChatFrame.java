package Views;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import controllers.Context;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class GroupChatFrame extends JFrame{
    
    private GroupLayout orden;
    private String groupName;
    private JLabel nombreGrupo;
    private JTextArea mensajesArea;
    private JScrollPane scroll; 
    private JTextField escribirMensaje;
    private JButton enviar;
    private String id;
    Context context;

    
    public GroupChatFrame(String id,String name,Context con){
        this.groupName=name;
        this.context=con;
        this.id = id;
        Configuracion();
    }
    
    public void Configuracion(){
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setTitle("Grupo "+groupName);
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

        nombreGrupo=new JLabel("Grupo "+groupName); 
        nombreGrupo.setFont(LucidNom);
        nombreGrupo.setForeground(Color.white);

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
                .addComponent(nombreGrupo)
                .addComponent(scroll,200,500,2000)
                .addGroup(
                        orden.createSequentialGroup()
                        .addComponent(escribirMensaje)
                        .addComponent(enviar) 
                )
                
        );
        orden.setVerticalGroup(
                orden.createSequentialGroup()
                    .addComponent(nombreGrupo)
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
 
    public void EnviarMensaje()
    {
        try {
            JsonObject req = new JsonObject();
            
            req.addProperty("type", "newGroupMessage");
            
            JsonObject args = new JsonObject();
            
            args.addProperty("groupId", id);
            args.addProperty("remitente", context.getUsername());
            args.addProperty("mssg", escribirMensaje.getText());
            
            
            req.add("args", args);
            
            context.getConnection().getOutputStream().write(new Gson().toJson(req).getBytes());
            escribirMensaje.setText("");
        } catch (IOException ex) {
            Logger.getLogger(GroupChatFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void AgregarMensaje(String origen,String mensaje)
    {
        mensajesArea.setText(mensajesArea.getText()+origen+ ": \n" +mensaje+ "\n\n");
    }
}
