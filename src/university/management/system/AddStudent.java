package university.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.text.*;
import java.util.*;
import javax.swing.event.DocumentListener;
import javax.swing.event.DocumentEvent;
import com.toedter.calendar.JDateChooser;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AddStudent extends JFrame implements ActionListener {
    
    JLabel heading,lblname,lblfname,lblroll,labelRoll,dob,lblAddress,lblPhone,lblEAddress,lblAddress1,lblXMarks,lblXIIMarks,lblXIIMarks1,lblAdhaar,lblCourse,lblBranch;
    JTextField tfname,tffname,tfAddress,tfEAddress,tfXMarks,tfXIIMarks;
    static JTextField tfAdhaar,tfPhone;
    JComboBox cbcourse,cbbranch;
    JDateChooser dcdob;
    JButton submit, cancel;
    
    Random random= new Random();
    // Generates a random number between 70000000 to 79999999
    int randomNumber = 70000000 + random.nextInt(10000000);
    
    // Generate a random letter A-Z (ASCII values 65 to 90)
    // 25 + 65 = 65,66,...90 i.e A-Z
    char randomLetter = (char) (random.nextInt(26) + 'A');
    
    // Combine the number and letter
    String result = randomNumber + String.valueOf(randomLetter);

    AddStudent(){
        
        setLayout(null);
        
        //Form Heading
        heading= new JLabel("New Student Details: ");
        heading.setBounds(310,30,500,50);
        heading.setFont(new Font("Montserrat",Font.PLAIN,30));
        add(heading);
        
        // First input
        lblname = new JLabel("Name: ");
        lblname.setBounds(50,150,100,30);
        lblname.setFont(new Font("Lato",Font.PLAIN,20));
        add(lblname);
        
        tfname = new JTextField();
        tfname.setBounds(160,145,200,40);
        tfname.setFont(new Font("Lato",Font.PLAIN,18));
        add(tfname);
        
        // Second input
        lblfname = new JLabel("Father's Name: ");
        lblfname.setBounds(400,150,150,30);
        lblfname.setFont(new Font("Lato",Font.PLAIN,20));
        add(lblfname);
        
        tffname = new JTextField();
        tffname.setBounds(600,145,200,40);
        tffname.setFont(new Font("Lato",Font.PLAIN,18));
        add(tffname);
        
        // Third input
        lblroll = new JLabel("PRN: ");
        lblroll.setBounds(50,200,150,30);
        lblroll.setFont(new Font("Lato",Font.PLAIN,20));
        add(lblroll);
        
        // Because we want non editable field -> so JLabel and not JTextfield
        labelRoll = new JLabel(result);
        labelRoll.setBounds(160, 200, 150, 40);
        labelRoll.setFont(new Font("Lato",Font.PLAIN,18));
        add(labelRoll);
        
        // DOB
        dob = new JLabel("Date of Birth: ");
        dob.setBounds(400,200,150,30);
        dob.setFont(new Font("Lato",Font.PLAIN,20));
        add(dob);
        
        dcdob = new JDateChooser();
        dcdob.setBounds(600,200,150,30);
        add(dcdob);
        
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
            public void insertString(FilterBypass fb, int offset, String text, AttributeSet attr) throws BadLocationException {
                if (isDigit(text)) {
                    super.insertString(fb, offset, text, attr);
                }
                else{
                    JOptionPane.showMessageDialog(null,"Please enter digits (0-9) only.","Invalid Input",JOptionPane.WARNING_MESSAGE);
                }
            }

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
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
        
        tfXMarks = new JTextField();
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
        
        tfXIIMarks = new JTextField();
        tfXIIMarks.setBounds(160,390,200,40);
        tfXIIMarks.setFont(new Font("Lato",Font.PLAIN,18));
        add(tfXIIMarks);
        
        //Adhaar No
        lblAdhaar = new JLabel("Adhaar No: ");
        lblAdhaar.setBounds(400,390,150,30);
        lblAdhaar.setFont(new Font("Lato",Font.PLAIN,20));
        add(lblAdhaar);
        
        tfAdhaar = new JTextField();
        tfAdhaar.getDocument().addDocumentListener(new DocumentListener(){
            @Override
           public void insertUpdate(DocumentEvent e){
               validateAdhaar(tfAdhaar.getText());
           } 
           @Override
           public void removeUpdate(DocumentEvent e){
               validateAdhaar(tfAdhaar.getText());
           } 
           @Override
           public void changedUpdate(DocumentEvent e){
               validateAdhaar(tfAdhaar.getText());
           } 
        });
        tfAdhaar.setBounds(600,390,200,40);
        tfAdhaar.setFont(new Font("Lato",Font.PLAIN,18));
        add(tfAdhaar);
        
        //Course info
        lblCourse = new JLabel("Course: ");
        lblCourse.setBounds(50,460,150,30);
        lblCourse.setFont(new Font("Lato",Font.PLAIN,20));
        add(lblCourse);
        
        String[] course = {"B.Tech","B.E","BBA","MBA"};
        cbcourse = new JComboBox(course);
        cbcourse.setBounds(160,460,150,30);
        cbcourse.setFont(new Font("Lato",Font.PLAIN,14));
        cbcourse.setBackground(Color.WHITE);
        add(cbcourse);
        
        //Branch
        lblBranch = new JLabel("Branch: ");
        lblBranch.setBounds(400,460,150,30);
        lblBranch.setFont(new Font("Lato",Font.PLAIN,20));
        add(lblBranch);
        
        String[] branch = {"CSE","IT","E&TC","Mechanical","Robotics","AI&ML","Finance","Marketing","SC&L"};
        cbbranch = new JComboBox(branch);
        cbbranch.setBounds(600,460,150,30);
        cbbranch.setFont(new Font("Lato",Font.PLAIN,14));
        cbbranch.setBackground(Color.WHITE);
        add(cbbranch);
        
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
        submit.setBounds(300,550,120,30);
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
        cancel.setBounds(470,550,120,30);
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this);
        add(cancel);

        
        
        setSize(900,700);
        setLocation(350,50);
        getContentPane().setBackground(Color.WHITE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        
    }
    
    private static void validateAdhaar(String adhaar){
        boolean isValid = false;
        if (adhaar.length() == 12){
            isValid = true;
        }
        if (isValid){
            tfAdhaar.setBackground(Color.WHITE);
        }
        else{
            tfAdhaar.setBackground(Color.YELLOW);
        }
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
            
            if (ae.getSource() == submit){
                String name = tfname.getText();
                String fname = tffname.getText();
                String roll = labelRoll.getText();
                String doB = ( (JTextField) dcdob.getDateEditor().getUiComponent() ).getText();
                String address = tfAddress.getText();
                String phone = tfPhone.getText();
                String email = tfEAddress.getText();
                String X = tfXMarks.getText();
                String XII = tfXIIMarks.getText();
                String adhaar = tfAdhaar.getText();
                String course =(String) cbcourse.getSelectedItem();
                String branch = (String) cbbranch.getSelectedItem();
                
                try {
                    
                    String query = "INSERT INTO student VALUES ('"+name+"','"+fname+"','"+roll+"','"+doB+"','"+address+"','"+phone+"','"+email+"','"+X+"','"+XII+"','"+adhaar+"','"+course+"','"+branch+"')";
                    
                    Conn c = new Conn();
                    //DML command
                    c.s.executeUpdate(query);
                    
                    JOptionPane.showMessageDialog(null,"Student Details inserted Successfully");
                    setVisible(false);
                    
                } catch (Exception e){
                    e.printStackTrace();
                }
                
                
            } else {
                setVisible(false);
            }
            
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    
    
    public static void main(String[] args){
        
        new AddStudent();
        
    }
    
}
