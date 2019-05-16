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
import javax.swing.JOptionPane;

/**
 *
 * @author usuario
 */
public class GroupFrame extends JFrame{
    
    private JButton[] botones;
    GroupLayout orden;
    private String username;
    private JLabel NombreUsuario;
    private String id;
    Context context;
    private String name;

    
    public GroupFrame(String id,String name,Context con){
        this.username=name;
        this.context=con;
        this.id=id;
        Configuracion();
    }
    
    public void Configuracion(){
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setTitle("Opciones para grupo "+username);
        this.getContentPane().setBackground(Color.black);

        Font LucidNom = new Font("Lucida Sans Typewriter", 1, 15);    
        Font fuente1 = new Font("Calibri", 1, 15);
        Font fuente2 = new Font("Calibri", 1, 20);
        
        botones=new JButton[4]; 
        for(int i=0;i<4;i++){
                botones[i]=new JButton();
                botones[i].setFont(fuente1);
        }
        
        NombreUsuario=new JLabel(username); 
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
    
    public void AddToGroup()
    {
        JsonObject resp = new JsonObject();
        resp.addProperty("type", "add-to-group");
        JsonObject args = new JsonObject();
        args.addProperty("id", id);
        String path = JOptionPane.showInputDialog("Escriba el nombre de usuario a agregar a grupo");
        if(path!=null && !path.equals(""))
        {
            try {
                args.addProperty("username",path);
                resp.add("args", args);
                context.getConnection().getOutputStream().write(new Gson().toJson(resp).getBytes());
            } catch (IOException ex) {
                Logger.getLogger(GroupFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return;
    }
    
    public void OpenChat(){
        GroupChatFrame form=new GroupChatFrame(id,username,context); 
        form.setVisible(true);
        return;
    }
    
    public void DeleteGroup()
    {
         int input = JOptionPane.showConfirmDialog(null, "¿Quiere eliminar el grupo?","Eliminar grupo",
				JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
         if(input == 0)
         {
             try {
                 JsonObject req = new JsonObject();
                 req.addProperty("type", "deleteGroup");
                 JsonObject args = new JsonObject();
                 
                 args.addProperty("admin", context.getUsername());
                 args.addProperty("groupId", id);
                 
                 req.add("args", args);
                 
                 context.getConnection().getOutputStream().write(new Gson().toJson(req).getBytes());
             } catch (IOException ex) {
                 Logger.getLogger(GroupFrame.class.getName()).log(Level.SEVERE, null, ex);
             }
         }
        return;
    }
    
    public void RenameGroup(){
        try {
            String path = JOptionPane.showInputDialog("Seleccione el nuevo nombre");

            if(path!=(null) && !path.equals(""))
            {
                JsonObject req = new JsonObject();
                req.addProperty("type", "modifyGroup");
                JsonObject args = new JsonObject();
                args.addProperty("groupId",id );
                args.addProperty("name", path);
                args.addProperty("admin", context.getUsername());
                req.add("args", args);

                context.getConnection().getOutputStream().write(new Gson().toJson(req).getBytes());   
            }            
        } catch (IOException ex) {
            Logger.getLogger(GroupFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
