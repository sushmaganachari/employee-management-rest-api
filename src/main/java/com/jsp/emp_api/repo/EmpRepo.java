package com.jsp.emp_api.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.jsp.emp_api.entity.Emp;

public interface EmpRepo extends JpaRepository<Emp, Integer> {

    boolean existsByPhone(Long phone);

    Optional<Emp> findByPhone(Long phone);

    List<Emp> findByName(String name);

    List<Emp> findByDepartment(String department);
}
