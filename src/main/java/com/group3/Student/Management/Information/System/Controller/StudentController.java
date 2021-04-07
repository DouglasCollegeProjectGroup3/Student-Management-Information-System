package com.group3.Student.Management.Information.System.Controller;


import com.group3.Student.Management.Information.System.Model.AlumniStudent;
import com.group3.Student.Management.Information.System.Model.Student;
import com.group3.Student.Management.Information.System.View.AlumniStudentServiceView;
import com.group3.Student.Management.Information.System.View.StudentServiceView;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.PushBuilder;
import java.util.List;


@Controller
public class StudentController {
    @Autowired
    private StudentServiceView studentServiceView;
    @Autowired
    private AlumniStudentServiceView alumniStudentServiceView;

    //Display list of students
    @GetMapping("/")
    public String viewHomePage(Model model){
        return viewPage(1,"firstName","asc",model);
    }

    @GetMapping("/showNewStudent")
    public String showNewStudent(Model model){
        Student student = new Student();
        model.addAttribute("student",student);
        return "new_student";
    }

    @GetMapping("/alumniStudent")
    public String alumniStudent(Model model){
        //model.addAttribute("alumniStudent",alumniStudentServiceView.getAllAlumniStudents());
        //return  "alumniStudent";
        return viewPageAlumniStudent(1,"firstName","asc",model);
    }

    @PostMapping("/saveStudent")
    public String saveStudent(@ModelAttribute("student") Student student){
        studentServiceView.addStudentRecord(student);
        return "redirect:/";
    }

    @GetMapping("/addAlumniStudentData")
    public String addAlumniStudentData(Model model){
        AlumniStudent alumniStudent= new AlumniStudent();
        model.addAttribute("alumniStudent",alumniStudent);
        return "add_alumniStudent_data";
    }

    @PostMapping("/saveAlumniStudent")
    public String saveAlumniStudent(@ModelAttribute("alumniStudent") AlumniStudent alumniStudent){
        alumniStudentServiceView.addAlumniStudent(alumniStudent);
        return "redirect:/alumniStudent";

    }

    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable (value = "id")long id, Model model){
        Student student = studentServiceView.getStudentByID(id);
        model.addAttribute("student",student);
        return "update_student_Information";
    }

    @GetMapping("/showFormForUpdateAlumniStudent/{id}")
    public String showFormForAlumniStudentUpdate(@PathVariable (value = "id")long id, Model model){
        AlumniStudent alumniStudent = alumniStudentServiceView.getAlumniStudentByID(id);
        model.addAttribute("alumniStudent",alumniStudent);
        return "update_alumniStudent_Information";
    }

    @GetMapping("/deleteStudentRecord/{id}")
    public String deleteStudentRecord(@PathVariable (value = "id")long id){
        this.studentServiceView.deleteStudentById(id);
        return "redirect:/";
    }

    @GetMapping("/deleteAlumniStudentRecord/{id}")
    public String deleteAlumniStudent(@PathVariable (value = "id")long id){
        this.alumniStudentServiceView.deleteAlumniStudentById(id);
        return "redirect:/alumniStudent";
    }

    @GetMapping("/page/{pageNo}")
    public String viewPage(@PathVariable (value = "pageNo") int pageNo,
                           @RequestParam("sortField") String sortField,
                           @RequestParam("sortDir") String sortDir,
                           Model model){
        int pageSize = 8;

        Page<Student> page = studentServiceView.findPageNumber(pageNo,pageSize,sortField,sortDir);
        List<Student> studentList = page.getContent();

        model.addAttribute("currentPage",pageNo);
        model.addAttribute("totalPages",page.getTotalPages());
        model.addAttribute("totalItems",page.getTotalElements());

        model.addAttribute("sortField",sortField);
        model.addAttribute("sortDir",sortDir);
        model.addAttribute("reverseSortDir",sortDir.equals("asc")?"desc":"asc");

        model.addAttribute("listStudents",studentList);

        return "index";
    }

    @GetMapping("/pageAlumni/{pageNo}")
    public String viewPageAlumniStudent(@PathVariable (value = "pageNo") int pageNo,
                                        @RequestParam("sortField") String sortField,
                                        @RequestParam("sortDir") String sortDir,
                                        Model model){

        int pageSize = 8;
        Page<AlumniStudent> page = alumniStudentServiceView.findPageNumberForAlumniStudent(pageNo,pageSize,sortField,sortDir);
        List<AlumniStudent> alumniStudentList = page.getContent();

        model.addAttribute("currentPage",pageNo);
        model.addAttribute("totalPages",page.getTotalPages());
        model.addAttribute("totalItems",page.getTotalElements());
        model.addAttribute("sortField",sortField);
        model.addAttribute("sortDir",sortDir);
        model.addAttribute("reverseSortDir",sortDir.equals("asc")?"desc":"asc");
        model.addAttribute("alumniStudent",alumniStudentList);
        return "alumniStudent";
    }
}
