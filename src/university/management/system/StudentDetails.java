package university.management.system;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;
import java.awt.event.*;
import java.awt.print.PrinterException;

public class StudentDetails extends JFrame implements ActionListener {
    
    JLabel heading;
    JTable sDetailsTable;
    JScrollPane jsp;
    JButton search,print,update,add,cancel;
    Choice cPrn;
    
    StudentDetails(){
        
        setLayout(null);
        
        // UNST logo
        ImageIcon i1 = Splash.loadAndScaleIcon("icons/appLogo.jpg", 200, 200);
        JLabel i2 = new JLabel(i1);
        i2.setBounds(20,20,200,200);
        add(i2);

        // heading
        heading = new JLabel("Search by PRN: ");
        heading.setBounds(250,60,150,20);
        heading.setFont(new Font("Montserrat",Font.PLAIN,20));
        add(heading);
        
        cPrn = new Choice();
        cPrn.setBounds(410,60,120,20);
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
        
        //For displaying student details
        sDetailsTable = new JTable();
        
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("SELECT * FROM student");
            sDetailsTable.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
        }
        
        jsp = new JScrollPane(sDetailsTable);
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
        
        update = new JButton("Update");
        update.setBounds(560,95,100,25);
        update.setBackground(new Color(0,153,204));
        update.setForeground(Color.WHITE);
        update.setFont(new Font("Montserrat",Font.BOLD,16));
        update.addActionListener(this);
        add(update);
        
        add = new JButton("Add");
        add.setBounds(680,95,100,25);
        add.setBackground(new Color(0,153,204));
        add.setForeground(Color.WHITE);
        add.setFont(new Font("Montserrat",Font.BOLD,16));
        add.addActionListener(this);
        add(add);
        
        cancel = new JButton("Cancel");
        cancel.setBounds(620,130,100,25);
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
                
                String query = "SELECT * FROM student WHERE PRN = '"+cPrn.getSelectedItem()+"' ";
                try {
                    Conn c = new Conn();
                    ResultSet rs = c.s.executeQuery(query);
                    sDetailsTable.setModel(DbUtils.resultSetToTableModel(rs));
                } catch (SQLException e){
                }
                
            } else if (ae.getSource() == print){
                try {
                    sDetailsTable.print();
                } catch (PrinterException e){
                }              
            } else if (ae.getSource() == update){
                setVisible(false);
                new UpdateStudents();
            } else if (ae.getSource() == add){
                setVisible(false);
                new AddStudent();
            } else {
                setVisible(false);
            }
            
        } catch (Exception e){
        }
        
    }
    
    public static void main(String[] args){
        
        new StudentDetails();
        
    }
    
}
