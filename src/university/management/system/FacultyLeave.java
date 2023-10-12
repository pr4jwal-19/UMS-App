package university.management.system;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import com.toedter.calendar.JDateChooser;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.*;

public class FacultyLeave extends JFrame implements ActionListener {
    
    JLabel heading1,heading2,lblDate,lbltime;
    JButton submit,cancel;
    Choice cEmpID,cTime;
    JDateChooser dcdate;
    
    FacultyLeave(){
        
        setLayout(null);
        
        heading1 = new JLabel("Apply Leave (Faculty): ");
        heading1.setBounds(40,50,300,30);
        heading1.setFont(new Font("Montserrat",Font.PLAIN,22));
        add(heading1);
        
        // heading
        heading2 = new JLabel("Search by Employee ID: ");
        heading2.setBounds(60,100,250,20);
        heading2.setFont(new Font("Tahoma",Font.PLAIN,18));
        add(heading2);
        
        cEmpID = new Choice();
        cEmpID.setBounds(60,130,200,20);
        cEmpID.setBackground(Color.LIGHT_GRAY);
        add(cEmpID);
        
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("SELECT * FROM teacher");
            while (rs.next()){
                cEmpID.add(rs.getString("Emp_ID"));
            }
        } catch (SQLException e) {
        }
        
        lblDate = new JLabel("Date: ");
        lblDate.setBounds(60,180,200,20);
        lblDate.setFont(new Font("Tahoma",Font.PLAIN,18));
        add(lblDate);
        
        dcdate = new JDateChooser();
        dcdate.setBounds(60,210,150,30);
        add(dcdate);
        
        lbltime = new JLabel("Time Duration: ");
        lbltime.setBounds(60,260,200,20);
        lbltime.setFont(new Font("Tahoma",Font.PLAIN,18));
        add(lbltime);
        
        cTime = new Choice();
        cTime.setBounds(60,290,200,20);
        cTime.setBackground(Color.LIGHT_GRAY);
        cTime.add("Half Day");
        cTime.add("Full Day");
        add(cTime);
        
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
        submit.setBounds(60,350,100,30);
        submit.setForeground(Color.WHITE);
        submit.setFont(new Font("Tahoma",Font.PLAIN,16));
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
        cancel.setBounds(200,350,100,30);
        cancel.setForeground(Color.WHITE);
        cancel.setFont(new Font("Tahoma",Font.PLAIN,16));
        cancel.addActionListener(this);
        add(cancel);

        
        setSize(500,550);
        setLocation(550,100);
        getContentPane().setBackground(Color.WHITE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        
    }
    
    @Override
    public void actionPerformed(ActionEvent ae){
        
        try {
            if (ae.getSource() == submit ){
                
                String empNo = cEmpID.getSelectedItem();
                String date = ( (JTextField) dcdate.getDateEditor().getUiComponent()).getText();
                String duration = cTime.getSelectedItem();
                
                String query = "INSERT INTO faculty_leave VALUES ('"+empNo+"','"+date+"','"+duration+"' )";
                
                try {
                    
                    Conn c = new Conn();
                    c.s.executeUpdate(query);
                    JOptionPane.showMessageDialog(null, "Leave Confirmed");
                    setVisible(false);
                    
                    
                } catch (HeadlessException | SQLException e){
                }
            } else {
                setVisible(false);
            }
        } catch(Exception e){
        }
        
    }
    
    public static void main(String[] args){
        new FacultyLeave();
    }
    
}
