package university.management.system;

import java.awt.*;
import java.net.URL;
import javax.swing.*;

public class Splash extends JFrame implements Runnable {
    
    //Declare thread t -> we used 2nd method of Multithreading
    Thread t;
    
    Splash(){
        
        //To add images over a frame
        ImageIcon i1 = Splash.loadAndScaleIcon("icons/first.jpg", 1000, 700);
        JLabel f_image = new JLabel(i1);
        add(f_image);
        
        JLabel head = new JLabel("WELCOME TO PRIZZWAL - UNIVERSITY");
        head.setFont(new Font("Open Sans",Font.BOLD,35));
        head.setBounds(80,60,900,35);
        f_image.add(head);
        
        JLabel info = new JLabel("Excellence in Rizzness, since 2003");
        info.setFont(new Font("Open Sans",Font.BOLD,20));
        info.setBounds(80,110,900,35);
        f_image.add(info);
        
        ImageIcon i2 = Splash.loadAndScaleIcon("icons/univLogo.jpg", 140, 120);
        JLabel u_image = new JLabel(i2);
        u_image.setBounds(800,45,140,120);
        f_image.add(u_image);
        
        t = new Thread(this);
        // this method internally starts the run() method
        t.start();
        
        //Make frame
        
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        
        // loop -> 0 to 1/2(image_length)
        int x = 1;
        for (int i = 2; i <= 600; i+=4, x+=1){
            setLocation(900 - (i+x/2),350 - (i/2));
            setSize(i + 3*x, i + x/2);
            
            try {
                //we are adding a delay of 10 ms
                Thread.sleep(10);
            } catch (InterruptedException e){
                System.out.println(e);
            }
        }
        
        
    }
    
    public static ImageIcon loadAndScaleIcon(String iconPath, int width, int height) {
        URL resource = ClassLoader.getSystemResource(iconPath);
        
        if (resource != null) {
            ImageIcon originalIcon = new ImageIcon(resource);
            Image scaledImage = originalIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
            return new ImageIcon(scaledImage);
        } else {
            // Handle the case where the resource is not found
            return null;
        }
    }
    
    @Override
    public void run(){
        try {
            Thread.sleep(7000);
            //Now we hide the start frame
            setVisible(false);
            //Open the next frame
            new Login();
        } catch (InterruptedException e){
            System.out.println(e);
        }
    }
    
    public static void main(String[] args){
        //Anonymous object
        new Splash();
    }
    
}
