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
public class GroupFrame extends JFrame{
    
    private JButton[] botones;
    GroupLayout orden;
    private String UserName;
    private JLabel NombreUsuario;
    private String id;
    Context context;

    
    public GroupFrame(String id,String name,Context con){
        this.UserName=name;
        this.context=con;
        this.id=id;
        Configuracion();
    }
    
    public void Configuracion(){
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setTitle("Usuario conectado "+UserName);
        this.getContentPane().setBackground(Color.black);

        Font LucidNom = new Font("Lucida Sans Typewriter", 1, 15);    
        Font fuente1 = new Font("Calibri", 1, 15);
        Font fuente2 = new Font("Calibri", 1, 20);
        
        botones=new JButton[4]; 
        for(int i=0;i<4;i++){
                botones[i]=new JButton();
                botones[i].setFont(fuente1);
        }
        
        NombreUsuario=new JLabel(UserName); 
        NombreUsuario.setFont(LucidNom);
        NombreUsuario.setForeground(Color.white);
        
        botones[0].setText("Agregar al grupo");
        botones[0].addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent a){  
                    AddToGroup();
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
        botones[2].setText("Borrar grupo");
        botones[2].addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent a){  
                    DeleteGroup();
                }             
            }
        );
        
        botones[3].setText("Renombrar");
        botones[3].addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent a){  
                    RenameGroup();
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
                .addComponent(botones[2],15,500,1000)
                .addComponent(botones[3],15,500,1000)
        );
        orden.setVerticalGroup(
                orden.createSequentialGroup()
                    .addComponent(NombreUsuario)
                    .addGroup(
                            orden.createParallelGroup()
                            .addComponent(botones[0],10,30,400)
                            .addComponent(botones[1],10,30,400)
                    )
                    .addComponent(botones[2],10,30,400)
                    .addComponent(botones[3],10,30,400)
        );
        this.setLayout(orden);
        this.pack();
    }
    
    public void AddToGroup(){

        return;
    }
    
    public void OpenChat(){

        return;
    }
    
    public void DeleteGroup(){

        return;
    }
    
    public void RenameGroup(){
        return;
    }
}
