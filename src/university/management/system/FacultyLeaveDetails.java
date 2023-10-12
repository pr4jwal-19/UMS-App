package university.management.system;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;
import java.awt.event.*;
import java.awt.print.PrinterException;

public class FacultyLeaveDetails extends JFrame implements ActionListener {
    
    JLabel heading1,heading2;
    JTable tDetailsTable;
    JScrollPane jsp;
    JButton search,print,cancel;
    Choice cEmpID;
    
    FacultyLeaveDetails(){
        
        setLayout(null);
        
        // UNST logo
        ImageIcon i1 = Splash.loadAndScaleIcon("icons/appLogo.jpg", 200, 200);
        JLabel i2 = new JLabel(i1);
        i2.setBounds(20,20,200,200);
        add(i2);

        // heading
        heading1 = new JLabel("Search by");
        heading1.setBounds(250,60,150,20);
        heading1.setFont(new Font("Montserrat",Font.PLAIN,20));
        add(heading1);
        
        heading2 = new JLabel("Employee ID: ");
        heading2.setBounds(250,85,150,20);
        heading2.setFont(new Font("Montserrat",Font.PLAIN,20));
        add(heading2);
        
        cEmpID = new Choice();
        cEmpID.setBounds(410,60,120,20);
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
        
        //For displaying student details
        tDetailsTable = new JTable();
        
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("SELECT * FROM faculty_leave");
            tDetailsTable.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
        }
        
        jsp = new JScrollPane(tDetailsTable);
        jsp.setBounds(0, 250, 900, 600);
        add(jsp);
        
        search = new JButton("Search");
        search.setBounds(560,60,100,25);
        search.setBackground(new Color(0,153,204));
        search.setForeground(Color.WHITE);
        search.setFont(new Font("Montserrat",Font.BOLD,16));
        search.addActionListener(this);
        add(search);
        
        print = new JButton("Print");
        print.setBounds(680,60,100,25);
        print.setBackground(new Color(0,153,204));
        print.setForeground(Color.WHITE);
        print.setFont(new Font("Montserrat",Font.BOLD,16));
        print.addActionListener(this);
        add(print);
        
        cancel = new JButton("Cancel");
        cancel.setBounds(620,100,100,25);
        cancel.setBackground(new Color(0,153,204));
        cancel.setForeground(Color.WHITE);
        cancel.setFont(new Font("Montserrat",Font.BOLD,16));
        cancel.addActionListener(this);
        add(cancel);
        
        getContentPane().setBackground(Color.WHITE);
        
        setSize(900,700);
        setLocation(300,50);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true); 
        
    }
    
    @Override
    public void actionPerformed(ActionEvent ae){
        
        try {
            
            if (ae.getSource() == search){
                
                String query = "SELECT * FROM faculty_leave WHERE PRN = '"+cEmpID.getSelectedItem()+"' ";
                try {
                    Conn c = new Conn();
                    ResultSet rs = c.s.executeQuery(query);
                    tDetailsTable.setModel(DbUtils.resultSetToTableModel(rs));
                } catch (SQLException e){
                }
                
            } else if (ae.getSource() == print){
                try {
                    tDetailsTable.print();
                } catch (PrinterException e){
                }              
            } else {
                setVisible(false);
            }
            
        } catch (Exception e){
        }
        
    }
    
    public static void main(String[] args){
        
        new FacultyLeaveDetails();
        
    }
    
}

