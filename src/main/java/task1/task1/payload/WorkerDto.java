package task1.task1.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import task1.task1.entity.Address;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkerDto {
    private String name;
    private String phoneNumber;
    private Integer departmentId;
    private String street;
    private String homeNumber;
}
