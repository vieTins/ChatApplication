/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import View.Main;
import com.github.sarxos.webcam.Webcam;
import java.awt.image.BufferedImage;
import java.io.ObjectOutputStream;
import javax.swing.ImageIcon;

/**
 *
 * @author FPTSHOP
 */
public class VideoOutstreamThread extends Thread {

    ImageIcon ic;
    BufferedImage br;
    ObjectOutputStream stream;
    Webcam cam;

    public VideoOutstreamThread(ImageIcon ic, BufferedImage br, ObjectOutputStream stream, Webcam cam) {
        this.ic = ic;
        this.br = br;
        this.stream = stream;
        this.cam = cam;
    }
     public void run() {
        try {
            while (Main.runCam) {
                br = cam.getImage();
                ic = new ImageIcon(br);
                stream.writeObject(ic);
                stream.flush();
            }
            ic = new ImageIcon("Icon\\endVideo.png", "END_VIDEO");
            stream.writeObject(ic);
            stream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }

        cam.close();
    }
}
