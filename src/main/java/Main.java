import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Main {

    private static final String JSON_FILE_PATH = "employees.json";
    private static final String CSV_FILE_PATH = "employees.csv";


    public static void main(String[] args) throws IOException {
        DataExtractor dataExtractor = new DataExtractor();

        List<Employee> employeeListFromJSONFile = dataExtractor.getEmployeeListFromJsonFile(JSON_FILE_PATH);
        List<Employee> employeeListFromCSVFile = dataExtractor.getEmployeeListFromCSVFile(CSV_FILE_PATH);

        transformSalaryFormat(employeeListFromJSONFile);
        transformSalaryFormat(employeeListFromCSVFile);

        Map<String,Double> salariesForJobsJSON = calculateSalariesForJobs(employeeListFromJSONFile);
        Map<String,Double> salariesForJobsCSV = calculateSalariesForJobs(employeeListFromCSVFile);

        printResult(salariesForJobsJSON);
        System.out.println("=======");
        printResult(salariesForJobsCSV);

    }

    static void printResult( Map<String,Double> salariesForJobs ){
        salariesForJobs.forEach((job, sumSalary) -> System.out.println(job + " - " + String.format("%.2f", sumSalary)));
    }

    static Map<String, Double> calculateSalariesForJobs(List<Employee> employeeList){
        return employeeList.stream()
                .collect(Collectors.groupingBy(Employee::getJob, Collectors.summingDouble(e -> Double.parseDouble(e.getSalary()))));
    }

    static void transformSalaryFormat(List<Employee> employeeList){
        for (Employee employee: employeeList) {
            employee.setSalary((employee.getSalary().replace(',', '.')));
        }
    }
}
