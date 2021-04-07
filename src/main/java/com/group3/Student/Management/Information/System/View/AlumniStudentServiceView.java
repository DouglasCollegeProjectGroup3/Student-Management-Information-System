package com.group3.Student.Management.Information.System.View;

import com.group3.Student.Management.Information.System.Model.AlumniStudent;
import com.group3.Student.Management.Information.System.Repository.AlumniStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlumniStudentServiceView implements AlumniStudentView{

    @Autowired
    private AlumniStudentRepository alumniStudentRepository;

    @Override
    public List<AlumniStudent> getAllAlumniStudents() {
        return alumniStudentRepository.findAll();
    }

    @Override
    public void addAlumniStudent(AlumniStudent alumniStudent) {
        this.alumniStudentRepository.save(alumniStudent);
    }

    @Override
    public AlumniStudent getAlumniStudentByID(long id) {
        Optional<AlumniStudent> optional = alumniStudentRepository.findById(id);
        AlumniStudent alumniStudent = null;
        if(optional.isPresent()){
            alumniStudent = optional.get();
        }else {
            throw new RuntimeException("Student Record not found."+ id);
        }
        return alumniStudent;
    }

    @Override
    public void deleteAlumniStudentById(long id) {
        this.alumniStudentRepository.deleteById(id);
    }

    @Override
    public Page<AlumniStudent> findPageNumberForAlumniStudent(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name())? Sort.by(sortField).ascending():
                Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNo-1,pageSize,sort);
        return this.alumniStudentRepository.findAll(pageable);
    }
}
