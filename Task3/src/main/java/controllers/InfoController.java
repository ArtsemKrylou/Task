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
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/updateInfo")
public class InfoController extends HttpServlet {

    private SubjectService subjectService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<Subject> subjects = subjectService.getSubjects();
        Gson gson = new Gson();
        gson.toJson(subjects);
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        out.print(gson.toJson(subjects));
        out.flush();
    }

    @Override
    public void init() {
        subjectService = new SubjectService();
    }
}
