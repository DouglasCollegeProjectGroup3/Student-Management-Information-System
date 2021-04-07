package com.group3.Student.Management.Information.System.View;


import com.group3.Student.Management.Information.System.Model.Student;
import org.springframework.data.domain.Page;

import java.util.List;

public interface StudentView {
    List<Student> getAllStudent();
    void addStudentRecord(Student student);
    Student getStudentByID(long id);

    void deleteStudentById(long id);
    Page<Student> findPageNumber(int pageNo, int pageSize, String sortField, String sortDirection);
}
