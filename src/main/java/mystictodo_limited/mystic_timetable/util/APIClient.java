/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mystictodo_limited.mystic_timetable.util;

import java.awt.Panel;
import java.io.*;
import java.io.IOException;
import java.lang.*;
import java.net.*;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import mystictodo_limited.mystic_timetable.db.DbConnectionManager;
import mystictodo_limited.mystic_timetable.tools.FileOpenSave;

/**
 *
 * @author Jamario_Downer
 */
public class APIClient extends SwingWorker<Void, Void> {
    
    //Constructor >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>  
    public APIClient (){
        logger = new DbConnectionManager(APIClient.class);
        fileOpenSave = new FileOpenSave();
        clientStarted = false;
    }//end default constructor
    
    public APIClient (int hostPort) throws UnknownHostException {
       this();
       if(clientHostInfo == null){
            this.clientHostInfo = InetAddress.getLocalHost();
       }
       this.clientPort = hostPort;
    
    }
    
    //Feilds >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>  
   
    private InetAddress clientHostInfo;  //Host information
    private int clientPort; //Declare and assign socket server port and which it listens
    private final int defaultClientPort = 6366;
    private boolean clientStarted;
    private boolean clientException = false;
    private DbConnectionManager logger;
    private FileOpenSave fileOpenSave;
    
    //Getters/Setters >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    public int getClientPort() {
        return clientPort;
    }
    
    public void setClientPort() {
        this.clientPort = defaultClientPort;
    }

    public void setClientPort(int clientPort) {
        //APIClient.clientPort = clientPort;
        this.clientPort = clientPort;
    }
    
    public InetAddress getClientHostInfo() {
        return clientHostInfo;
    }

    public void setHostInfo()throws UnknownHostException {
        try{
            //APIClient.clientHostInfo = InetAddress.getLocalHost();
            this.clientHostInfo = InetAddress.getLocalHost();
        }catch (UnknownHostException e){
            logger.CreateLog("error", "Failed to get Local host info.", e);
        }
    }
    
    public void setClientHostInfo(InetAddress clientHostInfo) {
        this.clientHostInfo = clientHostInfo;
        //APIClient.clientHostInfo = clientHostInfo;
    }

    public int getDefaultClientPort() {
        return defaultClientPort;
    }

    public boolean isClientStarted() {
        return clientStarted;
    }

    public boolean isClientException() {
        return clientException;
    }
    
    
    
    
    //Methods >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    @Override
    protected Void doInBackground() throws Exception {
        startClient();
        return null;
    }
    
    
    //Start Client
    public boolean startClient() {
        
        //Try starting the Client
        try {
            clientStarted = true;
            
            //Client keeps running when clientStarted status is true
            while (clientStarted){
               handleServerConnection();
               return clientStarted;
            }
        }catch (Exception e) {
            logger.CreateLog("error", "Server API : Failed to start.", e);
            if(clientStarted){
                stopClient();
                clientStarted = false;
                clientException = true;
            }
        }
        return clientStarted;
    }//End Server

    
    // Starts handleServerConnection
    public void handleServerConnection(){
        
        // Loops 4 times until a file or response is received
        for(int i = 0; i < 5; i++) {
            
            //Initialize Client Socket and Streams
            Socket senderSock = null;
            ObjectOutputStream objOutStream = null;
            ObjectInputStream objInStream = null;
            
            try {
                
                //Exstablish connection to server, write sockect for ObjectInputStream, read response from sender 
                senderSock = new Socket(clientHostInfo.getHostName(), clientPort);
                objOutStream = new ObjectOutputStream(senderSock.getOutputStream());
                objInStream = new ObjectInputStream(senderSock.getInputStream());
                
                //Send Request to server for response
                sendRequest(objOutStream, i);
                Object response = receiveResponse(objInStream);
                
                //Check if response is a string or file
                if (response instanceof String){
                    //Output String response to console 
                    System.out.println("Message received: " + response);
                    
                    //server closed if Exit response is received
                    if ("Exit".equals(response)){
                        stopClient();
                        break;
                    }
                } else if (response instanceof File){
                    //File response
                    File receivedFile = (File) response;
                    saveFile(receivedFile); //saves file received 
                    
                    //Output file name to console
                    System.out.println("File received: " + receivedFile.getName());                
                    stopClient();
                }
            } catch (IOException | ClassNotFoundException e) {
                logger.CreateLog("error", "Client API : Failed to start. ", e);
            }finally {
                CloseResources(senderSock, objOutStream, objInStream);
            }//End exception handling 
        }//End for loop
    }//End StartClient
    
    private void sendRequest(ObjectOutputStream objOutStream, int count) 
    throws IOException {
        System.out.println("Sending request to Server Socket");
        
        // Checks if 4 attempts are made, if so, use Exit to end close the client connection
        if (count == 4) {
            objOutStream.writeObject("Exit");
        } else {
            objOutStream.writeObject(String.valueOf(count));
        }
    }//End sendRequest
    
    
    private Object receiveResponse(ObjectInputStream objInStream) throws
    IOException, ClassNotFoundException{
        
        return objInStream.readObject();
    }//End receiveResponse
    
    private void saveFile(File file) throws IOException {
    try {
             //saves received file 
            String destinationPath = null; // will use default location if not set
            fileOpenSave.saveToFileDirect(file, destinationPath);
            JOptionPane.showMessageDialog(null, "File Downloaded!", "information", JOptionPane.INFORMATION_MESSAGE );

        } catch (IOException e){
            logger.CreateLog("error", "Failed to save file", e);
        }
    
    }//End saveFile
    
    //Close all resources
        public void CloseResources(Socket senderSock, ObjectOutputStream objOutStream,
    ObjectInputStream objInStream){
        try{
            if(objInStream != null) objInStream.close();
            if(objOutStream != null) objOutStream.close();
            if(senderSock != null) senderSock.close();
            logger.CreateLog("info", "Client API : All Connection closed.", null);
        }catch(IOException e){
             logger.CreateLog("error", "Client API : Failed to close connection or no connection available.", e);
        }
    }
    
    //Stop Client
    public void stopClient(){
        this.clientStarted = false;
    }
    
    
    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new APIClient();
            }
        });
    }

   
//   public void StartTestClient() throws UnknownHostException, InterruptedException, IOException, ClassNotFoundException {
//        //get the LocalHost IP address, and if the server is using the same ip then use that
//        InetAddress host = InetAddress.getLocalHost();
//        Socket senderSock = null;
//        ObjectOutputStream objOutStream = null;
//        ObjectInputStream objInStream = null;
//        
//        for (int i=0; i < 5; i++ ){
//            
//            //extablish a connection to the server 
//            senderSock = new Socket (host.getHostName(), clientPort);
//            
//            //Write the socket for the ObjectOutputStream
//            objOutStream = new ObjectOutputStream(senderSock.getOutputStream());
//            
//            //Print a Message to send a request to the server socket
//            System.out.println("Sending request to Server Socket");
//            
//            //if count is equal to 5, then exit
//            if (i == 4 || senderSock.isConnected()){
//                objOutStream.writeObject("Exit");
//            }else{
//                objOutStream.writeObject("" + i);
//            }
//            
//            //Read the response message from the sender
//            objInStream = new ObjectInputStream(senderSock.getInputStream());
//            
//            //Convert ObjectInputStream to String
//            String objMessage = (String) objInStream.readObject();
//           
//            //Print the Message on host
//            System.out.println("Message received : " + objMessage);
//            
//            //Close all
//            objInStream.close();
//            objOutStream.close();
//            senderSock.close();
//            
//        }// end for lopp
//    } // end Client method
    
    
    
} // End APIClient Class 
