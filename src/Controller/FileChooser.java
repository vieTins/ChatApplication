/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JPanel;

/**
 *
 * @author FPTSHOP
 */
public class FileChooser extends JPanel implements PropertyChangeListener {

    private int width, height;  // kích thước hình ảnh 
    private ImageIcon icon;
    private Image image;  // chứa hình ảnh 
    private static final int ACCSIZE = 155;// kích thước tối đa của panel 
    private final Color bg;  

    public FileChooser() {
        setPreferredSize(new Dimension(ACCSIZE, -1));
        bg = getBackground();
    }

//     được gọi khi có thay đổi từ JFileChoooser
    @Override
    public void propertyChange(PropertyChangeEvent e) {
        String propertyName = e.getPropertyName(); // lấy tên thuộc tính thay đổi 
        if (propertyName.equals(JFileChooser.SELECTED_FILE_CHANGED_PROPERTY)) {
            File selection = (File) e.getNewValue();
            String name;
            if (selection == null) {
                return;
            } else {
                name = selection.getAbsolutePath();
            }
//            kiểm tra nếu là file hình ảnh 
            if ((name != null)
                    && name.toLowerCase().endsWith(".jpg")
                    || name.toLowerCase().endsWith(".jpeg")
                    || name.toLowerCase().endsWith(".gif")
                    || name.toLowerCase().endsWith(".png")) {
                icon = new ImageIcon(name);
                image = icon.getImage();
                scaleImage();
                repaint();
            }
        }
    }

    private void scaleImage() {
        width = image.getWidth(this);
        height = image.getHeight(this);
        double ratio = 1.0;
        if (width >= height) {
            ratio = (double) (ACCSIZE - 5) / width;
            width = ACCSIZE - 5;
            height = (int) (height * ratio);
        } else {
            if (getHeight() > 150) {
                ratio = (double) (ACCSIZE - 5) / height;
                height = ACCSIZE - 5;
                width = (int) (width * ratio);
            } else {
                ratio = (double) getHeight() / height;
                height = getHeight();
                width = (int) (width * ratio);
            }
        }

        image = image.getScaledInstance(width, height, Image.SCALE_DEFAULT);
    }

    // vẽ lại panel 
    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(bg);
        g.fillRect(0, 0, ACCSIZE, getHeight());
        g.drawImage(image, getWidth() / 2 - width / 2 + 5,
                getHeight() / 2 - height / 2, this);
    }

}
