/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AudioStream;

import View.MainServer;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import javax.swing.ImageIcon;

/**
 *
 * @author FPTSHOP
 */
// xử lý việc truyền video từ một user đến tất cả user đã kết nối khác 
public class VideoStreamThread extends Thread{
    Socket s ;

    public VideoStreamThread(Socket s) {
        this.s = s;
    }

    @Override
    public void run() {
         MainServer server = new MainServer() ;
        try { 
            ImageIcon ic ;
            ObjectInputStream oin = new ObjectInputStream(s.getInputStream()) ;
            while (true) {
                ic = (ImageIcon) oin.readObject() ;
                if (ic != null && ic.getDescription()!= null && ic.getDescription().equals("END")) {
                    System.out.println("end recevied");
                    s.close(); 
                    break; 
                }
                else {
                    for (Socket c : MainServer.videoClientList) {
                        ObjectOutputStream oout = new ObjectOutputStream(c.getOutputStream()) ;
                        oout.writeObject(ic);
                        oout.flush(); 
                    }
                     if (ic != null && ic.getDescription() != null && ic.getDescription().equals("END_VIDEO")) {
                        oin = new ObjectInputStream(s.getInputStream());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace(); 
        }
        int i = MainServer.videoClientList.indexOf(s) ;
        MainServer.videoClientList.remove(i);
    }
    
    
}
