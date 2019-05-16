package Views;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import controllers.Context;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
/**
 *
 * @author Oscar
 */
public class Amigos_ventana extends JFrame {
    
    private JButton bChat, bAlias;
    private JLabel lName;
    private String UserName;
    private GroupLayout lay;
    private Socket socket;
    Context context;
    
    /**
     * Contructor de ventana que muestra opciones de amigos
     * @param name nombre del amigo en cuentión
     * @param con contexto del chat frame
     */
    public Amigos_ventana(String name,Context con) {
        this.UserName=name;
        this.context=con;
        configuracion();  
    }
       
    /**
     * Ejecuta la request para abrir la ventana de chat con un amigo y cargar el historial
     */
    void bChat() {
        try {
            FriendChatFrame form=new FriendChatFrame(UserName,context);
            JsonObject req = new JsonObject();
            req.addProperty("type", "getPersonal");
            JsonObject args = new JsonObject();
            args.addProperty("user1", context.getUsername());
            args.addProperty("user2", this.UserName);
            req.add("args", args);
            
            context.getConnection().getOutputStream().write(new Gson().toJson(req).getBytes());
            form.setVisible(true);
            
            context.getChats().put(UserName, form);
            
            
            return;
        } catch (IOException ex) {
            Logger.getLogger(Amigos_ventana.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Ejecuta la petición al servidor para cambiar el alias de un amigo
     */
    void bAlias() 
    {
        JsonObject req = new JsonObject();
        req.addProperty("type", "setAlias");
        JsonObject args = new JsonObject();
        
        String alias =  JOptionPane.showInputDialog("Seleccione el nuevo nombre");
        
        if(alias!=null && !alias.equals(""))
        {
            try {
                args.addProperty("username", context.getUsername());
                args.addProperty("friend", UserName);
                args.addProperty("alias", alias);
                
                req.add("args",args);
                
                context.getConnection().getOutputStream().write(new Gson().toJson(req).getBytes());
            } catch (IOException ex) {
                Logger.getLogger(Amigos_ventana.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return;
    }
    
    /**
     * Configuraciones de la ventana de opciones de amigo
     */
    public void configuracion() {
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setSize(1000, 1000);
        this.setTitle("Amigo");
        this.getContentPane().setBackground(Color.BLACK);

        lay = new GroupLayout(this.getContentPane());
        lay.setAutoCreateContainerGaps(true);
        lay.setAutoCreateGaps(true);

        GroupLayout layout = new GroupLayout(this);
        Font Broadway = new Font("Lucida Console", 1, 30);
        Font LucidCal = new Font("Lucida Handwriting", 1, 10);
        Font LucidNom = new Font("Lucida Sans Typewriter", 1, 15);

        bChat = new JButton("Abrir Chat");
        bChat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent a) {
                if (a.getSource() == bChat) {
                    bChat();
                }
            }
        }
        );
        bAlias = new JButton("Set Alias");
        bAlias.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent a) {
                if (a.getSource() == bAlias) {
                    bAlias();
                }
            }
        }
        );

        lName = new JLabel(UserName);
        lName.setFont(LucidNom);
        lName.setForeground(Color.white);

        lay.setHorizontalGroup(
                lay.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(lName)
                        .addComponent(bChat,170,170,170)
                        .addComponent(bAlias,170,170,170)
                       
        );

        lay.setVerticalGroup(
                lay.createSequentialGroup()
                        .addComponent(lName)
                        .addComponent(bChat,50,50,50)
                        .addComponent(bAlias,50,50,50)
                
        );

        this.setLayout(lay);
        this.pack();

    }
}
