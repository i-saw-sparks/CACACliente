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
public class ConnectedUsersFrame extends JFrame{
    
    private JButton[] botones;
    GroupLayout orden;
    private String UserName;
    private JLabel NombreUsuario;
    Context context;
    
    public ConnectedUsersFrame(String name,Context con){
        this.UserName=name;
        this.context=con;
        Configuracion();
    }
    
    public void Configuracion(){
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setTitle("Usuario conectado "+UserName);
        this.getContentPane().setBackground(Color.black);

        Font LucidNom = new Font("Lucida Sans Typewriter", 1, 15);    
        Font fuente1 = new Font("Calibri", 1, 15);
        Font fuente2 = new Font("Calibri", 1, 20);
        
        botones=new JButton[2]; 
        for(int i=0;i<2;i++){
                botones[i]=new JButton();
                botones[i].setFont(fuente1);
        }
        
        NombreUsuario=new JLabel(UserName); 
        NombreUsuario.setFont(LucidNom);
        NombreUsuario.setForeground(Color.white);
        
        botones[0].setText("Agregar Amigo");
        botones[0].addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent a){  
                    AddFriend();
                }             
            }
        );
        botones[1].setText("Iniciar conversación");
        botones[1].addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent a){  
                    StartConversation();
                }             
            }
        );
        
        
        orden=new GroupLayout(this.getContentPane());
        orden.setAutoCreateContainerGaps(true);
        orden.setAutoCreateGaps(true);
        orden.setHorizontalGroup(
          orden.createParallelGroup()
                .addComponent(NombreUsuario)
                .addComponent(botones[0],15,100,550) 
                .addComponent(botones[1],15,150,1000)
        );
        orden.setVerticalGroup(
                orden.createSequentialGroup()
                    .addComponent(NombreUsuario)
                    .addComponent(botones[0],10,30,400)
                    .addComponent(botones[1],10,30,400)
        );
        this.setLayout(orden);
        this.pack();
    }
    
    public void AddFriend(){
        new FriendRequest(context, UserName);
        return;
    }
    
    public void StartConversation(){

        return;
    }
}
