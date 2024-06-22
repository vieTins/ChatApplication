/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import View.Main;
import java.io.ObjectInputStream;
import java.net.Socket;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;

/**
 *
 * @author FPTSHOP
 */
public class ClientAudioStreamThread extends Thread {

    Socket audioSocket;
    ObjectInputStream ois;
    AudioFormat format;
    DataLine.Info info;
    SourceDataLine speakers;
    byte[] data;

    @Override
    public void run() {
        try {
            audioSocket = new Socket("localhost", 5002);
            Client.audioSocket = audioSocket;
            data = new byte[1024];
            format = new AudioFormat(48000.0f, 16, 2, true, false);
            info = new DataLine.Info(SourceDataLine.class, format);
            data = new byte[1024];
            speakers = (SourceDataLine) AudioSystem.getLine(info);
            speakers.start();
            ois = new ObjectInputStream(audioSocket.getInputStream());
            while (true) {
                int dsize = ois.read(data);
                if (dsize == 1024) {
                    speakers.write(data, 0, dsize);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
