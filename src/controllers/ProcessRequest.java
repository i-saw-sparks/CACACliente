/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import cacacliente.Chat_ventana;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author manie
 */
public class ProcessRequest {

    private static JsonParser parser;

    static {
        parser = new JsonParser();
    }

    public static void processRequest(String request, Context context) {
        JsonObject response;
        try {
            response = parser.parse(request).getAsJsonObject();
        } catch (IllegalStateException ex) {
            return;
        }

        switch (response.get("type").getAsString()) {
            case "sign":
                if (response.get("status").getAsBoolean()) {
                    JOptionPane alert = new JOptionPane();
                    alert.showMessageDialog(null, "Usuario registrado");
                } else {
                        JOptionPane alert = new JOptionPane();
                        alert.showMessageDialog(null, "El usuario no se ha podido registrar");
                }
                break;

            case "login":
                JsonObject json = response.get("args").getAsJsonObject();
                if (json.get("status").getAsBoolean()) {
                    try {
                        context.getLogVent().setVisible(false);
                        Chat_ventana vent = new Chat_ventana();
                        vent.setVisible(true);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(ProcessRequest.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    if (context.counter > 2) {
                        JOptionPane alert = new JOptionPane();
                        alert.showMessageDialog(null, "Morro, ya mejor registrate");
                    }
                }
                break;
            default:
                break;
        }
    }
}
