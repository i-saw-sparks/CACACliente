/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Views.ChatFrame;
import Views.FriendChatFrame;
import Views.GroupChatFrame;
import Views.UnfriendChatFrame;
import cacacliente.Login_ventana;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Hashtable;

/**
 *
 * @author manie
 */
public class Context 
{
    private int counter = 0;
    private String username;
    private ArrayList<String> connectedUsers;
    private Login_ventana logVent;
    public ChatFrame chat = null;
    private Socket connection;
    private Hashtable<String, GroupChatFrame> gChats = new Hashtable<>();
    private Hashtable<String, FriendChatFrame> chats = new Hashtable<>();
    private Hashtable<String, UnfriendChatFrame> uChats = new Hashtable();

    public Hashtable<String, UnfriendChatFrame> getuChats() {
        return uChats;
    }

    public void setuChats(Hashtable<String, UnfriendChatFrame> uChats) {
        this.uChats = uChats;
    }

    public Hashtable<String, GroupChatFrame> getgChats() {
        return gChats;
    }

    public void setgChats(Hashtable<String, GroupChatFrame> gChats) {
        this.gChats = gChats;
    }

    public Hashtable<String, FriendChatFrame> getChats() 
    {
        return chats;
    }

    public void setChats(Hashtable<String, FriendChatFrame> chats) 
    {
        this.chats = chats;
    }
    

    public ChatFrame getChat() {
        return chat;
    }

    public void setChat(ChatFrame chat) {
        this.chat = chat;
    }

    public Socket getConnection() {
        return connection;
    }

    public void setConnection(Socket connection) {
        this.connection = connection;
    }
    
    

    
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
