
package studentmanagementsystem;

import java.sql.*;
import java.util.Scanner;
import java.util.Arrays;


public class StudentManagementSystem {
    
    private static DbConfig dbconfig=DbConfig.getInstance();
    
    public static void dashboard() throws Exception{
        Scanner s =new Scanner(System.in);
        System.out.println("--------------------------------------------------------");
        System.out.println("|-Welcome to Developer Stack Student Management System-|");
        System.out.println("--------------------------------------------------------");
        System.out.println(" 1 Insert new student");
        System.out.println(" 2 View All students");
        System.out.println(" 3 Search student");
        System.out.println(" 4 Delete student");
        System.out.println(" 5 GetAll students");
        System.out.println(" 6 Get student By Id");
        System.out.println(" 7 Get student Name By Id");
        System.out.println(" 8 Log out \n");

        System.out.print(" Enter your choice : ");
        int choice = s.nextInt();
        System.out.println(" ");
//        System.out.print(choice);

        switch (choice) {
            case 1 -> insertStudent();
            case 2 -> getAllStudents();
            case 3 -> getStudent();
            case 4 -> deleteStudent();
            case 5 -> callGetAllStudent();
            case 6 -> callGetById();
            case 7 -> callGetNameById();
            case 8 -> System.exit(0); 
        
            default -> {
                System.out.println("\033[H\033[2J");
                System.out.println("Invalid choice try again.....!!!");
                dashboard();
            }
                
        }

    }
    public static void main(String[] args)throws Exception {
            dashboard();


    }
    
    public static void insertStudent() throws Exception{
        
        System.out.println("\033[H\033[2J");
        System.out.println("--------------------------------------------------------");
        System.out.println("|-Welcome to Developer Stack Student Management System-|");
        System.out.println("--------------------------------------------------------");
        System.out.println("----------------");
        System.out.println("|-Save Student-|");
        System.out.println("----------------\n");
        
        Scanner s=new Scanner(System.in);
        
        System.out.print("Enter Student Name : ");
        String name=s.nextLine();        
        
        System.out.print("Enter Student Age : ");
        int age=s.nextInt();
        s.nextLine();
        
        System.out.print("Enter Student Department : ");
        String department=s.nextLine();
        
        System.out.print("Enter Student District : ");
        String district=s.nextLine();
        
        System.out.print("Enter Student NIC : ");
        String NIC =s.nextLine();
        
        System.out.print("Enter Student Gender : ");
        String gender=s.nextLine();
        
        System.out.print("Enter Student Performance : ");
        int performance=s.nextInt();
        s.nextLine();
        
        String sql="insert into students (name,age,department,district,NIC,gender,performance)" + "values (?,?,?,?,?,?,?)";
        
        try(Connection con=dbconfig.dbconnection()){
            PreparedStatement ps=con.prepareStatement(sql);
            
            ps.setString(1, name);
            ps.setInt(2, age);
            ps.setString(3, department);
            ps.setString(4, district);
            ps.setString(5,NIC);
            ps.setString(6, gender);
            ps.setInt(7, performance);
            
            int row=ps.executeUpdate();
            System.out.println(row);
            
            System.out.println("Successfully done \n");
            System.out.println("Do you want to store another student details? press \"yes\" OR \"no \" ");
            String selection=s.nextLine();
            if(selection.equalsIgnoreCase("yes")){
                System.out.println("\033[H\033[2J");
                insertStudent();
            }
            else{
                System.out.println("\033[H\033[2J");
                dashboard();
            }
        
        }
        catch(Exception e){
            System.out.println(e);
        }
        
        
        
        
        
    }
    
    public static void getAllStudents()throws Exception{
        
        System.out.println("\033[H\033[2J");
        System.out.println("--------------------------------------------------------");
        System.out.println("|-Welcome to Developer Stack Student Management System-|");
        System.out.println("--------------------------------------------------------");
        System.out.println("--------------------");
        System.out.println("|-View all Student-|");
        System.out.println("--------------------\n");
        
        String sql="select * from students";
        
        try(Connection con = dbconfig.dbconnection()){
            Statement st=con.createStatement();
            ResultSet rs=st.executeQuery(sql);
            
            while(rs.next()){
            String userData=rs.getInt(1)+" "+rs.getString(2)+" "+rs.getInt(3)+" "+rs.getString(4)+" "+rs.getString(5)+" "+rs.getString(6)+" "+rs.getString(7)+" "+rs.getInt(8);
            System.out.println(userData);
            }
        }
        System.out.println(" ");
        Scanner s=new Scanner(System.in);
        System.out.print("Enter any key to return to Dashboard\n");
        String selection=s.nextLine();
        if(selection != null){
            System.out.println("\033[H\033[2J");
            dashboard();
        }
    }
    
    public static void deleteStudent() throws Exception{
        Scanner s = new Scanner(System.in);
        
        System.out.println("--------------------------------------------------------");
        System.out.println("|-Welcome to Developer Stack Student Management System-|");
        System.out.println("--------------------------------------------------------");
        System.out.println("------------------");
        System.out.println("|-Remove Student-|");
        System.out.println("------------------\n");
        
        System.out.print("Enter ID : ");
        int id=s.nextInt();
        s.nextLine();
        String sql="delete from students where id= ?";
        
        try(Connection con=dbconfig.dbconnection()){
        PreparedStatement ps=con.prepareStatement(sql);
        ps.setInt(1, id);
        int row=ps.executeUpdate();
            System.out.println(row);
        }
        System.out.print("Enter any key to return to Dashboard\n");
        String selection=s.nextLine();

        if(selection != null){
            System.out.println("\033[H\033[2J");
            dashboard();
        }
    }
    
    public static void getStudent()throws Exception{
        System.out.println("\033[H\033[2J");
        Scanner s = new Scanner(System.in);
        System.out.println("--------------------------------------------------------");
        System.out.println("|-Welcome to Developer Stack Student Management System-|");
        System.out.println("--------------------------------------------------------");
        System.out.println("------------------");
        System.out.println("|-Search Student-|");
        System.out.println("------------------\n");
        
        System.out.print("Enter ID : ");
        int id=s.nextInt();
        s.nextLine();
        String sql="select * from students where id= ?";
        
        try(Connection con=dbconfig.dbconnection()){
        PreparedStatement ps=con.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs=ps.executeQuery();
        
        if(rs.next()){
            String userData=rs.getInt(1)+" "+rs.getString(2)+" "+rs.getInt(3)+" "+rs.getString(4)+" "+rs.getString(5)+" "+rs.getString(6)+" "+rs.getString(7)+" "+rs.getInt(8);
            System.out.println(userData);
        }
        else {
                System.out.println("No student found with ID: " + id);
            }
        }
        System.out.print("Enter any key to return to Dashboard\n");
        String selection=s.nextLine();

        if(selection != null){
            System.out.println("\033[H\033[2J");
            dashboard();
        }
    
    }
    
    public static void callGetAllStudent() throws Exception{
     Connection con=dbconfig.dbconnection();
     CallableStatement cs=con.prepareCall("{call GetAll()}");
     ResultSet rs=cs.executeQuery();
     
     while(rs.next()){
     String name=rs.getString(2);
         System.out.println(name);
     }
     System.out.print("Enter any key to return to Dashboard\n");
     Scanner s = new Scanner(System.in);
        String selection=s.nextLine();

        if(selection != null){
            System.out.println("\033[H\033[2J");
            dashboard();
        }
    
    }
    
    public static void callGetById()throws Exception{
        Connection con=dbconfig.dbconnection();
        Scanner s = new Scanner(System.in);
        System.out.print("Enter ID ");
        int id=s.nextInt();
        s.nextLine();
     CallableStatement cs=con.prepareCall("{call GetById(?)}");
     cs.setInt(1, id);
     ResultSet rs=cs.executeQuery();
     
     if(rs.next()){
            String userData=rs.getInt(1)+" "+rs.getString(2)+" "+rs.getInt(3)+" "+rs.getString(4)+" "+rs.getString(5)+" "+rs.getString(6)+" "+rs.getString(7)+" "+rs.getInt(8);
            System.out.println(userData);
        }
        else {
                System.out.println("No student found with ID: " + id);
            }
     System.out.print("Enter any key to return to Dashboard\n");
     
        String selection=s.nextLine();

        if(selection != null){
            System.out.println("\033[H\033[2J");
            dashboard();
        }
    }
    
    public static void callGetNameById()throws Exception{
        Connection con=dbconfig.dbconnection();
        Scanner s = new Scanner(System.in);
        System.out.print("Enter ID ");
        int id=s.nextInt();
        s.nextLine();
     CallableStatement cs=con.prepareCall("{call GetNameById(?,?)}");
     cs.setInt(1, id);
     cs.registerOutParameter(2, Types.VARCHAR);
     cs.executeUpdate();
     
        System.out.println(cs.getString(2));
    
        System.out.print("Enter any key to return to Dashboard\n");
     
        String selection=s.nextLine();

        if(selection != null){
            System.out.println("\033[H\033[2J");
            dashboard();
        }
    }
    
            
    public static void batchProcessing() throws Exception{
    
        Connection con=dbconfig.dbconnection();
        String query1="update students set performance=72 where id=2";
        String query2="update students set performance=95 where id=4";
        String query3="update students set performance=88 where id=5";
        
        Statement st=con.createStatement();
        st.addBatch(query1);
        st.addBatch(query2);
        st.addBatch(query3);
        
        int[] a=st.executeBatch();
        System.out.println(Arrays.toString(a));
        
        System.out.print("Enter any key to return to Dashboard\n");
     Scanner s = new Scanner(System.in);
        String selection=s.nextLine();

        if(selection != null){
            System.out.println("\033[H\033[2J");
            dashboard();
        }
    }
    
    public static void rollBackPractice() throws Exception{
        
        Connection con=dbconfig.dbconnection();
        String query1="update students set performance=72 where id=2";
        String query2="updat students set performance=95 where id=4";
        String query3="update students set performance=88 where id=5";
        
        Statement st=con.createStatement();
        con.setAutoCommit(false);
        
        st.addBatch(query1);
        st.addBatch(query2);
        st.addBatch(query3);
        
        int[] a=st.executeBatch();
        
        for(int i:a){
            if(i>0){
                continue;
            }
            else{
                con.rollback();
            }
        
        }
        con.commit();
        System.out.print("Enter any key to return to Dashboard\n");
        Scanner s = new Scanner(System.in);
        String selection=s.nextLine();

        if(selection != null){
            System.out.println("\033[H\033[2J");
            dashboard();
        }
    }
    
    public static void commitPractice()throws Exception{
        
        Connection con=dbconfig.dbconnection();
        String query1="update students set performance=72 where id=2";
        String query2="update students set performance=95 where id=4";
        
        
        
        Statement st=con.createStatement();
        
        con.setAutoCommit(false);
        
        int a=st.executeUpdate(query1);
        int b=st.executeUpdate(query2);
        
        if(a>0 && b>0){
            con.commit();
        }
        
        System.out.println(a);
        System.out.println(b);
        con.close();
    }
    
    
    
    
}
