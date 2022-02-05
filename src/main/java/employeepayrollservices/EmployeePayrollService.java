/**
 * Author: Vinit Kumar
 * Created: 05-Feb-2022
 * Service Class
 */
package employeepayrollservices;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmployeePayrollService {
    public enum IOService {CONSOLE_IO, FILE_IO, DB_IO, REST_IO}

    private List<EmployeePayrollData> employeePayrollList;

    public EmployeePayrollService() {
    }

    ;

    public EmployeePayrollService(List<EmployeePayrollData> employeePayrollList) {
        this.employeePayrollList = employeePayrollList;
    }

    public static void main(String[] args) {
        List<EmployeePayrollData> employeePayrollList = new ArrayList<>();
        EmployeePayrollService employeePayrollService = new EmployeePayrollService(employeePayrollList);
        Scanner consoleInputReader = new Scanner(System.in);
        employeePayrollService.readEmployeePayrollData(consoleInputReader);
        employeePayrollService.writeEmployeePayrollData(IOService.CONSOLE_IO);
    }

    private void readEmployeePayrollData(Scanner consoleInputReader) {
        System.out.println("Enter Employee ID: ");
        int id = consoleInputReader.nextInt();
        System.out.println("Enter Employee Name: ");
        String name = consoleInputReader.next();
        System.out.println("Enter Employee Salary: ");
        double salary = consoleInputReader.nextDouble();
        employeePayrollList.add(new EmployeePayrollData(id, name, salary));
    }

    public void writeEmployeePayrollData(IOService ioService) {
        if (ioService.equals(IOService.CONSOLE_IO))
            System.out.println("\nWriting Employee Payroll Roaster to Console\n" + employeePayrollList);
        else {
            if (ioService.equals(IOService.FILE_IO)) {
                new EmployeePayrollFileIOService().writeData(employeePayrollList);
            }
        }
    }

    public long countEntries(IOService ioService) {
        if (ioService.equals(IOService.FILE_IO)) {
            return new EmployeePayrollFileIOService().countEntries();
        }
        return -2;
    }

    public void printData(IOService ioService) {
        if (ioService.equals(IOService.FILE_IO)) {
            new EmployeePayrollFileIOService().printData();
        }
    }

    public long readEmployeePayrollData(IOService ioService) {
        if (ioService.equals(IOService.FILE_IO)) {
            return new EmployeePayrollFileIOService().countEntries();
        }
        return -2;
    }

    /**
     * retrieve Employee Data
     * checks io Operation
     * calls retrieveEmployee of EmployeeDB
     * returns list
     *
     * @param ioService
     * @return
     */
    public List<EmployeePayrollData> retrieveEmployee(IOService ioService) {
        if (ioService.equals(IOService.DB_IO)) {
            return new EmployeePayrollFileDBService().retrieveEmployee();
        } else {
            List<EmployeePayrollData> employee = new ArrayList<>();
            return employee;
        }
    }

    /**
     * Update Employee Data
     * checks io Operation
     * calls UpdateEmployee of EmployeeDB
     * returns new Data
     *
     * @param ioService
     * @return
     */
    public EmployeePayrollData updateEmployee(IOService ioService) {
        if (ioService.equals(IOService.DB_IO)) {
            return new EmployeePayrollFileDBService().updateEmployee();
        } else {
            EmployeePayrollData employee = new EmployeePayrollData();
            return employee;
        }
    }
}
