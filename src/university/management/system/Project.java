package university.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class Project extends JFrame implements ActionListener{
    
    JMenuBar mb;
    JMenu newInfo,details,leave,leaveDetails,exams,
            updateInfo,fees,utility,about,exit;
    JMenuItem facultyInfo,studentInfo,facultyDetails,
            studentDetails,facultyLeave,studentLeave,facultyLeaveD,
            studentLeaveD,examR,enterMarks,updateFI,updateSI,
            feeStructure,feeForm,notepad,calc,browser,ab,ex;
    
    Project(){
        
        ImageIcon i1 = Splash.loadAndScaleIcon("icons/third.jpg", 1500, 750);
        JLabel image = new JLabel(i1);
        add(image);
        
        mb = new JMenuBar();
        mb.setBackground(Color.DARK_GRAY);
        mb.setPreferredSize(new Dimension(400,40));
        
        //New Information
        newInfo = new JMenu("New Information");
        newInfo.setFont(new Font("Popins",Font.PLAIN,18));
        newInfo.setForeground(Color.WHITE);
        mb.add(newInfo);
        
        facultyInfo = new JMenuItem("New Faculty Information");
        facultyInfo.setFont(new Font("Segoe UI", Font.PLAIN, 14)); // Use a modern font
        facultyInfo.setForeground(Color.BLACK);
        facultyInfo.addActionListener(this);
        newInfo.add(facultyInfo);
        
        studentInfo = new JMenuItem("New Student Information");
        studentInfo.setFont(new Font("Segoe UI",Font.PLAIN,14));
        studentInfo.setForeground(Color.BLACK);
        studentInfo.addActionListener(this);
        newInfo.add(studentInfo);
        
        //Details
        details = new JMenu("View Details");
        details.setFont(new Font("Popins",Font.PLAIN,18));
        details.setForeground(Color.WHITE);
        mb.add(details);
        
        facultyDetails = new JMenuItem("View Faculty Details");
        facultyDetails.setFont(new Font("Segoe UI",Font.PLAIN,14));
        facultyDetails.setForeground(Color.BLACK);
        facultyDetails.addActionListener(this);
        details.add(facultyDetails);
        
        studentDetails = new JMenuItem("View Student Details");
        studentDetails.setFont(new Font("Segoe UI",Font.PLAIN,14));
        studentDetails.setForeground(Color.BLACK);
        studentDetails.addActionListener(this);
        details.add(studentDetails);
        
        //Leave
        leave = new JMenu("Apply Leave");
        leave.setFont(new Font("Popins",Font.PLAIN,18));
        leave.setForeground(Color.WHITE);
        mb.add(leave);
        
        facultyLeave = new JMenuItem("Faculty Leave");
        facultyLeave.setFont(new Font("Segoe UI",Font.PLAIN,14));
        facultyLeave.setForeground(Color.BLACK);
        facultyLeave.addActionListener(this);
        leave.add(facultyLeave);
        
        studentLeave = new JMenuItem("Student Leave");
        studentLeave.setFont(new Font("Segoe UI",Font.PLAIN,14));
        studentLeave.setForeground(Color.BLACK);
        studentLeave.addActionListener(this);
        leave.add(studentLeave);
        
        //Leave Details
        leaveDetails = new JMenu("Leave Details");
        leaveDetails.setFont(new Font("Popins",Font.PLAIN,18));
        leaveDetails.setForeground(Color.WHITE);
        mb.add(leaveDetails);
        
        facultyLeaveD = new JMenuItem("Faculty Leave Details");
        facultyLeaveD.setFont(new Font("Segoe UI",Font.PLAIN,14));
        facultyLeaveD.setForeground(Color.BLACK);
        facultyLeaveD.addActionListener(this);
        leaveDetails.add(facultyLeaveD);
        
        studentLeaveD = new JMenuItem("Student Leave Details");
        studentLeaveD.setFont(new Font("Segoe UI",Font.PLAIN,14));
        studentLeaveD.setForeground(Color.BLACK);
        studentLeaveD.addActionListener(this);
        leaveDetails.add(studentLeaveD);
        
        //Exams
        exams = new JMenu("Examination");
        exams.setFont(new Font("Popins",Font.PLAIN,18));
        exams.setForeground(Color.WHITE);
        mb.add(exams);
        
        examR = new JMenuItem("Examination Results");
        examR.setFont(new Font("Segoe UI",Font.PLAIN,14));
        examR.setForeground(Color.BLACK);
        examR.addActionListener(this);
        exams.add(examR);
        
        enterMarks = new JMenuItem("Enter Marks");
        enterMarks.setFont(new Font("Segoe UI",Font.PLAIN,14));
        enterMarks.setForeground(Color.BLACK);
        enterMarks.addActionListener(this);
        exams.add(enterMarks);
        
        //Update Info
        updateInfo = new JMenu("Update Details");
        updateInfo.setFont(new Font("Popins",Font.PLAIN,18));
        updateInfo.setForeground(Color.WHITE);
        mb.add(updateInfo);
        
        updateFI = new JMenuItem("Update Faculty Details");
        updateFI.setFont(new Font("Segoe UI",Font.PLAIN,14));
        updateFI.setForeground(Color.BLACK);
        updateFI.addActionListener(this);
        updateInfo.add(updateFI);
        
        updateSI = new JMenuItem("Update Student Details");
        updateSI.setFont(new Font("Segoe UI",Font.PLAIN,14));
        updateSI.setForeground(Color.BLACK);
        updateSI.addActionListener(this);
        updateInfo.add(updateSI);
        
        //Fee
        fees = new JMenu("Fee Details");
        fees.setFont(new Font("Popins",Font.PLAIN,18));
        fees.setForeground(Color.WHITE);
        mb.add(fees);
        
        feeStructure = new JMenuItem("Fees Structure");
        feeStructure.setFont(new Font("Segoe UI",Font.PLAIN,14));
        feeStructure.setForeground(Color.BLACK);
        feeStructure.addActionListener(this);
        fees.add(feeStructure);
        
        feeForm = new JMenuItem("Fees Form");
        feeForm.setFont(new Font("Segoe UI",Font.PLAIN,14));
        feeForm.setForeground(Color.BLACK);
        fees.add(feeForm);
        
        //Utility
        utility = new JMenu("Utility");
        utility.setFont(new Font("Popins",Font.PLAIN,18));
        utility.setForeground(Color.WHITE);
        mb.add(utility);
        
        notepad = new JMenuItem("Notepad");
        notepad.setFont(new Font("Segoe UI",Font.PLAIN,14));
        notepad.setForeground(Color.BLACK);
        notepad.addActionListener(this);
        utility.add(notepad);
        
        calc = new JMenuItem("Calculator");
        calc.setFont(new Font("Segoe UI",Font.PLAIN,14));
        calc.setForeground(Color.BLACK);
        calc.addActionListener(this);
        utility.add(calc);
        
        browser = new JMenuItem("Browser");
        browser.setFont(new Font("Segoe UI",Font.PLAIN,14));
        browser.setForeground(Color.BLACK);
        browser.addActionListener(this);
        utility.add(browser);
        
        //exit
        about = new JMenu("About");
        about.setFont(new Font("Popins",Font.PLAIN,18));
        about.setForeground(Color.WHITE);
        mb.add(about);
        
        ab = new JMenuItem("About");
        ab.setFont(new Font("Segoe UI",Font.PLAIN,14));
        ab.setForeground(Color.BLACK);
        ab.addActionListener(this);
        about.add(ab);
        
        //exit
        exit = new JMenu("Exit");
        exit.setFont(new Font("Popins",Font.PLAIN,18));
        exit.setForeground(Color.WHITE);
        mb.add(exit);
        
        ex = new JMenuItem("Exit");
        ex.setFont(new Font("Segoe UI",Font.PLAIN,14));
        ex.setForeground(Color.BLACK);
        ex.addActionListener(this);
        exit.add(ex);

        
        setJMenuBar(mb);
        
        setSize(1540,850);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        
    }
    
    @Override
    public void actionPerformed(ActionEvent ae){
        try {
            
            String msg = ae.getActionCommand();
            
            if (msg.equals("Exit")){
                setVisible(false);
            } else if (msg.equals("Browser")){
                try {
                    Runtime.getRuntime().exec("C:\\Program Files\\BraveSoftware\\Brave-Browser\\Application\\brave.exe");
                } catch (IOException e){
                    System.out.println(e);
                }          
            } else if (msg.equals("Calculator")){
                try {
                    Runtime.getRuntime().exec("calc.exe");
                } catch (IOException e){
                    System.out.println(e);
                }            
            } else if (msg.equals("Notepad")){
                try {
                    Runtime.getRuntime().exec("notepad.exe");
                } catch (IOException e){
                    System.out.println(e);
                }
            } else if (msg.equals("New Faculty Information")){
                new AddInstructor();
            } else if (msg.equals("New Student Information")){
                new AddStudent();
            } else if (msg.equals("View Faculty Details")){
                new TeacherDetails();
            } else if (msg.equals("View Student Details")){
                new StudentDetails();
            } else if (msg.equals("Faculty Leave")){
                new FacultyLeave();
            } else if (msg.equals("Student Leave")){
                new StudentLeave();
            } else if (msg.equals("Faculty Leave Details")){
                new FacultyLeaveDetails();
            } else if (msg.equals("Student Leave Details")){
                new StudentLeaveDetails();
            } else if (msg.equals("Update Faculty Details")){
                new UpdateTeacher();
            } else if (msg.equals("Update Student Details")){
                new UpdateStudents();
            } else if (msg.equals("Enter Marks")){
                new EnterMarks();
            } else if (msg.equals("Examination Results")){
                new ExaminationDetails();
            } else if (msg.equals("Fees Structure")){
                new FeeStructure();
            } else if (msg.equals("About")){
                new About();
            }
            
            
        } catch (Exception e){
            System.out.println(e);
        }
    }
    
    public static void main(String[] args){
        new Project();
    }
    
}
