
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    private static final String JSON_FILE_PATH = "/home/marta/IdeaProjects/VoiceBotIntern/employees.json";
    private static final String CSV_FILE_PATH = "/home/marta/IdeaProjects/VoiceBotIntern/employees.csv";

    public static void main(String[] args) throws IOException {
        List<Employee> employeeListFromJSONFile = getEmployeeListFromJsonFile(JSON_FILE_PATH);
        List<Employee> employeeListFromCSVFile = getEmployeeListFromCSVFile(CSV_FILE_PATH);

        printResult(employeeListFromJSONFile);
        System.out.println("=======");
        printResult(employeeListFromCSVFile);

    }

    static void printResult(List<Employee> employeeList){
        transformSalaryFormat(employeeList);
        employeeList.stream()
                .collect(Collectors.groupingBy(Employee::getJob, Collectors.summingDouble(e -> Double.parseDouble(e.getSalary()))))
                .forEach((job, sumSalary) -> System.out.println(job + " - " + String.format("%.2f", sumSalary)));
    }

//    tu jest to fajne krotkie ladne rozwiazanie
    static List<Employee> getEmployeeListFromJsonFile(String filePath) throws IOException {
        Employees employees = new ObjectMapper().readValue(Paths.get(filePath).toFile(), new TypeReference<Employees>() {});
        return employees.getEmployees();
    }

    static void transformSalaryFormat(List<Employee> employeeList){
        for (Employee employee: employeeList) {
            employee.setSalary((employee.getSalary().replace(',', '.')));
        }
    }


//a tu to dlugie z csv XD

    static List<Employee> getEmployeeListFromCSVFile(String filePath) {
        List<Employee> employeeList = new ArrayList<>();
        try (BufferedReader br = Files.newBufferedReader(Paths.get(filePath), StandardCharsets.UTF_8)) {
//            trzeba ominac linie z naglowkiem
            String headers = br.readLine();
            String line = br.readLine();
            while (line != null) {
                String[] attributes = line.split(";");
                Employee employee = createEmployee(attributes);
                employeeList.add(employee);
                line = br.readLine();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return employeeList;
    }
    

    static Employee createEmployee(String[] metadata) {
//        tu chcialam w jakies petli te regexy bo no beznadziejnie tak w kazdej linijce ale trzebaby zrobic nowa zmienna i nwm czy nie lepije to wgl jakos inaczej zrobic
        int id = Integer.parseInt(metadata[0].replaceAll("\\s+",""));
        String name = metadata[1].replaceAll("\\s+","").replaceAll("^.|.$", "");
        String surname = metadata[2].replaceAll("\\s+","").replaceAll("^.|.$", "");
        String job = metadata[3].replaceAll("\\s+","").replaceAll("^.|.$", "");
        String salary = metadata[4].replaceAll("\\s+","").replaceAll("^.|.$", "");
        return new Employee(id,name,surname,job,salary);
    }


}
