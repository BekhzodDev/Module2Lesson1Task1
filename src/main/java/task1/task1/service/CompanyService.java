package task1.task1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import task1.task1.entity.Address;
import task1.task1.entity.Company;
import task1.task1.payload.CompanyDto;
import task1.task1.payload.Result;
import task1.task1.repository.AddressRepository;
import task1.task1.repository.CompanyRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {
    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    AddressRepository addressRepository;

    public Result addCompany(CompanyDto companyDto) {
        if (companyRepository.existsByCorpName(companyDto.getCorpName()))
            return new Result("Bunday nom band", false);
        Optional<Address> optionalAddress = addressRepository.findById(companyDto.getAddressId());
        if (!optionalAddress.isPresent())
            return new Result("Bunday address mavjud emas", false);
        Company company = new Company();
        company.setCorpName(companyDto.getCorpName());
        company.setDirectorName(companyDto.getDirectorName());
        company.setAddress(optionalAddress.get());
        companyRepository.save(company);
        return new Result("Yangi kompaniya saqlandi", true);
    }

    public List<Company> getCompanies() {
        return companyRepository.findAll();
    }

    public Company getCompany(Integer id) {
        Optional<Company> optionalCompany = companyRepository.findById(id);
        return optionalCompany.orElse(null);
    }

    public Result editCompany(Integer id, CompanyDto companyDto) {
        Optional<Company> optionalCompany = companyRepository.findById(id);
        if (!optionalCompany.isPresent())
            return new Result("Bunday company mavjud emas", false);
        if (companyRepository.existsByCorpName(companyDto.getCorpName()))
            return new Result("Bunday nom band", false);
        Optional<Address> optionalAddress = addressRepository.findById(companyDto.getAddressId());
        if (!optionalAddress.isPresent())
            return new Result("Bunday address mavjud emas", false);
        Company company = optionalCompany.get();
        company.setCorpName(companyDto.getCorpName());
        company.setDirectorName(companyDto.getDirectorName());
        company.setAddress(optionalAddress.get());
        companyRepository.save(company);
        return new Result("Kimpaniya taxrirlandi", true);
    }

    public Result deleteCompany(Integer id) {
        Optional<Company> optionalCompany = companyRepository.findById(id);
        if (!optionalCompany.isPresent())
            return new Result("Bunday company mavjud emas", false);
        companyRepository.deleteById(id);
        return new Result("Kimpaniya o'chirildi", true);
    }
}
