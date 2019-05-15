/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import cacacliente.Login_ventana;
import java.util.ArrayList;

/**
 *
 * @author manie
 */
public class Context 
{
    private int counter = 0;
    String username;
    ArrayList<String> connectedUsers;
    Login_ventana logVent;
    
    
    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }
    
    public Login_ventana getLogVent(){
        return logVent;
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setLogVent(Login_ventana logVent){
        this.logVent = logVent;
    }
    
    public ArrayList<String> getConnectedUsers() {
        return connectedUsers;
    }

    public void setConnectedUsers(ArrayList<String> connectedUsers) {
        this.connectedUsers = connectedUsers;
    }    
}
