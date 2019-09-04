package controllers;

import models.*;
import service.FacultyService;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@WebServlet(urlPatterns = "/registration")
public class RegistrationController extends HttpServlet {
    private UserService userService;
    private FacultyService facultyService;

    @Override
    public void init() {
        userService = new UserService();
        facultyService = new FacultyService();
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("registration.html").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String subjectName1 = req.getParameter("subject_name1");
        int subjectMark1 = Integer.parseInt(req.getParameter("subject_mark1"));
        String subjectName2 = req.getParameter("subject_name2");
        int subjectMark2 = Integer.parseInt(req.getParameter("subject_mark2"));
        String subjectName3 = req.getParameter("subject_name3");
        int subjectMark3 = Integer.parseInt(req.getParameter("subject_mark3"));
        int certificate = Integer.parseInt(req.getParameter("certificate"));
        String facultyName = req.getParameter("faculty");
        Entrant entrant = new Entrant();
        entrant.setName(username);
        User user = new User();
        List <Subject> subjects = new ArrayList<>();
        subjects.add(new Subject(subjectName1,subjectMark1));
        subjects.add(new Subject(subjectName2,subjectMark2));
        subjects.add(new Subject(subjectName3,subjectMark3));
        entrant.setSubjects(subjects);
        entrant.setCertificate(new Certificate(certificate));
        user.setUserName(username);
        user.setPassword(password);
        user.setEntrant(entrant);
        User newUser = userService.createUser(user);
        Faculty faculty = new Faculty(facultyName, Collections.singletonList(newUser.getEntrant()));
        facultyService.createFaculty(faculty);
        resp.sendRedirect("/login");
    }
}
