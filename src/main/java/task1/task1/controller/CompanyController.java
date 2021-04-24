package task1.task1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import task1.task1.entity.Company;
import task1.task1.payload.CompanyDto;
import task1.task1.payload.Result;
import task1.task1.service.CompanyService;

import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyController {
    @Autowired
    CompanyService companyService;

    @PostMapping
    public ResponseEntity<?> addCompany(@RequestBody CompanyDto companyDto) {
        Result result = companyService.addCompany(companyDto);
        return ResponseEntity.status(result.isSuccess() ? 201 : 409).body(result);
    }

    @GetMapping
    public ResponseEntity<?> getCompanies() {
        List<Company> companies = companyService.getCompanies();
        return ResponseEntity.status(200).body(companies);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCompany(@PathVariable Integer id) {
        Company company = companyService.getCompany(id);
        return ResponseEntity.status((company == null) ? 404 : 200).body(company);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editCompany(@PathVariable Integer id, @RequestBody CompanyDto companyDto) {
        Result result = companyService.editCompany(id, companyDto);
        return ResponseEntity.status(result.isSuccess() ? 202 : 401).body(result);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCompany(@PathVariable Integer id) {
        Result result = companyService.deleteCompany(id);
        return ResponseEntity.status(result.isSuccess() ? 202 : 401).body(result);
    }


}
