import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    private static final String JSON_FILE_PATH = "employees.json";
    private static final String CSV_FILE_PATH = "employees.csv";

    public static void main(String[] args) throws IOException {
        DataExtractor dataExtractor = new DataExtractor();

        List<Employee> employeeListFromJSONFile = dataExtractor.getEmployeeListFromJsonFile(JSON_FILE_PATH);
        List<Employee> employeeListFromCSVFile = dataExtractor.getEmployeeListFromCSVFile(CSV_FILE_PATH);

        execute(employeeListFromJSONFile);
        System.out.println("=======");
        execute(employeeListFromCSVFile);
    }

    private static void execute(List<Employee> employeeList){
        transformSalaryFormat(employeeList);
        Map<String,Double> salariesForJobs = calculateSalariesForJobs(employeeList);
        printResult(salariesForJobs);
    }

    private static void printResult( Map<String,Double> salariesForJobs ){
        salariesForJobs.forEach((job, sumSalary) -> System.out.println(job + " - " + String.format("%.2f", sumSalary)));
    }

    private static Map<String, Double> calculateSalariesForJobs(List<Employee> employeeList){
        return employeeList.stream()
                .collect(Collectors.groupingBy(Employee::getJob, Collectors.summingDouble(e -> Double.parseDouble(e.getSalary()))));
    }

    private static void transformSalaryFormat(List<Employee> employeeList){
        for (Employee employee: employeeList) {
            employee.setSalary((employee.getSalary().replace(',', '.')));
        }
    }
}
