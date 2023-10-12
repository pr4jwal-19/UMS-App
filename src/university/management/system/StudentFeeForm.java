package university.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ItemEvent;
import java.sql.*;
import java.sql.SQLException;

public class StudentFeeForm extends JFrame implements ActionListener {
    
    JLabel search,lblname,tfname,lblfname,tffname,lblCourse,lblBranch,semester,lbltotal,tftotal,type;
    Choice cPrn;
    JComboBox cbcourse,cbbranch,sem,category;
    JButton update,pay,back;
    
    StudentFeeForm(){
        
        setLayout(null);
        
        setSize(900,500);
        setLocation(300,100);
        getContentPane().setBackground(Color.WHITE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        ImageIcon i1 = Splash.loadAndScaleIcon("icons/fee.jpg", 450, 300);
        JLabel image = new JLabel(i1);
        image.setBounds(400,50,450,300);
        add(image);
        
        // heading
        search = new JLabel("Select by PRN: ");
        search.setBounds(40,60,150,20);
        search.setFont(new Font("Karla",Font.PLAIN,20));
        add(search);
        
        cPrn = new Choice();
        cPrn.setBounds(200,60,150,22);
        cPrn.setBackground(Color.LIGHT_GRAY);
        cPrn.addItemListener((ItemEvent ie) -> {
            try {
                Conn c = new Conn();
                String query = " SELECT * FROM student WHERE PRN ='"+cPrn.getSelectedItem()+"' ";
                ResultSet rs = c.s.executeQuery(query);
                while (rs.next()){
                    tfname.setText(rs.getString("Name"));
                    tffname.setText(rs.getString("FName"));
                     
                }
            } catch (SQLException e){
            }
        });
        add(cPrn);
        
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("SELECT * FROM student");
            while (rs.next()){
                cPrn.add(rs.getString("PRN"));
            }
        } catch (SQLException e) {
        }
        
        // First input
        lblname = new JLabel("Name: ");
        lblname.setBounds(50,100,100,20);
        lblname.setFont(new Font("Lato",Font.PLAIN,18));
        add(lblname);
        
        tfname = new JLabel();
        tfname.setBounds(200,100,200,20);
        tfname.setFont(new Font("Lato",Font.PLAIN,18));
        add(tfname);
        
        // Second input
        lblfname = new JLabel("Father's Name: ");
        lblfname.setBounds(50,140,150,20);
        lblfname.setFont(new Font("Lato",Font.PLAIN,18));
        add(lblfname);
        
        tffname = new JLabel();
        tffname.setBounds(200,140,200,20);
        tffname.setFont(new Font("Lato",Font.PLAIN,18));
        add(tffname);
        
        try {
              Conn c = new Conn();
              String query = " SELECT * FROM student WHERE PRN ='"+cPrn.getSelectedItem()+"' ";
              ResultSet rs = c.s.executeQuery(query);
                 while (rs.next()){
                     tfname.setText(rs.getString("Name"));
                     tffname.setText(rs.getString("FName"));             
                 }
                 } catch (SQLException e){ 
        }
        
        //Course info
        lblCourse = new JLabel("Course: ");
        lblCourse.setBounds(50,180,150,20);
        lblCourse.setFont(new Font("Lato",Font.PLAIN,18));
        add(lblCourse);
        
        String[] course = {"B.TECH","B.E","BBA","MBA"};
        cbcourse = new JComboBox(course);
        cbcourse.setBounds(200,180,150,20);
        cbcourse.setFont(new Font("Lato",Font.PLAIN,16));
        cbcourse.setBackground(Color.WHITE);
        add(cbcourse);
        
        //Branch
        lblBranch = new JLabel("Branch: ");
        lblBranch.setBounds(50,220,150,20);
        lblBranch.setFont(new Font("Lato",Font.PLAIN,18));
        add(lblBranch);
        
        String[] branch = {"CSE","IT","E&TC","Mechanical","Robotics","AI&ML","Finance","Marketing","SC&L"};
        cbbranch = new JComboBox(branch);
        cbbranch.setBounds(200,220,150,20);
        cbbranch.setFont(new Font("Lato",Font.PLAIN,16));
        cbbranch.setBackground(Color.WHITE);
        add(cbbranch);
        
        semester = new JLabel("Select Semester: ");
        semester.setBounds(50,260,150,20);
        semester.setFont(new Font("Lato",Font.PLAIN,18));
        add(semester);
        
        String[] ssem = {"SEM1","SEM2","SEM3","SEM4","SEM5","SEM6","SEM7","SEM8"}; 
        sem = new JComboBox(ssem);
        sem.setBounds(200,260,150,20);
        sem.setFont(new Font("Lato",Font.PLAIN,16));
        sem.setBackground(Color.WHITE);
        add(sem);
        
        type = new JLabel("Category: ");
        type.setBounds(50,300,150,20);
        type.setFont(new Font("Lato",Font.PLAIN,18));
        add(type);
        
        String[] cat = {"SC/ST","VJNT","DEFENCE","OBC","OPEN"}; 
        category = new JComboBox(cat);
        category.setBounds(200,300,150,20);
        category.setFont(new Font("Lato",Font.PLAIN,16));
        category.setBackground(Color.WHITE);
        add(category);
        
         // First input
        lbltotal = new JLabel("Total Payable: ");
        lbltotal.setBounds(50,340,200,20);
        lbltotal.setFont(new Font("Lato",Font.PLAIN,18));
        add(lbltotal);
        
        tftotal = new JLabel();
        tftotal.setBounds(260,340,150,20);
        tftotal.setFont(new Font("Lato",Font.PLAIN,18));
        add(tftotal);
        
        // Submit Button
        update = new JButton("Update");
        update.setBackground(Color.BLACK);
        update.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e){
                update.setBackground(Color.DARK_GRAY);
            }
            @Override
            public void mouseExited(MouseEvent e){
                update.setBackground(Color.BLUE);
            }
        });
        update.setBounds(30,400,100,25);
        update.setForeground(Color.WHITE);
        update.addActionListener(this);
        add(update);
        
        // Cancel Button
        pay = new JButton("Pay");
        pay.setBackground(Color.BLACK);
        pay.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e){
                pay.setBackground(Color.DARK_GRAY);
            }
            @Override
            public void mouseExited(MouseEvent e){
                pay.setBackground(Color.BLUE);
            }
        });
        pay.setBounds(150,400,100,25);
        pay.setForeground(Color.WHITE);
        pay.addActionListener(this);
        add(pay);

        // Cancel Button
        back = new JButton("Back");
        back.setBackground(Color.BLACK);
        back.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e){
                back.setBackground(Color.DARK_GRAY);
            }
            @Override
            public void mouseExited(MouseEvent e){
                back.setBackground(Color.BLUE);
            }
        });
        back.setBounds(290,400,100,25);
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        add(back);
        
        setVisible(true);
    }
    
    
    @Override
    public void actionPerformed(ActionEvent ae){
        
        if(ae.getSource() == update){
            String course = (String) cbcourse.getSelectedItem();
            String semesterF = (String) sem.getSelectedItem();
           // String cat =(String) category.getSelectedItem();
            try{
                Conn c = new Conn();
                ResultSet rs = c.s.executeQuery("SELECT * FROM fee WHERE Course = '"+course+"' ");
                while (rs.next()){
                    tftotal.setText(rs.getString(semesterF));
                }
            } catch (SQLException e){
            }
        } else if (ae.getSource() == pay){
            
        } else{
            setVisible(false);
        }
        
    }
    public static void main(String[] args){
        new StudentFeeForm();
    }
    
}
