import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDataExtractor {

    public List<Employee> getEmployeeListFromJsonFile(String filePath) throws IOException {
        Employees employees = new ObjectMapper().readValue(Paths.get(filePath).toFile(), new TypeReference<Employees>() {});
        return employees.getEmployees();
    }

    public List<Employee> getEmployeeListFromCSVFile(String filePath) {
        EmployeeMapper employeeMapper = new EmployeeMapper();
        List<Employee> employeeList = new ArrayList<>();
        try (BufferedReader br = Files.newBufferedReader(Paths.get(filePath), StandardCharsets.UTF_8)) {
//            header line needs to be skipped
            br.readLine();
            String line = br.readLine();
            while (line != null) {
                String[] attributes = line.split(";");
                Employee employee = employeeMapper.createEmployee(attributes);
                employeeList.add(employee);
                line = br.readLine();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return employeeList;
    }

}
