/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import controllers.Context;
import java.awt.Component;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import petitions.FriendRequest;

/**
 *
 * @author ivan_
 */

public class ChatFrame extends JFrame
{ 
    Context context;
    JTabbedPane selector;
    JPanel friends;
    JPanel groups;
    JPanel connected;
    JPanel disconnected;
    JPanel notifications;
    
    public ChatFrame(Context source)
    {
        this.context = source;
        configureWindow();
        this.setLayout(null);
    }
    
    public void addNotification(String name, String type)
    {
        JButton label = new JButton();
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        switch(type)
        {
            case "friend":
                label.setText("Solicitud de "+name);
            case "group":
                label.setText("Solicitud de ingreso al grupo "+name);
            case "chat":
                label.setText("Solicitud de chat privado con "+name);
            case "friend-unnacepted":
                label.setText("Solicitud pendiente a "+name);
        }
        
        label.addActionListener( e -> 
        {
            optionsNotification( name,  type);
        });
        
        notifications.add(label);
    }
    
    private void optionsNotification(String name, String type)
    {
        return;
    }
    
    public void addFriend(String name)
    {
        JButton button = new JButton(name);
        button.setSize(500, 100);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.addActionListener(e->
        {
            optionsFriends(name,button);
        });
        friends.add(button);
    }
    
    public void addDisconnected(String name)
    {
        JButton button = new JButton(name);
        button.setSize(500, 100);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.addActionListener(e->
        {
            optionsDisconnected(name,button);
        });
        disconnected.add(button);  
    }
    
    private void optionsDisconnected(String name, JButton button)
    {
        new FriendRequest(context, name);
    }
    
    public void addConnected(String name)
    {
        JButton button = new JButton(name);
        button.setSize(500,100);
        button.addActionListener(e->
        {
            optionsConnected(name, button);
        });
        connected.add(button);
    }
    
    
      private void setAddGroupBtt(){
        JButton button = new JButton("Añadir nuevo grupo");
        button.addActionListener(e->
        {
            cacacliente.GruposForm_Ventana form = new cacacliente.GruposForm_Ventana(context);
            form.setVisible(true);
        });
        groups.add(button);
    }
      
    private void optionsConnected(String name, JButton button)
    {
        ConnectedUsersFrame form=new ConnectedUsersFrame(name);
        form.setVisible(true);
    }
    
    private void optionsFriends(String name, JButton sender)
    {
        Amigos_ventana form=new Amigos_ventana(name);
        form.setVisible(true);
        System.out.println("Hola");
    }
    
    private void configureWindow()
    {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Panel de control CACA");
        selector = new JTabbedPane();
        this.add(selector);
        
        JPanel[] creator = new JPanel[5];
        String[] titles = {"Amigos","Grupos","Conectados",
        "Desconectados", "Notificaciones"};
        final JScrollPane[] scrolls = new JScrollPane[5]; 
        
        for (int i = 0; i < 5; i++) 
        {
            creator[i] = new JPanel();
            creator[i].setLayout(new BoxLayout(creator[i],BoxLayout.Y_AXIS));
            scrolls[i] = new JScrollPane(creator[i], JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
            selector.addTab(titles[i], scrolls[i]);
        }
        
        friends  = creator[0];
        groups = creator[1];
        connected = creator[2];
        disconnected = creator[3];
        notifications = creator[4];
        
        setAddGroupBtt();
        
        GroupLayout order = new GroupLayout(this.getContentPane());
        order.setVerticalGroup(order.createSequentialGroup()
        .addComponent(selector,500,500,500));
        
        order.setHorizontalGroup(order.createSequentialGroup()
        .addComponent(selector,200,500,600));
        
        this.setLayout(order);
        this.pack();
    }   
}