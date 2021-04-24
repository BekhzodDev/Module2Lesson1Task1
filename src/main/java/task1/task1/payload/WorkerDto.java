package task1.task1.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkerDto {
    private String name;
    private String phoneNumber;
    private Integer addressId;
    private Integer departmentId;
}
