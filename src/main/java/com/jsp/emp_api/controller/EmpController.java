package com.jsp.emp_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.jsp.emp_api.entity.Emp;
import com.jsp.emp_api.service.EmpService;

@RestController
@RequestMapping("/api/v1/emp")
public class EmpController {

    @Autowired
    private EmpService service;

    // CREATE
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Emp createEmployee(@RequestBody Emp emp) {
        return service.saveEmployee(emp);
    }

    // READ - ALL (Pagination + Sorting)
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<Emp> getAllEmployees(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {

        return service.getAllEmployees(page, size, sortBy, direction);
    }

    // READ - BY ID
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Emp getById(@PathVariable Integer id) {
        return service.getById(id);
    }

    // READ - BY PHONE
    @GetMapping("/phone/{phone}")
    @ResponseStatus(HttpStatus.OK)
    public Emp getByPhone(@PathVariable Long phone) {
        return service.getByPhone(phone);
    }

    // READ - BY NAME
    @GetMapping("/name/{name}")
    @ResponseStatus(HttpStatus.OK)
    public List<Emp> getByName(@PathVariable String name) {
        return service.getByName(name);
    }

    // READ - BY DEPARTMENT
    @GetMapping("/department/{department}")
    @ResponseStatus(HttpStatus.OK)
    public List<Emp> getByDepartment(@PathVariable String department) {
        return service.getByDepartment(department);
    }

    // DELETE
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployee(@PathVariable Integer id) {
        service.deleteEmployee(id);
    }

    // PUT
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Emp completeUpdate(
            @PathVariable Integer id,
            @RequestBody Emp emp) {
        return service.completeUpdate(id, emp);
    }

    // PATCH
    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Emp partialUpdate(
            @PathVariable Integer id,
            @RequestBody Emp emp) {
        return service.partialUpdate(id, emp);
    }
}
