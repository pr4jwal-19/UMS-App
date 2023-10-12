package university.management.system;

import java.awt.*;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class About extends JFrame {
    
    JLabel heading,name,linkedin,insta,mail;
    
    About(){
        
         setLayout(null);
        
         setSize(700,500);
         setLocation(400,150);
         getContentPane().setBackground(Color.WHITE);
        
         ImageIcon i1 = Splash.loadAndScaleIcon("icons/about.jpg", 300, 200);
         JLabel image = new JLabel(i1);
         image.setBounds(350,0,300,200);
         add(image);
         
         //start
         heading = new JLabel("<html>UNIVERSITY<br/>MANAGEMENT SYSTEM</html>");
         heading.setBounds(30,25, 300,130);
         heading.setFont(new Font("Avenir",Font.PLAIN,25));
         add(heading);
         
         name = new JLabel("Developed By : Prajwal Nakure");
         name.setBounds(30,225, 500,30);
         name.setFont(new Font("Avenir",Font.PLAIN,25));
         add(name);
         
         linkedin = new JLabel();
         linkedin.setText("<html><a href='https://www.linkedin.com/in/prajwal-nakure-566672241'><font size='5'>LinkedIn</font></a></html>");
         linkedin.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseClicked(MouseEvent e) {
                // Open the link in a web browser
                if (Desktop.isDesktopSupported()) {
                    try {
                        Desktop.getDesktop().browse(new URI("https://www.linkedin.com/in/prajwal-nakure-566672241"));
                    } catch (IOException | URISyntaxException ex) {
                        throw new RuntimeException(ex);
                    }
                }
         }
         });
         linkedin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
         linkedin.setForeground(Color.BLUE);
         linkedin.setBounds(30,275,100,20);
         add(linkedin);
         
         insta = new JLabel();
         insta.setText("<html><a href='https://www.instagram.com/itsmeprajwal19/'><font size='5'>Instagram</font></a></html>");
         insta.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseClicked(MouseEvent e) {
                // Open the link in a web browser
                if (Desktop.isDesktopSupported()) {
                    try {
                        Desktop.getDesktop().browse(new URI("https://www.instagram.com/itsmeprajwal19/"));
                    } catch (IOException | URISyntaxException ex) {
                    }
                }
         }
         });
         insta.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
         insta.setForeground(Color.BLUE);
         insta.setBounds(30,315,100,20);
         add(insta);
         
         mail = new JLabel();
         mail.setText("<html><a href='mailto:prajwal.dvl.2025.19@gmail.com'><font size='5'>Contact</font></a></html>");
         mail.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseClicked(MouseEvent e) {
                // Open the link in a web browser
                if (Desktop.isDesktopSupported()) {
                    try {
                        Desktop.getDesktop().browse(new URI("mailto:prajwal.dvl.2025.19@gmail.com"));
                    } catch (IOException | URISyntaxException ex) {
                    }
                }
         }
         });
         mail.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
         mail.setForeground(Color.BLUE);
         mail.setBounds(30,355,100,20);
         add(mail);
         
         ImageIcon i2 = Splash.loadAndScaleIcon("icons/linkedin.png", 50, 50);
         JLabel linkedIn = new JLabel(i2);
         linkedIn.setBounds(140,275,30,30);
         add(linkedIn);
         
         ImageIcon i3 = Splash.loadAndScaleIcon("icons/insta.png", 50, 50);
         JLabel instaGram = new JLabel(i3);
         instaGram.setBounds(140,310,30,30);
         add(instaGram);
         
         ImageIcon i4 = Splash.loadAndScaleIcon("icons/gmail.png", 50, 50);
         JLabel gMail = new JLabel(i4);
         gMail.setBounds(140,345,30,30);
         add(gMail);
         
         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         setVisible(true);
    }
    
    public static void main(String[] args){
        new About();
    }
}
