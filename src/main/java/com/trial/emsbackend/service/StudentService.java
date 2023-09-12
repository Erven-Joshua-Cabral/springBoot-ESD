package com.trial.emsbackend.service;


import com.trial.emsbackend.model.Students;

import java.util.List;

public interface StudentService {
     Students addStudents(Students students);
     List<Students> getStudents();
     Students updateStudents(Students students, Long Id);
     Students getStudentId(Long Id);

     void deleteStudent(Long Id);
}
