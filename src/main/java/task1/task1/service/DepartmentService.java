package task1.task1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import task1.task1.entity.Company;
import task1.task1.entity.Department;
import task1.task1.payload.DepartmentDto;
import task1.task1.payload.Result;
import task1.task1.repository.CompanyRepository;
import task1.task1.repository.DepartmentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {
    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    DepartmentRepository departmentRepository;

    public Result addDepartment(DepartmentDto departmentDto) {
        if (departmentRepository.existsByNameAndCompanyId(departmentDto.getName(), departmentDto.getCompanyId()))
            return new Result("Ushbu kompaniyada bu departament uje mavjud", false);
        Optional<Company> optionalCompany = companyRepository.findById(departmentDto.getCompanyId());
        if (!optionalCompany.isPresent())
            return new Result("Bunday kompaniya bazada mavjud emas", false);
        Department department = new Department();
        department.setName(departmentDto.getName());
        department.setCompany(optionalCompany.get());
        departmentRepository.save(department);
        return new Result("Yangi departament saqlandi", true);
    }

    public List<Department> getDepartments() {
        return departmentRepository.findAll();
    }

    public Department getDepartment(Integer id) {
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        return optionalDepartment.orElse(null);
    }

    public Result editDepartment(Integer id, DepartmentDto departmentDto) {
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        if (!optionalDepartment.isPresent())
            return new Result("Bunday departament bazada mavjud emas", false);
        if (departmentRepository.existsByNameAndCompanyId(departmentDto.getName(), departmentDto.getCompanyId()))
            return new Result("Ushbu kompaniyada bu departament uje mavjud", false);
        Optional<Company> optionalCompany = companyRepository.findById(departmentDto.getCompanyId());
        if (!optionalCompany.isPresent())
            return new Result("Bunday kompaniya bazada mavjud emas", false);
        Department department = optionalDepartment.get();
        department.setName(departmentDto.getName());
        department.setCompany(optionalCompany.get());
        departmentRepository.save(department);
        return new Result("Departament taxrirlandi", true);
    }

    public Result deleteDepartment(Integer id) {
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        if (!optionalDepartment.isPresent())
            return new Result("Bunday departament bazada mavjud emas", false);
        departmentRepository.deleteById(id);
        return new Result("Departament o'chirildi", true);
    }

}
