package university.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.sql.SQLException;
import java.awt.event.*;


public class EnterMarks extends JFrame implements ActionListener {
    
    JLabel heading,search,semester,eSub,eMarks;
    JComboBox sem;
    Choice cPrn;
    JTextField tfSub1,tfSub2,tfSub3,tfSub4,tfSub5,
            tfMarks1,tfMarks2,tfMarks3,tfMarks4,tfMarks5;
    JButton submit,cancel;
    
    EnterMarks(){
        
        setLayout(null);
        
        ImageIcon i1 = Splash.loadAndScaleIcon("icons/exam.jpg", 400, 300);
        JLabel image = new JLabel(i1); 
        image.setBounds(500,40,400,300);
        add(image);
        
         //Form Heading
        heading= new JLabel("Enter Marks of Student: ");
        heading.setBounds(50,10,500,50);
        heading.setFont(new Font("Quicksand",Font.PLAIN,25));
        add(heading);
        
        // heading
        search = new JLabel("Select by PRN: ");
        search.setBounds(50,80,150,20);
        search.setFont(new Font("Karla",Font.PLAIN,20));
        add(search);
        
        cPrn = new Choice();
        cPrn.setBounds(250,80,120,25);
        cPrn.setBackground(Color.LIGHT_GRAY);
        add(cPrn);
        
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("SELECT * FROM student");
            while (rs.next()){
                cPrn.add(rs.getString("PRN"));
            }
        } catch (SQLException e) {
        }
        
        semester = new JLabel("Select Semester: ");
        semester.setBounds(50,120,200,20);
        semester.setFont(new Font("Karla",Font.PLAIN,20));
        add(semester);
        
        String[] ssem = {"I-Sem","II-Sem","III-Sem","IV-Sem","V-Sem","VI-Sem","VII-Sem","VIII-Sem"}; 
        sem = new JComboBox(ssem);
        sem.setBounds(250,120,150,25);
        sem.setBackground(Color.LIGHT_GRAY);
        add(sem);
        
        eSub = new JLabel("Enter Subject: ");
        eSub.setBounds(50,180,200,40);
        eSub.setFont(new Font("Karla",Font.PLAIN,20));
        add(eSub);
        
        eMarks = new JLabel("Enter Marks: ");
        eMarks.setBounds(270,180,200,40);
        eMarks.setFont(new Font("Karla",Font.PLAIN,20));
        add(eMarks);
        
        tfSub1 = new JTextField();
        tfSub1.setBounds(50,230,170,25);
        tfSub1.setFont(new Font("Lato",Font.PLAIN,18));
        add(tfSub1);
        
        tfSub2 = new JTextField();
        tfSub2.setBounds(50,260,170,25);
        tfSub2.setFont(new Font("Lato",Font.PLAIN,18));
        add(tfSub2);
        
        tfSub3 = new JTextField();
        tfSub3.setBounds(50,290,170,25);
        tfSub3.setFont(new Font("Lato",Font.PLAIN,18));
        add(tfSub3);
        
        tfSub4 = new JTextField();
        tfSub4.setBounds(50,320,170,25);
        tfSub4.setFont(new Font("Lato",Font.PLAIN,18));
        add(tfSub4);
        
        tfSub5 = new JTextField();
        tfSub5.setBounds(50,360,170,25);
        tfSub5.setFont(new Font("Lato",Font.PLAIN,18));
        add(tfSub5);
        
        tfMarks1 = new JTextField();
        tfMarks1.setBounds(260,230,170,25);
        tfMarks1.setFont(new Font("Lato",Font.PLAIN,18));
        add(tfMarks1);
        
        tfMarks2 = new JTextField();
        tfMarks2.setBounds(260,260,170,25);
        tfMarks2.setFont(new Font("Lato",Font.PLAIN,18));
        add(tfMarks2);
        
        tfMarks3 = new JTextField();
        tfMarks3.setBounds(260,290,170,25);
        tfMarks3.setFont(new Font("Lato",Font.PLAIN,18));
        add(tfMarks3);
        
        tfMarks4 = new JTextField();
        tfMarks4.setBounds(260,320,170,25);
        tfMarks4.setFont(new Font("Lato",Font.PLAIN,18));
        add(tfMarks4);
        
        tfMarks5 = new JTextField();
        tfMarks5.setBounds(260,360,170,25);
        tfMarks5.setFont(new Font("Lato",Font.PLAIN,18));
        add(tfMarks5);
        
         // Submit Button
        submit = new JButton("Submit");
        submit.setBackground(Color.BLACK);
        submit.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e){
                submit.setBackground(Color.DARK_GRAY);
            }
            @Override
            public void mouseExited(MouseEvent e){
                submit.setBackground(Color.BLUE);
            }
        });
        submit.setBounds(500,360,120,30);
        submit.setForeground(Color.WHITE);
        submit.addActionListener(this);
        add(submit);
        
        // Cancel Button
        cancel = new JButton("Cancel");
        cancel.setBackground(Color.BLACK);
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
        cancel.setBounds(640,360,120,30);
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this);
        add(cancel);

        setSize(1000,500);
        setLocation(300,150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.WHITE);
        setVisible(true);
        
    }
    
    @Override
    public void actionPerformed(ActionEvent ae){
        try {
            if (ae.getSource() == submit){
                try {
                    Conn c = new Conn();
                    
                    String query1 = "INSERT INTO subject VALUES ('"+cPrn.getSelectedItem()+"','"+sem.getSelectedItem()+"','"+tfSub1.getText()+"','"+tfSub2.getText()+"','"+tfSub3.getText()+"','"+tfSub4.getText()+"','"+tfSub5.getText()+"')";
                    String query2 = "INSERT INTO marks VALUES ('"+cPrn.getSelectedItem()+"','"+sem.getSelectedItem()+"','"+tfMarks1.getText()+"','"+tfMarks2.getText()+"','"+tfMarks3.getText()+"','"+tfMarks4.getText()+"','"+tfMarks5.getText()+"')";
                    
                    c.s.executeUpdate(query1);
                    c.s.executeUpdate(query2);
                    
                    JOptionPane.showMessageDialog(null,"Marks inserted Successfully");
                    
                    setVisible(false);
                    
                } catch (HeadlessException | SQLException e){
                }
            } else {
                setVisible(false);
            }
        } catch (Exception e){
        }
    }
    
    public static void main(String[] args){
        new EnterMarks();
    }
    
}
