package az.bdc.coursereporterjsp;

import az.bdc.coursereporterjsp.domain.Teacher;
import az.bdc.coursereporterjsp.service.TeacherService;
import az.bdc.coursereporterjsp.service.impl.TeacherServiceImpl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;


@WebServlet(name = "teacherServlet", value = "/teachers/*")
public class TeacherServlet extends HttpServlet {
    TeacherService teacherService = new TeacherServiceImpl();

    private static final long serialVersionUID = 1L;

    public void init() {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        System.out.println("ACTION: " + action);

        try {
            switch (action) {
                case "teachers/new":
                    showNewForm(request, response);
                    break;
                case "teachers/insert":
                    insertTeacher(request, response);
                    break;
                case "teachers/delete":
                    deleteTeacher(request, response);
                    break;
                case "teachers/edit":
                    showEditForm(request, response);
                    break;
                case "teachers/update":
                    updateTeacher(request, response);
                    break;
                default:
                    listTeacher(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listTeacher(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        List<Teacher> teacher = teacherService.getAll();
        request.setAttribute("teacher", teacher);
        RequestDispatcher dispatcher = request.getRequestDispatcher("teacher-list.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("action", "insert");
        RequestDispatcher dispatcher = request.getRequestDispatcher("teacher-form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Teacher existingTeacher = teacherService.getOne(id);
        request.setAttribute("id", id);
        request.setAttribute("teacher", existingTeacher);
        request.setAttribute("action", "update");
        RequestDispatcher dispatcher = request.getRequestDispatcher("teacher-form.jsp");
        dispatcher.forward(request, response);
    }

    private void insertTeacher(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String phoneNumber = request.getParameter("phone_number");
        String birthDate = request.getParameter("birthdate");
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

        Teacher newTeacher = new Teacher();
        newTeacher.setName(name);
        newTeacher.setSurname(surname);
        newTeacher.setFullName();
        newTeacher.setPhoneNumber(phoneNumber);
        newTeacher.setBirthDate(parsedBirthDate);
        newTeacher.setCreateDate(dateNow);
        newTeacher.setUpdateDate(dateNow);
        teacherService.add(new Teacher());
        response.sendRedirect("/list");
    }

    private void updateTeacher(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String phoneNumber = request.getParameter("phone_number");
        String birthDate = request.getParameter("birthdate");
        long id = Long.parseLong(request.getParameter("id"));
        Teacher currentTeacher = teacherService.getOne(id);
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

        currentTeacher.setName(name);
        currentTeacher.setSurname(surname);
        currentTeacher.setPhoneNumber(phoneNumber);
        currentTeacher.setBirthDate(parsedBirthDate);
        currentTeacher.setUpdateDate(dateNow);
        teacherService.update(currentTeacher);
        response.sendRedirect("/list");
    }

    private void deleteTeacher(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        long id = Long.parseLong(request.getParameter("id"));
        teacherService.deleteById(id);
        response.sendRedirect("list");
    }
}
