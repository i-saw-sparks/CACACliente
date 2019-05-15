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
import petitions.FriendRequest;

/**
 *
 * @author usuario
 */
public class DisconnectedUsersFrame extends JFrame{
    
    private JButton boton;
    GroupLayout orden;
    private String UserName;
    private JLabel NombreUsuario;
    Context context;
    
    public DisconnectedUsersFrame(String name,Context con){
        this.UserName=name;
        this.context=con;
        Configuracion();
    }
    
    public void Configuracion(){
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setTitle("Usuario "+UserName);
        this.getContentPane().setBackground(Color.black);

        Font LucidNom = new Font("Lucida Sans Typewriter", 1, 15);    
        Font fuente1 = new Font("Calibri", 1, 15);
        Font fuente2 = new Font("Calibri", 1, 20);
        
        boton=new JButton(); 
        boton=new JButton();
        boton.setFont(fuente1);
        
        NombreUsuario=new JLabel("Usuario desconectado "+UserName); 
        NombreUsuario.setFont(LucidNom);
        NombreUsuario.setForeground(Color.white);
        
        boton.setText("Agregar Amigo");
        boton.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent a){  
                    AddFriend();
                }             
            }
        );
        
        
        orden=new GroupLayout(this.getContentPane());
        orden.setAutoCreateContainerGaps(true);
        orden.setAutoCreateGaps(true);
        orden.setHorizontalGroup(
          orden.createParallelGroup()
                .addComponent(NombreUsuario)
                .addComponent(boton,15,150,550)
        );
        orden.setVerticalGroup(
                orden.createSequentialGroup()
                    .addComponent(NombreUsuario)
                    .addComponent(boton,10,30,400)
        );
        this.setLayout(orden);
        this.pack();
    }
    
    public void AddFriend(){
        new FriendRequest(context, UserName);
        return;
    }

}
