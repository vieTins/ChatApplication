/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import Controller.MethodClient;
import View.Main;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.ObjectInputStream;
import java.net.Socket;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author FPTSHOP
 */
public class ClientVideoStreamThread extends Thread{
    Socket videoSocket ;

    @Override
    public void run() {
        try {
            videoSocket = new Socket("localhost", 5001);          
            Client.videoSocket = videoSocket;
            JFrame videoFrame = Main.videoFrame;
            ImageIcon ic;
            JLabel videoFeed = new JLabel();
            MethodClient methodClient = new MethodClient() ;
            videoFrame.setTitle("Client :" + methodClient.getMyName());
            videoFrame.add(videoFeed);
            videoFrame.setVisible(false);
            videoFrame.setSize(Main.VIDEO_HEIGHT, Main.VIDEO_WIDTH);
            videoFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            videoFrame.addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    videoFrame.setVisible(false);
                }
            });
            while (true) {
                ObjectInputStream oin = new ObjectInputStream (videoSocket.getInputStream()) ;
                ic = (ImageIcon) oin.readObject() ;
                videoFeed.setIcon(ic);
                if (!videoFrame.isVisible()) {
                    videoFrame.setVisible(true);
                }
                if (ic != null && ic.getDescription() != null && ic.getDescription().equals("END_VIDEO")) {
                    videoFrame.setVisible(false);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
