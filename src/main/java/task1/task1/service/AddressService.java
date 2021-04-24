package task1.task1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import task1.task1.entity.Address;
import task1.task1.payload.Result;
import task1.task1.repository.AddressRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {
    @Autowired
    AddressRepository addressRepository;

    public Result addAddress(Address address) {
        if (addressRepository.existsByStreetAndHomeNumber(address.getStreet(), address.getHomeNumber()))
            return new Result("Bunday address uje mavjud", false);
        addressRepository.save(address);
        return new Result("Yangi address saqlandi", true);
    }

    public List<Address> getAddresses() {
        return addressRepository.findAll();
    }

    public Address getAddress(Integer id) {
        Optional<Address> optionalAddress = addressRepository.findById(id);
        return optionalAddress.orElse(null);
    }

    public Result editAddress(Integer id, Address address) {
        Optional<Address> optionalAddress = addressRepository.findById(id);
        if (!optionalAddress.isPresent())
            return new Result("Bunday address mavjud emas", false);

        if (addressRepository.existsByStreetAndHomeNumberAndIdNot(address.getStreet(), address.getHomeNumber(), id))
            return new Result("Bunday address uje mavjud", false);
        Address address1 = optionalAddress.get();
        address1.setStreet(address.getStreet());
        address1.setHomeNumber(address.getHomeNumber());
        addressRepository.save(address1);
        return new Result("Address taxrirlandi", true);
    }

    public Result deleteAddress(Integer id) {
        Optional<Address> optionalAddress = addressRepository.findById(id);
        if (!optionalAddress.isPresent())
            return new Result("Bunday address mavjud emas", false);
        addressRepository.deleteById(id);
        return new Result("Address o'chirildi", true);

    }
}
