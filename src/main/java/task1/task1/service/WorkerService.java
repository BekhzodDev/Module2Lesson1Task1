package task1.task1.service;

import com.sun.org.apache.xml.internal.security.signature.reference.ReferenceSubTreeData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import task1.task1.entity.Address;
import task1.task1.entity.Department;
import task1.task1.entity.Worker;
import task1.task1.payload.Result;
import task1.task1.payload.WorkerDto;
import task1.task1.repository.AddressRepository;
import task1.task1.repository.DepartmentRepository;
import task1.task1.repository.WorkerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class WorkerService {
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    WorkerRepository workerRepository;

    public Result addWorker(WorkerDto workerDto) {
        Optional<Department> optionalDepartment = departmentRepository.findById(workerDto.getDepartmentId());
        if (!optionalDepartment.isPresent())
            return new Result("Bunday departament bazada mavjud emas", false);
        if (workerRepository.existsByPhoneNumber(workerDto.getPhoneNumber()))
            return new Result("Bunday telefon raqamli ishchi bazada mavjud", false);
        if (addressRepository.existsByStreetAndHomeNumber(workerDto.getStreet(), workerDto.getHomeNumber()))
            return new Result("Bunday address band", false);
        Address address = new Address();
        address.setStreet(workerDto.getStreet());
        address.setHomeNumber(workerDto.getHomeNumber());
        Address savedAddress = addressRepository.save(address);
        Worker worker = new Worker();
        worker.setName(workerDto.getName());
        worker.setPhoneNumber(workerDto.getPhoneNumber());
        worker.setDepartment(optionalDepartment.get());
        worker.setAddress(savedAddress);
        workerRepository.save(worker);
        return new Result("Yangi ishchi saqlandi", true);
    }

    public List<Worker> getWorkers() {
        return workerRepository.findAll();
    }

    public Worker getWorker(Integer id) {
        Optional<Worker> optionalWorker = workerRepository.findById(id);
        return optionalWorker.orElse(null);
    }

    public Result editWorker(Integer id, WorkerDto workerDto) {
        Optional<Worker> optionalWorker = workerRepository.findById(id);
        if (!optionalWorker.isPresent())
            return new Result("Bunday ishchi bazada mavjud emas", false);
        Optional<Department> optionalDepartment = departmentRepository.findById(workerDto.getDepartmentId());
        if (!optionalDepartment.isPresent())
            return new Result("Bunday departament bazada mavjud emas", false);
        if (workerRepository.existsByPhoneNumberAndIdNot(workerDto.getPhoneNumber(), id))
            return new Result("Bunday telefon raqamli ishchi bazada mavjud", false);
        Worker worker = optionalWorker.get();
        Address address= worker.getAddress();
        if (addressRepository.existsByStreetAndHomeNumberAndIdNot(workerDto.getStreet(), workerDto.getHomeNumber(), address.getId()))
            return new Result("Bunday address band", false);
        address.setStreet(workerDto.getStreet());
        address.setHomeNumber(workerDto.getHomeNumber());
        addressRepository.save(address);
        worker.setName(workerDto.getName());
        worker.setPhoneNumber(workerDto.getPhoneNumber());
        worker.setDepartment(optionalDepartment.get());
        worker.setAddress(address);
        workerRepository.save(worker);
        return new Result("Yangi ishchi saqlandi", true);
    }

    public Result deleteWorker(Integer id) {
        Optional<Worker> optionalWorker = workerRepository.findById(id);
        if (!optionalWorker.isPresent())
            return new Result("Bunday ishchi bazada mavjud emas", false);
        workerRepository.deleteById(id);
        return new Result("Ishchi o'chirildi", true);
    }

}
