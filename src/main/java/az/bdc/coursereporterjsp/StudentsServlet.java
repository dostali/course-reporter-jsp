package az.bdc.coursereporterjsp;

import java.io.*;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import az.bdc.coursereporterjsp.domain.Student;
import az.bdc.coursereporterjsp.service.StudentService;
import az.bdc.coursereporterjsp.service.impl.StudentServiceImpl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "studentServlet", value = "/")
public class StudentsServlet extends HttpServlet {
    StudentService studentService = new StudentServiceImpl();

    private static final long serialVersionUID = 1L;

    public void init() {}

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        System.out.println("ACTION: " + action);

        try {
            switch (action) {
                case "/new":
                    showNewForm(request, response);
                    break;
                case "/insert":
                    insertStudent(request, response);
                    break;
                case "/delete":
                    deleteStudent(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                    updateStudent(request, response);
                    break;
                default:
                    listStudents(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listStudents(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        List<Student> students = studentService.getAll();
        request.setAttribute("students", students);
        RequestDispatcher dispatcher = request.getRequestDispatcher("student-list.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("action", "insert");
        RequestDispatcher dispatcher = request.getRequestDispatcher("student-form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Student existingStudent = studentService.getOne(id);
        request.setAttribute("id", id);
        request.setAttribute("student", existingStudent);
        request.setAttribute("action", "update");
        RequestDispatcher dispatcher = request.getRequestDispatcher("student-form.jsp");
        dispatcher.forward(request, response);
    }

    private void insertStudent(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String phoneNumber = request.getParameter("phone_number");
        String birthDate = request.getParameter("birthdate");
        String pincode = request.getParameter("pincode");
        LocalDateTime dateNow = LocalDateTime.now();

        // pare string to date
        Date parsedBirthDate;
        try {
            SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
            parsedBirthDate = parser.parse(birthDate);
        } catch (Exception e) {
            parsedBirthDate = new Date("1111-11-11");
            e.printStackTrace();
        }

        Student newStudent = new Student();
        newStudent.setName(name);
        newStudent.setSurname(surname);
        newStudent.setFullName();
        newStudent.setPhoneNumber(phoneNumber);
        newStudent.setBirthDate(parsedBirthDate);
        newStudent.setPinCode(pincode);
        newStudent.setCreateDate(dateNow);
        newStudent.setUpdateDate(dateNow);
        studentService.add(newStudent);
        response.sendRedirect("/list");
    }

    private void updateStudent(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String phoneNumber = request.getParameter("phone_number");
        String birthDate = request.getParameter("birthdate");
        String pincode = request.getParameter("pincode");
        long id = Long.parseLong(request.getParameter("id"));
        Student currentStudent = studentService.getOne(id);
        LocalDateTime dateNow = LocalDateTime.now();

        // pare string to date
        Date parsedBirthDate;
        try {
            SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
            parsedBirthDate = parser.parse(birthDate);
        } catch (Exception e) {
            parsedBirthDate = new Date("1111-11-11");
            e.printStackTrace();
        }

        currentStudent.setName(name);
        currentStudent.setSurname(surname);
        currentStudent.setPhoneNumber(phoneNumber);
        currentStudent.setBirthDate(parsedBirthDate);
        currentStudent.setPinCode(pincode);
        currentStudent.setUpdateDate(dateNow);
        studentService.update(currentStudent);
        response.sendRedirect("/list");
    }

    private void deleteStudent(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        long id = Long.parseLong(request.getParameter("id"));
        studentService.deleteById(id);
        response.sendRedirect("list");
    }
}