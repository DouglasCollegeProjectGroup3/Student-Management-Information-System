package com.group3.Student.Management.Information.System.Repository;


import com.group3.Student.Management.Information.System.Model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

}
