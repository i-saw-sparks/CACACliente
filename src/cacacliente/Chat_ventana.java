package cacacliente;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

/**
 *
 * @author L_Felipe
 */
public class Chat_ventana extends JFrame{
    
    private JButton[][] botones=new JButton[4][5];
    private JLabel operacion,resultado;
    private int posx=32;
    private int posy=150;
    private int paso=1;
    private boolean first=true;
    public int contadorEtiquetas=0;
    public JTextArea inmensajes;
    public ArrayList<Integer>etiquetas = new ArrayList<Integer>();
    
    public Chat_ventana() throws InterruptedException{
        Configuracion();
    }
    
    public void Configuracion() throws InterruptedException{
        
        Font fuente = new Font("Calibri", 1, 20);
        
        final PanelSlider42<JFrame> slider = new PanelSlider42<JFrame>(this);
        final JPanel jPanel = slider.getBasePanel();
        
        
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
       JButton buttonAdd = new JButton("Add Nuevo");
       buttonAdd.setBackground(Color.LIGHT_GRAY);
       buttonAdd.setFont(fuente);
       buttonAdd.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
                Agregar_Etiqueta(mainPanel);           
            }
        });
                
       
       JPanel panGrupos = new JPanel();
       panGrupos.setLayout(new BoxLayout(panGrupos, BoxLayout.Y_AXIS));
       JButton addGrupo = new JButton("Añadir Grupo");
       addGrupo.setBackground(Color.LIGHT_GRAY);
       addGrupo.setFont(fuente);
       addGrupo.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
                GrupoForm(panGrupos);           
            }
        });
        /*JButton buttonRemoveAll = new JButton("Remove All");     
        buttonRemoveAll.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
                Borrar_Etiqueta(mainPanel);               
            }
        });*/
                            
        mainPanel.add(buttonAdd, BorderLayout.PAGE_START);
        //mainPanel.add(buttonRemoveAll, BorderLayout.PAGE_END);                            
        slider.addComponent(mainPanel);                       
        
        slider.addComponent(new JButton("Amigos"));
        slider.addComponent(new JButton("Usuarios"));
        
        panGrupos.add(addGrupo, BorderLayout.PAGE_START);
        slider.addComponent(panGrupos);
        //slider.addComponent(new JButton("Grupos"));
                                                   

            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setSize(2000,2000);
            this.setResizable(false);
            this.setLocationRelativeTo(null);
            this.setTitle("CACA");
            this.getContentPane().setBackground(Color.DARK_GRAY);
            this.setLayout(null);
            
            
            
            inmensajes=new JTextArea();// 8
            
            
            JButton usuario=new JButton("Usuario"); //Boton 1
            usuario.setBackground(Color.LIGHT_GRAY);
            usuario.setFont(fuente);
            
            
            JButton grupos=new JButton("Grupos"); //Boton 2
            grupos.setBackground(Color.LIGHT_GRAY);
            grupos.setFont(fuente);
            grupos.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(final ActionEvent e) {
                        slider.slideBottom();
                    }
                });
            
            JButton amigos=new JButton("Amigos"); //Boton 3
            amigos.setBackground(Color.LIGHT_GRAY);
            amigos.setFont(fuente);
            amigos.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(final ActionEvent e) {
                        slider.slideTop();
                    }
                });
            
            JButton nuevos=new JButton("Nuevos"); //Boton 4
            nuevos.setBackground(Color.LIGHT_GRAY);
            nuevos.setFont(fuente);
            nuevos.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(final ActionEvent e) {
                        slider.slideLeft();
                    }
                });
            
            JButton usuarios=new JButton("Usuarios"); //Boton 5
            usuarios.setBackground(Color.LIGHT_GRAY);
            usuarios.setFont(fuente);
            usuarios.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(final ActionEvent e) {
                        slider.slideRight();
                    }
                });
            
            JButton cuadro=new JButton("Cuadro"); //Boton 6
            cuadro.setBackground(Color.LIGHT_GRAY);
            cuadro.setFont(fuente);
            
            JButton mensajes=new JButton("Mensajes"); //Boton 7
            mensajes.setBackground(Color.LIGHT_GRAY);
            mensajes.setFont(fuente);
            
            JButton enviar=new JButton("Enviar"); //Boton 9
            enviar.setBackground(Color.LIGHT_GRAY);
            enviar.setFont(fuente);
            
            
            GroupLayout orden=new GroupLayout(this.getContentPane());
            orden.setAutoCreateContainerGaps(true);
            orden.setAutoCreateGaps(true);
            
            orden.setHorizontalGroup(
                    orden.createSequentialGroup()
                            .addGroup(
                                    orden.createParallelGroup()
                                            .addComponent(usuario,100,500,1000)
                                            .addGroup(
                                                    orden.createSequentialGroup()
                                                            .addComponent(grupos,50,247,500)
                                                            .addComponent(amigos,50,247,500)
                                            )
                                            .addGroup(
                                                    orden.createSequentialGroup()
                                                            .addComponent(nuevos,50,247,500)
                                                            .addComponent(usuarios,50,247,500)
                                            )
                                            .addComponent(jPanel,100,500,1000)
                            )
                            .addGroup(
                                    orden.createParallelGroup()
                                            .addComponent(mensajes,200,900,2000)
                                            .addGroup(
                                                    orden.createSequentialGroup()
                                                            .addComponent(inmensajes,100,785,1000)
                                                            .addComponent(enviar,110,110,110)
                                            )
                            )
            );
            orden.setVerticalGroup(
                    orden.createParallelGroup()
                            .addGroup(
                                    orden.createSequentialGroup()
                                            .addComponent(usuario,70,70,70)
                                            .addGroup(
                                                    orden.createParallelGroup()
                                                            .addComponent(grupos)
                                                            .addComponent(amigos)
                                            )
                                            .addGroup(
                                                    orden.createParallelGroup()
                                                            .addComponent(nuevos)
                                                            .addComponent(usuarios)
                                            )
                                            .addComponent(jPanel,455,455,455)
                            )
                            .addGroup(
                                    orden.createSequentialGroup()
                                            .addComponent(mensajes,200,500,2000)
                                            .addGroup(
                                                    orden.createParallelGroup()
                                                            .addComponent(inmensajes,110,110,110)
                                                            .addComponent(enviar,110,110,110)
                                            )
                            )
                    
            );
            this.setLayout(orden);
            this.pack();
    }
    
    public JButton Agregar_Etiqueta(JPanel mainPanel){
        JButton boton=new JButton("Nueva Caca");
        boton.setBackground(Color.LIGHT_GRAY);
        boton.setFont(new Font("Calibri", 1, 20));
        mainPanel.add(boton);
        etiquetas.add(contadorEtiquetas);
        contadorEtiquetas++;
        this.pack();
        return boton;
    }
    
    public JButton GrupoForm(JPanel panGrupos){
        JButton boton=new JButton("Nueva Grupo"); //Va a cambiar
        boton.setBackground(Color.LIGHT_GRAY);
        boton.setFont(new Font("Calibri", 1, 20));
        panGrupos.add(boton);
        etiquetas.add(contadorEtiquetas);
        contadorEtiquetas++;
        this.pack();
        GruposForm_Ventana form = new GruposForm_Ventana(boton);
        form.setVisible(true);
        return boton;
    }
    
    public void Borrar_Etiqueta(JPanel mainPanel){
        mainPanel.removeAll();
        this.pack();
    }
}

