/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;


import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;
import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
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
    
    public Amigos_ventana(String name) {
        this.UserName=name;
        configuracion();
        
    }
       
    
    void bChat() {
        System.out.println("Abrir chat");
        return;
    }

    void bAlias() {
        System.out.println("Set Alias");
        return;

    }
    
    
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
