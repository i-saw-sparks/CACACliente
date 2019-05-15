package Views;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import controllers.Context;
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
    
    public RequestFrame(String name,String type, Context con){
        this.tipo=type;
        this.nombre=name;
        this.context=con;
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
    
    public void AcceptGroup(){

        return;
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

        return;
    }
    
    public void UnacceptChat(){

        return;
    }
   
}
