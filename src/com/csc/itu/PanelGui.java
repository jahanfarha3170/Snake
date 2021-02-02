package com.csc.itu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class PanelGui extends JPanel implements ActionListener {
    static final int SCREEN_WIDTH = 800;
    static final int SCREEN_HEIGHT = 500;
    static final int OBJECT_SIZE = 15;// defines the size of the object
    static final int OBJECTS_COUNT = (SCREEN_WIDTH*SCREEN_HEIGHT)/(OBJECT_SIZE); // defines the number of object to fit in this size
    static final int OBJECTS_DELAY = 75;// higher the delay, slow will be the game
    final int x[] = new int[OBJECTS_COUNT]; // x co-ordinate for snake movement
    final int y[] = new int[OBJECTS_COUNT]; // y co-ordinate for snake movement
    int snakeBodyPart = 6;
    int appleEatCount;
    int appleGenerateXaxis;
    int appleGenerateYaxis;
    char snakeMovingDirection = 'R'; // assuming snake start from right direction
    boolean running =false;
    Timer time;
    Random random;
    ImageIcon SnakeIcon;

    PanelGui(){
        random = new Random();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        startGame();
    }
    public void startGame(){
        addNewAppleAtDifferentLocation();
        running = true;
        time = new Timer(OBJECTS_DELAY, this);
        time.start();
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        drawApple(g);
    }

    public void drawApple(Graphics g){
//        SnakeIcon = new ImageIcon("snakeIcon.png");
//        SnakeIcon.paintIcon(this, g, 25,11);
//        System.out.println("=====");
//
        for(int i=0; i<(SCREEN_HEIGHT/OBJECT_SIZE);i++){
            //g.drawLine(x1, y1, x2, y2);
            g.drawLine(i*OBJECT_SIZE,0, i*OBJECT_SIZE , SCREEN_HEIGHT);
            g.drawLine(0,i*OBJECT_SIZE, SCREEN_WIDTH , i*OBJECT_SIZE);
        }
        g.setColor(Color.red);
        g.fillOval(appleGenerateXaxis, appleGenerateYaxis,OBJECT_SIZE, OBJECT_SIZE);//Size of Apple

        //Below will draw snake body
        for(int i=0;i<snakeBodyPart;i++){
            if(i==0){
                g.setColor(Color.green);
            }
            else{
                g.setColor(Color.yellow);
            }
            g.fillRect(x[i], y[i],OBJECT_SIZE, OBJECT_SIZE);
        }

    }

    public void addNewAppleAtDifferentLocation(){
        appleGenerateXaxis = random.nextInt((int)(SCREEN_WIDTH/OBJECT_SIZE))*OBJECT_SIZE ;//generates apple in X axis
        System.out.println("app x---"+appleGenerateXaxis);
        appleGenerateYaxis = random.nextInt((int)(SCREEN_HEIGHT/OBJECT_SIZE))*OBJECT_SIZE;//generates apple in Y axis
        System.out.println("app y---"+appleGenerateYaxis);
    }

    public void move(){
        for(int i=snakeBodyPart; i>0; i--){
            x[i] = x[i-1];
            y[i] = y[i-1];
        }
        switch (snakeMovingDirection){
            case('R'):
                x[0] = x[0]+OBJECT_SIZE;
                break;
            case('L'):
                x[0] = x[0]-OBJECT_SIZE;
                break;
            case('U'):
                y[0] = y[0]-OBJECT_SIZE;
                break;
            case('D'):
                y[0] = y[0]+OBJECT_SIZE;
                break;
        }

    }
    public void checkApple(){

    }

    public void checkCollision(){
        // Here we will check snake head is collide with snake body
        for(int i=snakeBodyPart; i>0;i--){
            if((x[0]==x[i]) && (y[0]==y[i]) ){
                running=false;
            }
        }
        // Here we will check snake head is collide with left border of game area
        if(x[0] < 0){
            running=false;
        }
        // Here we will check snake head is collide with right border of game area
        if(x[0] > SCREEN_WIDTH){
            running=false;
        }
        // Here we will check snake head is collide with Top border of game area
        if(y[0] <0){
            running=false;
        }
        // Here we will check snake head is collide with Bottom border of game area
        if(y[0] > SCREEN_HEIGHT){
            running=false;
        }
        if(!running){
            time.stop();
        }

    }

    public void GameOver(){

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(running){
            move();
            checkApple();
            checkCollision();
        }
        repaint();
    }

    public class  MyKeyAdapter extends KeyAdapter {
        //Here we will limit the snake turn direction to 90 degree limit
        @Override
        public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_RIGHT:
                if (snakeMovingDirection != 'L') {
                    snakeMovingDirection = 'R';
                }
                break;
            case KeyEvent.VK_LEFT:
                if (snakeMovingDirection != 'R') {
                    snakeMovingDirection = 'L';
                }
                break;
            case KeyEvent.VK_UP:
                if (snakeMovingDirection != 'D') {
                    snakeMovingDirection = 'U';
                }
                break;
            case KeyEvent.VK_DOWN:
                if (snakeMovingDirection != 'U') {
                    snakeMovingDirection = 'D';
                }
                break;
        }
        }

        }

    }


