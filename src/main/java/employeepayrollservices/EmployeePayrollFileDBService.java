/**
 * Author: Vinit Kumar
 * Created: 05-Feb-2022
 * Employee Payroll Database
 */
package employeepayrollservices;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeePayrollFileDBService {
    /**
     * Check connection
     * loads Driver
     * use JDBC for connection
     * connect with dataBase
     * throw Exception in case of any error
     *
     * @param args
     */
    public static void main(String[] args) {
        String jdbcURL = "jdbc:mysql://localhost:3306/payroll_services?allowPublicKeyRetrieval=true&useSSL=False";
        String userName = "root";
        String password = "";
        Connection con;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver Loaded");
        } catch (Exception e) {
            System.out.println(e);
        }
        try {
            System.out.println("Connecting to database:" + jdbcURL);
            con = DriverManager.getConnection(jdbcURL, userName, password);
            System.out.println("Connected");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * create Connection
     * uses Driver Manager
     * returns connection object or null
     */
    public Connection getConnection() {
        String jdbcURL = "jdbc:mysql://localhost:3306/payroll_services?allowPublicKeyRetrieval=true&useSSL=False";
        String userName = "root";
        String password = "";
        Connection con = null;
        try {
            con = DriverManager.getConnection(jdbcURL, userName, password);
        } catch (Exception e) {
            System.out.println(e);
        }
        return con;
    }

    /**
     * retrieve data from employee_payroll
     * gets connection
     * if connection is success
     * create statement
     * execute statement
     * store result by iterating data set
     * return result
     */
    public List<EmployeePayrollData> retrieveEmployee() {
        List<EmployeePayrollData> employeePayrollData = new ArrayList<EmployeePayrollData>();
        try {
            Connection con = getConnection();
            if (con != null) {
                try {
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery("Select * from employee_payroll");
                    while (rs.next()) {
                        employeePayrollData.add(
                                new EmployeePayrollData(rs.getInt(1), rs.getString(2), rs.getDouble(7)));
                    }
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Connection un-successfull");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return employeePayrollData;
    }

    /**
     * retrieve data from employee_payroll
     * gets connection
     * if connection is success
     * create statement prepared statement for update
     * set values
     * execute statement update and select
     * store result
     * return result
     */
    public EmployeePayrollData updateEmployee() {
        EmployeePayrollData employeePayrollData = new EmployeePayrollData();
        try {
            Connection con = getConnection();
            if (con != null) {
                try {
                    Statement stmt = con.createStatement();
                    PreparedStatement pstmt =con.prepareStatement("update employee_payroll set basic_pay=? where name=?");
                    pstmt.setDouble(1, 3000000.00);
                    pstmt.setString(2,"Terisa");
                    int a=pstmt.executeUpdate();
                    ResultSet rs = stmt.executeQuery("Select * from employee_payroll where name ='Terisa';");
                    while (rs.next()) {
                        employeePayrollData = new EmployeePayrollData(rs.getInt(1), rs.getString(2), rs.getDouble(7));
                    }
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Connection un-successfull");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return employeePayrollData;
    }
}
