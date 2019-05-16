/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cacacliente;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import controllers.Context;
import controllers.ProcessRequest;
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
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author Claudia
 */
public class Login_ventana extends JFrame {

    protected JButton bLog, bSign;
    protected JTextField tUser;
    protected JPasswordField tPassword;
    protected JLabel lName, lWelcome, lUser, lPassword, lLine, lSpace;
    private GroupLayout lay;
    private Socket socket;
    private int counter = 0;
    private Context context;
    
    public Login_ventana() 
    {
        configuracion();
        System.out.println("Conectando...");
        context = new Context();
        try
        {
            socket = new Socket("127.0.0.1", 1000);
            context.setConnection(socket);
            this.setVisible(true);
            
            Thread thread  = new Thread(()->
            {
               while(true)
               {                   
                   try {
                       
                       if(socket.getInputStream().available()>0)
                       {
                           byte[] response = new byte[socket.getInputStream().available()];
                           socket.getInputStream().read(response);
                           String res = new String(response);
                           if(!"p".equals(res))
                           {
                                ProcessRequest.processRequest(res, context);
                           }
                       }
                   } catch (IOException ex) {
                       Logger.getLogger(Login_ventana.class.getName()).log(Level.SEVERE, null, ex);
                   }
               }
            });
            
            thread.start();
            
        } catch (IOException  ex) {
            Logger.getLogger(Login_ventana.class.getName()).log(Level.SEVERE, null, ex);
            this.setVisible(false);
        }       
    }

    void bLog() {
        context.setCounter(context.getCounter()+1);
        String user = tUser.getText();
        String password = tPassword.getText();
        
        context.setUsername(user);
        context.setLogVent(this);

        JsonObject envio = new JsonObject();
        envio.addProperty("type", "login");

        JsonObject args = new JsonObject();
        args.addProperty("username", user);
        args.addProperty("password", password);

        envio.add("args", args);

        Gson gson = new Gson();

        String packet = gson.toJson(envio);
        try 
        {
            byte[] data = packet.getBytes();
            socket.getOutputStream().write(data);
            
        } catch (IOException ex) {
            Logger.getLogger(Login_ventana.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void bSign() {
        String user = tUser.getText();
        String password = tPassword.getText();

        JsonObject envio = new JsonObject();
        envio.addProperty("type", "sign");

        JsonObject args = new JsonObject();
        args.addProperty("username", user);
        args.addProperty("password", password);

        envio.add("args", args);

        Gson gson = new Gson();

        String packet = gson.toJson(envio);
        try 
        {
            byte[] data = packet.getBytes();
            socket.getOutputStream().write(data);
            
        } catch (IOException ex) {
            Logger.getLogger(Login_ventana.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void connect() {
    }

    protected void configuracion() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 600);
        this.setTitle("Mi primera aplicacion grafica en JAVA");
        this.getContentPane().setBackground(Color.BLACK);

        lay = new GroupLayout(this.getContentPane());
        lay.setAutoCreateContainerGaps(true);
        lay.setAutoCreateGaps(true);

        GroupLayout layout = new GroupLayout(this);
        Font Broadway = new Font("Lucida Console", 1, 30);
        Font LucidCal = new Font("Lucida Handwriting", 1, 10);
        Font LucidNom = new Font("Lucida Sans Typewriter", 1, 15);
        tUser = new JTextField();
        tPassword = new JPasswordField();

        bLog = new JButton("Log-in");
        bLog.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent a) {
                if (a.getSource() == bLog) {
                    bLog();
                }
            }
        }
        );
        bSign = new JButton("Sign-in");
        bSign.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent a) {
                if (a.getSource() == bSign) {
                    bSign();
                }
            }
        }
        );

        lName = new JLabel("Clientes Adoran Chatear Aqui");
        lName.setFont(LucidCal);
        lWelcome = new JLabel("Bienvenido");
        lWelcome.setFont(Broadway);
        lUser = new JLabel("Usuario");
        lUser.setFont(LucidNom);
        lLine = new JLabel("______________________________");
        lSpace = new JLabel(" ");
        lLine.setForeground(Color.white);

        lPassword = new JLabel("Password");
        lPassword.setFont(LucidNom);
        lName.setForeground(Color.white);
        lUser.setForeground(Color.white);
        lWelcome.setForeground(Color.white);
        lPassword.setForeground(Color.white);

        lay.setHorizontalGroup(
                lay.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addGroup(lay.createSequentialGroup()
                                .addGroup(lay.createParallelGroup(GroupLayout.Alignment.CENTER)
                                        .addComponent(lSpace)
                                        .addComponent(lName)
                                        .addComponent(lSpace)
                                        .addComponent(lWelcome)
                                )
                        )
                        .addGroup(lay.createSequentialGroup()
                                .addGroup(lay.createParallelGroup()
                                        .addComponent(lUser)
                                        .addComponent(tUser)
                                        .addComponent(lPassword)
                                        .addComponent(tPassword)
                                )
                        )
                        .addGroup(lay.createSequentialGroup()
                                .addGroup(lay.createParallelGroup(GroupLayout.Alignment.CENTER)
                                        .addComponent(lSpace)
                                        .addComponent(bLog)
                                        .addComponent(lLine)
                                        .addComponent(lSpace)
                                        .addComponent(bSign)
                                )
                        )
        );

        lay.setVerticalGroup(
                lay.createSequentialGroup()
                        .addGroup(lay.createParallelGroup()
                                .addGroup(lay.createSequentialGroup()
                                        .addComponent(lName)
                                        .addComponent(lSpace)
                                        .addComponent(lWelcome)
                                        .addComponent(lSpace)
                                )
                        )
                        .addGroup(lay.createParallelGroup()
                                .addGroup(lay.createSequentialGroup()
                                        .addComponent(lUser)
                                        .addComponent(tUser)
                                        .addComponent(lPassword)
                                        .addComponent(tPassword)
                                )
                        )
                        .addGroup(lay.createParallelGroup()
                                .addGroup(lay.createSequentialGroup()
                                        .addComponent(lSpace)
                                        .addComponent(bLog)
                                        .addComponent(lLine)
                                        .addComponent(lSpace)
                                        .addComponent(bSign)
                                )
                        )
        );

        this.setLayout(lay);
        this.pack();

    }

}
