/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import controllers.Context;
import java.awt.Component;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import requests.Refresh;

/**
 *
 * @author ivan_
 */
public class ChatFrame extends JFrame {    

    private Context context;
    private JTabbedPane selector;
    private JPanel friends;
    private JPanel groups;
    private JPanel connected;
    private JPanel disconnected;
    private JPanel notifications;
    private JPanel more;
    private JButton Refresh;
    
    Hashtable<String, JButton> connectedList = new Hashtable<String, JButton>();
    Hashtable<String, JButton> disconnectedList = new Hashtable<String, JButton>();
    Hashtable<String, JButton> groupsList = new Hashtable<String, JButton>();
    Hashtable<String, JButton> friendsList = new Hashtable<String, JButton>();
    Hashtable<String, JButton> notificationsList = new Hashtable<String, JButton>();
    
    public ChatFrame(Context source) {
        this.context = source;
        configureWindow();
        this.setLayout(null);
    }
    
    public void removeAll() {
        friends.removeAll();
        friends.revalidate();
        friends.repaint();
        groups.removeAll();
        groups.revalidate();
        groups.repaint();
        connected.removeAll();
        connected.revalidate();
        connected.repaint();
        disconnected.removeAll();
        disconnected.revalidate();
        disconnected.repaint();
        notifications.removeAll();
        notifications.revalidate();
        notifications.repaint();
    }
    
    public void addNotification(String name, String type, String id) {
        JButton label = new JButton("Solicitud de ingreso a " + name);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        label.addActionListener(e
                -> {
            optionsNotification(name, type, id);
        });
        
        notifications.add(label);
    }
    
    public void addNotification(String name, String type) {
        JButton label = new JButton();
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        switch (type) {
            case "friend":
                label.setText("Solicitud de " + name);
                break;
            case "group":
                label.setText("Solicitud de ingreso al grupo " + name);
                break;
            case "chat":
                label.setText("Solicitud de chat privado con " + name);
                break;
            case "friend-unnacepted":
                label.setText("Solicitud pendiente a " + name);
                break;
        }
        
        label.addActionListener(e
                -> {
            optionsNotification(name, type);
        });
        
        notifications.add(label);
        notificationsList.put(name, label);
    }
    
        private void optionsNotification(String name, String type, String id) {
        RequestFrame frame = new RequestFrame(name, type, context, id);
        frame.setVisible(true);
    }
    
    private void optionsNotification(String name, String type) {
        if (!type.equals("friend-unnacepted")) {
            RequestFrame form = new RequestFrame(name, type, context);
            form.setVisible(true);
        }
    }
    
    
    public void addFriend(String name, String alias) {
        JButton button = new JButton(alias);
        button.setSize(500, 100);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.addActionListener(e
                -> {
            optionsFriends(name, button);
        });
        friends.add(button);
        friendsList.put(name, button);
    }
    
    public void addGroup(String id, String asunto) {
        JButton button = new JButton(asunto);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        button.addActionListener(e
                -> {
            optionsGroup(id, asunto);
        });
        
        groups.add(button);
        groupsList.put(id, button);
    }
    
    private void optionsGroup(String id, String asunto) {
        GroupFrame form = new GroupFrame(id, asunto, context);
        form.setVisible(true);
    }
    
    public void addDisconnected(String name) {
        JButton button = new JButton(name);
        button.setSize(500, 100);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.addActionListener(e
                -> {
            optionsDisconnected(name, button);
        });
        disconnected.add(button);
        disconnectedList.put(name, button);
    }
    
    private void optionsDisconnected(String name, JButton button) {
        DisconnectedUsersFrame form = new DisconnectedUsersFrame(name, context);
        form.setVisible(true);
    }
    
    public void addConnected(String name) {
        JButton button = new JButton(name);
        button.setSize(500, 100);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.addActionListener(e
                -> {
            optionsConnected(name, button);
        });
        connected.add(button);
        connectedList.put(name, button);
    }
    
    private void setAddGroupBtt() {
        JButton button = new JButton("Añadir nuevo grupo");
        button.addActionListener(e
                -> {
            Views.GruposForm_Ventana form = new Views.GruposForm_Ventana(context);
            form.setVisible(true);
        });
        more.add(button);
    }
    
    private void optionsConnected(String name, JButton button) {
        ConnectedUsersFrame form = new ConnectedUsersFrame(name, context);
        form.setVisible(true);
    }
    
    private void optionsFriends(String name, JButton sender) {
        Amigos_ventana form = new Amigos_ventana(name, context);
        form.setVisible(true);
    }
    
    public void refresh() {
        this.revalidate();
        this.repaint();
    }
    
    private void configureWindow() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Panel de control CACA " + context.getUsername());
        selector = new JTabbedPane(JTabbedPane.LEFT);
        this.add(selector);
        
        Refresh = new JButton("Refrescar");
        Refresh.addActionListener(e
                -> {
            try {
                new Refresh(context);
                Thread.sleep(1500);
                refresh();
            } catch (InterruptedException ex) {
                Logger.getLogger(ChatFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        });
        
        JPanel[] creator = new JPanel[6];
        String[] titles = {"Amigos", "Grupos", "Conectados",
            "Desconectados", "Notificaciones", "Más"};
        final JScrollPane[] scrolls = new JScrollPane[6];        
        
        for (int i = 0; i < 6; i++) {
            creator[i] = new JPanel();
            creator[i].setLayout(new BoxLayout(creator[i], BoxLayout.Y_AXIS));
            scrolls[i] = new JScrollPane(creator[i], JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
            selector.addTab(titles[i], scrolls[i]);
        }
        
        selector.add(Refresh);
        
        friends = creator[0];
        groups = creator[1];
        connected = creator[2];
        disconnected = creator[3];
        notifications = creator[4];
        more = creator[5];
        setAddGroupBtt();
        
        GroupLayout order = new GroupLayout(this.getContentPane());
        order.setVerticalGroup(order.createSequentialGroup()
                .addComponent(selector, 300, 300, 300)
                .addComponent(Refresh));
        
        order.setHorizontalGroup(order.createParallelGroup()
                .addComponent(selector, 350, 350, 350)
                .addComponent(Refresh));
        
        this.setLayout(order);
        
        this.pack();
    }    
    
}
