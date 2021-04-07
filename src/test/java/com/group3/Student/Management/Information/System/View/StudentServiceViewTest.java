package com.group3.Student.Management.Information.System.View;

import com.group3.Student.Management.Information.System.Model.Student;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StudentServiceViewTest {
    //  Bring service class in here
    @Autowired
    StudentServiceView studentServiceView;

    @Test
    void testStudentSizeIs16() {
       List<Student> students = studentServiceView.getAllStudent();
        System.out.println(students.size());
       assertEquals(16, students.size());
    }

    @Test
    void testStudentSizeIs17AfterAdd() {
        Student student = new Student();
        student.setStudentID("111222");
        student.setFirstName("kkll");
        student.setLastName("gill");
        student.setEmailID("kkgill@gmail.com");
        studentServiceView.addStudentRecord(student);

        List<Student> students = studentServiceView.getAllStudent();
        System.out.println(students.size());
        assertEquals(17, students.size());
    }

    @Test
    void testStudentIdExist21() {
       Student student =  studentServiceView.getStudentByID(21);
       assertNotNull(student);
       assertEquals(21,student.getId());
    }

    @Test
    void testStudentId20DoesNotExistAfterDelete() {
        studentServiceView.deleteStudentById(21);
        assertThrows(RuntimeException.class, ()-> studentServiceView.getStudentByID(21));
    }

}