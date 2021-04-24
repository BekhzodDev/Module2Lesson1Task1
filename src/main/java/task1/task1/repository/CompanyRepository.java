package task1.task1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import task1.task1.entity.Address;
import task1.task1.entity.Company;

public interface CompanyRepository extends JpaRepository<Company, Integer> {
boolean existsByCorpName(String corpName);
//boolean existsByCorpNameAndAddressIdAndIdNot(String corpName, Integer address_id, Integer id);
}
