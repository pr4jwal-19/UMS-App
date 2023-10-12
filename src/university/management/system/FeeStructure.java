package university.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import net.proteanit.sql.DbUtils;

public class FeeStructure extends JFrame implements ActionListener{
    
    JLabel heading,select;
    JTable table;
    JScrollPane jsp;
    JComboBox category;
    JButton check,cancel;
    
    FeeStructure(){
        
        setLayout(null);
        
        setSize(1000,700);
        setLocation(250,50);
        getContentPane().setBackground(Color.WHITE);
        
        //start
        heading = new JLabel("FEE STRUCTURE");
        heading.setBounds(80,15, 250,50);
        heading.setFont(new Font("Avenir",Font.PLAIN,25));
        add(heading);
        
        select = new JLabel("Select your Category: ");
        select.setBounds(400,15, 250,50);
        select.setFont(new Font("Avenir",Font.PLAIN,25));
        add(select);
        
        String[] cat = {"Open","OBC","VJNT","SC/ST","Defence"};
        category = new JComboBox(cat);
        category.setBounds(650,28,150,25);
        category.setBackground(Color.LIGHT_GRAY);
        add(category);
        
        check = new JButton("Check");
        check.setBounds(820,28,120,25);
        check.setBackground(Color.BLACK);
        check.setForeground(Color.WHITE);
        check.setFont(new Font("Quicksand",Font.PLAIN,18));
        check.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e){
                check.setBackground(Color.DARK_GRAY);
            }
            @Override
            public void mouseExited(MouseEvent e){
                check.setBackground(Color.BLUE);
            }
        });
        check.addActionListener(this);
        add(check);
        
        cancel = new JButton("Cancel");
        cancel.setBounds(820,60,120,25);
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
        
        //table
        table = new JTable();
        
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("SELECT * FROM fee");
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e){
        }
        
        jsp = new JScrollPane(table);
        jsp.setBounds(0,100,1000,700);
        add(jsp);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae){
        if (ae.getSource() == check){
            try {
                Conn c = new Conn();
                ResultSet rs = c.s.executeQuery("SELECT * FROM fee WHERE Category = '"+category.getSelectedItem()+"'");
                table.setModel(DbUtils.resultSetToTableModel(rs));
                
            } catch (SQLException e){
            }
        } else {
            setVisible(false);
        }
    }
    
    public static void main(String[] args){
        new FeeStructure();
    }
    
}
