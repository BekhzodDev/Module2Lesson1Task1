package task1.task1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import task1.task1.entity.Worker;
import task1.task1.payload.WorkerDto;
import task1.task1.payload.Result;
import task1.task1.service.WorkerService;

import java.util.List;

@RestController
@RequestMapping("/worker")
public class WorkerController {
    @Autowired
    WorkerService workerService;

    @PostMapping
    public ResponseEntity<?> addWorker(@RequestBody WorkerDto workerDto) {
        Result result = workerService.addWorker(workerDto);
        return ResponseEntity.status(result.isSuccess() ? 201 : 409).body(result);
    }

    @GetMapping
    public ResponseEntity<?> getCompanies() {
        List<Worker> companies = workerService.getWorkers();
        return ResponseEntity.status(200).body(companies);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getWorker(@PathVariable Integer id) {
        Worker worker = workerService.getWorker(id);
        return ResponseEntity.status((worker == null) ? 404 : 200).body(worker);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editWorker(@PathVariable Integer id, @RequestBody WorkerDto workerDto) {
        Result result = workerService.editWorker(id, workerDto);
        return ResponseEntity.status(result.isSuccess() ? 202 : 401).body(result);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteWorker(@PathVariable Integer id) {
        Result result = workerService.deleteWorker(id);
        return ResponseEntity.status(result.isSuccess() ? 202 : 401).body(result);
    }
}
