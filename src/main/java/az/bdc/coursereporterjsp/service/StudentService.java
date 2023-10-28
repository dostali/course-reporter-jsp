package az.bdc.coursereporterjsp.service;

import az.bdc.coursereporterjsp.domain.Student;

import java.util.List;

public interface StudentService {

    List<Student> getAll();

    boolean add(Student student);

    boolean update(Student student);

    boolean deleteById(long id);

    Student getOne(long id);

}
