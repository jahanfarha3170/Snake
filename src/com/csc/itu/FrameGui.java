package com.csc.itu;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class FrameGui extends JFrame {
    String iconImagePath = "snakeIcon.png";

    FrameGui(){
        PanelGui palnelobj  = new PanelGui();
        this.add(palnelobj);
        this.setTitle("Snake Game");
       // this.setIconImage("");
        this.pack();
        //this.setBackground(Color.black);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
//    private BufferedImage image;
//
//    public ImageIconPanel() {
//        try {
//            image = ImageIO.read(new File("snakeIcon.png"));
//        } catch (IOException ex) {
//            System.out.println("Exception is Icon display"+ex);
//        }
    }

//}
