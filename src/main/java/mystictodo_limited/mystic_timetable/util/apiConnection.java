/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mystictodo_limited.mystic_timetable.util;

import java.io.*;
import java.io.IOException;
import java.lang.*;
import java.net.*;

/**
 *
 * @author Jamario_Downer
 */
public class apiConnection {
    
    //Constructor >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>  
    public apiConnection (){
        
    }//end default constructor
    
    //Feilds >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>  
    //Declare Server Socket variables
    private static ServerSocket serverSock;
    
    //Declare and assign socket server port and which it listens
    private static int port = 6366;
    
    
    //Getters/Setters >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>  
    
    
    //Methods >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>  
  
    //Start Server
    public void StartServer() throws IOException, ClassNotFoundException{
    
        //create the object for the server socket
        serverSock = new ServerSocket (port);
    
        //Listen indefinitely for "exit" call or program terminates
        while (true){ 
            System.out.println("Waiting for client request");
        
            //Create a socket to accept the request 
            Socket acceptSock = serverSock.accept();
            
            //Read from the socket and send to ObjectInputStream
            ObjectInputStream objInStream = new ObjectInputStream(acceptSock.getInputStream());
        
            //convert OpjectInputStream to a String
            String objMessage = (String) objInStream.readObject();
            
            //Output the message that was received   
            System.out.println("Message received :" + objMessage);
            
            //Create an ObjectOutputStream
            ObjectOutputStream objOutStream = new ObjectOutputStream(acceptSock.getOutputStream());
            
            //Write object to the socket
            objOutStream.writeObject("Hello, Friend, " + objMessage);
            
            //close all
            objInStream.close();
            objOutStream.close();
            acceptSock.close();
            
            //Close the server if the client sends exit request
            if(objMessage.equalsIgnoreCase("exit")){
                break;
            }
            
        }//end while loop 
        
        System.out.println("Shutting down the server now... ");
        
        //Close the Server Socket
        serverSock.close();
        
    }// end Server method

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
            if (i == 4){
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
    
} // End apiConnection Class 
