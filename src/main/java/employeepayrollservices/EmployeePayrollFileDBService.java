/**
 * Author: Vinit Kumar
 * Created: 05-Feb-2022
 * Employee Payroll Database
 */
package employeepayrollservices;

import java.sql.Connection;
import java.sql.DriverManager;

public class EmployeePayrollFileDBService {
    /**
     * Check connection
     * loads Driver
     * use JDBC for connection
     * connect with dataBase
     * throw Exception in case of any error
     * @param args
     */
    public static void main(String[] args){
        String jdbcURL="jdbc:mysql://localhost:3306/payroll_services?allowPublicKeyRetrieval=true&useSSL=False";
        String userName="root";
        String password="";
        Connection con;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver Loaded");
        }catch(Exception e){
            System.out.println(e);
        }
        try{
            System.out.println("Connecting to database:"+jdbcURL);
            con= DriverManager.getConnection(jdbcURL,userName,password);
            System.out.println("Connected");
        }catch(Exception e){
            System.out.println(e);
        }
    }
}
