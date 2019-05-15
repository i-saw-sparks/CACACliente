package Views;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author usuario
 */
public class ConnectedUsersFrame extends JFrame{
    
    private JButton[] botones;
    GroupLayout orden;
    private String UserName;
    private JLabel NombreUsuario;
    
    public ConnectedUsersFrame(String name){
        this.UserName=name;
        Configuracion();
    }
    
    public void Configuracion(){
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setTitle("Usuario "+UserName);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(Color.DARK_GRAY);

        
                
        botones=new JButton[3];
        Font fuente1 = new Font("Calibri", 1, 15);
        for(int i=0;i<3;i++){
                botones[i]=new JButton();
                botones[i].setBackground(Color.LIGHT_GRAY);
                botones[i].setFont(fuente1);
        }
        Font fuente2 = new Font("Calibri", 1, 20);
        NombreUsuario=new JLabel(UserName); 
        NombreUsuario.setFont(fuente2);
        NombreUsuario.setForeground(Color.white);
        
        botones[0].setText("Agregar Amigo");
        botones[0].addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent a){  
                    AddFriend();
                }             
            }
        );
        botones[1].setText("Abrir chat");
        botones[1].addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent a){  
                    OpenChat();
                }             
            }
        );
        botones[2].setText("Iniciar conversación");
        botones[2].addActionListener(new ActionListener(){
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
                .addGroup(
                        orden.createSequentialGroup()
                        .addComponent(botones[0],15,150,550)
                        .addComponent(botones[1],15,100,550) 
                )
                .addComponent(botones[2],15,150,1000)
        );
        orden.setVerticalGroup(
                orden.createSequentialGroup()
                    .addComponent(NombreUsuario)
                    .addGroup(
                            orden.createParallelGroup()
                            .addComponent(botones[0],10,50,400)
                            .addComponent(botones[1],10,50,400)
                    )
                    .addComponent(botones[2],10,50,400)
        );
        this.setLayout(orden);
        this.pack();
    }
    
    public void AddFriend(){
        System.out.println("Amigazo");
        return;
    }
    
    public void OpenChat(){
        System.out.println("Amigochat");
        return;
    }
    
    public void StartConversation(){
        System.out.println("Conversachat");
        return;
    }
}
