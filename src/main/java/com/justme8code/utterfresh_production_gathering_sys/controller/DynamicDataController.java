package com.justme8code.utterfresh_production_gathering_sys.controller;

import com.justme8code.utterfresh_production_gathering_sys.services.interfaces.DynamicDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/dynamic")
public class DynamicDataController {


    private final DynamicDataService service;

    public DynamicDataController(DynamicDataService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public Long createData(@RequestParam String name, @RequestBody Map<String, Object> data) throws Exception {
        return service.createData(name, data);
    }

    @GetMapping("/{id}")
    public Map<String, Object> getDataById(@PathVariable Long id) throws Exception {
        return service.findById(id);
    }

    @GetMapping("/name/{name}")
    public Map<String, Object> getDataByName(@PathVariable String name) throws Exception {
        return service.findByName(name);
    }

    @PutMapping("/{id}")
    public void updateData(@PathVariable Long id, @RequestBody Map<String, Object> data) throws Exception {
        service.updateData(id, data);
    }

    @DeleteMapping("/{id}")
    public void deleteData(@PathVariable Long id) {
        service.deleteData(id);
    }
}
