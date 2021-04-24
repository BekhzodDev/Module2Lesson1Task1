package task1.task1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import task1.task1.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {
    boolean existsByStreetAndHomeNumber(String street, String homeNumber);

    boolean existsByStreetAndHomeNumberAndIdNot(String street, String homeNumber, Integer id);
}
