/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AudioStream;

import View.MainServer;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author FPTSHOP
 */
public class AudioStreamThread extends Thread{
    Socket socket ;
    private ObjectInputStream ois ;
    private ObjectOutputStream out  ;

    public AudioStreamThread(Socket socket, ObjectOutputStream out) {
        this.socket = socket;
        this.out = out;
    }

    @Override
    public void run() {
          MainServer server = new MainServer() ;
        try {
            ois  = new ObjectInputStream(socket.getInputStream()) ;
            byte[] data = new byte[1024] ;
            while (true) {
                int dsize = ois.read(data) ;
                if (dsize == 1024) {
                   for (ObjectOutputStream oout : server.getAudioClientList()) {
                       oout.write(data , 0 , dsize);
                       oout.reset();
                   }
                }
                else if (dsize == 512) {
                    System.out.println("Server : dsize - " + dsize + " client stopped ");
                    ois = new ObjectInputStream(socket.getInputStream());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        int i =  server.getAudioClientList().indexOf(out);
        server.getAudioClientList().remove(i);
    }


}
