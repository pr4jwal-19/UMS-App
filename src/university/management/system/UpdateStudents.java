package university.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.text.*;
import javax.swing.event.DocumentListener;
import javax.swing.event.DocumentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UpdateStudents extends JFrame implements ActionListener {
    
    JLabel heading,search,lblname,tfname,
            lblfname,tffname,lblroll,labelRoll,dob,
            lbldob,lblAddress,lblPhone,lblEAddress,
            lblAddress1,lblXMarks,tfXMarks,lblXIIMarks,
            lblXIIMarks1,tfXIIMarks,lblAdhaar,tfAdhaar,lblCourse,
            lblBranch;
    JTextField tfCourse,tfBranch,tfAddress,tfEAddress;
    static JTextField tfPhone;
    JButton update, cancel;
    Choice cPrn;

    UpdateStudents(){
        
        setLayout(null);
        
        //Form Heading
        heading= new JLabel("Update Student Details: ");
        heading.setBounds(50,15,500,50);
        heading.setFont(new Font("Quicksand",Font.PLAIN,35));
        add(heading);
        
        // heading
        search = new JLabel("Select by PRN: ");
        search.setBounds(50,100,150,20);
        search.setFont(new Font("Karla",Font.PLAIN,20));
        add(search);
        
        cPrn = new Choice();
        cPrn.setBounds(220,100,200,22);
        cPrn.setBackground(Color.LIGHT_GRAY);
        cPrn.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent ie){
                try {
                    Conn c = new Conn();
                    String query = " SELECT * FROM student WHERE PRN ='"+cPrn.getSelectedItem()+"' ";
                    ResultSet rs = c.s.executeQuery(query);
                    while (rs.next()){
                        tfname.setText(rs.getString("Name"));
                        tffname.setText(rs.getString("FName"));
                        labelRoll.setText(rs.getString("PRN"));
                        lbldob.setText(rs.getString("DOB"));
                        tfAddress.setText(rs.getString("ADDRESS"));
                        tfPhone.setText(rs.getString("PHONE_NO"));
                        tfEAddress.setText(rs.getString("Email"));
                        tfXMarks.setText(rs.getString("X_Marks"));
                        tfXIIMarks.setText(rs.getString("XII_Marks"));
                        tfAdhaar.setText(rs.getString("Adhaar_No"));
                        tfCourse.setText(rs.getString("Course"));
                        tfBranch.setText(rs.getString("Branch"));
                    }
                } catch (SQLException e){ 
                }
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
        lblname.setBounds(50,150,100,30);
        lblname.setFont(new Font("Lato",Font.PLAIN,20));
        add(lblname);
        
        tfname = new JLabel();
        tfname.setBounds(160,150,200,40);
        tfname.setFont(new Font("Lato",Font.PLAIN,18));
        add(tfname);
        
        // Second input
        lblfname = new JLabel("Father's Name: ");
        lblfname.setBounds(400,150,150,30);
        lblfname.setFont(new Font("Lato",Font.PLAIN,20));
        add(lblfname);
        
        tffname = new JLabel();
        tffname.setBounds(600,145,200,40);
        tffname.setFont(new Font("Lato",Font.PLAIN,18));
        add(tffname);
        
        // Third input
        lblroll = new JLabel("PRN: ");
        lblroll.setBounds(50,200,150,30);
        lblroll.setFont(new Font("Lato",Font.PLAIN,20));
        add(lblroll);
        
        // Because we want non editable field -> so JLabel and not JTextfield
        labelRoll = new JLabel();
        labelRoll.setBounds(160, 200, 150, 40);
        labelRoll.setFont(new Font("Lato",Font.PLAIN,18));
        add(labelRoll);
        
        // DOB
        dob = new JLabel("Date of Birth: ");
        dob.setBounds(400,200,150,30);
        dob.setFont(new Font("Lato",Font.PLAIN,20));
        add(dob); 
        
        lbldob = new JLabel();
        lbldob.setBounds(600,200,150,30);
        lbldob.setFont(new Font("Lato",Font.PLAIN,20));
        add(lbldob);
        
        //Address
        lblAddress = new JLabel("Address: ");
        lblAddress.setBounds(50,250,150,30);
        lblAddress.setFont(new Font("Lato",Font.PLAIN,20));
        add(lblAddress);
        
        tfAddress = new JTextField();
        tfAddress.setBounds(160,250,200,40);
        tfAddress.setFont(new Font("Lato",Font.PLAIN,18));
        add(tfAddress);
        
        //Phone number
        lblPhone = new JLabel("Phone No: ");
        lblPhone.setBounds(400,250,150,30);
        lblPhone.setFont(new Font("Lato",Font.PLAIN,20));
        add(lblPhone);
        
        tfPhone = new JTextField();
        ((AbstractDocument) tfPhone.getDocument()).setDocumentFilter(new DocumentFilter(){
            @Override
            public void insertString(DocumentFilter.FilterBypass fb, int offset, String text, AttributeSet attr) throws BadLocationException {
                if (isDigit(text)) {
                    super.insertString(fb, offset, text, attr);
                }
                else{
                    JOptionPane.showMessageDialog(null,"Please enter digits (0-9) only.","Invalid Input",JOptionPane.WARNING_MESSAGE);
                }
            }

            @Override
            public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                if (isDigit(text)) {
                    super.replace(fb, offset, length, text, attrs);
                }
                else{
                     JOptionPane.showMessageDialog(null,"Please enter digits (0-9) only.","Invalid Input",JOptionPane.WARNING_MESSAGE);
                }
            }

            private boolean isDigit(String text) {
                for (int i = 0; i < text.length(); i++) {
                    if (!Character.isDigit(text.charAt(i))) {
                        return false;
                    }
                }
                return true;
            }
        });
        tfPhone.getDocument().addDocumentListener(new DocumentListener(){
            @Override
           public void insertUpdate(DocumentEvent e){
               validatePhone(tfPhone.getText());
           } 
           @Override
           public void removeUpdate(DocumentEvent e){
               validatePhone(tfPhone.getText());
           } 
           @Override
           public void changedUpdate(DocumentEvent e){
               validatePhone(tfPhone.getText());
           } 
        });
        tfPhone.setBounds(600,250,200,40);
        tfPhone.setFont(new Font("Lato",Font.PLAIN,18));
        add(tfPhone);
        
        //Email iD
        lblEAddress = new JLabel("Email ");
        lblEAddress.setBounds(50,300,150,30);
        lblEAddress.setFont(new Font("Lato",Font.PLAIN,20));
        add(lblEAddress);
        
        lblAddress1 = new JLabel("Address: ");
        lblAddress1.setBounds(50,330,150,30);
        lblAddress1.setFont(new Font("Lato",Font.PLAIN,20));
        add(lblAddress1);
        
        tfEAddress = new JTextField();
        tfEAddress.setBounds(160,320,200,40);
        tfEAddress.setFont(new Font("Lato",Font.PLAIN,18));
        add(tfEAddress);
        
        // X (%)
        lblXMarks = new JLabel("Class X(%): ");
        lblXMarks.setBounds(400,330,150,30);
        lblXMarks.setFont(new Font("Lato",Font.PLAIN,20));
        add(lblXMarks);
        
        tfXMarks = new JLabel();
        tfXMarks.setBounds(600,320,200,40);
        tfXMarks.setFont(new Font("Lato",Font.PLAIN,18));
        add(tfXMarks);
        
        //XII (%)
        lblXIIMarks = new JLabel("Class ");
        lblXIIMarks.setBounds(50,380,150,30);
        lblXIIMarks.setFont(new Font("Lato",Font.PLAIN,20));
        add(lblXIIMarks);
        
        lblXIIMarks1 = new JLabel("XII(%): ");
        lblXIIMarks1.setBounds(50,400,150,30);
        lblXIIMarks1.setFont(new Font("Lato",Font.PLAIN,20));
        add(lblXIIMarks1);
        
        tfXIIMarks = new JLabel();
        tfXIIMarks.setBounds(160,390,200,40);
        tfXIIMarks.setFont(new Font("Lato",Font.PLAIN,18));
        add(tfXIIMarks);
        
        //Adhaar No
        lblAdhaar = new JLabel("Adhaar No: ");
        lblAdhaar.setBounds(400,390,150,30);
        lblAdhaar.setFont(new Font("Lato",Font.PLAIN,20));
        add(lblAdhaar);
        
        tfAdhaar = new JLabel();
        tfAdhaar.setBounds(600,390,200,40);
        tfAdhaar.setFont(new Font("Lato",Font.PLAIN,18));
        add(tfAdhaar);
        
        //Course info
        lblCourse = new JLabel("Course: ");
        lblCourse.setBounds(50,460,150,30);
        lblCourse.setFont(new Font("Lato",Font.PLAIN,20));
        add(lblCourse);

        tfCourse = new JTextField();
        tfCourse.setBounds(160,460,200,40);
        tfCourse.setFont(new Font("Lato",Font.PLAIN,18));
        add(tfCourse);

        //Branch
        lblBranch = new JLabel("Branch: ");
        lblBranch.setBounds(400,460,150,30);
        lblBranch.setFont(new Font("Lato",Font.PLAIN,20));
        add(lblBranch);
        
        tfBranch = new JTextField();
        tfBranch.setBounds(600,460,200,40);
        tfBranch.setFont(new Font("Lato",Font.PLAIN,18));
        add(tfBranch);
        
        try {
            Conn c = new Conn();
            String query = " SELECT * FROM student WHERE PRN ='"+cPrn.getSelectedItem()+"' ";
            ResultSet rs = c.s.executeQuery(query);
            while (rs.next()){
                tfname.setText(rs.getString("Name"));
                tffname.setText(rs.getString("FName"));
                labelRoll.setText(rs.getString("PRN"));
                lbldob.setText(rs.getString("DOB"));
                tfAddress.setText(rs.getString("ADDRESS"));
                tfPhone.setText(rs.getString("PHONE_NO"));
                tfEAddress.setText(rs.getString("Email"));
                tfXMarks.setText(rs.getString("X_Marks"));
                tfXIIMarks.setText(rs.getString("XII_Marks"));
                tfAdhaar.setText(rs.getString("Adhaar_No"));
                tfCourse.setText(rs.getString("Course"));
                tfBranch.setText(rs.getString("Branch"));
            }
        } catch (Exception e){
            e.printStackTrace();
        }
 
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
        update.setBounds(300,550,120,30);
        update.setForeground(Color.WHITE);
        update.addActionListener(this);
        add(update);
        
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
        cancel.setBounds(470,550,120,30);
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this);
        add(cancel);

        
        
        setSize(900,650);
        setLocation(350,50);
        getContentPane().setBackground(Color.WHITE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        
    }
    
    private static void validatePhone(String number){
        boolean isValid = false;
        if (number.length() == 10){
            isValid = true;
        }
        if (isValid){
            tfPhone.setBackground(Color.WHITE);
        }
        else{
            tfPhone.setBackground(Color.YELLOW);
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent ae){
        try {
            
            if (ae.getSource() == update){ 
                String roll = labelRoll.getText();
                String address = tfAddress.getText();
                String phone = tfPhone.getText();
                String email = tfEAddress.getText();
                String course = tfCourse.getText();
                String branch = tfBranch.getText();
                try {
                    
                    String query = "UPDATE student SET ADDRESS ='"+address+"', PHONE_NO = '"+phone+"', Email = '"+email+"', Course = '"+course+"', Branch = '"+branch+"' WHERE PRN = '"+roll+"'";
                    
                    Conn c = new Conn();
                    //DML command
                    c.s.executeUpdate(query);
                    
                    JOptionPane.showMessageDialog(null,"Student Details updated Successfully");
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
        
        new UpdateStudents();
        
    }
    
}

