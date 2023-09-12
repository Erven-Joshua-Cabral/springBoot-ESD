package com.trial.emsbackend.service;

import com.trial.emsbackend.exception.StudentAlreadyExistsException;
import com.trial.emsbackend.exception.StudentNotFoundException;
import com.trial.emsbackend.model.Students;
import com.trial.emsbackend.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceClass implements StudentService{

    private final StudentRepository studentRepository;


    @Override
    public List<Students> getStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Students addStudents(Students students) {
        if(studentAlreadyExists(students.getEmail())){
            throw new StudentAlreadyExistsException(students.getEmail()+"already exists");
        }
        return studentRepository.save(students);
    }

    @Override
    public Students updateStudents(Students students, Long Id) {
        return studentRepository.findById(Id).map(st ->{
            st.setFirstName(students.getFirstName());
            st.setLastName(students.getLastName());
            st.setEmail(students.getEmail());
            st.setDepartment(students.getEmail());
            return studentRepository.save(st);
        }).orElseThrow(()-> new StudentNotFoundException("Sorry, this student could not be found"));
    }

    @Override
    public Students getStudentId(Long Id) {
        return null;
    }

    @Override
    public void deleteStudent(Long Id) {

    }
    private boolean studentAlreadyExists(String email) {
        return studentRepository.findByEmail(email).isPresent();
    }
}
