package university.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import net.proteanit.sql.DbUtils;

public class ExaminationDetails extends JFrame implements ActionListener {
    
    JLabel heading;
    JTextField search;
    JButton cResult, cancel;
    JTable table;
    JScrollPane jsp;
    
    ExaminationDetails(){

        setLayout(null);
        
        setSize(1000,475);
        setLocation(300,100);
        getContentPane().setBackground(Color.WHITE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

         //Form Heading
        heading= new JLabel("Check Result :  ");
        heading.setBounds(80,15, 400,50);
        heading.setFont(new Font("Quicksand",Font.PLAIN,25));
        add(heading);
        
        search = new JTextField("Enter PRN here...");
        search.setForeground(Color.LIGHT_GRAY);
        search.setBounds(80,90,200,30);
        search.setFont(new Font("Arimo",Font.PLAIN,20));
        // Add a FocusListener to clear the default message when the field is focused
        search.addFocusListener(new FocusListener() {
             @Override
             public void focusGained(FocusEvent e) {
                  if (search.getText().equals("Enter PRN here...")) {
                      search.setText("");
                      search.setForeground(Color.BLACK); // Change text color to black
                  }
             }

             @Override
             public void focusLost(FocusEvent e) {
                  if (search.getText().isEmpty()) {
                      search.setText("Enter PRN here...");
                      search.setForeground(Color.GRAY); // Change text color to gray
                  }
             }
        });
        add(search);
        
        cResult = new JButton("Result");
        cResult.setBounds(300,90,120,30);
        cResult.setBackground(Color.BLACK);
        cResult.setForeground(Color.WHITE);
        cResult.setFont(new Font("Quicksand",Font.PLAIN,18));
        cResult.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e){
                cResult.setBackground(Color.DARK_GRAY);
            }
            @Override
            public void mouseExited(MouseEvent e){
                cResult.setBackground(Color.BLUE);
            }
        });
        cResult.addActionListener(this);
        add(cResult);
        
        cancel = new JButton("Cancel");
        cancel.setBounds(440,90,120,30);
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
        
        //Adding a table
        table = new JTable();
        table.setFont(new Font("Muli",Font.PLAIN,18));
        
        jsp = new JScrollPane(table);
        jsp.setBounds(0,135,1000,300);
        add(jsp);
        
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("SELECT * FROM student");
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e){
        }
        
        table.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent me){
                int row = table.getSelectedRow();
                search.setText(table.getModel().getValueAt(row, 2).toString());
            }
        });
        
        setVisible(true);
        
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        
        if(ae.getSource() == cResult){
            setVisible(false);
            new Marks(search.getText());
        } else{
            setVisible(false);
        }
    }
    
    public static void main(String[] args){
        new ExaminationDetails();
    }
    
}
