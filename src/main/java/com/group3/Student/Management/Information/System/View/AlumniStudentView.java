package com.group3.Student.Management.Information.System.View;

import com.group3.Student.Management.Information.System.Model.AlumniStudent;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AlumniStudentView {
    List<AlumniStudent> getAllAlumniStudents();
    void addAlumniStudent(AlumniStudent alumniStudent);

    AlumniStudent getAlumniStudentByID(long id);

    void deleteAlumniStudentById(long id);

    Page<AlumniStudent> findPageNumberForAlumniStudent(int pageNo, int pageSize, String sortField, String sortDirection);

}
