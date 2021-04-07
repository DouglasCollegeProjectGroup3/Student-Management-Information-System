package com.group3.Student.Management.Information.System.View;

import com.group3.Student.Management.Information.System.Model.AlumniStudent;
import com.group3.Student.Management.Information.System.Model.Student;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AlumniStudentServiceViewTest {

    @Autowired
    AlumniStudentServiceView alumniStudentServiceView;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getAllAlumniStudents() {
        List<AlumniStudent> alumniStudent = alumniStudentServiceView.getAllAlumniStudents();
        System.out.println(alumniStudent.size());
        assertEquals(12,alumniStudent.size());
    }

    @Test
    void addAlumniStudent() {
        AlumniStudent alumniStudent = new AlumniStudent();

        alumniStudent.setStudentID("1000212");
        alumniStudent.setFirstName("Steve");
        alumniStudent.setLastName("Singh");
        alumniStudent.setEmailID("TestAlumni@gmail.com");

        alumniStudentServiceView.addAlumniStudent(alumniStudent);
        List<AlumniStudent> alumniStudents = alumniStudentServiceView.getAllAlumniStudents();
        System.out.println(alumniStudents.size());
        assertEquals(13,alumniStudents.size());

    }

    @Test
    void getAlumniStudentByID() {
        AlumniStudent student = alumniStudentServiceView.getAlumniStudentByID(16);
        assertNotNull(student);
        assertEquals(16,student.getId());
    }

    @Test
    void deleteAlumniStudentById() {
        alumniStudentServiceView.deleteAlumniStudentById(16);
        assertThrows(RuntimeException.class, ()-> alumniStudentServiceView.getAlumniStudentByID(16));
    }
}