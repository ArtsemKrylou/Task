package controllers;

import com.google.gson.Gson;
import models.Subject;
import service.SubjectService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/subjects")
public class SubjectController extends HttpServlet {
    private SubjectService subjectService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Subject> subjects = subjectService.getSubjects();
        req.setAttribute("subjects", subjects);
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Gson gson = new Gson();
        Subject subject = gson.fromJson(req.getReader(), Subject.class);
        subjectService.createSubject(subject);
        resp.sendRedirect("subjects");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Gson gson = new Gson();
        Subject subject = gson.fromJson(req.getReader(), Subject.class);
        subjectService.updateSubject(subject);
        resp.sendRedirect("subjects");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        Gson gson = new Gson();
        Subject subject = gson.fromJson(req.getReader(), Subject.class);
        subjectService.deleteSubject(subject.getId());
        //resp.sendRedirect("subjects");
    }

    @Override
    public void init() {
        subjectService = new SubjectService();
    }
}
