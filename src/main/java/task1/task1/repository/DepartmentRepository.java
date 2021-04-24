package task1.task1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import task1.task1.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {
boolean existsByNameAndCompanyId(String name, Integer company_id);
}
