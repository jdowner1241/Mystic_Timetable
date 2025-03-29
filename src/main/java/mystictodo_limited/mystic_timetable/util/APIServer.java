/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mystictodo_limited.mystic_timetable.util;

import java.io.*;
import java.io.IOException;
import java.lang.*;
import java.net.*;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import mystictodo_limited.mystic_timetable.db.DbConnectionManager;

/**
 *
 * @author Jamario_Downer
 */
public class APIServer extends SwingWorker<Void, Void>{
    
    //Constructor >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>  
    public APIServer (){
         serverStarted = false;
//        try{
//            StartTestServer();
//        }catch (IOException | ClassNotFoundException e) {
//            System.out.println(e);
//        }
        
        
    }//end default constructor
    
    public APIServer (int serverPort){
        this();
        this.serverPort = serverPort;
    }
    
    
    //Feilds >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>  
    
    private ServerSocket serverSock; //Declare Server Socket variables
    private int serverPort = 6366; //Declare and assign socket server serverPort and which it listens
    private final int defaultServerPort = 6366;
    private boolean serverStarted;
    private boolean serverException = false;
    private DbConnectionManager logger = new DbConnectionManager(APIServer.class);
    
    
    //Getters/Setters >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>  

    public ServerSocket getServerSock() {
        return serverSock;
    }

    public void setServerSock(ServerSocket serverSock) {
        this.serverSock = serverSock;
    }

    public int getServerPort() {
        return serverPort;
    }

    public void setServerPort(int serverPort) {
        this.serverPort = serverPort;
    }

    public int getDefaultServerPort() {
        return defaultServerPort;
    }

    public boolean isServerStarted() {
        return serverStarted;
    }

    public boolean isServerException() {
        return serverException;
    }
    
    
    
    //Methods >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>  
  
    //Start test Server
    public void StartTestServer() throws IOException, ClassNotFoundException{
    
        //create the object for the server socket
        serverSock = new ServerSocket (serverPort);
    
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
        
    }// end TestServer method
    
    @Override
    protected Void doInBackground() throws Exception {
       startServer();
       return null;
    }
    
    
    //Start Server
    public boolean startServer() {
        
        //Try starting the server
        try (ServerSocket serverSock = new ServerSocket(serverPort)){
            serverStarted = true;
            
            //Server keeps running when serverStarted status is true
            while (serverStarted){
                handleClientConnection(serverSock);
               return serverStarted;
            }
            
        }catch (IOException e) {
            logger.CreateLog("error", "Server API : Failed to start.", e);
            if(serverStarted){
                stopServer();
                serverStarted = false;
                serverException = true;
            }
        }
        return serverStarted;
    }//End Server
    
    private void handleClientConnection(ServerSocket serverSock){
        
        //Initialize Client Socket and Streams
        Socket clientSock = null;
        ObjectOutputStream objOutStream = null;
        ObjectInputStream objInStream = null;
        
        try{
            //Accept connection from client , get output and inpurt stream 
            clientSock = serverSock.accept();
            objOutStream = new ObjectOutputStream(clientSock.getOutputStream());
            objInStream = new ObjectInputStream(clientSock.getInputStream());
            
            //Read response from client
            Object request = objInStream.readObject();
            if ("Exit".equals(request)){
                objOutStream.writeObject("Exit"); //send exit response to client
            }else {
                String path = "F:\\School\\Bsc Semester 1 (2025)\\Advanced Object Oriented Programming\\Projects\\Mystic_Timetable\\Transfer\\file.xml";
                sendFile(objOutStream, path); //Send file to client
            }
        }catch (IOException | ClassNotFoundException e){
            logger.CreateLog("error", "Server API : Server closed due to connection or missing file. ", e);
        } finally {
            CloseResources(clientSock, objOutStream, objInStream);
        }
    }// handleClientConnection
    
    //Send file
    private void sendFile(ObjectOutputStream objOutStream, String filePath)
    throws IOException{
        File fileToSend = new File(filePath);
        objOutStream.writeObject(fileToSend);
        JOptionPane.showMessageDialog(null, "File Sent!", "information", JOptionPane.INFORMATION_MESSAGE );
    }
    
    //Close all connection
    public void CloseResources(Socket clientSock, ObjectOutputStream objOutStream,
    ObjectInputStream objInStream){
        try{
            if(objInStream != null) objInStream.close();
            if(objOutStream != null) objOutStream.close();
            if(clientSock != null) clientSock.close();
            logger.CreateLog("info", "Server API : All Connection closed.", null);
        }catch(IOException e){
             logger.CreateLog("error", "Server API : Failed to close connectipn or no connection available.", e);
        }
    }
    
    //Stop Server
    public void stopServer() {
        this.serverStarted = false;
    
    }
    

    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new APIServer();
            }
        });
    }

 
    
} // End APIServer Class 
