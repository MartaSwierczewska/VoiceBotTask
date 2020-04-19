import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class EmployeeMapperTest {
    private EmployeeMapper employeeMapper;

    @Before
    public void setUp(){
        employeeMapper = new EmployeeMapper();
    }
    @Test
    public void shouldCreateEmployee() {
        Employee expectedEmployee = new Employee(1,"Mark","Green","Teacher","3540,20");
        String[] attributes = new String[]{"      1","      \"Mark\"","      \"Green\"","      \"Teacher\"","      \"3540,20\""};
        Employee employee = employeeMapper.createEmployee(attributes);
        assertEquals(expectedEmployee,employee);
    }
}