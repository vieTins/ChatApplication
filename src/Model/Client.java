/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import Controller.MethodServer;
import View.Button;
import com.github.sarxos.webcam.Webcam;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.filechooser.FileSystemView;
import message.Message;

/**
 *
 * @author FPTSHOP
 */
public class Client extends Thread{
    private Socket socket;
    private String userName;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private ImageIcon profile;
    private int ID;
    private String time;
    static MethodServer methodServer ;
    public static Socket videoSocket ;
    public static Socket audioSocket; 
    
    public Client(Socket socket) {
        this.socket = socket;
        methodServer = new MethodServer() ;
        execute();
    }

    private void execute() {
        this.start();
    }

    @Override
    public void run() {
        try {
            in = new ObjectInputStream(socket.getInputStream());
            out = new ObjectOutputStream(socket.getOutputStream());
            ID = methodServer.addClient(this);
            //  loop starting get message from client
            while (true) {
                Message ms = (Message) in.readObject();
                String status = ms.getStatus();
                if (status.equals("New")) {
                    userName = ms.getName().split("!")[0];
                    time = ms.getName().split("!")[1];
                    profile = ms.getImage();
//                    show lên text area khi người dùng mới connect 
                    methodServer.getTxt().append("New Client name : " + userName + " has connected ...\n");
                    //  list all friend send to new client login
                    for (Client client : methodServer.getClients()) {
                        ms = new Message();
                        ms.setStatus("New");
                        ms.setID(client.getID());
                        ms.setName(client.getUserName() + "!" + client.getTime());
                        ms.setImage(client.getProfile());
                        out.writeObject(ms);
                        out.flush();
                    }
                    // gửi client mới đến client cũ 
                    for (Client client : methodServer.getClients()) {
                        if (client != this) {
                            ms = new Message();
                            ms.setStatus("New");
                            ms.setName(userName + "!" + time);
                            ms.setID(ID);
                            ms.setImage(profile);
                            client.getOut().writeObject(ms);
                            client.getOut().flush();
                        }
                    }
                } else if (status.equals("File")) {
                    int fileID = methodServer.getFileID();
                    String fileN = ms.getName();
                    SimpleDateFormat df = new SimpleDateFormat("ddMMyyyyhhmmssaa");
                    String fileName = fileID + "!" + df.format(new Date()) + "!" + ms.getName().split("!")[0];
                    methodServer.getTxt().append(fileName);
                    FileOutputStream output = new FileOutputStream(new File("D:\\AdvancedJAVA\\JavaChatGroup\\src\\DataServer\\" + fileName));
                    output.write(ms.getData());
                    output.close();
                    methodServer.setFileID(fileID + 1);
                    ms = new Message();
                    ms.setStatus("File");
                    ms.setName(fileID + "!" + fileN);
                    ms.setImage((ImageIcon) FileSystemView.getFileSystemView().getSystemIcon(new File("D:\\AdvancedJAVA\\JavaChatGroup\\src\\DataServer\\" + fileName)));
                    ms.setID(ID);
                    for (Client client : methodServer.getClients()) {
                        client.getOut().writeObject(ms);
                        client.getOut().flush();
                    }
                } else if (status.equals("download")) {
                    sendFile(ms);
                } else {
                    for (Client client : methodServer.getClients()) {
                        client.getOut().writeObject(ms);
                        client.getOut().flush();
                    }
                }
            }

        } catch (Exception e) {
            try {
                methodServer.getClients().remove(this);
                methodServer.getTxt().append("Client Name : " + userName + " has been out of this server ...\n");
                for (Client s : methodServer.getClients()) {
                    Message ms = new Message();
                    ms.setStatus("Error");
                    ms.setID(ID);
                    ms.setName(userName);
                    s.getOut().writeObject(ms);
                    s.getOut().flush();
                }
            } catch (Exception e1) {
                methodServer.getTxt().append("Error : " + e1);
            }
        }
    }

    private void sendFile(Message ms) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String fID = ms.getMessage();
                File file = new File("D:\\AdvancedJAVA\\JavaChatGroup\\src\\DataServer");
                for (File f : file.listFiles()) {
                    if (f.getName().startsWith(fID)) {
                        try {
                            FileInputStream ins = new FileInputStream(f);
                            byte data[] = new byte[ins.available()];
                            ins.read(data);
                            ins.close();
                            ms.setData(data);
                            ms.setStatus("GetFile");
                            out.writeObject(ms);
                            out.flush();
                            break;
                        } catch (Exception e) {

                        }
                    }
                }
            }
        }).start();
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public ObjectInputStream getIn() {
        return in;
    }

    public void setIn(ObjectInputStream in) {
        this.in = in;
    }

    public ObjectOutputStream getOut() {
        return out;
    }

    public void setOut(ObjectOutputStream out) {
        this.out = out;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public ImageIcon getProfile() {
        return profile;
    }

    public void setProfile(ImageIcon profile) {
        this.profile = profile;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
    public Socket getVideoSocket() {
        return videoSocket;
    }    
    public Client() {
    }
    
}
