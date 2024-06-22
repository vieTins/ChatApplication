/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AudioStream;

import View.MainServer;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author FPTSHOP
 */
public class AudioServer extends Thread{
    ServerSocket audioServerSocket ;

    public AudioServer(ServerSocket ss) {
        this.audioServerSocket = ss ;
    }

    @Override
    public void run() {
        try {
            while (true) {                
                Socket s = audioServerSocket.accept() ;
                ObjectOutputStream out = new ObjectOutputStream (s.getOutputStream()); 
                MainServer mainServer = new MainServer() ;
                mainServer.getAudioClientList().add(out );
                new AudioStreamThread(s, out).start(); 
            }
        } catch (Exception e) {
            e.printStackTrace(); 
        }
    }
    
    
}
