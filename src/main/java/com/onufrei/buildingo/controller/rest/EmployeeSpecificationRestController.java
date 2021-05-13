package com.onufrei.buildingo.controller.rest;

import com.onufrei.buildingo.model.EmployeeSpecification;
import com.onufrei.buildingo.service.employeeSpecification.interfaces.EmployeeSpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Represents object of EmployeeSpecificationRestController
 *
 * @author Artem Onufrei
 * @version 1
 * @since 11.05.2021
 */

@RequestMapping("api/v1/employee-specs")
@RestController
public class EmployeeSpecificationRestController {

    EmployeeSpecificationService service;

    private EmployeeSpecificationRestController(@Autowired EmployeeSpecificationService service) {
        this.service = service;
    }

    @GetMapping("/")
    private List<EmployeeSpecification> getAll() {
        return service.findAll();
    }

    @PostMapping("/")
    private EmployeeSpecification add(@RequestBody EmployeeSpecification spec) {
        return service.add(spec);
    }

    @GetMapping("/{id}")
    private EmployeeSpecification getById(@PathVariable String id) {
        return service.findById(id);
    }

    @PutMapping("/")
    private EmployeeSpecification update(@RequestBody EmployeeSpecification spec) {
        return service.update(spec);
    }

    @DeleteMapping("/{id}")
    private EmployeeSpecification delete(@PathVariable String id) {
        return service.delete(id);
    }

}
