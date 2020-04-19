import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class EmployeeDataExtractorTest {
    private EmployeeDataExtractor employeeDataExtractor;

    @Before
    public void setUp(){
        employeeDataExtractor = new EmployeeDataExtractor();
    }

    @Test
    public void shouldReturnListOfEmployeesFromJson() throws IOException {
        String jsonFileToTest = "src/main/resources/JsonFileTest.json";
        List<Employee> employeeList = employeeDataExtractor.getEmployeeListFromJsonFile(jsonFileToTest);
        List<Employee> expectedEmployeeList = new ArrayList<>();
        expectedEmployeeList.add(new Employee(1,"Mark","Green","Teacher", "3540,20"));
        expectedEmployeeList.add(new Employee(2,"Oscar","Mustache","Janitor", "13460.45"));
        assertEquals(expectedEmployeeList,employeeList);
    }

    @Test(expected = IOException.class)
    public void shouldThrownIOExceptionWhenJsonFileNotFound() throws IOException {
        employeeDataExtractor.getEmployeeListFromJsonFile("");
    }

    @Test
    public void shouldReturnListOfEmployeesFromCSV() throws IOException {
        String csvFileToTest = "src/main/resources/CSVFileTest.csv";
        List<Employee> employeeList = employeeDataExtractor.getEmployeeListFromCSVFile(csvFileToTest);
        List<Employee> expectedEmployeeList = new ArrayList<>();
        expectedEmployeeList.add(new Employee(1,"Mark","Green","Teacher", "3540,20"));
        expectedEmployeeList.add(new Employee(2,"Oscar","Mustache","Janitor", "13460.45"));
        assertEquals(expectedEmployeeList,employeeList);
    }

    @Test(expected = IOException.class)
    public void shouldThrownIOExceptionWhenCSVFileNotFound() throws IOException {
        employeeDataExtractor.getEmployeeListFromCSVFile("");
    }

}