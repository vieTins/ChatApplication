/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AudioStream;

import View.MainServer;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author FPTSHOP
 */
// lắng nghe các kết nối của user video đến 
public class VideoServer extends Thread{
    ServerSocket videoServerSocket;

    public VideoServer (ServerSocket ss) {
        videoServerSocket = ss;
    }

    @Override
    public void run() {
        try {
            Socket socket = videoServerSocket.accept();
            MainServer mainServer = new MainServer() ;
//            mainServer.getVideoClientList().add(socket);
            MainServer.videoClientList.add(socket);
            new VideoStreamThread(socket).start(); 
        } catch (Exception e) {
            e.printStackTrace(); 
        }
    }
    
}
