import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class EmployeeServiceTest {

    private EmployeeService employeeService;

    @Before
    public void setUp(){
        employeeService = new EmployeeService();
    }

    @Test
    public void shouldCalculateSalariesByJobs() {
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee(1,"Mark","Green","Teacher", "3540.20"));
        employeeList.add(new Employee(2,"Oscar","Mustache","Janitor", "13460.45"));
        employeeList.add(new Employee(3,"Michael","Spear","Janitor", "10420.50"));
        Map<String, Double> expectedSalariesByJobs  = new HashMap<String, Double>() {{
            put("Teacher", 3540.20);
            put("Janitor", 23880.95);
        }};
        Map<String,Double> salariesByJobs = employeeService.calculateSalariesForJobs(employeeList);
        assertEquals(expectedSalariesByJobs,salariesByJobs);
    }
}