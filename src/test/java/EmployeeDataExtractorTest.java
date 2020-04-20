import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class EmployeeDataExtractorTest {
    private EmployeeDataExtractor employeeDataExtractor;

    @BeforeEach
    void setUp(){
        employeeDataExtractor = new EmployeeDataExtractor();
    }

    @Test
    void shouldReturnListOfEmployeesFromJson() throws IOException {
        String jsonFileToTest = "src/test/resources/JsonFileTest.json";

        List<Employee> employeeList = employeeDataExtractor.getEmployeeListFromJsonFile(jsonFileToTest);

        List<Employee> expectedEmployeeList = new ArrayList<>();
        expectedEmployeeList.add(new Employee(1,"Mark","Green","Teacher", "3540,20"));
        expectedEmployeeList.add(new Employee(2,"Oscar","Mustache","Janitor", "13460.45"));
        assertEquals(expectedEmployeeList,employeeList);
    }

    @Test
    void shouldThrownIOExceptionWhenJsonFileNotFound() {
        assertThrows(IOException.class, () -> employeeDataExtractor.getEmployeeListFromJsonFile(""));
    }

    @Test
    void shouldReturnListOfEmployeesFromCSV() throws IOException {
        String csvFileToTest = "src/test/resources/CSVFileTest.csv";

        List<Employee> employeeList = employeeDataExtractor.getEmployeeListFromCSVFile(csvFileToTest);

        List<Employee> expectedEmployeeList = new ArrayList<>();
        expectedEmployeeList.add(new Employee(1,"Mark","Green","Teacher", "3540,20"));
        expectedEmployeeList.add(new Employee(2,"Oscar","Mustache","Janitor", "13460.45"));
        assertEquals(expectedEmployeeList,employeeList);
    }

    @Test
    void shouldThrownIOExceptionWhenCSVFileNotFound() throws IOException {
        assertThrows(IOException.class, () -> employeeDataExtractor.getEmployeeListFromCSVFile(""));
    }

}