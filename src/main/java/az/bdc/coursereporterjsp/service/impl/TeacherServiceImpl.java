package az.bdc.coursereporterjsp.service.impl;

import az.bdc.coursereporterjsp.constant.SqlCommands;
import az.bdc.coursereporterjsp.domain.Teacher;
import az.bdc.coursereporterjsp.service.DatabaseConnection;
import az.bdc.coursereporterjsp.service.TeacherService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TeacherServiceImpl extends DatabaseConnection implements TeacherService {

    @Override
    public List<Teacher> getAll() {
        try (Connection connection = connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SqlCommands.Teacher.SELECT_ALL);
            preparedStatement.execute();
            List<Teacher> teacherList = new ArrayList<>();
            ResultSet resultSet = preparedStatement.getResultSet();
            while (resultSet.next()) {
                Teacher teacher = new Teacher();
                teacher.setId(resultSet.getLong("id"));
                teacher.setName(resultSet.getString("name"));
                teacher.setSurname(resultSet.getString("surname"));
                teacher.setFullName();
                teacher.setBirthDate(resultSet.getDate("birthdate"));
                teacher.setPhoneNumber(resultSet.getString("phone_number"));
                teacher.setCreateDate(resultSet.getObject("create_date", LocalDateTime.class));
                teacher.setUpdateDate(resultSet.getObject("update_date", LocalDateTime.class));
                teacherList.add(teacher);
            }
            return teacherList;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public boolean add(Teacher teacher) {
        try (Connection connection = connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SqlCommands.Teacher.INSERT_INTO);
            preparedStatement.setString(1, teacher.getName());
            preparedStatement.setString(2, teacher.getSurname());
            preparedStatement.setString(3, teacher.getFullName());
            preparedStatement.setObject(4, teacher.getBirthDate());
            preparedStatement.setString(5, teacher.getPhoneNumber());
            preparedStatement.setObject(7, teacher.getCreateDate());
            preparedStatement.setObject(8, teacher.getUpdateDate());
            return preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public boolean update(Teacher teacher) {
        try (Connection connection = connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SqlCommands.Teacher.UPDATE_SET);
            preparedStatement.setString(1, teacher.getName());
            preparedStatement.setString(2, teacher.getSurname());
            preparedStatement.setString(3, teacher.getFullName());
            preparedStatement.setObject(4, teacher.getBirthDate());
            preparedStatement.setString(5, teacher.getPhoneNumber());
            preparedStatement.setObject(7, teacher.getUpdateDate());
            preparedStatement.setLong(8, teacher.getId());
            return preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public boolean deleteById(long id) {
        try (Connection connection = connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SqlCommands.Teacher.DELETE_BY_ID);
            preparedStatement.setLong(1, id);
            return preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Teacher getOne(long id) {
        try (Connection connection = connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SqlCommands.Teacher.GET_BY_ID);
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            resultSet.next();
            Teacher teacher = new Teacher();
            teacher.setId(resultSet.getLong("id"));
            teacher.setName(resultSet.getString("name"));
            teacher.setSurname(resultSet.getString("surname"));
            teacher.setFullName();
            teacher.setBirthDate(resultSet.getDate("birthdate"));
            teacher.setPhoneNumber(resultSet.getString("phone_number"));
            teacher.setCreateDate(resultSet.getObject("create_date", LocalDateTime.class));
            teacher.setUpdateDate(resultSet.getObject("update_date", LocalDateTime.class));
            return teacher;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }
}

