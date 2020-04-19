import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmployeeMapper {
    private static final Pattern pattern = Pattern.compile("\\s\"(.*?)\"");

    private String executePatternOnMetadata(String data){
        Matcher m = pattern.matcher(data);
        if(m.find()) {
            return (m.group(1));
        }
        return "";
    }

    public Employee createEmployee(String[] metadata) {
        int id = Integer.parseInt(metadata[0].trim());
        String name = executePatternOnMetadata(metadata[1]);
        String surname = executePatternOnMetadata(metadata[2]);
        String job = executePatternOnMetadata(metadata[3]);
        String salary = executePatternOnMetadata(metadata[4]);
        return new Employee(id,name,surname,job,salary);
    }
}
