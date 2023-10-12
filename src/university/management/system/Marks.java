package university.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.Year;
import java.sql.*;
import java.awt.event.*;

public class Marks extends JFrame implements ActionListener {
    
    String prn;
    JLabel heading,subheading,lblprn,lblsem,sub1,sub2,sub3,sub4,sub5;
    JButton cancel;
    
    public Marks(String prn) {
        
        this.prn = prn;
        
        setLayout(null);
        
         //Form Heading
        heading= new JLabel("Prizzwal University ");
        heading.setBounds(40,35, 250,30);
        heading.setFont(new Font("Quicksand",Font.PLAIN,25));
        add(heading);
        
        ImageIcon i2 = Splash.loadAndScaleIcon("icons/appLogo.jpg", 140, 120);
        JLabel u_image = new JLabel(i2);
        u_image.setBounds(300,15,140,120);
        add(u_image);
        
        int cYear = Year.now().getValue();
        
        subheading= new JLabel("| Result of Examination "+ cYear+" |");
        subheading.setBounds(40,80, 500,20);
        subheading.setFont(new Font("Segoe UI",Font.PLAIN,18));
        add(subheading);
        
        lblprn = new JLabel("PRN: "+prn);
        lblprn.setBounds(50, 160, 500, 20);
        lblprn.setFont(new Font("Source Sans Pro",Font.PLAIN,18));
        add(lblprn);
        
        lblsem = new JLabel();
        lblsem.setBounds(50, 200, 500, 20);
        lblsem.setFont(new Font("Source Sans Pro",Font.PLAIN,18));
        add(lblsem);
        
        sub1 = new JLabel();
        sub1.setBounds(50, 240, 500, 20);
        sub1.setFont(new Font("Nunito",Font.PLAIN,18));
        add(sub1);
        
        sub2 = new JLabel();
        sub2.setBounds(50, 270, 500, 20);
        sub2.setFont(new Font("Nunito",Font.PLAIN,18));
        add(sub2);
        
        sub3 = new JLabel();
        sub3.setBounds(50, 300, 500, 20);
        sub3.setFont(new Font("Nunito",Font.PLAIN,18));
        add(sub3);
        
        sub4 = new JLabel();
        sub4.setBounds(50, 330, 500, 20);
        sub4.setFont(new Font("Nunito",Font.PLAIN,18));
        add(sub4);
        
        sub5 = new JLabel();
        sub5.setBounds(50, 360, 500, 20);
        sub5.setFont(new Font("Nunito",Font.PLAIN,18));
        add(sub5);
        
        try {
            Conn c = new Conn();
            ResultSet rs1 = c.s.executeQuery("SELECT * FROM subject WHERE PRN = '"+prn+"'");

            while (rs1.next()){
                sub1.setText(rs1.getString("SUB1"));
                sub2.setText(rs1.getString("SUB2"));
                sub3.setText(rs1.getString("SUB3"));
                sub4.setText(rs1.getString("SUB4"));
                sub5.setText(rs1.getString("SUB5"));
            }
            ResultSet rs2 = c.s.executeQuery("SELECT * FROM marks WHERE PRN = '"+prn+"'");
            while (rs2.next()){
                sub1.setText(sub1.getText() + "--------------" +rs2.getString("MARKS1"));
                sub2.setText(sub2.getText() + "--------------" +rs2.getString("MARKS2"));
                sub3.setText(sub3.getText() + "--------------" +rs2.getString("MARKS3"));
                sub4.setText(sub4.getText() + "--------------" +rs2.getString("MARKS4"));
                sub5.setText(sub5.getText() + "--------------" +rs2.getString("MARKS5"));
                lblsem.setText("Semester "+ rs2.getString("SEM"));
            }

        } catch (SQLException e){
        }
        
        cancel = new JButton("Back");
        cancel.setBounds(160,440,120,30);
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.setFont(new Font("Quicksand",Font.PLAIN,18));
        cancel.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e){
                cancel.setBackground(Color.DARK_GRAY);
            }
            @Override
            public void mouseExited(MouseEvent e){
                cancel.setBackground(Color.BLUE);
            }
        });
        cancel.addActionListener(this);
        add(cancel);
        
        
        setSize(500,600);
        setLocation(500,100);
        getContentPane().setBackground(Color.WHITE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        
    }
    
    @Override
    public void actionPerformed(ActionEvent ae){
        setVisible(false);
    }
    
    public static void main(String[] args){
        new Marks("");
    }
    
}
