package com.jsp.emp_api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import com.jsp.emp_api.entity.Emp;
import com.jsp.emp_api.exception.DataExistEx;
import com.jsp.emp_api.exception.DataNotFoundEx;
import com.jsp.emp_api.repo.EmpRepo;

@Service
public class EmpService {

    @Autowired
    private EmpRepo empRepo;

    // CREATE
    public Emp saveEmployee(Emp emp) {
        if (empRepo.existsByPhone(emp.getPhone())) {
            throw new DataExistEx("Employee with this phone already exists");
        }
        return empRepo.save(emp);
    }

    // READ - ALL (Pagination + Sorting)
    public Page<Emp> getAllEmployees(
            int page,
            int size,
            String sortBy,
            String direction) {

        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Emp> employees = empRepo.findAll(pageable);

        if (employees.isEmpty()) {
            throw new DataNotFoundEx("No employee records found");
        }
        return employees;
    }

    // READ - BY ID
    public Emp getById(Integer id) {
        return empRepo.findById(id)
                .orElseThrow(() -> new DataNotFoundEx("Employee not found with id: " + id));
    }

    // READ - BY PHONE
    public Emp getByPhone(Long phone) {
        return empRepo.findByPhone(phone)
                .orElseThrow(() -> new DataNotFoundEx("Employee not found with phone: " + phone));
    }

    // READ - BY NAME
    public List<Emp> getByName(String name) {
        List<Emp> list = empRepo.findByName(name);
        if (list.isEmpty()) {
            throw new DataNotFoundEx("No employee found with name: " + name);
        }
        return list;
    }

    // READ - BY DEPARTMENT
    public List<Emp> getByDepartment(String department) {
        List<Emp> list = empRepo.findByDepartment(department);
        if (list.isEmpty()) {
            throw new DataNotFoundEx("No employee found in department: " + department);
        }
        return list;
    }

    // DELETE
    public void deleteEmployee(Integer id) {
        Emp emp = empRepo.findById(id)
                .orElseThrow(() -> new DataNotFoundEx("Employee not found with id: " + id));
        empRepo.delete(emp);
    }

    // PUT - Complete Update
    public Emp completeUpdate(Integer id, Emp emp) {
        Emp existing = empRepo.findById(id)
                .orElseThrow(() -> new DataNotFoundEx("Employee not found with id: " + id));
        emp.setId(existing.getId());
        return empRepo.save(emp);
    }

    // PATCH - Partial Update
    public Emp partialUpdate(Integer id, Emp emp) {
        Emp exEmp = empRepo.findById(id)
                .orElseThrow(() -> new DataNotFoundEx("Employee not found with id: " + id));

        if (emp.getName() != null)
            exEmp.setName(emp.getName());

        if (emp.getDepartment() != null)
            exEmp.setDepartment(emp.getDepartment());

        if (emp.getPhone() != null)
            exEmp.setPhone(emp.getPhone());

        if (emp.getSalary() != null)
            exEmp.setSalary(emp.getSalary());

        return empRepo.save(exEmp);
    }
}
