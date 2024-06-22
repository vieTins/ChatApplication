/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import Controller.MethodClient;
import Controller.FileChooser;
import Controller.SaveMessage;
import Controller.Scrolling;
import Model.AudioOutStreamThread;
import Model.Client;
import Model.ClientAudioStreamThread;
import Model.ClientVideoStreamThread;
import Model.VideoOutstreamThread;
import com.github.sarxos.webcam.Webcam;
import java.awt.Adjustable;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.Timer;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;
import message.Message;

/**
 *
 * @author FPTSHOP
 */
public class Main extends javax.swing.JFrame {
    
      public static JFrame videoFrame = new JFrame() ;
      public static final int VIDEO_HEIGHT = 320, VIDEO_WIDTH = 240;
      public static  boolean runCam ;
    /**
     * Creates new form Main
     */
    public Main() {
        initComponents();
        this.setLocationRelativeTo(null);
        open();
    }

    private void open() {
        MethodClient Method = new MethodClient();
        setIconImage(new ImageIcon(getClass().getResource("/icon/icon.png")).getImage());
        popUp.add(panel);
        popUp_Emoji.add(panel_Emoji);
        popMix.add(panelMix);
        popMix.setBackground(new Color(0, 0, 0, 0));
        Method.setFram(this);
        new Scrolling(panelChat);
        new Scrolling(panelFriend);
        Method.setTextFieldSyle(txt, "Type a message here ...");
        for (int i = 0; i < 10; i++) {
            cmdSendActionPerformed(null);
        }
        Emoji_Group eg1 = new Emoji_Group("emoji_green.png", 28);
        eg1.setName("emoji_green");
        eg1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                setEmoji(eg1);
            }
        });
        panelGroup.add(eg1);
        Emoji_Group eg2 = new Emoji_Group("emoji_yellow.png", 28);
        eg2.setName("emoji_yellow");
        eg2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                panelEmoji.removeAll();
                for (int i = 1; i <= eg2.getIcons(); i++) {
                    Emoji emo = new Emoji(eg2.getName() + " (" + i + ").png");
                    emo.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent ae) {
                            setEmoji(emo.getName());
                        }
                    });
                    panelEmoji.add(emo);
                }
                panelEmoji.revalidate();
                panelEmoji.repaint();
            }
        });
        panelGroup.add(eg2);
        setEmoji(eg1);
    }

    private void setEmoji(Emoji_Group eg1) {
        panelEmoji.removeAll();
        for (int i = 1; i <= eg1.getIcons(); i++) {
            Emoji emo = new Emoji(eg1.getName() + " (" + i + ").png");
            emo.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    setEmoji(emo.getName());
                }
            });
            panelEmoji.add(emo);
        }
        panelEmoji.revalidate();
        panelEmoji.repaint();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        popUp = new javax.swing.JPopupMenu();
        panel = new View.Panel();
        cmdPhoto = new View.Button();
        cmdEmoji = new View.Button();
        cmdFile = new View.Button();
        cmdMicro = new View.Button();
        cmdColor = new View.Button();
        popUp_Emoji = new javax.swing.JPopupMenu();
        panel_Emoji = new View.Panel();
        panel1 = new View.Panel();
        panelEmoji = new javax.swing.JLayeredPane();
        spGroup = new javax.swing.JScrollPane();
        panelGroup = new javax.swing.JPanel();
        popMix = new javax.swing.JPopupMenu();
        panelMix = new View.Panel();
        panel2 = new View.Panel();
        cmdMix = new View.Button();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lbStatus = new javax.swing.JLabel();
        spFriend = new javax.swing.JScrollPane();
        panelFriend = new javax.swing.JPanel();
        spChat = new javax.swing.JScrollPane();
        panelChat = new javax.swing.JDesktopPane(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.WHITE);
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        cmdLogout = new javax.swing.JButton();
        cmdMore = new javax.swing.JButton();
        cmdSend = new javax.swing.JButton();
        txt = new javax.swing.JTextField(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setColor(new Color(195, 191, 191));
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 0, 0);
            }
        };
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        panel.setBackground(java.awt.Color.white);

        cmdPhoto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8-image-26.png"))); // NOI18N
        cmdPhoto.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/photo_click.png"))); // NOI18N
        cmdPhoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdPhotoActionPerformed(evt);
            }
        });

        cmdEmoji.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8-smile-26.png"))); // NOI18N
        cmdEmoji.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/emoji_click.png"))); // NOI18N
        cmdEmoji.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdEmojiActionPerformed(evt);
            }
        });

        cmdFile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8-paperclip-26.png"))); // NOI18N
        cmdFile.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/file_click.png"))); // NOI18N
        cmdFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdFileActionPerformed(evt);
            }
        });

        cmdMicro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8-microphone-26.png"))); // NOI18N
        cmdMicro.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/microphone_click.png"))); // NOI18N
        cmdMicro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdMicroActionPerformed(evt);
            }
        });

        cmdColor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8-color-26.png"))); // NOI18N
        cmdColor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdColorActionPerformed(evt);
            }
        });

        panel.setLayer(cmdPhoto, javax.swing.JLayeredPane.DEFAULT_LAYER);
        panel.setLayer(cmdEmoji, javax.swing.JLayeredPane.DEFAULT_LAYER);
        panel.setLayer(cmdFile, javax.swing.JLayeredPane.DEFAULT_LAYER);
        panel.setLayer(cmdMicro, javax.swing.JLayeredPane.DEFAULT_LAYER);
        panel.setLayer(cmdColor, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmdEmoji, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmdPhoto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmdFile, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                    .addComponent(cmdMicro, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                    .addComponent(cmdColor, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cmdPhoto, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmdEmoji, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmdFile, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmdMicro, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmdColor, javax.swing.GroupLayout.PREFERRED_SIZE, 30, Short.MAX_VALUE)
                .addContainerGap())
        );

        popUp_Emoji.setForeground(new java.awt.Color(204, 204, 204));
        popUp_Emoji.setBorder(null);
        popUp_Emoji.setMaximumSize(new java.awt.Dimension(504, 355));
        popUp_Emoji.setMinimumSize(new java.awt.Dimension(504, 355));
        popUp_Emoji.setPreferredSize(new java.awt.Dimension(504, 355));

        panel_Emoji.setBackground(new java.awt.Color(255, 255, 255));
        panel_Emoji.setBorder(new EmptyBorder(0, 0, 0, 0));
        panel_Emoji.setForeground(new java.awt.Color(255, 255, 255));
        panel_Emoji.setMaximumSize(new java.awt.Dimension(502, 349));
        panel_Emoji.setMinimumSize(new java.awt.Dimension(502, 349));
        javax.swing.GroupLayout panel_emojiLayout = new javax.swing.GroupLayout(panel_Emoji);
        panel_Emoji.setLayout(panel_emojiLayout);
        panel_emojiLayout.setHorizontalGroup(
            panel_emojiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_emojiLayout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(1, 1, 1))
        );
        panel_emojiLayout.setVerticalGroup(
            panel_emojiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_emojiLayout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(1, 1, 1))
        );

        panel1.setBackground(new java.awt.Color(255, 255, 255));
        panel1.setForeground(new java.awt.Color(255, 255, 255));

        panelEmoji.setMaximumSize(new java.awt.Dimension(448, 291));
        panelEmoji.setMinimumSize(new java.awt.Dimension(448, 291));
        java.awt.FlowLayout flowLayout1 = new java.awt.FlowLayout(java.awt.FlowLayout.LEFT);
        flowLayout1.setAlignOnBaseline(true);
        panelEmoji.setLayout(flowLayout1);

        spGroup.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        spGroup.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        panelGroup.setBackground(new java.awt.Color(249, 249, 249));
        panelGroup.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(218, 213, 213), 1, true));
        spGroup.setViewportView(panelGroup);

        panel1.setLayer(panelEmoji, javax.swing.JLayeredPane.DEFAULT_LAYER);
        panel1.setLayer(spGroup, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panelEmoji, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(spGroup, javax.swing.GroupLayout.DEFAULT_SIZE, 490, Short.MAX_VALUE))
                .addContainerGap())
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(spGroup, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelEmoji, javax.swing.GroupLayout.DEFAULT_SIZE, 293, Short.MAX_VALUE)
                .addContainerGap())
        );

        panel_Emoji.setLayer(panel1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout panel_EmojiLayout = new javax.swing.GroupLayout(panel_Emoji);
        panel_Emoji.setLayout(panel_EmojiLayout);
        panel_EmojiLayout.setHorizontalGroup(
            panel_EmojiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panel_EmojiLayout.setVerticalGroup(
            panel_EmojiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        cmdMix.setBackground(new java.awt.Color(242, 67, 67));
        cmdMix.setText("Start");
        cmdMix.setContentAreaFilled(false);
        cmdMix.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        cmdMix.setOpaque(true);
        cmdMix.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                cmdMixMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmdMixMouseReleased(evt);
            }
        });
        cmdMix.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdMixActionPerformed(evt);
            }
        });

        panel2.setLayer(cmdMix, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout panel2Layout = new javax.swing.GroupLayout(panel2);
        panel2.setLayout(panel2Layout);
        panel2Layout.setHorizontalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cmdMix, javax.swing.GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)
                .addContainerGap())
        );
        panel2Layout.setVerticalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cmdMix, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                .addContainerGap())
        );

        panelMix.setLayer(panel2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout panelMixLayout = new javax.swing.GroupLayout(panelMix);
        panelMix.setLayout(panelMixLayout);
        panelMixLayout.setHorizontalGroup(
            panelMixLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        panelMixLayout.setVerticalGroup(
            panelMixLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Chat ");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8-friend-26.png"))); // NOI18N
        jLabel1.setText("Your list friend");

        lbStatus.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        lbStatus.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);

        spFriend.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        spFriend.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        spFriend.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        panelFriend.setBackground(new java.awt.Color(255, 255, 255));
        panelFriend.setLayout(new javax.swing.BoxLayout(panelFriend, javax.swing.BoxLayout.Y_AXIS));
        spFriend.setViewportView(panelFriend);

        spChat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        spChat.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        spChat.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        panelChat.setBackground(new java.awt.Color(255, 255, 255));
        panelChat.setLayout(new javax.swing.BoxLayout(panelChat, javax.swing.BoxLayout.Y_AXIS));
        spChat.setViewportView(panelChat);

        cmdLogout.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        cmdLogout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8-log-out-26.png"))); // NOI18N
        cmdLogout.setText("Log out");
        cmdLogout.setFocusable(false);
        cmdLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdLogoutActionPerformed(evt);
            }
        });

        cmdMore.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8-menu-26.png"))); // NOI18N
        cmdMore.setBorder(null);
        cmdMore.setContentAreaFilled(false);
        cmdMore.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cmdMore.setFocusable(false);
        cmdMore.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8-menu-26.png"))); // NOI18N
        cmdMore.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8-menu-26.png"))); // NOI18N
        cmdMore.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdMoreActionPerformed(evt);
            }
        });

        cmdSend.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8-send-26.png"))); // NOI18N
        cmdSend.setContentAreaFilled(false);
        cmdSend.setFocusable(false);
        cmdSend.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8-send-26.png"))); // NOI18N
        cmdSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdSendActionPerformed(evt);
            }
        });

        txt.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txt.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 5, 1, 5));
        txt.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txt.setSelectionColor(new java.awt.Color(131, 188, 227));
        txt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtActionPerformed(evt);
            }
        });
        txt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtKeyTyped(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 255));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8-inbox-26.png"))); // NOI18N
        jLabel2.setText("Chat Group ( You and other ...)");

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(cmdLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)))
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(txt, javax.swing.GroupLayout.PREFERRED_SIZE, 571, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(cmdMore, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmdSend, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 157, Short.MAX_VALUE)
                        .addComponent(lbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(spFriend, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(714, Short.MAX_VALUE)))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addContainerGap(253, Short.MAX_VALUE)
                    .addComponent(spChat, javax.swing.GroupLayout.PREFERRED_SIZE, 680, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(473, 473, 473))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(472, 472, 472)))
                .addGap(3, 3, 3)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addContainerGap())
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addComponent(cmdLogout)
                            .addGap(3, 3, 3)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txt)
                            .addComponent(cmdMore)
                            .addComponent(cmdSend, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(34, 34, 34)
                    .addComponent(spFriend, javax.swing.GroupLayout.PREFERRED_SIZE, 466, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(47, Short.MAX_VALUE)))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(34, 34, 34)
                    .addComponent(spChat, javax.swing.GroupLayout.PREFERRED_SIZE, 466, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(47, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void cmdLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdLogoutActionPerformed
        int c = JOptionPane.showConfirmDialog(this, "Are you sure you want to sign out ?", "Sign out", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (c == JOptionPane.YES_OPTION) {
            try {
                MethodClient methodClient = new MethodClient();
                methodClient.getClient().close();
            } catch (Exception e) {
            }
        }
    }//GEN-LAST:event_cmdLogoutActionPerformed

    private void cmdMoreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdMoreActionPerformed
        popUp.show(cmdMore, -10, -210);
    }//GEN-LAST:event_cmdMoreActionPerformed

//    gửi tin nhắn đi 
    private void cmdSendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdSendActionPerformed
        if (txt.getName().equals("have") && !txt.getText().equals("")) {
            try {
                MethodClient Method = new MethodClient();
                String content = Method.getEmailUser() + ":  " + txt.getText() ;
                System.out.println("email " + Method.getEmailUser());
                System.out.println("method " + Method.getMyName());
                SaveMessage saveMessage = new SaveMessage();
                saveMessage.saveMessage(Method.getEmailUser(), Method.getMyName(), txt.getText().trim());
                Method.sendMessage(txt.getText().trim());
                txt.setText("");
                txt.grabFocus();
                writeFile(content);
            } catch (Exception e) {
            }
        }
    }//GEN-LAST:event_cmdSendActionPerformed

    private void cmdEmojiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdEmojiActionPerformed
        popUp.setVisible(false);
        popUp_Emoji.show(txt, 10, -365);
    }//GEN-LAST:event_cmdEmojiActionPerformed

    private void cmdPhotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdPhotoActionPerformed
        popUp.setVisible(false);
        setImage();
    }//GEN-LAST:event_cmdPhotoActionPerformed

    private void cmdFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdFileActionPerformed
        try {
            popUp.setVisible(false);
            setFile();
        } catch (Exception e) {
            showStatus("Error : " + e.getMessage());
            System.out.println(e);
        }
    }//GEN-LAST:event_cmdFileActionPerformed

    private void cmdMicroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdMicroActionPerformed
        popUp.setVisible(false);
        popMix.show(txt, 180, -90);
    }//GEN-LAST:event_cmdMicroActionPerformed

    private void cmdMixActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdMixActionPerformed

    }//GEN-LAST:event_cmdMixActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        start();
    }//GEN-LAST:event_formWindowOpened

    private void cmdMixMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdMixMouseReleased
        try {
            MethodClient Method = new MethodClient();
            cmdMix.setBackground(new Color(242, 67, 67));
            cmdMix.setText("Start");
//            gửi file âm thanh đi khi người dùng thả chuột
            Method.sendSound(Method.getRecoder().stop(), Method.getRecoder().getTime());
            popMix.setVisible(false);
        } catch (Exception e) {
            showStatus("Error : " + e.getMessage());
        }
    }//GEN-LAST:event_cmdMixMouseReleased

    private void txtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtKeyTyped
//        khi nhấn enter gửi đi 
        if (evt.getKeyChar() == 10) {
            cmdSendActionPerformed(null);
        }
    }//GEN-LAST:event_txtKeyTyped

//    xử lý khi người dùng nhấn giữ
    private void cmdMixMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdMixMousePressed
        MethodClient Method = new MethodClient();
        cmdMix.setBackground(new Color(94, 197, 95));
        cmdMix.setText("Starting");
        Method.getRecoder().captureAudio();
    }//GEN-LAST:event_cmdMixMousePressed

    private void txtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtActionPerformed
        // TODO add your handling code here:    
    }//GEN-LAST:event_txtActionPerformed
    private Color selectedColor; 
    private void cmdColorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdColorActionPerformed
      selectedColor = JColorChooser.showDialog(this, "Choose Theme Color", selectedColor);
      if (selectedColor != null) {
          panelChat.setBackground(selectedColor) ;
      } 
    }//GEN-LAST:event_cmdColorActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Webcam cam = Webcam.getDefault() ;
        this.runCam = true ;
        cam.setViewSize(new Dimension(this.VIDEO_HEIGHT , this.VIDEO_WIDTH));
        try {
            ImageIcon ic = null ;
            BufferedImage br = null ;
            ObjectOutputStream vstream = new ObjectOutputStream(Client.videoSocket.getOutputStream()) ;
            cam.open() ;
            new VideoOutstreamThread(ic, br, vstream, cam).start();
            new AudioOutStreamThread().start();
        } catch (Exception e) {
            e.printStackTrace();
        }
        videoStreamStopUI();
    }//GEN-LAST:event_jButton1ActionPerformed
     void videoStreamStopUI () {
        JFrame stopFrame = new JFrame();
        stopFrame.setTitle("Pack()");
        stopFrame.setLayout(new FlowLayout());
        JButton stopButton = new JButton("Stop");
        stopFrame.add(stopButton);
        stopFrame.pack();
        stopFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        stopFrame.setLocationRelativeTo(null);
        stopFrame.setVisible(true);
        stopButton.addActionListener(ae -> {
            this.runCam = false;
            stopFrame.dispose();
        });
        stopFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                runCam = false;
                stopFrame.dispose();
            }
        });
    }
    private Thread th;
    private int currentID = 0;

    private void start() {  
        th = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        MethodClient Method = new MethodClient();
                        System.out.println("waiting message ...");
                        Message ms = (Message) Method.getIn().readObject();
                        System.out.println("Message ID " + ms.getID());
                        String status = ms.getStatus();
                        if (status.equals("Message")) {
                            getMessage(ms.getID(), ms.getMessage());
                        } else if (status.equals("New")) {
                            newFriend(ms.getImage(), ms.getID(), ms.getName().split("!")[0], ms.getName().split("!")[1]);
                        } else if (status.equals("Photo")) {
                            getPhoto(ms.getID(), ms.getImage());
                        } else if (status.equals("File")) {
                            getFile(ms.getID(), ms.getName(), ms.getImage());
                        } else if (status.equals("Error")) {
                            errorFrient(ms.getID());
                        } else if (status.equals("Emoji")) {
                            getEmoji(ms.getID(), ms.getMessage());
                        } else if (status.equals("GetFile")) {
                            download(ms);
                        } else if (status.equals("Sound")) {
                            getSound(ms.getID(), ms.getData(), ms.getMessage());
                        }
                    }
                } catch (Exception e) {
                    String ms = e.getMessage();
                    if (ms.equals("Socket closed")) {
                        signOut("Sign out");
                    } else if (ms.equals("Connection reset")) {
                        signOut("Server has error");
                    } else {
                        signOut("Error : " + ms);
                    }

                }
            }
        });
        th.start();
    }
//   đóng cửa số hiện tại và khởi động màn hình đăng nhập 
    private void signOut(String ms) {
        try {
            this.dispose();
            String[] v = {ms};
            UserLogin userLogin = new UserLogin();
            userLogin.main(v);
        } catch (Exception e) {
        }
    }

    private void getMessage(int ID, String ms) {
        MethodClient Method = new MethodClient();
        if (ID == Method.getMyID()) {   // so sánh người gửi tin nhắn với người dùng hiện tại 
            if (ID == currentID) {   // nếu nhắn từ chính người dùng hiện tại thì sẽ hiển thị trong sendbox 
                Send_Box box = new Send_Box();  
                box.setMessage(ms);
                panelChat.add(box);
            } else {
                Send_Box_New box = new Send_Box_New();
                box.setMessage(ID, ms);
                panelChat.add(box);
            }
        } else { // nếu là khác thì sẽ nhận tin nhắn 
            if (ID == currentID) {
                Get_Box box = new Get_Box();
                box.setMessage(ms);
                panelChat.add(box);
            } else {
                Get_Box_New box = new Get_Box_New();
                box.setMessage(ID, ms);
                panelChat.add(box);
            }
            playSound();
        }
        currentID = ID; // cập nhật lại để lưu lại ID của người mới nhất vừa gửi 
        refresh(panelChat);
        scrollToBottom(spChat);
    }

    private void getPhoto(int ID, ImageIcon image) {
        MethodClient Method = new MethodClient();
        if (ID == Method.getMyID()) {
            if (ID == currentID) {
                Send_Photo_Box box = new Send_Photo_Box();
                box.setPhoto(image);
                panelChat.add(box);
            } else {
                Send_Photo_Box_New box = new Send_Photo_Box_New();
                box.setPhoto(ID, image);
                panelChat.add(box);
            }
        } else {
            if (ID == currentID) {
                Get_Photo_Box box = new Get_Photo_Box();
                box.setPhoto(image);
                panelChat.add(box);
            } else {
                Get_Photo_Box_New box = new Get_Photo_Box_New();
                box.setPhoto(ID, image);
                panelChat.add(box);
            }
            playSound();
        }
        currentID = ID;
        refresh(panelChat);
        scrollToBottom(spChat);
    }

    private void getEmoji(int ID, String emoji) {
        MethodClient Method = new MethodClient();
        if (ID == Method.getMyID()) {
            if (ID == currentID) {
                Send_Emoji box = new Send_Emoji();
                box.setPhoto(emoji);
                panelChat.add(box);
            } else {
                Send_Emoji_New box = new Send_Emoji_New();
                box.setPhoto(ID, emoji);
                panelChat.add(box);
            }
        } else {
            if (ID == currentID) {
                Get_Emoji box = new Get_Emoji();
                box.setPhoto(emoji);
                panelChat.add(box);
            } else {
                Get_Emoji_New box = new Get_Emoji_New();
                box.setPhoto(ID, emoji);
                panelChat.add(box);
            }
            playSound();
        }
        currentID = ID;
        refresh(panelChat);
        scrollToBottom(spChat);
    }

    private void getFile(int ID, String ms, ImageIcon icon) {
        MethodClient Method = new MethodClient();
        if (ID == Method.getMyID()) {
            if (ID == currentID) {
                Send_File box = new Send_File();
                box.set(ms, icon);
                panelChat.add(box);
            } else {
                Send_File_New box = new Send_File_New();
                box.set(ID, ms, icon);
                panelChat.add(box);
            }
        } else {
            if (ID == currentID) {
                Get_File box = new Get_File();
                box.set(ms, icon);
                panelChat.add(box);
            } else {
                Get_File_New box = new Get_File_New();
                box.set(ID, ms, icon);
                panelChat.add(box);
            }
            playSound();
        }
        currentID = ID;
        refresh(panelChat);
        scrollToBottom(spChat);
    }

    private void getSound(int ID, byte[] sound, String time) {
        MethodClient Method = new MethodClient();
        if (ID == Method.getMyID()) {
            if (ID == currentID) {
                Send_Sound box = new Send_Sound();
                box.set(sound, time);
                panelChat.add(box);
            } else {
                Send_Sound_New box = new Send_Sound_New();
                box.set(ID, sound, time);
                panelChat.add(box);
            }
        } else {
            if (ID == currentID) {
                Get_Sound box = new Get_Sound();
                box.set(sound, time);
                panelChat.add(box);
            } else {
                Get_Sound_New box = new Get_Sound_New();
                box.set(ID, sound, time);
                panelChat.add(box);
            }
            playSound();
        }
        currentID = ID;
        refresh(panelChat);
        scrollToBottom(spChat);
    }

    private void download(Message ms) {  // tải file xuống khi người dùng gửi đến 
        try {
            File file = new File(ms.getName());
            FileOutputStream out = new FileOutputStream(file);
            out.write(ms.getData());  // ghi dữ liệu vào file
            out.close();
        } catch (Exception e) {
            showStatus("Error : can't download");
        }
    }

    private void newFriend(ImageIcon image, int ID, String name, String time) {
        MethodClient Method = new MethodClient();
        Friend_Box friend = new Friend_Box();
        friend.set(image, ID, name, time);
//         thêm vào danh sách client online 
        Method.getFriends().put(ID, friend);
        if (Method.getMyName().equalsIgnoreCase(name)) {  // nếu tên người dùng trùng với tên của người dùng hiện tại 
            Method.setMyID(ID);
            friend.itMe();  // đánh dấu là chính bản thân mình 
        }
        panelFriend.add(friend);
        refresh(panelFriend);
    }

    private void errorFrient(int ID) {
        MethodClient Method = new MethodClient();
        panelFriend.remove((Component) Method.getFriends().get(ID));
        Method.getFriends().remove(ID);
        refresh(panelFriend);
    }

    private void refresh(Component obj) {
        obj.revalidate();
        obj.repaint();
    }

    private void setImage() {
        JFileChooser ch = new JFileChooser();
        FileChooser preview = new FileChooser();
        ch.setAccessory(preview);
        ch.addPropertyChangeListener(preview);
        ch.setFileFilter(new FileFilter() {  // lọc file
            @Override
            public boolean accept(File file) { // chỉ cho phép chọn file ảnh 
                String name = file.getName();
                return file.isDirectory() || name.endsWith(".png") || name.endsWith(".PNG") || name.endsWith("jpg") || name.endsWith("JPG");
            }

            @Override
            public String getDescription() {
                return "png,jpg";
            }
        });
        int c = ch.showOpenDialog(this);
        if (c == JFileChooser.APPROVE_OPTION) {
            ImageIcon image = new ImageIcon(ch.getSelectedFile().getAbsolutePath());
            try {
                MethodClient Method = new MethodClient();
//                 gửi file ảnh 
                Method.sendPhoto(image);
            } catch (Exception e) {
                showStatus("Error : Can't send photo");
            }
        }
    }

    private void setFile() throws Exception {
        JFileChooser ch = new JFileChooser();
//        tạo một bản xem trước 
        FileChooser preview = new FileChooser();
        ch.setAccessory(preview);
        ch.addPropertyChangeListener(preview);
        int c = ch.showOpenDialog(this);
        if (c == JFileChooser.APPROVE_OPTION) {
//            gửi file đi 
            MethodClient Method = new MethodClient();
            Method.sendFile(ch.getSelectedFile());
        }
    }

//     tự cuộn xuống cuối nếu nội dung có sự thay đổi
    private void scrollToBottom(JScrollPane scrollPane) {
        JScrollBar verticalBar = scrollPane.getVerticalScrollBar();
//        xem sự thay đổi của thanh cuộn
        AdjustmentListener downScroller = new AdjustmentListener() {
            @Override
            public void adjustmentValueChanged(AdjustmentEvent e) {
                Adjustable adjustable = e.getAdjustable();
                adjustable.setValue(adjustable.getMaximum());
                verticalBar.removeAdjustmentListener(this);
            }
        };
        verticalBar.addAdjustmentListener(downScroller);
    }

//    gửi emoji
    private void setEmoji(String emoji) {
        try {
            MethodClient Method = new MethodClient();
            Method.sendEmoji(emoji);
        } catch (Exception e) {
            showStatus("Error : " + e.getMessage());
        }
    }
// phát âm thanh 
    private void playSound() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = this.getClass().getClassLoader().getResource("sound/sound.wav");
                    AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
                    Clip clip = AudioSystem.getClip();
                    clip.open(audioIn);
                    clip.start();
                } catch (Exception e) {
                }
            }
        }).start();
    }
//     tạo một timer để hiển thị thông báo trong khoảng tgian 5s 
  private Timer timer = new Timer(5000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            lbStatus.setText("");
            timer.stop();
        }
    });    


    private void showStatus(String error) {
        if (timer.isRunning()) {
            lbStatus.setText("");
            timer.stop();
        }
        timer.start();
        lbStatus.setText(error);
    }

    public void writeFile(String content) {
        BufferedWriter bufferedWriter = null;
        try {
            LocalDate currentDate = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");
            String dateString = currentDate.format(formatter);
            String fileName = "D:\\" + dateString + ".txt";
            LocalTime currentTime = LocalTime.now();
            DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("hh:mm:ss a");
            String formattedTime = currentTime.format(formatter2);
            File file = new File(fileName);
            if (!file.exists()) {
                file.createNewFile();
                bufferedWriter = new BufferedWriter(new FileWriter(file));
                bufferedWriter.write("--------------------------------------------------\n");
                bufferedWriter.write("--------------- " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")) + " --------------\n");
                bufferedWriter.write("--------------------------------------------------\n\n");
            }
            else {
                 bufferedWriter = new BufferedWriter(new FileWriter(file , true));
            }
            bufferedWriter.write(content + " (" + formattedTime + ")");
            bufferedWriter.newLine(); 
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {

                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
                new ClientVideoStreamThread().start();
                new ClientAudioStreamThread().start();
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private View.Button cmdColor;
    private View.Button cmdEmoji;
    private View.Button cmdFile;
    private javax.swing.JButton cmdLogout;
    private View.Button cmdMicro;
    private View.Button cmdMix;
    private javax.swing.JButton cmdMore;
    private View.Button cmdPhoto;
    private javax.swing.JButton cmdSend;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lbStatus;
    private View.Panel panel;
    private View.Panel panel1;
    private View.Panel panel2;
    private javax.swing.JDesktopPane panelChat;
    private javax.swing.JLayeredPane panelEmoji;
    private javax.swing.JPanel panelFriend;
    private javax.swing.JPanel panelGroup;
    private View.Panel panelMix;
    private View.Panel panel_Emoji;
    private javax.swing.JPopupMenu popMix;
    private javax.swing.JPopupMenu popUp;
    private javax.swing.JPopupMenu popUp_Emoji;
    private javax.swing.JScrollPane spChat;
    private javax.swing.JScrollPane spFriend;
    private javax.swing.JScrollPane spGroup;
    private javax.swing.JTextField txt;
    // End of variables declaration//GEN-END:variables
}
