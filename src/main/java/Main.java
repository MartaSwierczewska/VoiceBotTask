import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    private static final String JSON_FILE_PATH = "employees.json";
    private static final String CSV_FILE_PATH = "employees.csv";

    private static EmployeeService employeeService = new EmployeeService();

    public static void main(String[] args) throws IOException {
        EmployeeDataExtractor employeeDataExtractor = new EmployeeDataExtractor();

        List<Employee> employeeListFromJSONFile = employeeDataExtractor.getEmployeeListFromJsonFile(JSON_FILE_PATH);
        List<Employee> employeeListFromCSVFile = employeeDataExtractor.getEmployeeListFromCSVFile(CSV_FILE_PATH);

        execute(employeeListFromJSONFile);
        System.out.println("=======");
        execute(employeeListFromCSVFile);
    }

    private static void execute(List<Employee> employeeList){
        transformSalaryFormat(employeeList);
        Map<String,Double> salariesForJobs = employeeService.calculateSalariesForJobs(employeeList);
        printResult(salariesForJobs);
    }

    private static void printResult( Map<String,Double> salariesForJobs ){
        salariesForJobs.forEach((job, sumSalary) -> System.out.println(job + " - " + String.format("%.2f", sumSalary)));
    }

    private static void transformSalaryFormat(List<Employee> employeeList){
        for (Employee employee: employeeList) {
            employee.setSalary((employee.getSalary().replace(',', '.')));
        }
    }
}
