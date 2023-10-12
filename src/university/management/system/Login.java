package university.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.DocumentListener;
import javax.swing.event.DocumentEvent;
import java.util.regex.*;
import java.sql.*;


public class Login extends JFrame implements ActionListener {
    
    JLabel username,password;
     static JTextField un_text;
    static JPasswordField un_pass;
    JButton login, cancel;
    
    
    Login(){
        
        setLayout(null);
        setTitle("Prizzwal University");
        
        username = new JLabel("Username: ");
        username.setFont(new Font("Segoe UI",Font.PLAIN,20));
        username.setBounds(40,20,100,20);
        add(username);
      
        un_text = new JTextField();
        un_text.getDocument().addDocumentListener(new DocumentListener(){
            @Override
            public void insertUpdate(DocumentEvent e){
                validateUsername(un_text.getText());
            }
            @Override
            public void removeUpdate(DocumentEvent e){
                validateUsername(un_text.getText());
            }
            @Override
            public void changedUpdate(DocumentEvent e){
                validateUsername(un_text.getText());
            }          
        });
        un_text.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.DARK_GRAY));
        un_text.setBounds(150, 22, 200, 30);
        un_text.setFont(new Font("Nunito",Font.PLAIN,16));
        add(un_text);
        
        password = new JLabel("Password: ");
        password.setFont(new Font("Segoe UI",Font.PLAIN,20));
        password.setBounds(40,70,100,20);
        add(password);
        
        un_pass = new JPasswordField();
        un_pass.getDocument().addDocumentListener(new DocumentListener(){
           @Override
           public void insertUpdate(DocumentEvent e){
               validatePassword(new String(un_pass.getPassword()));
           } 
           @Override
           public void removeUpdate(DocumentEvent e){
               validatePassword(new String(un_pass.getPassword()));
           } 
           @Override
           public void changedUpdate(DocumentEvent e){
               validatePassword(new String(un_pass.getPassword()));
           } 
        });
        un_pass.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.DARK_GRAY));
        un_pass.setBounds(150, 72, 200, 30);
        un_pass.setFont(new Font("Nunito",Font.PLAIN,16));
        add(un_pass);
        
        // Login Button
        login = new JButton("Login");
        login.setBackground(Color.BLACK);
        login.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e){
                login.setBackground(Color.DARK_GRAY);
            }
            @Override
            public void mouseExited(MouseEvent e){
                login.setBackground(Color.BLUE);
            }
        });
        login.setBounds(40,140,120,30);
        login.setForeground(Color.WHITE);
        login.addActionListener(this);
        add(login);
        
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
        cancel.setBounds(180,140,120,30);
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this);
        add(cancel);
        
        ImageIcon i1 = Splash.loadAndScaleIcon("icons/second.jpg", 200, 200);
        JLabel image = new JLabel(i1);
        image.setBounds(350,0,200,200);
        add(image);
        
        
        //Creating frame
        getContentPane().setBackground(Color.WHITE);
        setSize(600,300);
        setLocation(500,250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        
    }
    
    private static void validateUsername(String username){
        boolean isValid = true;
        //check minimum length(8 characters)
        if (username.length() < 8){
            isValid = false;
        }
        if (isValid){
            //username valid
            un_text.setBackground(Color.WHITE);
        }
        else{
            un_text.setBackground(Color.RED);
            //Shows a message
            //JOptionPane.showMessageDialog(null,"Username does not meet the criteria.","Invalid Username",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private static void validatePassword(String password){
        boolean isValid = true;
        //checks minimum length
        if (password.length() < 8){
            isValid = false;
        }
        //checks for more constraints
        if (!Pattern.matches(".*[A-Z].*",password) || !Pattern.matches(".*\\d.*", password) || !Pattern.matches(".*[!@#$%^&*()].*", password)){
            isValid = false;
        }
        if (isValid){
            //password valid
            un_pass.setBackground(Color.WHITE);
        }
        else{
            un_pass.setBackground(Color.RED);
            //Shows a message
            //JOptionPane.showMessageDialog(null,"Password does not meet the criteria.","Invalid Password",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent ae){
        
        try {
            
            if (ae.getSource() == login){
                // Get username and Password when login clicked
                String userName = un_text.getText();
                char[] pass = un_pass.getPassword();
                String userPassword = new String(pass);
                
                String query = "SELECT * FROM login WHERE USERNAME = '"+userName+"' AND PASSWORD = '"+userPassword+"' ";
                try {
                    // we have to create the connection and hit the database with the above query
                    Conn c = new Conn();
                    //4) Execute query
                    //this is a DDL command. we store the result of that query into "rs" 
                    ResultSet rs = c.s.executeQuery(query);
                    if (rs.next()){
                        JOptionPane.showMessageDialog(null, "User Authorized");
                        setVisible(false);
                        new Project();
                    } else {
                        JOptionPane.showMessageDialog(null,"Invalid username or password. Please check the criteria.", "Login Error",JOptionPane.ERROR_MESSAGE);
                        //setVisible(false);
                    }
                    //5) Close connection
                    c.s.close();
                } catch (Exception e){
                    e.printStackTrace();
                }
                
            } else if (ae.getSource() == cancel){
                setVisible(false);
            }
            
        } catch (Exception e){
            System.out.println(e);
        }
        
    }
    
    public static void main(String[] args){
        new Login();
    }
    
    
    /*
    char[] passwordChars = getPasswordFromUser(); // Retrieve the password as a char array

    // After using the password, clear it from memory by overwriting it with random values or spaces
    Arrays.fill(passwordChars, ' '); // Overwrite with spaces

    // Nullify the reference to the password char array
    passwordChars = null;

    */
    
}
