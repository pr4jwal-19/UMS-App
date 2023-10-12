package university.management.system;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;
import java.awt.event.*;
import java.awt.print.PrinterException;

public class TeacherDetails extends JFrame implements ActionListener {
    
    JLabel heading,heading1;
    JTable tDetailsTable;
    JScrollPane jsp;
    JButton search,print,update,add,cancel;
    Choice cEmpID;
    
    TeacherDetails(){
        
        setLayout(null);
        
        // UNST logo
        ImageIcon i1 = Splash.loadAndScaleIcon("icons/appLogo.jpg", 200, 200);
        JLabel i2 = new JLabel(i1);
        i2.setBounds(20,20,200,200);
        add(i2);

        // heading
        heading = new JLabel("Search by");
        heading.setBounds(250,60,150,20);
        heading.setFont(new Font("Montserrat",Font.PLAIN,20));
        add(heading);
        
        heading1 = new JLabel("Employee ID: ");
        heading1.setBounds(250,85,150,20);
        heading1.setFont(new Font("Montserrat",Font.PLAIN,20));
        add(heading1);
        
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
            ResultSet rs = c.s.executeQuery("SELECT * FROM teacher");
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
                
                String query = "SELECT * FROM teacher WHERE Emp_ID = '"+cEmpID.getSelectedItem()+"' ";
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
            } else if (ae.getSource() == update){
                setVisible(false);
                new UpdateTeacher();
            } else if (ae.getSource() == add){
                setVisible(false);
                new AddInstructor();
            } else {
                setVisible(false);
            }
            
        } catch (Exception e){
        }
        
    }
    
    public static void main(String[] args){
        
        new TeacherDetails();
        
    }
    
}
