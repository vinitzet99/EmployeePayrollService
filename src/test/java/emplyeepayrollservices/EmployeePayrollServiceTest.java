/**
 * Author: Vinit Kumar
 * Created: 05-Feb-2022
 * Test File
 */
package emplyeepayrollservices;

import employeepayrollservices.EmployeePayrollData;
import employeepayrollservices.EmployeePayrollService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static employeepayrollservices.EmployeePayrollService.IOService.DB_IO;
import static employeepayrollservices.EmployeePayrollService.IOService.FILE_IO;

public class EmployeePayrollServiceTest {
    @Test
    public void givenEmployeesWhenWrittenToFileShouldMatchEmployeeEntries() {
        EmployeePayrollData[] arrayOfEmps = {
                new EmployeePayrollData(1, "3eff Bezos", 100000.0),
                new EmployeePayrollData(2, "Bill Gates", 200000.0),
                new EmployeePayrollData(3, "Mark Zuckerberg", 300000.0)};
        EmployeePayrollService employeePayrollService =
                new EmployeePayrollService(Arrays.asList(arrayOfEmps));
        employeePayrollService.writeEmployeePayrollData(FILE_IO);
        employeePayrollService.printData(FILE_IO);
        long entries = employeePayrollService.countEntries(FILE_IO);
        Assertions.assertEquals(3, entries);
    }

    @Test
    public void givenFile0nReadingFromFileShouldMatchEmployeeCount() {
        EmployeePayrollService employeePayrollService = new EmployeePayrollService();
        long entries = employeePayrollService.readEmployeePayrollData(FILE_IO);
        Assertions.assertEquals(3, entries);
    }

    /**
     * validates retrieval data
     * retrieve Employee data
     * check count
     */
    @Test
    public void checkRetrieveDataCount() {
        List<EmployeePayrollData> data = new EmployeePayrollService().retrieveEmployee(DB_IO);
        Assertions.assertEquals(3, data.size());
    }

    /**
     * validates update data
     * retrieve Employee data
     * check salary and name
     */
    @Test
    public void updatedValue_shouldMatch_toActualDBResult() {
        EmployeePayrollData data = new EmployeePayrollService().updateEmployee(DB_IO);
        Assertions.assertEquals(3000000, data.getSalary());
        Assertions.assertEquals("Terisa", data.getName());
    }

    /**
     * validates date range data
     * retrieve data by date range
     * check count
     */
    @Test
    public void validateDateRange() {
        List<EmployeePayrollData> data = new EmployeePayrollService().employeeDateRange(DB_IO);
        Assertions.assertEquals(2, data.size());
    }

    /**
     * validate Employee Analysis
     * retrieve analysis list
     * assert with original value
     */
    @Test
    public void validateEmployeeAnalysis() {
        List<?> data = new EmployeePayrollService().employeeAnalysis(DB_IO);
        List<?> data1 = (List<?>) data.get(0);
        System.out.println(data1);
        Assertions.assertEquals(4000000.00, data1.get(0));
        Assertions.assertEquals(2000000.00, data1.get(1));
        Assertions.assertEquals(1000000.00, data1.get(2));
        Assertions.assertEquals(3000000.00, data1.get(3));
        Assertions.assertEquals(2.0, data1.get(4));
        data1 = (List<?>) data.get(1);
        Assertions.assertEquals(3000000.00, data1.get(0));
        Assertions.assertEquals(3000000.00, data1.get(1));
        Assertions.assertEquals(3000000.00, data1.get(2));
        Assertions.assertEquals(3000000.00, data1.get(3));
        Assertions.assertEquals(1.0, data1.get(4));


    }
}
