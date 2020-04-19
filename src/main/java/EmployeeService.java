import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EmployeeService {
    public Map<String, Double> calculateSalariesForJobs(List<Employee> employeeList){
        return employeeList.stream()
                .collect(Collectors.groupingBy(Employee::getJob, Collectors.summingDouble(e -> Double.parseDouble(e.getSalary()))));
    }
}
