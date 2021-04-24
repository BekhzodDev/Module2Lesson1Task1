package task1.task1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import task1.task1.entity.Address;
import task1.task1.payload.Result;
import task1.task1.service.AddressService;

@RestController
@RequestMapping("/address")
public class AddressController {
    @Autowired
    AddressService addressService;

    @PostMapping
    public ResponseEntity<Result> addAddress(@RequestBody Address address){
        return ResponseEntity.status(addressService.addAddress(address).isSuccess()?201:409).body(addressService.addAddress(address));
    }
    @GetMapping
    public ResponseEntity<?> getAddresses(){
        return ResponseEntity.status(200).body(addressService.getAddresses());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getAddress(@PathVariable Integer id){
        Address address= addressService.getAddress(id);
        return ResponseEntity.status((address==null)?404:200).body(address);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Result> editAddress(@PathVariable Integer id, @RequestBody Address address){
        Result result= addressService.editAddress(id, address);
        return ResponseEntity.status(result.isSuccess()?202:401).body(result);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> gdeleteAddress(@PathVariable Integer id) {
        Result result = addressService.deleteAddress(id);
        return ResponseEntity.status(result.isSuccess() ? 202 : 401).body(result);
    }

}
