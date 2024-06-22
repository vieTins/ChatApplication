/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import View.Main;
import java.io.ObjectOutputStream;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.TargetDataLine;

/**
 *
 * @author FPTSHOP
 */
public class AudioOutStreamThread extends Thread{
    private ObjectOutputStream oos;
    private AudioFormat format;
    private DataLine.Info info;
    private TargetDataLine microphone;
    private byte[] data;
    private int dsize;

    public AudioOutStreamThread() {
    }

    @Override
    public void run() {
        try {
            format = new AudioFormat(48000.0f, 16, 2, true, false);
            microphone = AudioSystem.getTargetDataLine(format);
            info = new DataLine.Info(TargetDataLine.class, format);
            data = new byte[1024];

            microphone = (TargetDataLine) AudioSystem.getLine(info);
            microphone.open(format);
            microphone.start();
            oos = new ObjectOutputStream(Client.audioSocket.getOutputStream());
            while (Main.runCam) {
                dsize = microphone.read(data, 0, data.length);
                oos.write(data, 0, dsize);
                oos.reset();
            }
            System.out.println("[ Client ] : Attempting to stop ");
            oos.write(data, 0, 512);
            oos.flush();

        } catch (Exception e) {
            e.printStackTrace();
        }
        microphone.stop();
        microphone.close();
    }

}
