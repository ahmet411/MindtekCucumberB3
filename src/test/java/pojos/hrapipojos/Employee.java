package pojos.hrapipojos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Employee {

    private Department department;
    private Integer employeeId;
    private String firstName;
    private Job job;
    private String lastName;

}
