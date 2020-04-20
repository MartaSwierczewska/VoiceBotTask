import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.gen5.api.Assertions.assertEquals;

public class EmployeeMapperTest {
    private EmployeeMapper employeeMapper;

    @BeforeEach
    public void setUp(){
        employeeMapper = new EmployeeMapper();
    }
    @Test
    public void shouldCreateEmployee() {
        String[] attributes = new String[]{"      1","      \"Mark\"","      \"Green\"","      \"Teacher\"","      \"3540,20\""};
        
        Employee employee = employeeMapper.createEmployee(attributes);

        Employee expectedEmployee = new Employee(1,"Mark","Green","Teacher","3540,20");
        assertEquals(expectedEmployee,employee);
    }
}