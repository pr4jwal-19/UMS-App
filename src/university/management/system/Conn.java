package university.management.system;

import java.sql.*; // contains c & s

public class Conn {
    
    //Make a reference to the interface Connection -> info of jdbc
    Connection c;
    //Make a reference to the interface Statement -> to execute a query
    Statement s;
    
    Conn(){
        
        try{
            
            //1) Register the Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            //2) Creating a Connection String
            c = DriverManager.getConnection("jdbc:mysql:///university","root","lkjhgfdsa");
            //3) Creating the Statement
            s = c.createStatement();
            
            
        } catch (Exception e){
            e.printStackTrace();
        }
        
        
    }

}
