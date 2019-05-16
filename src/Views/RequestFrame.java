package Views;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import controllers.Context;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import petitions.FriendAccept;

/**
 *
 * @author usuario
 */
public class RequestFrame extends JFrame{
    
    private JButton[] botones;
    GroupLayout orden;
    private String tipo,nombre;
    private JLabel Etiqueta;
    Context context;
    String id;
    
    public RequestFrame(String name,String type, Context con){
        this.tipo=type;
        this.nombre=name;
        this.context=con;
        Configuracion();
        
    }
    
    public RequestFrame(String name, String type, Context con, String id)
    {
        this.tipo=type;
        this.nombre=name;
        this.context=con;
        this.id = id;
        Configuracion();
    }
    
    public void Configuracion(){
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setTitle(SetLabel());
        this.getContentPane().setBackground(Color.black);

        Font LucidNom = new Font("Lucida Sans Typewriter", 1, 15);    
        Font fuente1 = new Font("Calibri", 1, 15);
        Font fuente2 = new Font("Calibri", 1, 20);
        
        botones=new JButton[2]; 
        for(int i=0;i<2;i++){
                botones[i]=new JButton();
                botones[i].setFont(fuente1);
        }
        
        Etiqueta=new JLabel(SetLabel()); 
        Etiqueta.setFont(LucidNom);
        Etiqueta.setForeground(Color.white);
        
        botones[0].setText("Aceptar");
        botones[0].addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent a){  
                    switch(tipo){
                        case "friend":{
                            AcceptFriend();
                        }
                        case "group":{
                            AcceptGroup();
                        }
                         case "chat":{
                            AcceptChat();
                        }
                    }
                }             
            }
        );
        botones[1].setText("Rechazar");
        botones[1].addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent a){  
                    switch(tipo){
                        case "friend":{
                            UnacceptFriend();
                        }
                        case "group":{
                            UnacceptGroup();
                        }
                         case "chat":{
                            UnacceptChat();
                        }
                    }
                }             
            }
        );
        
        
        orden=new GroupLayout(this.getContentPane());
        orden.setAutoCreateContainerGaps(true);
        orden.setAutoCreateGaps(true);
        orden.setHorizontalGroup(
          orden.createParallelGroup()
                .addComponent(Etiqueta)
                .addGroup(
                        orden.createSequentialGroup()
                        .addComponent(botones[0],15,100,550)
                        .addComponent(botones[1],15,100,550) 
                )
        );
        orden.setVerticalGroup(
                orden.createSequentialGroup()
                    .addComponent(Etiqueta)
                    .addGroup(
                            orden.createParallelGroup()
                            .addComponent(botones[0],10,30,400)
                            .addComponent(botones[1],10,30,400)
                    )
        );
        this.setLayout(orden);
        this.pack();
    }
    
    public String SetLabel(){
        switch(tipo){
            case "friend":{
                return "Solicitud de amistad de "+nombre;
            }
            case "group":{
                return "Solicitud para unirse al Grupo de "+nombre;
            }
             case "chat":{
                return "Solicitud de chat privado de "+nombre;
            }
        }
        return "Solicitud erronea de "+nombre;
    }
    
    //Aceptados
    public void AcceptFriend(){
        new FriendAccept(context,nombre,true);
        return;
    }
    
    public void AcceptGroup()
    {        
        try {
            JsonObject req = new JsonObject();
            req.addProperty("type", "accept-group-request");
            JsonObject args = new JsonObject();
            
            args.addProperty("username", context.getUsername());
            args.addProperty("status", true);
            args.addProperty("id", id);
            
            req.add("args", args);
            
            context.getConnection().getOutputStream().write(new Gson().toJson(req).getBytes());
            
            return;
        } catch (IOException ex) {
            Logger.getLogger(RequestFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void AcceptChat(){

        return;
    }
    
    
    ////Rechazados
    public void UnacceptFriend(){
        new FriendAccept(context,nombre,false);
        return;
    }
    
    public void UnacceptGroup(){
        try {
            JsonObject req = new JsonObject();
            req.addProperty("type", "accept-group-request");
            JsonObject args = new JsonObject();
            
            args.addProperty("username", context.getUsername());
            args.addProperty("status", true);
            args.addProperty("id", this.id);
            req.add("args", args);
            
            context.getConnection().getOutputStream().write(new Gson().toJson(req).getBytes());
            return;
        } catch (IOException ex) {
            Logger.getLogger(RequestFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void UnacceptChat(){

        return;
    }
   
}
