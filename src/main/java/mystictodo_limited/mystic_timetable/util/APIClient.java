/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mystictodo_limited.mystic_timetable.util;

import java.io.*;
import java.io.IOException;
import java.lang.*;
import java.net.*;
import mystictodo_limited.mystic_timetable.UI.JTimetableOptions;

/**
 *
 * @author Jamario_Downer
 */
public class APIClient {
    
    //Constructor >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>  
    public APIClient (){
        
        try{
            StartClient();
        }catch ( InterruptedException | IOException | ClassNotFoundException e) {
            System.out.println(e);
        }
        
    }//end default constructor
    
    //Feilds >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>  
    //Declare Server Socket variables
    private static ServerSocket serverSock;
    
    //Declare and assign socket server port and which it listens
    private static int port = 6366;
    
    
    //Getters/Setters >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>  
    
    
    //Methods >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>  

    //Start Client
    public void StartClient() throws UnknownHostException, InterruptedException, 
            IOException, ClassNotFoundException{
        
        //get the LocalHost IP address, and if the server is using the same ip then use that
        InetAddress host = InetAddress.getLocalHost();
        Socket senderSock = null;
        ObjectOutputStream objOutStream = null;
        ObjectInputStream objInStream = null;
        
        for (int i=0; i < 5; i++ ){
            
            //extablish a connection to the server 
            senderSock = new Socket (host.getHostName(), port);
            
            //Write the socket for the ObjectOutputStream
            objOutStream = new ObjectOutputStream(senderSock.getOutputStream());
            
            //Print a Message to send a request to the server socket
            System.out.println("Sending request to Server Socket");
            
            //if count is equal to 5, then exit
            if (i == 4 || senderSock.isConnected()){
                objOutStream.writeObject("Exit");
            }else{
                objOutStream.writeObject("" + i);
            }
            
            //Read the response message from the sender
            objInStream = new ObjectInputStream(senderSock.getInputStream());
            
            //Convert ObjectInputStream to String
            String objMessage = (String) objInStream.readObject();
           
            //Print the Message on host
            System.out.println("Message received : " + objMessage);
            
            //Close all
            objInStream.close();
            objOutStream.close();
            senderSock.close();
            
        }// end for lopp
        
    
    }// end Client method
    
    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new APIClient();
            }
        });
    }
    
} // End APIClient Class 
