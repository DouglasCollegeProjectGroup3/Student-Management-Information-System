package com.group3.Student.Management.Information.System.Repository;

import com.group3.Student.Management.Information.System.Model.AlumniStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlumniStudentRepository extends JpaRepository<AlumniStudent, Long> {
}
