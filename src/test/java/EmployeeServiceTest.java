import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class EmployeeServiceTest {

    private EmployeeService employeeService;

    @BeforeEach
    public void setUp(){
        employeeService = new EmployeeService();
    }

    @Test
    public void shouldCalculateSalariesByJobs() {
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee(1,"Mark","Green","Teacher", "3540.20"));
        employeeList.add(new Employee(2,"Oscar","Mustache","Janitor", "13460.45"));
        employeeList.add(new Employee(3,"Michael","Spear","Janitor", "10420.50"));

        Map<String,Double> salariesByJobs = employeeService.calculateSalariesForJobs(employeeList);

        Map<String, Double> expectedSalariesByJobs  = new HashMap<String, Double>() {{
            put("Teacher", 3540.20);
            put("Janitor", 23880.95);
        }};
        assertEquals(expectedSalariesByJobs,salariesByJobs);
    }
}