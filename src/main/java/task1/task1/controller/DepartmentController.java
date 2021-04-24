package task1.task1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import task1.task1.entity.Department;
import task1.task1.payload.DepartmentDto;
import task1.task1.payload.Result;
import task1.task1.service.DepartmentService;

import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    @Autowired
    DepartmentService departmentService;

    @PostMapping
    public ResponseEntity<?> addDepartment(@RequestBody DepartmentDto departmentDto) {
        Result result = departmentService.addDepartment(departmentDto);
        return ResponseEntity.status(result.isSuccess() ? 201 : 409).body(result);
    }

    @GetMapping
    public ResponseEntity<?> getCompanies() {
        List<Department> companies = departmentService.getDepartments();
        return ResponseEntity.status(200).body(companies);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDepartment(@PathVariable Integer id) {
        Department department = departmentService.getDepartment(id);
        return ResponseEntity.status((department == null) ? 404 : 200).body(department);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editDepartment(@PathVariable Integer id, @RequestBody DepartmentDto departmentDto) {
        Result result = departmentService.editDepartment(id, departmentDto);
        return ResponseEntity.status(result.isSuccess() ? 202 : 401).body(result);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDepartment(@PathVariable Integer id) {
        Result result = departmentService.deleteDepartment(id);
        return ResponseEntity.status(result.isSuccess() ? 202 : 401).body(result);
    }
}
