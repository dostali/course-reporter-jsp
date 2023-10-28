package az.bdc.coursereporterjsp.service.impl;

import az.bdc.coursereporterjsp.constant.SqlCommands;
import az.bdc.coursereporterjsp.domain.Student;
import az.bdc.coursereporterjsp.service.DatabaseConnection;
import az.bdc.coursereporterjsp.service.StudentService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class StudentServiceImpl extends DatabaseConnection implements StudentService {

    @Override
    public List<Student> getAll() {
        try (Connection connection = connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SqlCommands.Student.SELECT_ALL);
            preparedStatement.execute();
            List<Student> studentList = new ArrayList<>();
            ResultSet resultSet = preparedStatement.getResultSet();
            while (resultSet.next()) {
                Student student = new Student();
                student.setId(resultSet.getLong("id"));
                student.setName(resultSet.getString("name"));
                student.setSurname(resultSet.getString("surname"));
                student.setFullName();
                student.setBirthDate(resultSet.getDate("birthdate"));
                student.setPhoneNumber(resultSet.getString("phone_number"));
                student.setPinCode(resultSet.getString("pincode"));
                student.setCreateDate(resultSet.getObject("create_date", LocalDateTime.class));
                student.setUpdateDate(resultSet.getObject("update_date", LocalDateTime.class));
                studentList.add(student);
            }
            return studentList;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public boolean add(Student student) {
        try (Connection connection = connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SqlCommands.Student.INSERT_INTO);
            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getSurname());
            preparedStatement.setString(3, student.getFullName());
            preparedStatement.setObject(4, student.getBirthDate());
            preparedStatement.setString(5, student.getPhoneNumber());
            preparedStatement.setString(6, student.getPinCode());
            preparedStatement.setObject(7, student.getCreateDate());
            preparedStatement.setObject(8, student.getUpdateDate());
            return preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public boolean update(Student student) {
        try (Connection connection = connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SqlCommands.Student.UPDATE_SET);
            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getSurname());
            preparedStatement.setString(3, student.getFullName());
            preparedStatement.setObject(4, student.getBirthDate());
            preparedStatement.setString(5, student.getPhoneNumber());
            preparedStatement.setString(6, student.getPinCode());
            preparedStatement.setObject(7, student.getUpdateDate());
            preparedStatement.setLong(8, student.getId());
            return preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public boolean deleteById(long id) {
        try (Connection connection = connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SqlCommands.Student.DELETE_BY_ID);
            preparedStatement.setLong(1, id);
            return preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Student getOne(long id) {
        try (Connection connection = connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SqlCommands.Student.GET_BY_ID);
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            resultSet.next();
            Student student = new Student();
            student.setId(resultSet.getLong("id"));
            student.setName(resultSet.getString("name"));
            student.setSurname(resultSet.getString("surname"));
            student.setFullName();
            student.setBirthDate(resultSet.getDate("birthdate"));
            student.setPhoneNumber(resultSet.getString("phone_number"));
            student.setPinCode(resultSet.getString("pincode"));
            student.setCreateDate(resultSet.getObject("create_date", LocalDateTime.class));
            student.setUpdateDate(resultSet.getObject("update_date", LocalDateTime.class));
            return student;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }
}
