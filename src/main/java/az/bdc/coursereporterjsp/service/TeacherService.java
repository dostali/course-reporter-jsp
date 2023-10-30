package az.bdc.coursereporterjsp.service;

import az.bdc.coursereporterjsp.domain.Student;
import az.bdc.coursereporterjsp.domain.Teacher;

import java.util.List;

public interface TeacherService {

        List<Teacher> getAll();

        boolean add(Teacher teacher);

        boolean update(Teacher  teacher);

        boolean deleteById(long id);

        Teacher getOne(long id);

    }

