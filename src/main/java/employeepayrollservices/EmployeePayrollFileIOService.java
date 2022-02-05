package employeepayrollservices;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class EmployeePayrollFileIOService {
    EmployeePayrollFileIOService() {
    };

    public static String PAYROLL_FILE_NAME = "resources/payroll-file.txt";

    public void writeData(List<EmployeePayrollData> employeePayrollList) {
        StringBuffer empBuffer = new StringBuffer();
        employeePayrollList.forEach(employee -> {
            String employeeDataString = employee.toString().concat("\n");
            empBuffer.append(employeeDataString);
        });

        try {
            Files.write(Paths.get(PAYROLL_FILE_NAME), empBuffer.toString().getBytes());
        } catch (IOException e) {
            System.out.println(e);
        }

    }

    public long countEntries(){
        try{
            return Files.lines(new File(PAYROLL_FILE_NAME).toPath()).count();
        }catch (IOException e){
            return -1;
        }
    }
}
